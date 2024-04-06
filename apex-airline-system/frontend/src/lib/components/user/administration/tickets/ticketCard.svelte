<script lang="ts">
  import { Receipt } from "lucide-svelte";
  import { Settings } from "lucide-svelte";
  import { MoveLeft } from "lucide-svelte";
  import { PUBLIC_BASE_URL } from '$env/static/public';

  export let ticket: Ticket;

  let isOpen = false;

  function sendEmail(userName, ticketId, email) {
    const motive = document.getElementById("motive").value;

    try {
    const response = fetch(`${PUBLIC_BASE_URL}/ticketcanceled/${ticketId}`, {
      method: 'POST',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify({ 
      }),
    });
  } catch (error) {
    console.error('API error:', error);
  }

    Email.send({
      SecureToken: "11c78855-54e1-46a7-bc68-6751ad92e0dc",
      To: email,
      From: "systemspectracjm@gmail.com",
      Subject: "Ticket - Cancelation",
      Body: `Hi: ${userName} <br> 
    Your ticket with the ID: ${ticketId} was canceled. <br> 
    <br> Because the following reason: <br> 
    ${motive}`,
    }).then(isOpen = false)

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
    <div class="flex mt-5 ml-10 ">
      <button on:click={() => (isOpen = false)}><MoveLeft /></button>

    </div>
      <div class="w-full">
        <div class="m-1 max-w-[400px] mx-auto">
          <div class="mb-8">
            <h1 class="mb-4 text-3xl font-extrabold">
              User Ticket - Cancelation
            </h1>
            <p class="text-gray-600">User: {ticket.userName}</p>
            <p class="text-gray-600">Ticket ID: {ticket.ticketId}</p>
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
              on:click={sendEmail(ticket.userName, ticket.ticketId, ticket.email)}
              >Cancel</button
            >
          </div>
        </div>
      </div>
    </div>
  </div>
{/if}

<div class="flex flex-col border rounded-lg w-fit">
  <div class="flex gap-x-3 p-3 bg-muted">
    <div class="font-bold">Ticket #{ticket.ticketId}</div>
    {#if ticket.state == "active"}
      <div class="capitalize font-bold text-green-600">{ticket.state}</div>
    {:else}
      <div class="capitalize font-bold text-red-600">{ticket.state}</div>
    {/if}
  </div>
  <div class="flex flex-col p-3 gap-y-2">
    <div class="flex flex-col gap-y-1">
      <div class="flex">
        <div class="font-medium">Flight ID</div>
        {#if ticket.userName != null}
          <div class="ml-10">
            <button on:click={() => (isOpen = true)}><Settings /></button>
          </div>
        {/if}
      </div>

      <div class="text-sm text-muted-foreground">{ticket.flightId}</div>
    </div>
    <div class="flex flex-col gap-y-1">
      <div class="font-medium">Category</div>
      {#if ticket.type === "economy"}
        <div
          class="capitalize flex text-white font-bold items-center text-sm w-fit bg-green-600 p-2 rounded-lg"
        >
          {ticket.type}
          <Receipt class="h-4 w-4 ml-3" />
        </div>
      {:else}
        <div
          class="capitalize flex text-white font-bold items-center text-sm w-fit bg-yellow-600 p-2 rounded-lg"
        >
          {ticket.type}
          <Receipt class="h-4 w-4 ml-3" />
        </div>
      {/if}
    </div>
    <div class="flex flex-col gap-y-1">
      <div class="font-medium">Price</div>
      <div class="text-sm text-muted-foreground">{ticket.price} $</div>
    </div>
    <div class="flex flex-col gap-y-1">
      <div class="font-medium">Owned By</div>
      <div class="text-sm text-muted-foreground">
        {#if ticket.userName === null}
          Available
        {:else}
          {ticket.userName} #{ticket.userId}
        {/if}
      </div>
    </div>
  </div>
</div>

<style>
  .motivet{ 
    font-weight: bold;
  }
</style>
