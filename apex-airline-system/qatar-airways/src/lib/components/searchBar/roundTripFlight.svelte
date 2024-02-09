<script lang="ts">
  import LocationPicker from "$lib/components/searchBar/locationPicker.svelte";
  import DateRangePicker from "$lib/components/searchBar/dateRangePicker.svelte";
  import NumberPicker from "$lib/components/searchBar/numberPicker.svelte";
  import { Button } from "$lib/components/ui/button";
  import { PlaneTakeoff, PlaneLanding, UsersRound } from "lucide-svelte";
</script>

<div class="bg-background rounded-lg p-3 shadow flex gap-x-3 flex-wrap gap-y-3">
  <div class="flex flex-col gap-y-2 w-fit">
    <div class="font-bold flex gap-x-2 items-center">
      Origin<PlaneTakeoff class="shrink-0 w-5 h-5" />
    </div>
    <LocationPicker />
  </div>
  <div class="flex flex-col gap-y-2 w-fit">
    <div class="font-bold flex gap-x-2 items-center">
      Destination<PlaneLanding class="shrink-0 w-5 h-5" />
    </div>
    <LocationPicker />
  </div>
  <div class="flex flex-col gap-y-2 w-fit">
    <div class="font-bold">Departure - Return</div>
    <DateRangePicker />
  </div>
  <div class="flex flex-col gap-y-2 w-fit">
    <div class="font-bold flex gap-x-2 items-center">
      Passengers<UsersRound class="shrink-0 w-5 h-5" />
    </div>
    <NumberPicker />
  </div>
  <div class="flex flex-col gap-y-2 w-fit">
    <div class="text-background">Find</div>
    <Button href="/search">Find Flights</Button>
  </div>
</div>
