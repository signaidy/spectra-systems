<script lang="ts">
  import { Button } from "$lib/components/ui/button";
  import { MoveLeft } from "lucide-svelte";
  import FlightCard from "$lib/components/user/administration/flights/flightCard.svelte";
  import FlightCardSkeleton from "$lib/components/user/administration/flights/flightCardSkeleton.svelte";
  export let data;
</script>

<div class="flex items-center mt-4 mb-8 gap-x-3">
  <a href="/user/administration"><MoveLeft /></a>
  <h1 class="text-xl font-bold">Flights Administration</h1>
</div>
<Button href="/user/administration/create-flight">Create Flight</Button>
{#await data.flights}
  {#each Array(3).fill(0) as _}
    <FlightCardSkeleton />
  {/each}
{:then flights}
  {#each flights as flight}
    <FlightCard {flight} />
  {/each}
{:catch error}
  <p>{error.message}</p>
{/await}
