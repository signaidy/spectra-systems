import { Injectable } from "@nestjs/common";
import { MongoClient } from "mongodb";
import { ObjectId } from "mongodb";
import { format } from "date-fns";

@Injectable()
export class HotelService {
  client = new MongoClient(process.env.MONGODB_URI);
  db = this.client.db(process.env.DB_NAME);

  async getLocations() {
    try {
      const locationsCollection = this.db.collection("locations");

      const result = locationsCollection.find();

      const locations = [];
      for await (const location of result) {
        locations.push({ ...location, _id: location._id.toString() });
      }

      return locations;
    } catch (e) {
      return this.errorHandler("Failed to Retrieve Locations", e);
    }
  }

  async getCities() {
    try {
      const citiesCollection = this.db.collection("locations");

      const cities = await citiesCollection.distinct("city");

      return cities;
    } catch (e) {
      return this.errorHandler("Failed to Retrieve Cities", e);
    }
  }

  async getHotels() {
    try {
      const hotelsCollection = this.db.collection("hotels");

      const result = hotelsCollection.find();

      const hotels = [];
      for await (const hotel of result) {
        const commentaries = this.generateCommentaryTree(hotel.commentaries);

        hotels.push({ ...hotel, _id: hotel._id.toString(), commentaries });
      }

      return hotels;
    } catch (e) {
      return this.errorHandler("Failed to Retrieve Hotels", e);
    }
  }

  async getHotelById(id: string) {
    try {
      const hotelsCollection = this.db.collection("hotels");

      const result = await hotelsCollection.findOne({ _id: new ObjectId(id) });

      if (!result) {
        throw new Error("Hotel not found");
      }

      const commentaries = this.generateCommentaryTree(result.commentaries);

      const hotel = { ...result, commentaries: commentaries, _id: result._id.toString() };

      return hotel;
    } catch (e) {
      return this.errorHandler("Failed to Retrieve Hotel", e);
    }
  }

  async getFilteredHotels(city: string) {
    try {
      const hotelsCollection = this.db.collection("hotels");

      const result = hotelsCollection.find({
        "location.city": city,
      });

      const hotels = [];
      for await (const hotel of result) {
        const commentaries = this.generateCommentaryTree(hotel.commentaries);

        hotels.push({ ...hotel, _id: hotel._id.toString(), commentaries });
      }

      return hotels;
    } catch (e) {
      return this.errorHandler("Failed to Retrieve Hotels", e);
    }
  }

  async getUserReservations(id: string) {
    try {
      const reservationsCollection = this.db.collection("reservations");

      const result = reservationsCollection.aggregate([
        {
          $match: {
            userId: new ObjectId(id),
          },
        },
        {
          $lookup: {
            from: "hotels",
            localField: "hotelId",
            foreignField: "_id",
            as: "hotel",
          },
        },
        {
          $unwind: "$hotel",
        },
        {
          $lookup: {
            from: "users",
            localField: "userId",
            foreignField: "_id",
            as: "user",
          },
        },
        {
          $unwind: "$user",
        },
        {
          $addFields: {
            _id: { $toString: "$_id" },
            userId: { $toString: "$userId" },
            hotelId: { $toString: "$hotelId" },
            "hotel._id": { $toString: "$hotel._id" },
            "user._id": { $toString: "$user._id" },
          },
        },
      ]);

      const reservations = [];
      for await (const reservation of result) {
        const commentaries = this.generateCommentaryTree(
          reservation.hotel.commentaries
        );

        reservations.push({
          ...reservation,
          hotel: { ...reservation.hotel, commentaries: commentaries },
        });
      }

      return reservations as Reservation[];
    } catch (e) {
      return this.errorHandler("Failed to Retrieve Reservations", e);
    }
  }

  async getReservationById(id: string) {
    try {
      const hotelCollection = this.db.collection("hotels");
      const userCollection = this.db.collection("users");
      const reservationsCollection = this.db.collection("reservations");

      const reservationResult = await reservationsCollection.findOne({
        _id: new ObjectId(id),
      });

      if (!reservationResult) {
        throw new Error("Reservation not found");
      }

      const hotelResult = await hotelCollection.findOne({
        _id: reservationResult.hotelId,
      });

      if (!hotelResult) {
        throw new Error("Hotel not found");
      }

      const userResult = await userCollection.findOne({
        _id: reservationResult.userId,
      });

      if (!userResult) {
        throw new Error("User not found");
      }

      const commentaries = this.generateCommentaryTree(
        hotelResult.commentaries
      );

      const reservation = {
        ...reservationResult,
        _id: reservationResult._id.toString(),
        hotelId: reservationResult.hotelId.toString(),
        userId: reservationResult.userId.toString(),
        hotel: {
          ...hotelResult,
          commentaries,
          _id: hotelResult._id.toString(),
        },
        user: { ...userResult, _id: userResult._id.toString() },
      };

      return reservation as Reservation;
    } catch (e) {
      return this.errorHandler("Failed to Retrieve Reservation", e);
    }
  }

  async createReservation(body) {
    try {
      const reservations = this.db.collection("reservations");

      const reservation = {
        hotelId: new ObjectId(body.hotelId as string),
        userId: new ObjectId(body.userId as string),
        checkin: body.checkin,
        checkout: body.checkout,
        roomType: body.roomType,
        roomPrice: Number(body.roomPrice),
        guests: Number(body.guests),
        stayDays: Number(body.stayDays),
        totalPrice: Number(body.totalPrice),
        state: "active",
        madeAt: format(new Date(), "MM/dd/yyyy HH:mm"),
      };

      this.validateFields(reservation);

      const result = await reservations.insertOne(reservation);

      const newReservation = {
        _id: result.insertedId.toString(),
        ...reservation,
      };

      return newReservation;
    } catch (e) {
      return this.errorHandler("Failed to Make Reservation", e);
    }
  }

  generateCommentaryTree(
    commentaries: Commentary[],
    parentId: string | "" = ""
  ) {
    const result = [];

    for (const commentary of commentaries) {
      if (commentary.parentId === parentId) {
        const children = this.generateCommentaryTree(
          commentaries,
          commentary._id
        );
        if (children.length) {
          commentary.children = children;
        }
        result.push(commentary);
      }
    }

    return result;
  }

  errorHandler(standardMessage: string, e: Error) {
    console.log(e);
    if (e.name === "BSONError") {
      return { error: `${standardMessage}: Invalid ID` };
    }
    if (e.message.toLowerCase().includes("not found")) {
      return { error: `${standardMessage}: ${e.message}` };
    }
    if (e.message.toLowerCase().includes("is required")) {
      return { error: `${standardMessage}: ${e.message}` };
    }
    return { error: standardMessage };
  }

  validateFields(document) {
    for (const [key, value] of Object.entries(document)) {
      if (!value) {
        throw new Error(`Field '${key}' is required`);
      }
    }
  }
}
