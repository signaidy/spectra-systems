import { Input } from "@/components/ui/input";
import { DatePicker } from "@/components/datePicker";
import { Button } from "@/components/ui/button";
import { NumberPicker } from "@/components/numberPicker";
import { LocationPicker } from "@/components/locationPicker";

function SearchBarElement({
  title,
  children,
}: {
  title: string;
  children: React.ReactNode;
}) {
  return (
    <div className="flex flex-col gap-y-2">
      <div className="text-foreground font-bold tracking-tight text-base">
        {title}
      </div>
      {children}
    </div>
  );
}

export function SearchBar() {
  return (
    <div className="flex container items-center justify-center ">
      <div className="flex gap-x-3 bg-background  rounded-md p-3 items-center">
        <SearchBarElement title="Where to?">
          <LocationPicker />
        </SearchBarElement>
        <SearchBarElement title="When?">
          <DatePicker />
        </SearchBarElement>
        <SearchBarElement title="How many?">
          <NumberPicker />
        </SearchBarElement>
        <div className="flex flex-col gap-y-2">
          <div className="text-background font-bold">Search</div>
          <Button>Find Hotels</Button>
        </div>
      </div>
    </div>
  );
}
