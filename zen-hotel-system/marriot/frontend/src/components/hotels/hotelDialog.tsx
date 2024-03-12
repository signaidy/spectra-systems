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
import { ArrowRight, Star, MapPin, Check, MessageSquare } from "lucide-react";

export function HotelDialog(props: Hotel) {
  return (
    <Dialog>
      <DialogTrigger asChild>
        <Button variant="link" className="w-fit p-0 mt-auto">
          View Hotel Details
          <ArrowRight className="ml-1 w-4 h-4" />
        </Button>
      </DialogTrigger>
      <DialogContent className="max-w-3xl max-h-[70vh] overflow-y-auto custom-scrollbar">
        <div className="flex flex-col gap-y-1 ">
          <h1 className="text-xl font-bold">{props.name}</h1>
          <div className="flex gap-x-1 text-sm items-center text-muted-foreground">
            <div>{props.reviews.average}</div>
            <Star className="w-4 h-4 text-black fill-yellow-500" />
            <div>({props.reviews.count} reviews)</div>
            <div>|</div>
            <MapPin className="w-4 h-4 text-black fill-red-700" />
            <div>{props.location.address}</div>
          </div>
          <div className="flex flex-col gap-y-2 mt-5">
            <div className="font-medium text-lg">Overview</div>
            <p className="text-sm">{props.description}</p>
          </div>
          <div className="flex flex-col gap-y-2 mt-5">
            <div className="font-medium text-lg">Rooms</div>
            <div className="grid grid-cols-2 gap-y-5 gap-x-5">
              {Array(4)
                .fill(0)
                .map((_, i) => (
                  <div key={i} className="relative h-52">
                    <Image
                      src={
                        i === 0
                          ? props.rooms.juniorSuite.picture
                          : i === 1
                          ? props.rooms.standardSuite.picture
                          : i === 2
                          ? props.rooms.doubleSuite.picture
                          : props.rooms.bigSuite.picture
                      }
                      fill
                      alt="Room Image"
                      className="object-cover rounded-lg border"
                    />
                    <div className="text-xs border font-bold absolute top-0 right-0 bg-background p-1 rounded-bl-lg rounded-tr-lg">
                      {i === 0
                        ? "Junior"
                        : i === 1
                        ? "Standard"
                        : i === 2
                        ? "Double"
                        : "Big"}
                    </div>
                  </div>
                ))}
            </div>
          </div>
          <div className="flex flex-col gap-y-2 mt-5">
            <div className="font-medium text-lg">Amenities</div>
            <p className="text-sm grid grid-cols-3 gap-y-5">
              {props.amenities.map((amenity, index) => (
                <Amenity
                  key={index}
                  title={amenity}
                  icon={<Check className="w-4 h-4 mr-3" />}
                />
              ))}
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

function Amenity({ title, icon }: { title: string; icon: React.ReactElement }) {
  return (
    <div className="flex items-center">
      {icon}
      <div>{title}</div>
    </div>
  );
}
