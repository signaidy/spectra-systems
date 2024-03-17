// Data
import { getHotelById } from "@/lib/data";
// Components
import { RoomCard } from "@/components/rooms/roomCard";

export async function HotelRooms({ id }: { id: string }) {
  const hotel = await getHotelById(id);

  return (
    <>
      <RoomCard type="juniorSuite" {...hotel.rooms.juniorSuite} />
      <RoomCard type="standardSuite" {...hotel.rooms.standardSuite} />
      <RoomCard type="doubleSuite" {...hotel.rooms.doubleSuite} />
      <RoomCard type="bigSuite" {...hotel.rooms.bigSuite} />
    </>
  );
}
