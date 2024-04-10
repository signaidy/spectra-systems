// Data
import { getAnalytics } from "@/lib/data";
// Components
import { AnalyticCard } from "@/components/user/analytics/analyticCard";

export async function Analytics() {
  const analytics = await getAnalytics();

  return (
    <>
      {analytics.length === 0 && (
        <div className="flex justify-center items-center h-full">
          <h1 className="text-xl font-bold">No Analytics Found</h1>
        </div>
      )}
      {analytics.map((analytic) => (
        <AnalyticCard key={analytic._id} analytic={analytic} />
      ))}
    </>
  );
}
