import { NavBar } from "@/components/user/navBar";

export default function UserLayout({
  children,
}: {
  children: React.ReactNode;
}) {
  return (
    <div className="flex container h-[calc(100vh-4.813rem)] overflow-y-auto custom-scrollbar">
      <NavBar />
      <div className="flex flex-col pt-8 pl-8 w-full overflow-x-auto custom-scrollbar">{children}</div>
    </div>
  );
}
