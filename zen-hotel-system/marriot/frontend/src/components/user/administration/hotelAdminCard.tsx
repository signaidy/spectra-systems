import Image from "next/image";
import { Button } from "@/components/ui/button";
import { DeleteHotel } from "@/components/user/administration/actionButtons";
import { Star, MapPin, ArrowRight } from "lucide-react";

export function HotelAdminCard({
  id,
  name,
  description,
  location,
  reviewCount,
  reviewAverage,
}: {
  id: string;
  name: string;
  description: string;
  location: string;
  reviewCount: number;
  reviewAverage: number;
}) {
  return (
    <article className="border rounded-md grid-cols-[0.75fr_1fr] grid">
      <div className="relative ">
        <Image
          src="/hotel-background.jpg"
          alt="Hotel Image"
          fill
          className="object-cover rounded-l-md"
        />
      </div>
      <div className="flex flex-col justify-between rounded-r-md">
        <div className="flex flex-col gap-y-1 p-4">
          <h2 className="font-medium tracking-tight text-2xl">{name}</h2>
          <div className="flex gap-x-2 text-sm items-center text-muted-foreground">
            <div>{reviewAverage}</div>
            <Star className="w-4 h-4 text-yellow-500" />
            <div>({reviewCount} reviews)</div>
            <div>|</div>
            <MapPin className="w-4 h-4 text-red-700" />
            <div>{location}</div>
            <div>{id}</div>
          </div>
          <div className="flex flex-col gap-y-3 mt-5">
            <p className="text-sm line-clamp-2">{description}</p>
            <Button variant="link" className="w-fit p-0">
              View Hotel Details
              <ArrowRight className="ml-1 w-4 h-4" />
            </Button>
            <div className="flex gap-x-3">
              {/* <Button variant="outline">Update</Button> */}
              <DeleteHotel id={id} />
            </div>
          </div>
        </div>
        <div className="border-t p-4 flex justify-end gap-x-3">
          <div className="flex gap-x-1 items-end">
            <div className="font-bold text-xl">255</div>
            <div className="text-sm mb-[2px] text-muted-foreground">
              USD / Night
            </div>
          </div>
          <Button>View Rates</Button>
        </div>
      </div>
    </article>
  );
}
