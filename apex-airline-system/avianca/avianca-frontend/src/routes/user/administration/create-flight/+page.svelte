<script lang="ts">
  import FlightCreator from "$lib/components/user/administration/flightCreator/flightCreator.svelte";

  export let form;
</script>

<h1 class="text-xl font-bold mb-8">Create Flight</h1>
{#if form?.error}
  <div class="text-sm text-red-500 font-medium mb-2">
    Error: {form.error}
  </div>
{/if}
<FlightCreator />
