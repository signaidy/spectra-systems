import Image from "next/image";
import { HeaderNav } from "@/components/home/headerNav";
import { LogInButton } from "@/components/home/logInButton";

export function Header() {
  return (
    <header className="h-20 border-b">
      <div className="container flex justify-between items-center h-full">
        <Image
          src={`/zen-logo.png`}
          alt="Zen Hotels Logo"
          width="150"
          height="60"
          priority
        />
        <HeaderNav />
        <LogInButton />
      </div>
    </header>
  );
}
