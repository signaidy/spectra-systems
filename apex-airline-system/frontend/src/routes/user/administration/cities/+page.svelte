<script lang="ts">
  import { Button } from "$lib/components/ui/button";
  import CityCard from "$lib/components/user/administration/cities/cityCard.svelte";
  import CitySkeleton from "$lib/components/user/administration/cities/citieSkeleton.svelte";
  import { PUBLIC_BASE_URL } from '$env/dynamic/public';

  export let data;

  let alert_message;
  let display = false;

  async function AddCity() {
    const city = document.getElementById("city").value;
    if (city === "") {
      alert_message = "You have to enter a city first!";
      display = true;
    } else {
      try {
        const response = await fetch(
          `${PUBLIC_BASE_URL}/create-city/${city}`,
          {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify({}),
          }
        );
      } catch (error) {
        console.error("API error:", error);
      }

      let shouldRefresh = true;

      if (shouldRefresh) {
        await new Promise((resolve) => setTimeout(resolve, 1000));
        window.location.reload();
      }
    }
  }
</script>

<div class="flex items-center mt-4 mb-8 gap-x-3">
  <h1 class="text-xl font-bold">Cities administration</h1>
</div>
<div class="flex items-center justify-center">
  <input
    required
    type="text"
    id="city"
    class="mr-2 text-center bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg p-2 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500"
    placeholder="Insert a new country"
    style="width: 700px;"
  />
  <Button on:click={AddCity}>Add city</Button>
</div>
{#if display == true}
  <p class="mt-2 text-red-500 text-center dspl">{alert_message}</p>
{/if}
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

<style>
  .dspl {
    margin-right: 85px;
  }
</style>
