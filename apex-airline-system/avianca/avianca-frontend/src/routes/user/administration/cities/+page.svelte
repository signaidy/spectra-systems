<script lang="ts">
  import { Button } from "$lib/components/ui/button";
  import CityCard from "$lib/components/user/administration/cities/cityCard.svelte";
  import CitySkeleton from "$lib/components/user/administration/cities/citieSkeleton.svelte";
  export let data;



  
</script>

<div class="flex items-center mt-4 mb-8 gap-x-3">
  <h1 class="text-xl font-bold">Cities administration</h1>
</div>
<div class="flex items-center justify-center">
  <Button>Add city</Button>
  <input
    type="text"
    id="city"
    class="ml-2 text-center bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg p-2 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500"
    placeholder="Insert the new country"
    style="width: 700px;"
  />
</div>
{#await data.cities}
  {#each Array(3).fill(0) as _}
    <CitySkeleton />
  {/each}
{:then city}
  {#each city as cities}
    <CityCard {cities} />
  {/each}
{:catch error}
  <p>{error.message}</p>
{/await}
