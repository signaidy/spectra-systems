import Image from "next/image";
// Components
import { HotelDialog } from "@/components/hotels/hotelDialog";
// UI Components
import { Button } from "@/components/ui/button";
import { Star, MapPin } from "lucide-react";

export function HotelCard({
  name,
  description,
  location,
  reviewCount,
  reviewAverage,
}: {
  name: string;
  description: string;
  location: string;
  reviewCount: number;
  reviewAverage: number;
}) {
  return (
    <article className="border rounded-md grid-cols-[0.75fr_1fr] grid h-72">
      <div className="relative">
        <Image
          src="/hotel-background.jpg"
          sizes="(min-width: 1536px) 535px, (min-width: 1280px) 425px, (min-width: 1024px) 230px"
          alt={`${name} Hotel Image`}
          fill
          className="object-cover rounded-l-md"
        />
      </div>
      <div className="flex flex-col justify-between rounded-r-md bg-background">
        <div className="flex flex-col gap-y-1 p-4 h-full">
          <div className="font-medium tracking-tight text-xl">{name}</div>
          <div className="flex gap-x-1 text-sm items-center text-muted-foreground">
            <div>{reviewAverage}</div>
            <Star className="w-4 h-4 text-black fill-yellow-500" />
            <div>({reviewCount} reviews)</div>
            <div>|</div>
            <MapPin className="w-4 h-4 text-black fill-red-700" />
            <div>{location}</div>
          </div>
          <p className="text-sm line-clamp-3 mt-4">{description}</p>
          <HotelDialog />
        </div>
        <div className="border-t p-3 flex justify-end gap-x-3">
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
