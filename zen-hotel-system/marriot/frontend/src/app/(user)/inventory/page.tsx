import { Suspense } from "react";
// Components
import { Hotels } from "@/components/user/inventory/hotels";
import { HotelCardSkeleton } from "@/components/skeletons/hotelCardSkeleton";
import { SectionTitle } from "@/components/user/sectionTitle";
// UI Components
import { Input } from "@/components/ui/input";

export default function InventoryHome() {
  return (
    <>
      <div className="flex flex-col gap-y-3">
        <SectionTitle
          title="Inventory"
          description="View and Search for Available Hotels"
        />
        <Input placeholder="Search for a hotel" />
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
