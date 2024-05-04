<script lang="ts">
    import { Button } from "$lib/components/ui/button";
    import { PUBLIC_BASE_URL } from '$env/dynamic/public';
  
    export  let cities: City[] = [];
  
    let edit = false;

    function UpdateCity(name, id) {
      console.log(name); 
      console.log(id); 
      try {
        const response = fetch(
          `${PUBLIC_BASE_URL}/update-city/${name}/${id}`,
          {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify({}),
          }
        );
      } catch (error) {
        console.error("API error:", error);
      }

    edit = false; 
    }
  </script>

  {#if edit == true}
  <div class="flex flex-col mt-5">
    <div>
      <div class="max-w-s w-full rounded-lg bg-white p-2 shadow duration-150 hover:scale-105 hover:shadow-md ">
            <p class="my-4  font-bold text-gray-900">City:</p><input id="city" placeholder={cities.name} bind:value={cities.name}>
            <p class="my-4 text-xl font-semibold text-gray-500">ID: {cities.cityId}</p>
            <input hidden id="cityid" bind:value={cities.cityId}>
            <Button on:click={() => (edit = false)}>Go back</Button>
            <Button on:click={UpdateCity(cities.name, cities.cityId)}>Apply changes</Button>
      </div>
    </div>
  </div>
  {:else if edit == false}
  <div class="flex flex-col mt-5">
    <div>
      <div class="max-w-s w-full rounded-lg bg-white p-2 shadow duration-150 hover:scale-105 hover:shadow-md ">
            <p class="my-4  font-bold text-gray-900">City: {cities.name}</p>
            <p class="my-4 text-xl font-semibold text-gray-500">ID: {cities.cityId}</p>
            <Button on:click={() => (edit = true)}>Edit</Button>
      </div>
      
    </div>
  </div>
  {/if}
  