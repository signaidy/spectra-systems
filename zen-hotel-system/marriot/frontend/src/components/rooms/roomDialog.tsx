import Image from "next/image";
// UI Components
import { Button } from "@/components/ui/button";
import {
  Dialog,
  DialogContent,
  DialogFooter,
  DialogTrigger,
} from "@/components/ui/dialog";
import { Input } from "@/components/ui/input";
// Icons
import {
  ArrowRight,
  BedDouble,
  UserRound,
  Bed,
  Lamp
} from "lucide-react";

export function RoomDialog() {
  return (
    <Dialog>
      <DialogTrigger asChild>
        <Button variant="link" className="w-fit p-0 mt-auto">
          View Room Details
          <ArrowRight className="ml-1 w-4 h-4" />
        </Button>
      </DialogTrigger>
      <DialogContent className="max-w-3xl max-h-[70vh] overflow-y-auto custom-scrollbar">
        <div className="flex flex-col gap-y-1 ">
          <h1 className="text-xl font-bold">Double Room</h1>
          <div className="flex gap-x-1 text-sm items-center text-muted-foreground">
            <BedDouble className="w-4 h-4 text-black fill-yellow-500" />
            <div>5 beds</div>
            <div>|</div>
            <UserRound className="w-4 h-4 text-black fill-red-700" />
            <div>5 maximum occupancy</div>
          </div>
          <div className="flex flex-col gap-y-2 mt-5">
            <div className="font-medium text-lg">Overview</div>
            <p className="text-sm">
              Lorem ipsum dolor, sit amet consectetur adipisicing elit. Optio
              quibusdam tempore veritatis blanditiis quos, eos saepe nemo!
              Quaerat distinctio cumque aperiam quo quos rem odio, pariatur
              libero voluptatibus, aut nam.
            </p>
          </div>
          <div className="flex flex-col gap-y-2 mt-5">
            <div className="font-medium text-lg">Room View</div>
            <div className="relative h-52">
              <Image
                src="/hotel-background.jpg"
                fill
                alt="Room Image"
                className="object-cover rounded-lg border"
              />
            </div>
          </div>
          <div className="flex flex-col gap-y-2 mt-5">
            <div className="flex items-center font-medium text-lg">Beds and Bedding <Bed className="h-4 w-4 ml-3" /></div>
            <div className="flex flex-col gap-y-5 text-sm">
              <div>• 4 maximum occupancy</div>
              <div>• 5 beds</div>
            </div>
          </div>
          <div className="flex flex-col gap-y-2 mt-5">
            <div className="flex items-center font-medium text-lg">Room Features <Lamp className="h-4 w-4 ml-3" /></div>
            <div className="flex flex-col gap-y-5 text-sm">
              <div>• 450sqft/41sqm</div>
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
