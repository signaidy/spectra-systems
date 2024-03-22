import { Injectable } from "@nestjs/common";
import { MongoClient } from "mongodb";
import { ObjectId } from "mongodb";

const uri =
  "mongodb+srv://admin:uYCpziHTosl4NIxy@marriot-db.0ha5ya6.mongodb.net/?retryWrites=true&w=majority";

const client = new MongoClient(uri);
const db = client.db("marriot-db");

@Injectable()
export class MarriotService {
  async createHotel() {
    const result = await db.collection("hotels").insertOne({
      name: "Los Angeles Cecil Hotel Inn",
      description:
        "Family-friendly hotel with complimentary Wi-Fi, free breakfast and premier location in Midtown.", // Count and Average: Duped data pattern
      location: {
        street: "640 Main St",
        city: "Los Angeles",
        state: "CA",
        country: "USA",
      },
      reviews: {
        count: 0,
        average: 0,
      },
      commentaries: [],
    });
    return result;
  }

  async deleteHotel(id: string) {
    const result = await db.collection("hotels").deleteOne({
      _id: new ObjectId(id),
    });
    return result;
  }

  async findAll() {
    const result = await db.collection("hotels").find({}).toArray();
    return result;
  }
}
