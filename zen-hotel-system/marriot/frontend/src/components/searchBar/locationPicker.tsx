"use client";
import { useState } from "react";
import { Check, ChevronsUpDown } from "lucide-react";
import { cn } from "@/lib/utils";
import { Button } from "@/components/ui/button";
import {
  Command,
  CommandEmpty,
  CommandGroup,
  CommandInput,
  CommandItem,
} from "@/components/ui/command";
import {
  Popover,
  PopoverContent,
  PopoverTrigger,
} from "@/components/ui/popover";

export function LocationPicker({
  locations,
  currentLocation,
  setLocation,
}: {
  locations: string[];
  currentLocation: string;
  setLocation: (location: string) => void;
}) {
  const [open, setOpen] = useState(false);

  return (
    <Popover open={open} onOpenChange={setOpen}>
      <PopoverTrigger asChild>
        <Button
          variant="outline"
          role="combobox"
          aria-expanded={open}
          className="w-96 justify-between capitalize"
        >
          {currentLocation
            ? locations.find((location) => location === currentLocation)
            : "Search for a destination"}
          <ChevronsUpDown className="ml-2 h-4 w-4 opacity-50" />
        </Button>
      </PopoverTrigger>
      <PopoverContent className="w-96 p-0">
        <Command>
          <CommandInput placeholder="Search framework..." />
          <CommandEmpty>No destinations found.</CommandEmpty>
          <CommandGroup>
            {locations.map((location) => (
              <CommandItem
                key={location}
                value={location}
                onSelect={(currentValue) => {
                  setLocation(
                    currentValue === currentLocation ? "" : currentValue
                  );
                  setOpen(false);
                }}
                className="capitalize"
              >
                <Check
                  className={cn(
                    "mr-2 h-4 w-4",
                    currentLocation === location ? "opacity-100" : "opacity-0"
                  )}
                />
                {location}
              </CommandItem>
            ))}
          </CommandGroup>
        </Command>
      </PopoverContent>
    </Popover>
  );
}
