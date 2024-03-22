<script lang="ts">
  import Reservation from "$lib/components/hotelsearch/reservation.svelte";
  export let cities: Promise<any> = [];
</script>

<div class="flex flex-col bg-background/50 rounded-lg">
    <Reservation {cities}/>
</div>
