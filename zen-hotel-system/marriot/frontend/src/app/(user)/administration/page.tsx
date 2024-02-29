import { Suspense } from "react";
// Components
import { Hotels } from "@/components/user/administration/hotels";
import { HotelCardSkeleton } from "@/components/skeletons/hotelCardSkeleton";
import { CreateHotel } from "@/components/user/administration/actionButtons";
import { SectionTitle } from "@/components/user/sectionTitle";
// UI Components
import { Input } from "@/components/ui/input";

export default function AdministrationHome() {
  return (
    <>
      <div className="flex flex-col gap-y-3">
        <SectionTitle
          title="Administration"
          description="Add, Update or Delete Hotel Information"
        />
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
    </>
  );
}
