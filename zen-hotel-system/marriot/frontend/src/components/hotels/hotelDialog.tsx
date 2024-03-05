import Image from "next/image";
// UI Components
import { Button } from "@/components/ui/button";
import {
  Dialog,
  DialogContent,
  DialogDescription,
  DialogFooter,
  DialogHeader,
  DialogTitle,
  DialogTrigger,
} from "@/components/ui/dialog";
import { Input } from "@/components/ui/input";
// Icons
import {
  ArrowRight,
  Star,
  MapPin,
  Wifi,
  Dumbbell,
  Dog,
  ParkingSquare,
  EggFried,
  Check,
  MessageSquare,
} from "lucide-react";

function Amenitie({
  title,
  icon,
}: {
  title: string;
  icon: React.ReactElement;
}) {
  return (
    <div className="flex items-center">
      {icon}
      <div>{title}</div>
    </div>
  );
}

export function HotelDialog() {
  return (
    <Dialog>
      <DialogTrigger asChild>
        <Button variant="link" className="w-fit p-0 mt-auto">
          View Hotel Details
          <ArrowRight className="ml-1 w-4 h-4" />
        </Button>
      </DialogTrigger>
      <DialogContent className="max-w-3xl max-h-[70vh] overflow-y-auto custom-scrollbar">
        {/* <DialogHeader>
          <DialogTitle>View Hotel</DialogTitle>
          <DialogDescription>
            Make changes to your profile here. 
          </DialogDescription>
        </DialogHeader> */}
        <div className="flex flex-col gap-y-1 ">
          <h1 className="text-xl font-bold">The Grand Budapest Hotel</h1>
          <div className="flex gap-x-1 text-sm items-center text-muted-foreground">
            <div>4</div>
            <Star className="w-4 h-4 text-black fill-yellow-500" />
            <div>(23 reviews)</div>
            <div>|</div>
            <MapPin className="w-4 h-4 text-black fill-red-700" />
            <div>New York</div>
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
            <div className="font-medium text-lg">Rooms</div>
            <div className="grid grid-cols-2 gap-y-5 gap-x-5">
              {Array(4)
                .fill(0)
                .map((_, i) => (
                  <div key={i} className="relative h-52">
                    <Image
                      src="/hotel-background.jpg"
                      fill
                      alt="Room Image"
                      className="object-cover rounded-lg border"
                    />
                    <div className="text-xs border font-bold absolute top-0 right-0 bg-background p-1 rounded-bl-lg rounded-tr-lg">
                      Deluxe Room
                    </div>
                  </div>
                ))}
            </div>
          </div>
          <div className="flex flex-col gap-y-2 mt-5">
            <div className="font-medium text-lg">Amenities</div>
            <p className="text-sm grid grid-cols-3 gap-y-5">
              <Amenitie
                title="Free Wi-Fi"
                icon={<Wifi className="h-4 w-4 mr-3" />}
              />
              <Amenitie
                title="Gym"
                icon={<Dumbbell className="h-4 w-4 mr-3" />}
              />
              <Amenitie
                title="Pet Friendly"
                icon={<Dog className="h-4 w-4 mr-3" />}
              />
              <Amenitie
                title="Parking"
                icon={<ParkingSquare className="h-4 w-4 mr-3" />}
              />
              <Amenitie
                title="Free Food"
                icon={<EggFried className="h-4 w-4 mr-3" />}
              />
              <Amenitie
                title="Pool"
                icon={<Check className="h-4 w-4 mr-3" />}
              />
            </p>
          </div>
          <div className="flex flex-col gap-y-2 mt-5">
            <div className="font-medium text-lg">Commentaries</div>
            <form className="flex gap-x-2 mb-2">
              <Input type="text" placeholder="Add a comment" />
              <Button>Comment</Button>
            </form>
            {/* Thread Container */}
            <div className="flex flex-col gap-y-8">
              {/* Single */}
              <div className="flex flex-col gap-y-1 text-sm border-l rounded-tl-2xl">
                <div className="flex gap-x-2 items-center">
                  <div className="w-7 h-7 rounded-full bg-gradient-to-r from-cyan-500 via-purple-500 to-blue-500" />
                  <div className="flex items-center gap-x-1">
                    <div className="font-bold">Messi</div>
                    <div className="text-foreground font-extralight">
                      • 03/03/2024
                    </div>
                  </div>
                </div>
                <div className="flex flex-col gap-y-px pl-9">
                  <p>Great hotel!</p>
                  <Button variant="ghost" className="w-fit p-0">
                    Reply <MessageSquare className="h-4 w-4 ml-1" />
                  </Button>
                </div>
                {/* Nested Single */}
                <div className="ml-9 flex flex-col gap-y-1 mt-2 text-sm border-l rounded-tl-2xl">
                  <div className="flex gap-x-2 items-center">
                    <div className="w-7 h-7 rounded-full bg-gradient-to-r from-cyan-500 via-purple-500 to-blue-500" />
                    <div className="flex items-center gap-x-1">
                      <div className="font-bold">Messi</div>
                      <div className="text-foreground font-extralight">
                        • 03/03/2024
                      </div>
                    </div>
                  </div>
                  <div className="flex flex-col gap-y-px pl-9">
                    <p>Great hotel!</p>
                    <Button variant="ghost" className="w-fit p-0">
                      Reply <MessageSquare className="h-4 w-4 ml-1" />
                    </Button>
                  </div>
                </div>
                <div className="ml-9 flex flex-col gap-y-1 mt-2 text-sm border-l rounded-tl-2xl">
                  <div className="flex gap-x-2 items-center">
                    <div className="w-7 h-7 rounded-full bg-gradient-to-r from-cyan-500 via-purple-500 to-blue-500" />
                    <div className="flex items-center gap-x-1">
                      <div className="font-bold">Messi</div>
                      <div className="text-foreground font-extralight">
                        • 03/03/2024
                      </div>
                    </div>
                  </div>
                  <div className="flex flex-col gap-y-px pl-9">
                    <p>Great hotel!</p>
                    <Button variant="ghost" className="w-fit p-0">
                      Reply <MessageSquare className="h-4 w-4 ml-1" />
                    </Button>
                  </div>
                </div>
                {/* End Nested Single */}
              </div>
              {/* End Single */}
            </div>
          </div>
        </div>
        <DialogFooter>
          <Button type="submit">View Rates</Button>
        </DialogFooter>
      </DialogContent>
    </Dialog>
  );
}
