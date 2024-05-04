// Components
// import { AgencyDeletionForm } from "@/components/agencies/agencyDeletionForm";

export function AllianceCard({ alliance }: { alliance: Alliance }) {
  return (
    <div className="flex flex-col gap-y-2 p-3 border rounded-lg">
      <div className="flex justify-between gap-x-4">
        <div className="flex flex-col text-sm">
          <div className="font-bold">Name</div>
          <div>{alliance.name}</div>
        </div>
        {/* <AgencyDeletionForm id={agency._id} /> */}
      </div>
      <div className="flex flex-col text-sm">
        <div className="font-bold">Endpoint</div>
        <div>{alliance.key}</div>
      </div>
    </div>
  );
}
