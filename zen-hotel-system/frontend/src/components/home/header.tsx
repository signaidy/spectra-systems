import Image from "next/image";
import { HeaderNav } from "@/components/home/headerNav";
import { SignInButton } from "@/components/home/signInButton";

export function Header() {
  return (
    <header className="h-20 border-b shadow">
      <div className="container flex justify-between items-center h-full">
        <Image
          src={`/zen-logo.png`}
          alt="Zen Hotels Logo"
          width="150"
          height="60"
          priority
        />
        <HeaderNav />
        <SignInButton />
      </div>
    </header>
  );
}
