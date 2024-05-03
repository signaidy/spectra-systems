<script lang="ts">
  import LocationPicker from "$lib/components/searchBar/locationPicker.svelte";
  import LocationPickerSkeleton from "./locationPickerSkeleton.svelte";
  import DatePicker from "$lib/components/searchBar/datePicker.svelte";
  import NumberPicker from "$lib/components/searchBar/numberPicker.svelte";
  import { Button } from "$lib/components/ui/button";
  import { PlaneTakeoff, PlaneLanding, UsersRound } from "lucide-svelte";
  // Utilities
  import { type DateValue, DateFormatter } from "@internationalized/date";
  import { goto } from "$app/navigation";
  import { PUBLIC_BASE_URL } from "$env/static/public";

  export let cities: Promise<any> = [];

  let isValid = true;

  let type = "one-way";
  let originCity: string;
  let destinationCity: string;
  let departureDay: DateValue | undefined;
  let passengers: number;

  function combinedFunction() {
  searchFlights();
  handlecitycount(destinationCity);
  handletypecount(type); 
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
    if (originCity && destinationCity && departureDay && passengers) {
      const searchParams = new URLSearchParams();
      searchParams.append("originCity", originCity);
      searchParams.append("destinationCity", destinationCity);
      searchParams.append("departureDay", departureDay?.toString()!);
      searchParams.append("passengers", passengers.toString());
      searchParams.append("type", type);
      goto(`/search?${searchParams.toString()}`);
    } else {
      isValid = false;
    }
  }
</script>

{#if !isValid}
  <div class="ml-3 my-px text-red-500 font-medium">
    Please fill in all the fields
  </div>
{/if}
<div class="bg-background rounded-lg p-3 shadow flex gap-x-3 flex-wrap gap-y-3">
  <div class="flex flex-col gap-y-2">
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
  <div class="flex flex-col gap-y-2">
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
  <div class="flex flex-col gap-y-2">
    <div class="font-bold">Departure</div>
    <DatePicker bind:value={departureDay} />
  </div>
  <div class="flex flex-col gap-y-2">
    <div class="font-bold flex gap-x-2 items-center">
      Passengers<UsersRound class="shrink-0 w-5 h-5" />
    </div>
    <NumberPicker bind:passengers />
  </div>
  <div class="flex flex-col gap-y-2">
    <div class="text-background">Find</div>
    <Button on:click={combinedFunction}>Find Flights</Button>
  </div>
</div>
