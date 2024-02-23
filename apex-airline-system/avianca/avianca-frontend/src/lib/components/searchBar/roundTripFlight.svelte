<script lang="ts">
  import LocationPicker from "$lib/components/searchBar/locationPicker.svelte";
  import LocationPickerSkeleton from "./locationPickerSkeleton.svelte";
  import DateRangePicker from "$lib/components/searchBar/dateRangePicker.svelte";
  import NumberPicker from "$lib/components/searchBar/numberPicker.svelte";
  import { Button } from "$lib/components/ui/button";
  // Icons
  import { PlaneTakeoff, PlaneLanding, UsersRound } from "lucide-svelte";
  // Utilities
  import type { DateRange } from "bits-ui";

  export let cities: Promise<any> = [];

  let isValid = true;

  let type = "round-trip";
  let originCity: string;
  let destinationCity: string;
  let days: DateRange | undefined;
  let passengers: number;

  function searchFlights() {
    if (originCity && destinationCity && days && passengers) {
      console.log(days.start?.toString());
      console.log(days.end?.toString());
      console.log("Passed");
    } else {
      isValid = false;
      console.log("Failed");
    }
  }
</script>

{#if !isValid}
  <div class="ml-3 my-px text-red-500 font-medium">Please fill in all the fields</div>
{/if}
<div class="bg-background rounded-lg p-3 shadow flex gap-x-3 flex-wrap gap-y-3">
  <div class="flex flex-col gap-y-2 w-fit">
    <div class="font-bold flex gap-x-2 items-center">
      Origin<PlaneTakeoff class="shrink-0 w-5 h-5" />
    </div>
    {#await cities}
      <LocationPickerSkeleton />
    {:then cities}
      <LocationPicker {cities} bind:value={originCity} />
    {:catch error}
      <p>{error.message}</p>
    {/await}
  </div>
  <div class="flex flex-col gap-y-2 w-fit">
    <div class="font-bold flex gap-x-2 items-center">
      Destination<PlaneLanding class="shrink-0 w-5 h-5" />
    </div>
    {#await cities}
      <LocationPickerSkeleton />
    {:then cities}
      <LocationPicker {cities} bind:value={destinationCity} />
    {:catch error}
      <p>{error.message}</p>
    {/await}
  </div>
  <div class="flex flex-col gap-y-2 w-fit">
    <div class="font-bold">Departure - Return</div>
    <DateRangePicker bind:value={days} />
  </div>
  <div class="flex flex-col gap-y-2 w-fit">
    <div class="font-bold flex gap-x-2 items-center">
      Passengers<UsersRound class="shrink-0 w-5 h-5" />
    </div>
    <NumberPicker bind:passengers />
  </div>
  <div class="flex flex-col gap-y-2 w-fit">
    <div class="text-background">Find</div>
    <Button on:click={searchFlights}>Find Flights</Button>
  </div>
</div>
