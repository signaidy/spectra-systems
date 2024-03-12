const { MongoClient } = require("mongodb");

const client = new MongoClient(process.env.MONGODB_URI);

export async function getLocations() {
  try {
    const database = client.db("marriot-db");
    const locationsCollection = database.collection("locations");

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
    const hotelsCollection = database.collection("hotels");

    const result = hotelsCollection.find();

    const hotels = [];
    for await (const hotel of result) {
      hotels.push({ ...hotel, _id: hotel._id.toString() });
    }

    return hotels;
  } catch (e) {
    console.log(e);
    throw new Error("Failed to Retrieve Hotels");
  }
}
