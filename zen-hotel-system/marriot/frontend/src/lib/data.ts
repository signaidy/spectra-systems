const { MongoClient } = require("mongodb");

const client = new MongoClient(process.env.MONGODB_URI);

export async function getHotels() {
  try {
    const database = client.db("marriot-db");
    const hotelsCollection = database.collection("hotels");

    const hotels = hotelsCollection.find().toArray();
    
    return hotels;
  } catch (e) {
    console.log(e);
    throw new Error("Failed to Retrieve Hotels");
  }
}
