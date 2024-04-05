<script lang="ts">
  import SearchBar from "$lib/components/searchBar/searchBar.svelte";
  import FlightCard from "$lib/components/search/flightCard.svelte";
  import FlightCardSkeleton from "$lib/components/search/flightCardSkeleton.svelte";
  import { page } from "$app/stores";
  import Roundflight from "$lib/components/search/roundflight.svelte";

  export let data;
  export let form;
  console.log(data);
</script>

<div
  class="min-h-[calc(100vh-5rem)] bg-[url('$lib/assets/home-background.jpg')] bg-cover bg-fixed"
>
  <div class="flex flex-col container items-center p-8 h-full">
    <SearchBar cities={data.cities}/>
    {#await Promise.all([data.flights, data.scaleflights])}
    {#each Array(3).fill(0) as _}
      <FlightCardSkeleton />
    {/each}
  {:then [flights, scaleFlights]}
    {#if flights.length === 0 && scaleFlights.length === 0}
      <div class="self-start font-bold font-lg mt-5 my-3 bg-background p-3 rounded-lg">
        No flights found for specified cities on {$page.url.searchParams.get("departureDay")} for {$page.url.searchParams.get("passengers")} passengers
      </div>
    {:else}
      <div class="self-start font-bold font-lg mt-5 my-3 bg-background p-3 rounded-lg">
        Results for flights from {flights[0].originCityName} to {flights[0].destinationCityName} on {$page.url.searchParams.get("departureDay")} for {$page.url.searchParams.get("passengers")} passengers
      </div>
      {#each [...flights, ...scaleFlights] as flight}
        {#if $page.url.searchParams.get("type") == "round-trip"}
          <Roundflight {flight} {form} user={data.user} passengers={$page.url.searchParams.get("passengers")} phase={$page.url.searchParams.get("phase")}
            originCity={$page.url.searchParams.get("originCity")} destinationCity={$page.url.searchParams.get("destinationCity")} 
            departureDate={$page.url.searchParams.get("returnDay")} type={$page.url.searchParams.get("type")}/>
        {:else} 
          <FlightCard {flight} {form} user={data.user} passengers={$page.url.searchParams.get("passengers")} isScaleFlight={flights.indexOf(flight) === -1}/>
        {/if}
      {/each}
    {/if}
  {:catch error}
    <p>{error.message}</p>
  {/await}
  
  </div>
</div>
