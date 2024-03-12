"use client";
import { useFormStatus } from "react-dom";
import { cn } from "@/lib/utils";
// UI
import { Button } from "@/components/ui/button";
// Icons
import { Loader2 } from "lucide-react";

export function SubmitButton({
  className,
  children,
}: {
  className?: string;
  children: React.ReactNode;
}) {
  const { pending } = useFormStatus();

  return (
    <Button type="submit" className={className} disabled={pending}>
      {children}
      <Loader2
        className={cn("h-4 w-4 ml-3 animate-spin", !pending && "invisible")}
      />
    </Button>
  );
}
