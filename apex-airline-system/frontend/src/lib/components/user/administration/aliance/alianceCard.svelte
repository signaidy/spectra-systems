<script lang="ts">
    import { Button } from "$lib/components/ui/button";
    import { PUBLIC_BASE_URL } from '$env/static/public';
  
    export let aliance: any[];
  
    let edit = false;

    function upadteAliance(id, ip, endpoint, key) {
      try {
        const response = fetch(
          `${PUBLIC_BASE_URL}/update-aliance/${id}`,
          {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify({
              IP: ip, 
              endpoint: endpoint, 
              key: key
            }),
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
            <p class="my-4  font-bold text-gray-900">IP:</p><input id="IP" placeholder={aliance.IP} bind:value={aliance.IP}>
            <p class="my-4  font-bold text-gray-900">Endpoint:</p><input id="endpoint" placeholder={aliance.endpoint} bind:value={aliance.endpoint}>
            <p class="my-4  font-bold text-gray-900">Key:</p><input id="key" placeholder={aliance.key} bind:value={aliance.key}>
            <p class="my-4 text-xl font-semibold text-gray-500">ID: {aliance.ID}</p>
            <input hidden id="ID" bind:value={aliance.ID}>
            <Button on:click={() => (edit = false)}>Go back</Button>
            <Button on:click={upadteAliance(aliance.ID, aliance.IP, aliance.endpoint, aliance.key)}>Apply changes</Button>
      </div>
    </div>
  </div>
  {:else if edit == false}
  <div class="flex flex-col mt-5">
    <div>
      <div class="max-w-s w-full rounded-lg bg-white p-2 shadow duration-150 hover:scale-105 hover:shadow-md ">
            <p class="my-4  font-bold text-gray-900">IP: {aliance.IP}</p>
            <p class="my-4  font-bold text-gray-900">Endpoint: {aliance.endpoint}</p>
            <p class="my-4  font-bold text-gray-900">Key: {aliance.key}</p>

            <p class="my-4 text-xl font-semibold text-gray-500">ID: {aliance.ID}</p>
            <Button on:click={() => (edit = true)}>Edit</Button>
      </div>
      
    </div>
  </div>
  {/if}
  