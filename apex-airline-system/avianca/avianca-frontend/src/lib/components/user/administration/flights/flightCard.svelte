<script lang="ts">
  import logo from "$lib/assets/tetra.png";
  import { Button } from "$lib/components/ui/button";
  import DatePicker from "$lib/components/user/administration/flightCreator/datePicker.svelte";
  import LocationPicker from "$lib/components/user/administration/flightCreator/locationPicker.svelte";
  import { type DateValue } from "@internationalized/date";
  import { onMount } from "svelte";
  import {
  toCalendarDateTime,
  parseTime,
  parseDate,
} from "@internationalized/date";

  export let flight: CompleteFlight;

  let edit = false;

  let departureDay: DateValue | undefined;
  let arrivalDay: DateValue | undefined;
  let originCity: string;
  let destinationCity: string;
  let cities: City[] = [];

  onMount(async () => {
    fetch("http://localhost:8080/get-cities")
      .then((response) => response.json())
      .then((citiesinformation) => {
        cities = citiesinformation;
        return cities;
      });
  });

  async function Cancellation() {
    try {
      const response = await fetch(
        `http://localhost:8080/cancelation/${flight.flightId}`,
        {
          method: "POST",
          headers: { "Content-Type": "application/json" },
          body: JSON.stringify({}),
        }
      );
    } catch (error) {
      console.error("API error:", error);
    }
  }

   function UpdateFlight(flightId, departureDay, arrivalDay, originCity, destinationCity) {
    const detailFlight = document.getElementById("detail").value;

    const departureDateFormated = `${departureDay.year}-${departureDay.month}-${departureDay.day}`
    const arrivalDateFormated = `${arrivalDay.year}-${arrivalDay.month}-${arrivalDay.day}`

    console.log(departureDateFormated); 
    console.log(arrivalDateFormated); 

    try {
      const response = fetch(
        `http://localhost:8080/update-flight/${flightId}`,
        {
          method: "POST",
          headers: { "Content-Type": "application/json" },
          body: JSON.stringify({
            originCity: originCity,
            destinationCity: destinationCity,
            departureDate: departureDateFormated,
            arrivalDate: arrivalDateFormated,
            detail: detailFlight

        }),
        }
      );
    } catch (error) {
      console.error("API error:", error);
    }
  }
</script>

{#if edit == false}
  <div class="flex flex-col border my-8 rounded-lg">
    <div class="flex p-3 bg-muted justify-between">
      <div class="font-bold">Flight #{flight.flightId}</div>
      {#if flight.state === 0}
        <div class="font-bold text-red-600">Inactive</div>
      {:else}
        <div class="font-bold text-green-600">Active</div>
      {/if}
    </div>
    <div class="flex p-3 gap-x-10 flex-wrap">
      <img
        src={logo}
        alt="Avianca Logo"
        class="w-[60px] self-center h-[60px]"
      />
      <div class="flex flex-col gap-y-2">
        <div class="flex flex-col gap-y-1">
          <div class="font-medium">Departure Date</div>
          <div class="text-sm text-muted-foreground">
            {flight.departureDate}
          </div>
        </div>
        <div class="flex flex-col gap-y-1">
          <div class="font-medium">Arrival Date</div>
          <div class="text-sm text-muted-foreground">{flight.arrivalDate}</div>
        </div>
      </div>
      <div class="flex flex-col gap-y-2">
        <div class="flex flex-col gap-y-1">
          <div class="font-medium">Economy Capacity</div>
          <div class="text-sm text-muted-foreground">
            {flight.touristCapacity}
          </div>
        </div>
        <div class="flex flex-col gap-y-1">
          <div class="font-medium">Business Capacity</div>
          <div class="text-sm text-muted-foreground">
            {flight.businessCapacity}
          </div>
        </div>
      </div>
      <div class="flex flex-col gap-y-2">
        <div class="flex flex-col gap-y-1">
          <div class="font-medium">Economy Tickets</div>
          <div class="text-sm text-muted-foreground">
            {flight.touristQuantity}
          </div>
        </div>
        <div class="flex flex-col gap-y-1">
          <div class="font-medium">Business Tickets</div>
          <div class="text-sm text-muted-foreground">
            {flight.businessQuantity}
          </div>
        </div>
      </div>
      <div class="flex flex-col gap-y-2">
        <div class="flex flex-col gap-y-1">
          <div class="font-medium">Origin</div>
          <div class="text-sm text-muted-foreground">
            {flight.originCityName}
          </div>
        </div>
        <div class="flex flex-col gap-y-1">
          <div class="font-medium">Destination</div>
          <div class="text-sm text-muted-foreground">
            {flight.destinationCityName}
          </div>
        </div>
      </div>
      <div class="flex flex-col gap-y-2">
        <div class="flex flex-col gap-y-1">
          <div class="font-medium">Flight details</div>
          <div class="text-sm text-muted-foreground">
            {flight.detail}
          </div>
        </div>
      </div>
    </div>

    <div class="spbtn object-right">
      <Button on:click={() => (edit = true)}>Edit</Button>
      {#if flight.state == 1}
        <button
          class="text-white bg-red-600 rounded px-1 py-2 focus:outline-none focus:shadow-outline object-right"
          on:click={Cancellation}
        >
          <span class="ml-1 text-sm">Cancel</span>
        </button>
      {/if}
    </div>
  </div>
{:else}
  <!-- Display of flight to edit -->
  <div class="flex flex-col border my-8 rounded-lg">
    <div class="flex p-3 bg-muted justify-between">
      <div class="font-bold">Flight #{flight.flightId}</div>
      {#if flight.state === 0}
        <div class="font-bold text-red-600">Inactive</div>
      {:else}
        <div class="font-bold text-green-600">Active</div>
      {/if}
    </div>
    <div class="flex p-3 gap-x-10 flex-wrap">
      <img
        src={logo}
        alt="Avianca Logo"
        class="w-[60px] self-center h-[60px]"
      />
      <div class="flex flex-col gap-y-2">
        <div class="flex flex-col gap-y-1">
          <div class="font-medium">
            Departure Date: <p class="text-sm text-muted-foreground mt-0.5">
              {flight.departureDate}
            </p>
          </div>
          <DatePicker bind:value={departureDay} />
        </div>
        <div class="flex flex-col gap-y-1">
          <div class="font-medium">
            Arrival Date: <p class="text-sm text-muted-foreground mt-0.5">
              {flight.arrivalDate}
            </p>
          </div>
          <DatePicker bind:value={arrivalDay} />
        </div>
      </div>
      <div class="flex flex-col gap-y-2">
        <div class="flex flex-col gap-y-1">
          <div class="font-medium">Economy Capacity</div>
          <div class="text-sm text-muted-foreground">
            {flight.touristCapacity}
          </div>
        </div>
        <div class="flex flex-col gap-y-1">
          <div class="font-medium">Business Capacity</div>
          <div class="text-sm text-muted-foreground">
            {flight.businessCapacity}
          </div>
        </div>
      </div>
      <div class="flex flex-col gap-y-2">
        <div class="flex flex-col gap-y-1">
          <div class="font-medium">Economy Tickets</div>
          <div class="text-sm text-muted-foreground">
            {flight.touristQuantity}
          </div>
        </div>
        <div class="flex flex-col gap-y-1">
          <div class="font-medium">Business Tickets</div>
          <div class="text-sm text-muted-foreground">
            {flight.businessQuantity}
          </div>
        </div>
      </div>
      <div class="flex flex-col gap-y-2">
        <div class="flex flex-col gap-y-1">
          <div class="font-medium">Origin</div>
          <div class="text-sm text-muted-foreground">
            <p>{flight.originCityName}</p>
            <LocationPicker {cities} bind:value={originCity} />
          </div>
        </div>
        <div class="flex flex-col gap-y-1">
          <div class="font-medium">Destination</div>
          <div class="text-sm text-muted-foreground">
            <p>{flight.destinationCityName}</p>
            <LocationPicker {cities} bind:value={destinationCity} />
          </div>
        </div>
      </div>
      <div class="flex flex-col gap-y-2">
        <div class="flex flex-col gap-y-1">
          <div class="font-medium">Flight details</div>
          <div class="text-sm text-muted-foreground">
            <textarea id="detail" name="detail" placeholder={flight.detail} />
          </div>
        </div>
      </div>
      <div class="spbtn object-right mt-5">
        <Button on:click={() => (edit = false)}>Go back</Button>
        <button
          class="text-white bg-indigo-600 rounded px-2 py-2 focus:outline-none focus:shadow-outline object-right"
          on:click={UpdateFlight(flight.flightId, departureDay, arrivalDay, originCity, destinationCity)}
        >
          <span class="ml-1 text-sm">Confirm changes</span>
        </button>
      </div>
    </div>
  </div>
{/if}

<style>
  .spbtn {
    margin-bottom: 10px;
    margin-left: 10px;
  }
</style>
