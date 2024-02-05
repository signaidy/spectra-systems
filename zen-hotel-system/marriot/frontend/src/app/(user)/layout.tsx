import { Nav } from "@/components/user/nav";

export default function UserLayout({
  children,
}: {
  children: React.ReactNode;
}) {
  return (
    <div className="relative flex container h-[calc(100vh-5rem)] overflow-y-auto">
      <Nav />
      {children}
    </div>
  );
}
