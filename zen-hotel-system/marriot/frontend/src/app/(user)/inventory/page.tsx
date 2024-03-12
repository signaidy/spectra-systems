import { Suspense } from "react";
// Components
import { Hotels } from "@/components/user/inventory/hotels";
import { HotelCardSkeleton } from "@/components/skeletons/hotelCardSkeleton";
import { SectionTitle } from "@/components/user/sectionTitle";
// UI Components
import { InventorySearchBar } from "@/components/searchBar/inventorySearchBar";

export default function InventoryHome() {
  return (
    <>
      <SectionTitle
        title="Inventory"
        description="View and Search for Available Hotels"
      />
      <InventorySearchBar />
      <Suspense
        fallback={
          <div className="flex flex-col gap-y-3 pt-5 mb-8 mr-8">
            {Array(2)
              .fill(0)
              .map((_, index) => (
                <HotelCardSkeleton key={index} />
              ))}
          </div>
        }
      >
        <div className="flex flex-col gap-y-3 pt-5 mb-8 mr-8">
          <Hotels />
        </div>
      </Suspense>
    </>
  );
}
