export function AgencyCard({ agency }: { agency: Agency }) {
  return (
    <div className="flex flex-col gap-y-2 p-3 border rounded-lg">
      <div className="flex flex-col text-sm">
        <div className="font-bold">Name</div>
        <div>{agency.name}</div>
      </div>
      <div className="flex flex-col text-sm">
        <div className="font-bold">Endpoint</div>
        <div>{agency.endpoint}</div>
      </div>
    </div>
  );
}
