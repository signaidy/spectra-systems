import { Suspense } from "react";
import { getCities } from "@/lib/data";
// Components
import { SectionTitle } from "@/components/user/sectionTitle";
import { Analytics } from "@/components/user/analytics/analytics";
import { AnalyticCardSkeleton } from "@/components/skeletons/analyticCardSkeleton";
import { FilterBar } from "@/components/user/analytics/filterBar";

export default async function AnalyticsHome({
  searchParams,
}: {
  searchParams: {
    source?: "web" | "rest";
    location?: string;
    checkin?: string;
    checkout?: string;
    madeAt?: string;
  };
}) {
  const cities = await getCities();

  return (
    <>
      <SectionTitle
        title="Analytics"
        description="View Useful Analytics and Statistics"
      />
      <Suspense
        fallback={
          <div className="flex flex-col gap-y-3 mb-8 mr-8">
            {Array(5)
              .fill(0)
              .map((_, index) => (
                <AnalyticCardSkeleton key={index} />
              ))}
          </div>
        }
      >
        <div className="flex flex-col gap-y-3 mb-8 flex-wrap mr-8">
          <FilterBar locations={cities}  />
          <Analytics searchParams={searchParams} />
        </div>
      </Suspense>
    </>
  );
}
