import Link from "next/link";
// UI Components
import { Button } from "@/components/ui/button";
import { ShoppingBag } from "lucide-react";

export default function CheckoutSuccessHome() {
  return (
    <div className="container flex flex-col gap-y-5 items-center justify-center h-[calc(100vh-4.813rem)]">
      <h1 className="text-3xl font-bold">Thank You For Your Purchase!</h1>
      <ShoppingBag size={70} className="fill-green-600"/>
      <Button asChild className="text-base">
        <Link href="/">Continue Shopping</Link>
      </Button>
    </div>
  );
}
