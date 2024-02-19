<script lang="ts">
  import FlightCreator from "$lib/components/user/administration/flightCreator/flightCreator.svelte";
  import { MoveLeft } from "lucide-svelte";
  export let form;
  export let data;
</script>

<a href="/user/administration" class="mb-4"><MoveLeft /></a>
<h1 class="text-xl font-bold mb-8">Create Flight</h1>
{#if form?.error}
  <div class="text-sm text-red-500 font-medium mb-2">
    Error: {form.error}
  </div>
{/if}
<FlightCreator cities={data.cities} />
