<script lang="ts">
  import FlightTypePicker from "$lib/components/user/administration/flightCreator/flightTypePicker.svelte";
  import OneWayFlight from "$lib/components/user/administration/flightCreator/oneWayFlight.svelte";
  import RoundTripFlight from "$lib/components/user/administration/flightCreator/roundTripFlight.svelte";
  let type: string;
  export let cities: City[] = [];
</script>

<RoundTripFlight {cities}/>
