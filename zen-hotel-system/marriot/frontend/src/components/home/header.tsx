import Link from "next/link";
import { HeaderNav } from "@/components/home/headerNav";
import { SignInButton } from "@/components/home/signInButton";

export function Header() {
  return (
    <header className="border-b">
      <div className="container py-2 flex justify-between items-center">
        <Link href="/" className="flex items-center h-[60px]">
          <div className="text-4xl text-foreground font-satisfy">Marriot</div>
        </Link>
        <HeaderNav />
        <SignInButton />
      </div>
    </header>
  );
}
