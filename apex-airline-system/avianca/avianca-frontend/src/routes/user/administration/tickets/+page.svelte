<script lang="ts">
  import { MoveLeft } from "lucide-svelte";
  import TicketCard from "$lib/components/user/administration/tickets/ticketCard.svelte";
  import TicketCardSkeleton from "$lib/components/user/administration/tickets/ticketCardSkeleton.svelte";
  export let data;
  console.log(data)
</script>

<div class="flex items-center mt-4 mb-8 gap-x-3">
  <a href="/user/administration"><MoveLeft /></a>
  <h1 class="text-xl font-bold">Tickets Administration</h1>
</div>
<div class="flex flex-wrap gap-x-2 gap-y-2">
  {#await data.tickets}
    {#each Array(10).fill(0) as _}
      <TicketCardSkeleton />
    {/each}
  {:then tickets}
    {#each tickets as ticket}
      <TicketCard {ticket} />
    {/each}
  {:catch error}
    <p>{error.message}</p>
  {/await}
</div>
