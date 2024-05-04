<script lang="ts">
  import logo from "$lib/assets/tetra.png";
  import { Button } from "$lib/components/ui/button";
  import DatePicker from "$lib/components/user/administration/flightCreator/datePicker.svelte";
  import LocationPicker from "$lib/components/user/administration/flightCreator/locationPicker.svelte";
  import { type DateValue } from "@internationalized/date";
  import { onMount } from "svelte";
  import { Input } from "$lib/components/ui/input";
  import { Label } from "$lib/components/ui/label";
  import { MoveLeft } from "lucide-svelte";
  import { PUBLIC_BASE_URL } from '$env/dynamic/public';

  export let flight: CompleteFlight;

  let edit = false;
  let isOpen = false;
  let departureDay: DateValue | undefined;
  let arrivalDay: DateValue | undefined;
  let originCity: string;
  let destinationCity: string;
  let cities: City[] = [];
  let user = [];

  onMount(async () => {
    fetch(`${PUBLIC_BASE_URL}/get-cities`)
      .then((response) => response.json())
      .then((citiesinformation) => {
        cities = citiesinformation;
        return cities;
      });
  });

  onMount(async () => {
    fetch(`${PUBLIC_BASE_URL}/modification-notification/${flight.flightId}`)
      .then((response) => response.json())
      .then((usersinformation) => {
        user = usersinformation;
        return user;
      });
  });

  async function Cancellation(flightID) {
    const motive = document.getElementById("motive").value;

    try {
      const usersInformation = await fetch(
        `${PUBLIC_BASE_URL}/modification-notification/${flightID}`
      ).then((response) => response.json());
      if (usersInformation && usersInformation.length > 0) {
        user = usersInformation;
      } else {
        user = [];
      }
      // user = usersInformation;

      for (const userObject of user) {
        if (userObject.flight_id === flightID) {
          try {
            const email = userObject.email;
            const userName = userObject.name;
            const ticket = userObject.ticket;

            await Email.send({
              SecureToken: "11c78855-54e1-46a7-bc68-6751ad92e0dc",
              To: email,
              From: "systemspectracjm@gmail.com",
              Subject: "Flight canceled",
              Body: `Hi: ${userName} <br> 
            The flight associated to your ticker number: ${ticket} has been canceled. <br> 
            <br> <span style="font-weight: bold;">Reason of cancellation</span> <br> 
            ${motive}`,
            });
          } catch (error) {
            console.error("Error sending email to user:", error);
          }
        }
      }
      const response = await fetch(
        `${PUBLIC_BASE_URL}/cancelation/${flightID}`,
        {
          method: "POST",
          headers: { "Content-Type": "application/json" },
          body: JSON.stringify({}),
        }
      );

      if (response.ok) {
        console.log("Flight updated successfully!");
        let shouldRefresh = true;

        if (shouldRefresh) {
          await new Promise((resolve) => setTimeout(resolve, 1000));
          window.location.reload();
        }
      } else {
        console.error("Flight update failed:", response.status);
      }
    } catch (error) {
      console.error("API error:", error);
    }
  }

  async function UpdateFlight(
    flightId,
    departureDay,
    arrivalDay,
    originCity,
    destinationCity
  ) {
    try {
      const usersInformation = await fetch(
        `${PUBLIC_BASE_URL}/modification-notification/${flightId}`
      ).then((response) => response.json());
      if (usersInformation && usersInformation.length > 0) {
        user = usersInformation;
      } else {
        user = [];
      }
      // user = usersInformation;

      const detailFlight = document.getElementById("detail").value;
      const departureTime = document.getElementById("departureTime").value;
      const arrivalTime = document.getElementById("arrivalTime").value;
      const departureDateFormated = `${departureDay.year}-${departureDay.month}-${departureDay.day} ${departureTime}`;
      const arrivalDateFormated = `${arrivalDay.year}-${arrivalDay.month}-${arrivalDay.day} ${arrivalTime}`;

      for (const userObject of user) {
        if (userObject.flight_id === flightId) {
          try {
            const email = userObject.email;
            const userName = userObject.name;
            const ticket = userObject.ticket;

            await Email.send({
              SecureToken: "11c78855-54e1-46a7-bc68-6751ad92e0dc",
              To: email,
              From: "systemspectracjm@gmail.com",
              Subject: "Avianca has updated your flight",
              Body: `Hi: ${userName} <br> 
            The flight associated to your ticker number: ${ticket} has been modified. <br> 
            <br> <span style="font-weight: bold;">This is the new information of the flight:</span> <br> 
            <br> <span style="font-weight: bold;">Departure day: </span> ${departureDateFormated}
            <br> <span style="font-weight: bold;">Arrival Day: </span> ${arrivalDateFormated}
            <br> <span style="font-weight: bold;">Origin city: </span> ${originCity}
            <br> <span style="font-weight: bold;">Destination city: </span> ${destinationCity}
            <br> <span style="font-weight: bold;">Detail of flight: </span> ${detailFlight} 
          `,
            });
          } catch (error) {
            console.error("Error sending email to user:", error);
          }
        }
      }

      const response = await fetch(
        `${PUBLIC_BASE_URL}/update-flight/${flightId}`,
        {
          method: "POST",
          headers: { "Content-Type": "application/json" },
          body: JSON.stringify({
            originCity: originCity,
            destinationCity: destinationCity,
            departureDate: departureDateFormated,
            arrivalDate: arrivalDateFormated,
            detail: detailFlight,
          }),
        }
      );

      if (response.ok) {
        console.log("Flight updated successfully!");
        let shouldRefresh = true;

        if (shouldRefresh) {
          await new Promise((resolve) => setTimeout(resolve, 100));
          window.location.reload();
        }
      } else {
        console.error("Flight update failed:", response.status);
      }
    } catch (error) {
      console.error("API error:", error);
    }
  }
</script>

<svelte:head>
  <script src="https://smtpjs.com/v3/smtp.js">
  </script>
</svelte:head>

{#if isOpen == true}
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
          on:click={() => (isOpen = true)}
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
          <Label for="departureTime" class="font-bold mt-2"
            >Departure Time</Label
          >
          <Input type="time" id="departureTime" name="departureTime" />
        </div>
        <div class="flex flex-col gap-y-1">
          <div class="font-medium">
            Arrival Date: <p class="text-sm text-muted-foreground mt-0.5">
              {flight.arrivalDate}
            </p>
          </div>
          <DatePicker bind:value={arrivalDay} />
          <Label for="arrivalTime" class="font-bold mt-2">Arrival Time</Label>
          <Input type="time" id="arrivalTime" name="arrivalTime" />
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
          on:click={UpdateFlight(
            flight.flightId,
            departureDay,
            arrivalDay,
            originCity,
            destinationCity
          )}
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
