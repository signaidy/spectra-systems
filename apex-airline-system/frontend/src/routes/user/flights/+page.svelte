<script>
  import { onMount } from "svelte";
  import { Banknote } from 'lucide-svelte';
  import aviancalogo from "$lib/assets/Avianca-Ticket-logo.png"; 

  export let data;
  let userid = data.user.userId

  const userId = window.localStorage.getItem("user_id");

  let userflights = [];
  console.log(userid); 

  onMount(async () => {
  fetch(`http://localhost:8080/user_tickets/${userid}`)
  .then(response => response.json())
  .then(userfdata => {
    userflights = userfdata
    console.log(userflights); 
  })
  });

</script>

<h1 class="text-xl font-bold mb-8">Booked flights</h1>
<div>
  {#if userflights.length>0}
    {#each userflights as {ticket_id, type, state, flight_id, origin, destination, departure_date, arrival_date }}
      <div class="p-10">
        <div
          class="max-w-full bg-white flex flex-col rounded overflow-hidden shadow-lg"
        >
          <div class="flex flex-row items-baseline flex-nowrap bg-gray-100 p-2">
            <h1 class="ml-1 uppercase font-bold text-black-500">Flight</h1>
            <p class="ml-1 font-normal text-gray-500">{flight_id}</p>
            <h1 class="ml-1 uppercase font-bold text-black-500">Ticket</h1>
            <p class="ml-1 font-normal text-gray-500">{ticket_id}</p>
            {#if state == "active"}
            <h1 class="text-green-700 statef">Active</h1> 
            {:else}
            <p class="text-red-700 statef">Canceled</p>   
            {/if} 
          </div>
          <div class="mt-2 flex justify-start bg-white p-2">
            <div
              class="flex mx-2 ml-6 h8 px-2 flex-row items-baseline rounded-full bg-gray-100 p-1"
            >
              <div class="reduct"><Banknote/></div>
              {#if type == "economy"}
              <p class="font-normal text-sm ml-1 text-gray-500">First class</p>
              {:else}
              <p class="font-normal text-sm ml-1 text-gray-500">Economy</p>
              {/if}
            </div>
          </div>
          <div class="mt-2 flex sm:flex-row mx-6 sm:justify-between flex-wrap">
            <div class="flex flex-row place-items-center p-2">
              <img
                alt="Qatar Airways"
                class="w-20 h-20"
                src={aviancalogo}
                style="opacity: 1; transform-origin: 0% 50% 0px; transform: none;"
              />
              <div class="flex flex-col ml-2 mx-2">
                <!-- <p class="text-s text-black-500 font-bold">Avianca</p> -->
                <p class="text-xs text-gray-500">Departure date</p><p>{departure_date}</p>
                <p class="text-xs text-gray-500">Arrival date</p><p>{arrival_date}</p>
              </div>
            </div>

            <div class="flex flex-col p-2">
              <p class="text-black-500">
                <span class="font-bold">Origin:</span>
              </p>
              <p class="text-gray-500">{origin}</p>
            </div>
            <div class="flex flex-col flex-wrap p-2">
              <p class="text-black-500">
                <span class="font-bold">Destination</span>
              </p>
              <p class="text-gray-500">{destination}</p>
            </div>

          </div>
                    
        </div>
      </div>
    {/each}
    {:else}
    <div>
      <p class="text-gray-500">No flights book yet</p>
    </div>
    {/if}
</div>


<style>
  .reduct{
    height: 17px;
    width: 25px;
    color: grey;
  }

  .statef {
    margin-left: 920px;
  }

</style>