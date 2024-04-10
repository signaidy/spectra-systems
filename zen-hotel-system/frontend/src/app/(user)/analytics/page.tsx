import { Suspense } from "react";
// Components
import { SectionTitle } from "@/components/user/sectionTitle";
import { Analytics } from "@/components/user/analytics/analytics";

export default function AnalyticsHome() {
  return (
    <>
      <SectionTitle
        title="Analytics"
        description="View Useful Analytics and Statistics"
      />
      <Suspense
        fallback={
          <div className="flex flex-col gap-y-3 mb-8 mr-8">
            {Array(2)
              .fill(0)
              .map((_, index) => (
                // <ReservationCardSkeleton key={index} />
                <div key={index}></div>
              ))}
          </div>
        }
      >
        <div className="flex flex-col gap-y-3 mb-8 mr-8">
          <Analytics />
        </div>
      </Suspense>
    </>
  );
}
