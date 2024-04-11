"use client";
// Hooks
import { useState } from "react";
import { useSearchParams } from "next/navigation";
import { useRouter } from "next/navigation";
import { useEffect } from "react";
// UI
import {
  Select,
  SelectContent,
  SelectItem,
  SelectTrigger,
  SelectValue,
} from "@/components/ui/select";

export function FilterBar() {
  const router = useRouter();
  const searchParams = useSearchParams();

  const [filter, setFilter] = useState({
    source: searchParams.get("source") || null,
    location: searchParams.get("location") || null,
    checkin: searchParams.get("checkin") || null,
    checkout: searchParams.get("checkout") || null,
    madeAt: searchParams.get("madeAt") || null,
  });

  function handleFilter() {
    const params = new URLSearchParams();
    filter.source && params.set("source", filter.source);
    filter.location && params.set("location", filter.location);
    filter.checkin && params.set("checkin", filter.checkin);
    filter.checkout && params.set("checkout", filter.checkout);
    filter.madeAt && params.set("guests", filter.madeAt);

    router.push(`/analytics?${params.toString()}`);
  }

  useEffect(() => {
    handleFilter();
  }, [filter]);

  return (
    <div className="flex">
      <Select
        onValueChange={(value) => {
          setFilter({ ...filter, source: value });
        }}
      >
        <SelectTrigger className="w-[180px]">
          <SelectValue placeholder="Source" />
        </SelectTrigger>
        <SelectContent>
          <SelectItem value="web">Web</SelectItem>
          <SelectItem value="rest">Rest</SelectItem>
        </SelectContent>
      </Select>
    </div>
  );
}
