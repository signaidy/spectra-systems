<script lang="ts">
  import { UserRoundCog, Plane, TicketPercent, Settings } from "lucide-svelte";
  import { Button } from "$lib/components/ui/button";
</script>

<h1 class="text-xl font-bold mb-1">Administration</h1>
<div class="flex p-3 rounded-lg items-center justify-around border">
  <Button href="/user/administration/users" class="flex">
    <UserRoundCog class="mr-3 h-4 w-4 shrink-0" />
    <span>Users</span>
  </Button>
  <Button href="/user/administration/flights" class="flex">
    <Plane class="mr-3 h-4 w-4 shrink-0" />
    <span>Flights</span>
  </Button>
  <Button href="/user/administration/tickets" class="flex">
    <TicketPercent class="mr-3 h-4 w-4 shrink-0" />
    <span>Tickets</span>
  </Button>
  <Button href="#" class="flex">
    <Settings class="mr-3 h-4 w-4 shrink-0" />
    <span>Settings</span>
  </Button>
</div>
<slot />
