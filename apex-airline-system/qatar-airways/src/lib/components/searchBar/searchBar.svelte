<script lang="ts">
  import FlightTypePicker from "$lib/components/searchBar/flightTypePicker.svelte";
  import OneWayFlight from "$lib/components/searchBar/oneWayFlight.svelte";
  import RoundTripFlight from "$lib/components/searchBar/roundTripFlight.svelte";
</script>

<div class="flex flex-col bg-background/50 rounded-lg">
  <FlightTypePicker />
  <RoundTripFlight />
</div>
