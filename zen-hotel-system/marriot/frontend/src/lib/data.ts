import { MongoClient, ObjectId } from "mongodb";

const client = new MongoClient(process.env.MONGODB_URI!);

export async function getLocations() {
  try {
    const database = client.db("marriot-db");
    const locationsCollection = database.collection<HotelLocation>("locations");

    const result = locationsCollection.find();

    const locations = [];
    for await (const location of result) {
      locations.push({ ...location, _id: location._id.toString() });
    }

    return locations;
  } catch (e) {
    console.log(e);
    throw new Error("Failed to Retrieve Cities");
  }
}

export async function getHotels() {
  try {
    const database = client.db("marriot-db");
    const hotelsCollection = database.collection<Hotel>("hotels");

    const result = hotelsCollection.find();

    const hotels = [];
    for await (const hotel of result) {
      const commentaries = generateCommentaryTree(hotel.commentaries);

      hotels.push({ ...hotel, _id: hotel._id.toString(), commentaries });
    }

    return hotels;
  } catch (e) {
    console.log(e);
    throw new Error("Failed to Retrieve Hotels");
  }
}

export async function getHotelById(id: string) {
  try {
    const database = client.db("marriot-db");
    const hotelsCollection = database.collection("hotels");

    const result = await hotelsCollection.findOne({ _id: new ObjectId(id) });

    if (!result) {
      throw new Error("Hotel not found");
    }

    const hotel = { ...result, _id: result._id.toString() };

    return hotel as Hotel;
  } catch (e) {
    console.log(e);
    throw new Error("Failed to Retrieve Hotel");
  }
}

export async function getCities() {
  try {
    const database = client.db("marriot-db");
    const citiesCollection = database.collection("locations");

    const cities = await citiesCollection.distinct("city");

    return cities;
  } catch (e) {
    console.log(e);
    throw new Error("Failed to Retrieve Cities");
  }
}

export async function getFilteredHotels(searchParams: HotelSearchParams) {
  try {
    const database = client.db("marriot-db");
    const hotelsCollection = database.collection<Hotel>("hotels");

    const result = hotelsCollection.find({
      "location.city": searchParams.location,
      // guests: { $gte: searchParams.guests },
    });

    const hotels = [];
    for await (const hotel of result) {
      const commentaries = generateCommentaryTree(hotel.commentaries);

      hotels.push({ ...hotel, _id: hotel._id.toString(), commentaries });
    }

    return hotels;
  } catch (e) {
    console.log(e);
    throw new Error("Failed to Retrieve Hotels");
  }
}

const generateCommentaryTree = function (
  commentaries: Commentary[],
  parentId: string | "" = ""
) {
  const result = [];

  for (const commentary of commentaries) {
    if (commentary.parentId === parentId) {
      const children = generateCommentaryTree(commentaries, commentary._id);
      if (children.length) {
        commentary.children = children;
      }
      result.push(commentary);
    }
  }

  return result;
};