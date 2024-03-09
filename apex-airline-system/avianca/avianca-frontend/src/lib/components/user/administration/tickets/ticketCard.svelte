<script lang="ts">
  import { Receipt } from "lucide-svelte";

  export let ticket: Ticket;
</script>

<div class="flex flex-col border rounded-lg w-fit">
  <div class="flex gap-x-3 p-3 bg-muted">
    <div class="font-bold">Ticket #{ticket.ticketId}</div>
    {#if ticket.state == 'active'}
    <div class="capitalize font-bold text-green-600">{ticket.state}</div>
    {:else}
    <div class="capitalize font-bold text-red-600">{ticket.state}</div>
    {/if}
  </div>
  <div class="flex flex-col p-3 gap-y-2">
    <div class="flex flex-col gap-y-1">
      <div class="font-medium">Flight ID</div>
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
