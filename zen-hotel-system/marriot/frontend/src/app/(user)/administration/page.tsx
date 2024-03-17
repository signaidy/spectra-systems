import Link from "next/link";
// Components
import { SectionTitle } from "@/components/user/sectionTitle";
// UI Components
import { Button } from "@/components/ui/button";

export default function AdministrationHome() {
  const administrationLinks = [
    {
      name: "Hotels",
      description: "Create, Modify and Configure Hotel Information",
      href: "/administration/hotels",
    },
    {
      name: "Site Identity",
      description: "Configure Page Identity Features",
      href: "/administration/site-identity",
    },
  ];

  return (
    <>
      <SectionTitle
        title="Administration"
        description="Access and Modify Page Features"
      />
      <div className="grid grid-cols-2 gap-x-5">
        {administrationLinks.map((link) => (
          <AdministrationLink key={link.name} {...link} />
        ))}
      </div>
    </>
  );
}

export function AdministrationLink({
  name,
  description,
  href,
}: {
  name: string;
  description: string;
  href: string;
}) {
  return (
    <Link
      href={href}
      className="flex flex-col bg-secondary p-3 rounded-lg hover:bg-secondary/80 transition-colors"
    >
      <div className="font-bold">{name}</div>
      <div className="text-sm">{description}</div>
    </Link>
  );
}
