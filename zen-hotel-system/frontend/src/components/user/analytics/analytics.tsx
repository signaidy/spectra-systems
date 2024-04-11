// Data
import { getAnalytics } from "@/lib/data";
// Components
import { AnalyticCard } from "@/components/user/analytics/analyticCard";

export async function Analytics({
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
  const analytics = await getAnalytics(searchParams);

  return (
    <>
      {analytics.length === 0 && (
        <div className="flex justify-center items-center h-full">
          <h1 className="text-xl font-bold">No Analytics Found</h1>
        </div>
      )}
      <div className="flex gap-y-3 flex-wrap justify-between">
        {analytics.map((analytic) => (
          <AnalyticCard key={analytic._id} analytic={analytic} />
        ))}
      </div>
    </>
  );
}
