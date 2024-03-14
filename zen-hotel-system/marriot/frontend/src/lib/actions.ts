"use server";
import { redirect } from "next/navigation";
import { revalidateTag } from "next/cache";
import { revalidatePath } from "next/cache";
import { format } from "date-fns";

import { MongoClient, ObjectId } from "mongodb";

const client = new MongoClient(process.env.MONGODB_URI!);

export async function createHotel(prevState: any, formData: FormData) {
  try {
    const rawFormData = Object.fromEntries(formData.entries());

    if (!rawFormData.city) {
      return {
        error: "Please select a location.",
      };
    }

    const database = client.db("marriot-db");
    const hotels = database.collection("hotels");

    const hotel = {
      name: rawFormData.name,
      description: rawFormData.description,
      amenities: JSON.parse(rawFormData.amenities as string),
      picture: rawFormData.picture,
      location: {
        street: rawFormData.street,
        city: rawFormData.city,
        state: rawFormData.state,
        country: rawFormData.country,
        address: rawFormData.address,
      },
      reviews: {
        count: 0,
        average: 0,
      },
      rooms: {
        juniorSuite: {
          picture: rawFormData.juniorSuitePicture,
          price: Number(rawFormData.juniorSuitePrice),
          description: rawFormData.juniorSuiteDescription,
          maxOccupancy: Number(rawFormData.juniorSuiteMaxOccupancy),
          beds: {
            amount: Number(rawFormData.juniorSuiteBedsAmount),
            size: rawFormData.juniorSuiteBedsSize,
          },
          roomSize: rawFormData.juniorSuiteRoomSize,
          totalRooms: Number(rawFormData.juniorSuiteTotalRooms),
          reservedRooms: 0,
        },
        standardSuite: {
          picture: rawFormData.standardSuitePicture,
          price: Number(rawFormData.standardSuitePrice),
          description: rawFormData.standardSuiteDescription,
          maxOccupancy: Number(rawFormData.standardSuiteMaxOccupancy),
          beds: {
            amount: Number(rawFormData.standardSuiteBedsAmount),
            size: rawFormData.standardSuiteBedsSize,
          },
          roomSize: rawFormData.standardSuiteRoomSize,
          totalRooms: Number(rawFormData.standardSuiteTotalRooms),
          reservedRooms: 0,
        },
        doubleSuite: {
          picture: rawFormData.doubleSuitePicture,
          price: Number(rawFormData.doubleSuitePrice),
          description: rawFormData.doubleSuiteDescription,
          maxOccupancy: Number(rawFormData.doubleSuiteMaxOccupancy),
          beds: {
            amount: Number(rawFormData.doubleSuiteBedsAmount),
            size: rawFormData.doubleSuiteBedsSize,
          },
          roomSize: rawFormData.doubleSuiteRoomSize,
          totalRooms: Number(rawFormData.doubleSuiteTotalRooms),
          reservedRooms: 0,
        },
        bigSuite: {
          picture: rawFormData.bigSuitePicture,
          price: Number(rawFormData.bigSuitePrice),
          description: rawFormData.bigSuiteDescription,
          maxOccupancy: Number(rawFormData.bigSuiteMaxOccupancy),
          beds: {
            amount: Number(rawFormData.bigSuiteBedsAmount),
            size: rawFormData.bigSuiteBedsSize,
          },
          roomSize: rawFormData.bigSuiteRoomSize,
          totalRooms: Number(rawFormData.bigSuiteTotalRooms),
          reservedRooms: 0,
        },
      },
      commentaries: [],
    };

    await hotels.insertOne(hotel);
  } catch (e) {
    console.log(e);
    return {
      error: "Database Error: Failed to Create Hotel.",
    };
  }

  redirect("/administration/hotels");
}

export async function createCommentary(prevState: any, formData: FormData) {
  try {
    const rawFormData = Object.fromEntries(formData.entries());

    if (!rawFormData.message) {
      return {
        error: "Please enter a message.",
      };
    }

    const commentary = {
      _id: Date.now().toString(),
      parentId: rawFormData.parentId || "",
      userId: rawFormData.userId || "",
      userName: rawFormData.userName || "Anonymous",
      date: format(new Date(), "MM/dd/yyyy HH:mm"),
      message: rawFormData.message,
    };

    const database = client.db("marriot-db");
    const hotels = database.collection("hotels");

    await hotels.updateOne(
      { _id: new ObjectId(rawFormData.hotelId as string) },
      { $push: { commentaries: commentary } }
    );
  } catch (e) {
    console.log(e);
    return {
      error: "Database Error: Failed to Create Commentary.",
    };
  }

  revalidatePath("/");
}
