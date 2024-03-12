import { getHotels } from "@/lib/data";
import { HotelCard } from "@/components/hotels/hotelCard";

export async function Hotels() {
  const hotels = await getHotels();

  return (
    <>
      {hotels.length === 0 && (
        <div className="flex justify-center items-center h-full">
          <h1 className="text-xl font-bold">No Hotels Found</h1>
        </div>
      )}
      {hotels.map((hotel: Hotel) => (
        <HotelCard key={hotel._id} {...hotel} />
      ))}
    </>
  );
}
