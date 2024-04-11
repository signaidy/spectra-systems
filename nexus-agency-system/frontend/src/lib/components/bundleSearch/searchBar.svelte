<script lang="ts">
  import FlightTypePicker from "$lib/components/bundleSearch/flightTypePicker.svelte";
  import OneWayFlight from "$lib/components/bundleSearch/oneWayFlight.svelte";
  import RoundTripFlight from "$lib/components/bundleSearch/roundTripFlight.svelte";
  let type: string;
  export let cities: Promise<any> = [];
</script>

<div class="flex flex-col bg-background/50 rounded-lg">
  <FlightTypePicker bind:value={type} />
  {#if type === "one-way"}
    <OneWayFlight {cities}/>
  {:else}
    <RoundTripFlight {cities}/>
  {/if}
</div>
