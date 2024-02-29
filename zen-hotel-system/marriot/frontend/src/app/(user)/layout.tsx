import { NavBar } from "@/components/user/navBar";

export default function UserLayout({
  children,
}: {
  children: React.ReactNode;
}) {
  return (
    <div className="flex container h-[calc(100vh-4.813rem)] overflow-y-auto">
      <NavBar />
      <div className="flex flex-col py-8 pl-8 w-full">{children}</div>
    </div>
  );
}
