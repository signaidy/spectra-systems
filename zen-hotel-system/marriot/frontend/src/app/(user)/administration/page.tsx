// Components
import { SectionTitle } from "@/components/user/sectionTitle";
// UI Components
import { Button } from "@/components/ui/button";

export default function AdministrationHome() {
  const administrationLinks = [
    { name: "Hotels", href: "/admin/hotels" },
    { name: "Rooms", href: "/admin/rooms" },
    { name: "Site Identity", href: "/admin/site-identity" },
  ];

  return (
    <>
      <SectionTitle
        title="Administration"
        description="Access and Modify Page Features"
      />
    </>
  );
}
