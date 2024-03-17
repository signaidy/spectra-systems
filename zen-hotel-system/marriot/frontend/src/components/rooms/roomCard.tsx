import Image from "next/image";
// Components
import { RoomDialog } from "@/components/rooms/roomDialog";
// UI Components
import { Button } from "@/components/ui/button";
import { BedDouble, UserRound } from "lucide-react";

export function RoomCard(props: {
  type: string;
  picture: string;
  price: number;
  description: string;
  maxOccupancy: number;
  beds: { amount: number; size: string };
  roomSize: string;
  totalRooms: number;
  reservedRooms: number;
}) {
  return (
    <article className="border rounded-md grid-cols-[0.75fr_1fr] grid h-72">
      <div className="relative">
        <Image
          src={props.picture}
          alt={`${props.type} Image`}
          fill
          className="object-cover rounded-l-md"
        />
      </div>
      <div className="flex flex-col justify-between rounded-r-md bg-background">
        <div className="flex flex-col gap-y-1 p-4 h-full">
          <div className="font-medium tracking-tight text-xl">
            {props.type === "juniorSuite"
              ? "Junior Suite"
              : props.type === "standardSuite"
              ? "Standard Suite"
              : props.type === "doubleSuite"
              ? "Double Suite"
              : "Big Suite"}
          </div>
          <div className="flex gap-x-1 text-sm items-center text-muted-foreground">
            <BedDouble className="w-4 h-4 text-black fill-yellow-500" />
            <div>
              {props.beds.amount} {props.beds.size} Size beds
            </div>
            <div>|</div>
            <UserRound className="w-4 h-4 text-black fill-red-700" />
            <div>{props.maxOccupancy} maximum occupancy</div>
          </div>
          <p className="text-sm line-clamp-3 mt-4">{props.description}</p>
          <RoomDialog {...props} />
        </div>
        <div className="border-t p-3 flex justify-end gap-x-3">
          <div className="flex gap-x-1 items-end">
            <div className="font-bold text-xl">{props.price}</div>
            <div className="text-sm mb-[2px] text-muted-foreground">
              USD / Night
            </div>
          </div>
          <Button>Continue</Button>
        </div>
      </div>
    </article>
  );
}
