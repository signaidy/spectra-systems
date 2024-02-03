"use client";
import { useState } from "react";
import { Button } from "@/components/ui/button";
import { Minus, Plus } from "lucide-react";

export function NumberPicker() {
  const [goal, setGoal] = useState(1);

  function onClick(value: number) {
    setGoal(goal + value);
  }

  return (
    <div className="flex gap-x-3 items-center justify-center">
      <Button
        variant="outline"
        size="icon"
        className="h-8 w-8 rounded-full"
        onClick={() => onClick(-1)}
        disabled={goal <= 1}
      >
        <Minus className="h-4 w-4" />
        <span className="sr-only">Decrease</span>
      </Button>
      <div className="flex flex-col text-center">
        <div className="text-xl font-bold tracking-tighter">{goal}</div>
        <div className="text-xs uppercase text-muted-foreground">
          Guests
        </div>
      </div>
      <Button
        variant="outline"
        size="icon"
        className="h-8 w-8 rounded-full"
        onClick={() => onClick(1)}
        disabled={goal >= 20}
      >
        <Plus className="h-4 w-4" />
        <span className="sr-only">Increase</span>
      </Button>
    </div>
  );
}
