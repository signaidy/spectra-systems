<script lang="ts">
    import { Button } from "$lib/components/ui/button";
    import CityCard from "$lib/components/user/administration/cities/cityCard.svelte";
    import CitySkeleton from "$lib/components/user/administration/cities/citieSkeleton.svelte";
    export let data;
  </script>
  
  <div class="flex items-center mt-4 mb-8 gap-x-3">
    <h1 class="text-xl font-bold">Cities administration</h1>
  </div>
  <Button href="/user/administration/create-flight">Add city</Button>
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
  