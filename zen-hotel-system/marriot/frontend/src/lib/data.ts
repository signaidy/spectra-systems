import type { Hotel } from "@/lib/interfaces";

export async function getHotels(): Promise<Hotel[]> {
  try {
    const response = await fetch(
      `${process.env.NEXT_PUBLIC_API_URL}/get-hotels`,
      { next: { tags: ["hotels"] } }
    );
    const data = await response.json();
    return data;
  } catch (e) {
    throw new Error("Failed to Retrieve Hotels");
  }
}
