<script lang="ts">
    import logo from "$lib/assets/tetra.png";
    import { Button } from "$lib/components/ui/button";
    import { onMount } from "svelte";
    import { Input } from "$lib/components/ui/input";
    import { Label } from "$lib/components/ui/label";
    import { MoveLeft } from "lucide-svelte";
  
    export  let cities: City[] = [];
    console.log(cities); 
  
    let edit = false;
    // let isOpen = false;
  
  </script>

  <!-- {#if isOpen == true}
    <div
      class="fixed left-0 top-0 flex h-full w-full items-center justify-center bg-black bg-opacity-50 py-10"
    >
      <div
        class="max-h-full w-full max-w-xl overflow-y-auto sm:rounded-2xl bg-white"
      >
        <div class="flex mt-5 ml-10">
          <button on:click={() => (isOpen = false)}><MoveLeft /></button>
        </div>
        <div class="w-full">
          <div class="m-1 max-w-[400px] mx-auto">
            <div class="mb-8">
              <h1 class="mb-4 text-3xl font-extrabold">Flight - Cancelation</h1>
            </div>
            <div>
              <span class="motivet">Reason:</span>
              <textarea
                required
                id="motive"
                class="h-[100px] bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full pl-10 p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500"
              />
            </div>
            <div class="space-y-10 mt-10">
              <button
                class="p-3 bg-red-600 rounded-full text-white w-full font-semibold"
                on:click={Cancellation(flight.flightId)}>Cancel</button
              >
            </div>
          </div>
        </div>
      </div>
    </div>
  {/if}
   -->
  {#if edit == false}
  <div class="flex flex-col mt-5">
    <div>
      <div class="max-w-s w-full rounded-lg bg-white p-2 shadow duration-150 hover:scale-105 hover:shadow-md ">
            <p class="my-4  font-bold text-gray-900">City: {cities.name}</p>
            <p class="my-4 text-xl font-semibold text-gray-500">ID: {cities.cityId}</p>
      </div>
    </div>
  </div>

  {/if}
  