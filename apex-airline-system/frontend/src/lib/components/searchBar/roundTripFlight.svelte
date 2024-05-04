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
  import { goto } from "$app/navigation";
  import { PUBLIC_BASE_URL } from "$env/dynamic/public";

  export let cities: Promise<any> = [];

  let isValid = true;

  let type = "round-trip";
  let originCity: string;
  let destinationCity: string;
  let days: DateRange | undefined;
  let passengers: number;

  function combinedFunction() {
  searchFlights();
  handlecitycount(destinationCity);
  handletypecount(type); 
  searchregister();
}
  
  async function handlecitycount(data) {
    const response = await fetch(`${PUBLIC_BASE_URL}/citysearch/${data}`, {
      method: 'POST',
      headers: { 'Content-Type': 'application/json' },
    });

    if (!response.ok) {
      console.error("Error:", response.statusText);
    } else {
      const responseData = await response.json();
      console.log(responseData);
    }
  }

  async function searchregister() {
    const response = await fetch(`${PUBLIC_BASE_URL}/searchregistration`, {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify({
        origin: originCity,
        destination: destinationCity,
        departure_date: days.start.toString(),
        return_date: days.end.toString(),
        passengers: passengers,
        flight_type: type,
        type_search: "Rest"
      }),
    });

    if (!response.ok) {
      console.error("Error:", response.statusText);
    } else {
      const responseData = await response.json();
      console.log(responseData);
    }
  }

  async function handletypecount(data) {
    const response = await fetch(`${PUBLIC_BASE_URL}/typesearch/${data}`, {
      method: 'POST',
      headers: { 'Content-Type': 'application/json' },
    });

    if (!response.ok) {
      console.error("Error:", response.statusText);
    } else {
      const responseData = await response.json();
      console.log(responseData);
    }
  }

  function searchFlights() {
    if (originCity && destinationCity && days && days.start && days.end && passengers) {
      const searchParams = new URLSearchParams();
      searchParams.append("originCity", originCity);
      searchParams.append("destinationCity", destinationCity);
      searchParams.append("departureDay", days.start.toString());
      searchParams.append("returnDay", days.end.toString());
      searchParams.append("passengers", passengers.toString());
      searchParams.append("type", type);
      searchParams.append("phase", "1"); 
      searchParams.append("mainorigin", originCity); 
      searchParams.append("maindestination", destinationCity); 
      goto(`/search?${searchParams.toString()}`);
    } else {
      isValid = false;
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
    <Button on:click={combinedFunction}>Find Flights</Button>
  </div>
</div>
