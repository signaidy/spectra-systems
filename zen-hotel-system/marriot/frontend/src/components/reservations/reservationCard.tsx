// Components
import { HotelInventoryCard } from "@/components/hotels/hotelInventoryCard";
// UI Components
import { Button } from "@/components/ui/button";

export function ReservationCard({ reservation }: { reservation: Reservation }) {
  return (
    <div className="rounded-lg border">
      <div className="flex p-3 gap-x-5">
        <div className="flex flex-col text-sm">
          <div className="font-bold">{reservation.hotel.name}</div>
          <div className="text-muted-foreground">{reservation._id}</div>
        </div>
        <Button>Generate PDF</Button>
      </div>
      <div className="grid grid-cols-3 p-3 gap-y-5">
        <ReservationSection
          title="Room Purchased"
          value={
            reservation.roomType === "juniorSuite"
              ? "Junior Suite"
              : reservation.roomType === "standardSuite"
              ? "Standard Suite"
              : reservation.roomType === "doubleSuite"
              ? "Double Suite"
              : "Big Suite"
          }
        />
        <ReservationSection
          title="Room Price"
          value={`${reservation.roomPrice} USD`}
        />
        <ReservationSection title="Guests" value={reservation.guests} />
        <ReservationSection title="Stay-In Days" value={reservation.stayDays} />
        <ReservationSection title="Check In" value={reservation.checkin} />
        <ReservationSection title="Check Out" value={reservation.checkout} />
        <ReservationSection
          title="Total Price"
          value={`${reservation.totalPrice} USD`}
        />
        <ReservationSection title="Made At" value={reservation.madeAt} />
        <ReservationSection
          title="Buyer"
          value={reservation.user.firstName + " " + reservation.user.lastName}
        />
      </div>
      <HotelInventoryCard hotel={reservation.hotel} searchParams={{}} />
    </div>
  );
}

function ReservationSection({
  title,
  value,
}: {
  title: string;
  value: string | number;
}) {
  return (
    <div className="flex flex-col text-sm">
      <div className="font-bold">{title}</div>
      <div>{value}</div>
    </div>
  );
}
