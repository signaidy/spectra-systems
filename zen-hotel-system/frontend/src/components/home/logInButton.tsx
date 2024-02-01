import { Button } from "@/components/ui/button";
import { LuUserCircle2 } from "react-icons/lu";
import Link from "next/link";

export function LogInButton() {
  return (
    <Button asChild className="gap-x-3">
      <Link href="/login">
        <span>Join </span>
        <span className="text-xs">|</span>
        <span>Sign In</span>
        <LuUserCircle2 size={24} />
      </Link>
    </Button>
  );
}
