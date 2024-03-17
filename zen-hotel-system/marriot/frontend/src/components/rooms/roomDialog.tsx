import Image from "next/image";
// UI Components
import { Button } from "@/components/ui/button";
import {
  Dialog,
  DialogContent,
  DialogFooter,
  DialogTrigger,
} from "@/components/ui/dialog";
// Icons
import { ArrowRight, BedDouble, UserRound, Bed, Lamp } from "lucide-react";

export function RoomDialog(props: {
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
    <Dialog>
      <DialogTrigger asChild>
        <Button variant="link" className="w-fit p-0 mt-auto">
          View Room Details
          <ArrowRight className="ml-1 w-4 h-4" />
        </Button>
      </DialogTrigger>
      <DialogContent className="max-w-3xl max-h-[75vh] overflow-y-auto custom-scrollbar">
        <div className="flex flex-col gap-y-1 ">
          <h1 className="text-xl font-bold">
            {props.type === "juniorSuite"
              ? "Junior Suite"
              : props.type === "standardSuite"
              ? "Standard Suite"
              : props.type === "doubleSuite"
              ? "Double Suite"
              : "Big Suite"}
          </h1>
          <div className="flex gap-x-1 text-sm items-center text-muted-foreground">
            <BedDouble className="w-4 h-4 text-black fill-yellow-500" />
            <div>
              {props.beds.amount} {props.beds.size} Size beds
            </div>
            <div>|</div>
            <UserRound className="w-4 h-4 text-black fill-red-700" />
            <div>{props.maxOccupancy} maximum occupancy</div>
          </div>
          <div className="flex flex-col gap-y-2 mt-5">
            <div className="font-medium text-lg">Overview</div>
            <p className="text-sm">{props.description}</p>
          </div>
          <div className="flex flex-col gap-y-2 mt-5">
            <div className="font-medium text-lg">Room View</div>
            <div className="relative h-52">
              <Image
                src={props.picture}
                fill
                alt={`${props.type} Image`}
                className="object-cover rounded-lg border"
              />
            </div>
          </div>
          <div className="flex flex-col gap-y-2 mt-5">
            <div className="flex items-center font-medium text-lg">
              Beds and Bedding <Bed className="h-4 w-4 ml-3" />
            </div>
            <div className="flex flex-col gap-y-5 text-sm">
              <div>• {props.maxOccupancy} maximum occupancy</div>
              <div>
                • {props.beds.amount} {props.beds.size} Size beds
              </div>
            </div>
          </div>
          <div className="flex flex-col gap-y-2 mt-5">
            <div className="flex items-center font-medium text-lg">
              Room Features <Lamp className="h-4 w-4 ml-3" />
            </div>
            <div className="flex flex-col gap-y-5 text-sm">
              <div>• {props.roomSize}</div>
            </div>
          </div>
        </div>
        <DialogFooter>
          <Button type="submit">Continue</Button>
        </DialogFooter>
      </DialogContent>
    </Dialog>
  );
}
