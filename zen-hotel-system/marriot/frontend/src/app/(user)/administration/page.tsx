import { Suspense } from "react";
// Components
import { Hotels } from "@/components/user/administration/hotels";
import { HotelCardSkeleton } from "@/components/skeletons/hotelCardSkeleton";
import { CreateHotel } from "@/components/user/administration/actionButtons";
import { Input } from "@/components/ui/input";
import { InformativeTooltip } from "@/components/ui/informativeTooltip";

export default function AdministrationHome() {
  return (
    <div className="flex flex-col gap-y-3 py-8 pl-8 w-full">
      <div className="flex flex-col gap-y-3">
        <div className="flex gap-x-3">
          <h1 className="font-bold text-xl">Administration</h1>
          <InformativeTooltip description="Add, Update or Delete Hotel Information" />
        </div>
        <div className="flex gap-x-3">
          <Input placeholder="Search for a hotel" />
          <CreateHotel />
        </div>
      </div>
      <Suspense
        fallback={Array(2)
          .fill("")
          .map((_, index) => (
            <HotelCardSkeleton key={index} />
          ))}
      >
        <Hotels />
      </Suspense>
    </div>
  );
}
