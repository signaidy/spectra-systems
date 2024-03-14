import Image from "next/image";
// Components
import { RoomDialog } from "@/components/rooms/roomDialog";
// UI Components
import { Button } from "@/components/ui/button";
import { BedDouble, UserRound } from "lucide-react";

export function RoomCard({
  name,
  description,
  capacity,
  bedCount,
}: {
  name: string;
  description: string;
  capacity: number;
  bedCount: number;
}) {
  return (
    <article className="border rounded-md grid-cols-[0.75fr_1fr] grid h-72">
      <div className="relative">
        <Image
          src="/hotel-background.jpg"
          alt={`${name} Hotel Image`}
          fill
          className="object-cover rounded-l-md"
        />
      </div>
      <div className="flex flex-col justify-between rounded-r-md bg-background">
        <div className="flex flex-col gap-y-1 p-4 h-full">
          <div className="font-medium tracking-tight text-xl">{name}</div>
          <div className="flex gap-x-1 text-sm items-center text-muted-foreground">
            <BedDouble className="w-4 h-4 text-black fill-yellow-500" />
            <div>{bedCount} beds</div>
            <div>|</div>
            <UserRound className="w-4 h-4 text-black fill-red-700" />
            <div>{capacity} maximum occupancy</div>
          </div>
          <p className="text-sm line-clamp-3 mt-4">{description}</p>
          <RoomDialog />
        </div>
        <div className="border-t p-3 flex justify-end gap-x-3">
          <div className="flex gap-x-1 items-end">
            <div className="font-bold text-xl">255</div>
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
