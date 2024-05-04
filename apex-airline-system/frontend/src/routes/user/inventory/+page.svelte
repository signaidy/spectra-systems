<script>
  import { PlaneTakeoff } from "lucide-svelte";
  import { onMount } from "svelte";
  import { MoveRight } from "lucide-svelte";
  import { Gem } from "lucide-svelte";
  import { TicketPercent } from "lucide-svelte";
  import { PUBLIC_BASE_URL } from '$env/dynamic/public';

  let inventory = [];

  onMount(async () => {
    fetch(`${PUBLIC_BASE_URL}/inventory`)
      .then((response) => response.json())
      .then((data) => {
        inventory = data;
      });
  });
</script>

<h1 class="text-xl font-bold mb-10">Inventory</h1>
<div>
  {#if inventory.length > 0}
    {#each inventory as { origin, destination, departure_date, arrival_date, amount_normal, amount_premium, price_normal, price_premium, detail, type, state, flight_id }}
      <div
        class="w-full md:w-full mx-5 bg-white shadow-md rounded-md px-6 py-4 my-6"
      >
        <div class="sm:flex sm:justify-between">
          <div class="flex items-center">
            <div class="h-18 w-12 rounded-full">
              <PlaneTakeoff />
            </div>
            <div>
              <h3 class="text-lg text-gray-800 font-medium">
                Flight: {flight_id}
              </h3>
            </div>
          </div>
          <div class="flex justify-between">
            {#if type == 0}
              <span
                class="flex items-center text-white bg-gray-600 rounded px-2 py-1 focus:outline-none focus:shadow-outline m-2"
              >
                <span class="ml-1 text-sm">Direct</span>
              </span>
            {:else}
              <span
                class="flex items-center text-white bg-gray-600 rounded px-2 py-1 focus:outline-none focus:shadow-outline m-2"
              >
                <span class="ml-1 text-sm">Scale</span>
              </span>
            {/if}
            {#if state == 1}
              <span
                class="flex items-center text-white bg-green-600 rounded px-2 py-1 focus:outline-none focus:shadow-outline m-2"
              >
                <span class="ml-1 text-sm">Active</span>
              </span>
            {:else}
              <span
                class="flex items-center text-white bg-red-600 rounded px-2 py-1 focus:outline-none focus:shadow-outline m-2"
              >
                <span class="ml-1 text-sm">Canceled</span>
              </span>
            {/if}
          </div>
        </div>
        <div class="flex items-center mt-4">
          <div>
            <h4 class="text-gray-600 text-sm">Origin</h4>
            <span class="mt-2 text-xl font-medium text-gray-800">{origin}</span>
          </div>
          <div class="ml-3 mb-6"><MoveRight /></div>
          <div class="ml-8">
            <h4 class="text-gray-600 text-sm">Destination</h4>
            <span class="mt-2 text-xl font-medium text-gray-800"
              >{destination}</span
            >
          </div>
        </div>
        <div class="flex items-center mt-4">
          <div>
            <h4 class="text-gray-600 text-sm">Departure date</h4>
            <span class="mt-2 text-l font-medium text-gray-800"
              >{departure_date}</span
            >
          </div>
          <div class="ml-10">
            <h4 class="text-gray-600 text-sm">Arrival date</h4>
            <span class="mt-2 text-l font-medium text-gray-800"
              >{arrival_date}</span
            >
          </div>
        </div>
        <div class="flex items-center mt-4">
          <div class="mb-4">
            <TicketPercent />
          </div>
          <div class="ml-2">
            <h4 class="text-gray-600 text-sm">Tourist capacity</h4>
            <span class="mt-2 text-l font-medium text-gray-800"
              >{amount_normal}</span
            >
          </div>
          <div class="ml-10">
            <h4 class="text-gray-600 text-sm">Price:</h4>
            <span class="mt-2 text-l font-medium text-gray-800"
              >${price_normal}.00</span
            >
          </div>
          <div class="ml-20 mb-3">
            <Gem />
          </div>
          <div class=" ml-2">
            <h4 class="text-gray-600 text-sm .g">Premium capacity:</h4>
            <span class="mt-2 text-l font-medium text-gray-800"
              >{amount_premium}</span
            >
          </div>
          <div class="ml-10">
            <h4 class="text-gray-600 text-sm">Price:</h4>
            <span class="mt-2 text-l font-medium text-gray-800"
              >${price_premium}.00</span
            >
          </div>
        </div>
        <div class="flex justify-between items-center mt-4">
          <div>
            <h4 class="text-gray-600 text-sm">Description:</h4>
            <span class="mt-2 text-sl font-medium text-gray-800">{detail}</span>
          </div>
        </div>
      </div>
    {/each}
  {:else}
    <div>
      <p class="text-gray-500">Empty</p>
    </div>
  {/if}
</div>

<style>
  .g {
    float: left;
  }
</style>
