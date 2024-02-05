import { Suspense } from "react";
// Components
import { Hotels } from "@/components/user/inventory/hotels";
import { HotelCardSkeleton } from "@/components/skeletons/hotelCardSkeleton";
// UI Components
import { Input } from "@/components/ui/input";
import { InformativeTooltip } from "@/components/ui/informativeTooltip";

export default function InventoryHome() {
  return (
    <div className="flex flex-col gap-y-3 py-8 pl-8 w-full">
      <div className="flex flex-col gap-y-3">
        <div className="flex gap-x-3">
          <h1 className="font-bold text-xl">Inventory</h1>
          <InformativeTooltip description="View and Search for Available Hotels" />
        </div>
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
    </div>
  );
}
