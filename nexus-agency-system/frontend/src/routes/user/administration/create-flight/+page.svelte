<script lang="ts">
  import FlightCreator from "$lib/components/user/administration/flightCreator/flightCreator.svelte";
  import { MoveLeft } from "lucide-svelte";
  export let form;
  export let data;
</script>

<div class="flex items-center mt-4 mb-8 gap-x-3">
  <a href="/user/administration"><MoveLeft /></a>
  <h1 class="text-xl font-bold">Create Flight</h1>
</div>
{#if form?.error}
  <div class="text-sm text-red-500 font-medium mb-2">
    Error: {form.error}
  </div>
{/if}
<FlightCreator cities={data.cities} />
