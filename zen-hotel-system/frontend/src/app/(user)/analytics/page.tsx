import { Suspense } from "react";
// Components
import { SectionTitle } from "@/components/user/sectionTitle";
import { Analytics } from "@/components/user/analytics/analytics";
import { AnalyticCardSkeleton } from "@/components/skeletons/analyticCardSkeleton";

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
            {Array(5)
              .fill(0)
              .map((_, index) => (
                <AnalyticCardSkeleton key={index} />
              ))}
          </div>
        }
      >
        <div className="flex gap-y-3 gap-x-3 mb-8 flex-wrap mr-8">
          <Analytics />
        </div>
      </Suspense>
    </>
  );
}
