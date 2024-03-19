export function UserCard({ user }: { user: User }) {
  return (
    <div className="rounded-lg border flex flex-col gap-y-3">
      <div className="font-bold text-sm border-b p-3">
        {user.firstName} {user.lastName} <span className="text-muted-foreground">#{user._id}</span>
      </div>
      <div className="px-3 pb-3 grid grid-cols-3 gap-y-5">
        <UserSection title="First Name" value={user.firstName} />
        <UserSection title="Last Name" value={user.lastName} />
        <UserSection title="Full Name" value={user.firstName + " " + user.lastName} />
        <UserSection title="Age" value={user.age} />
        <UserSection title="Email" value={user.email} />
        <UserSection title="Country of Origin" value={user.originCountry} />
        <UserSection title="Passport Number" value={user.passportNumber} />
        <UserSection title="Role" value={user.role} />
        <UserSection title="id" value={user._id} />
      </div>
    </div>
  );
}
function UserSection({
  title,
  value,
}: {
  title: string;
  value: string | number;
}) {
  return (
    <div className="flex flex-col">
      <div className="font-bold text-sm">{title}</div>
      <div className="text-muted-foreground text-sm">{value}</div>
    </div>
  );
}
