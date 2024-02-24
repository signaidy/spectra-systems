<script lang="ts">
  import logo from "$lib/assets/tetra.png";
  export let flight: CompleteFlight;
</script>

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
    <img src={logo} alt="Avianca Logo" class="w-[60px] self-center h-[60px]" />
    <div class="flex flex-col gap-y-2">
      <div class="flex flex-col gap-y-1">
        <div class="font-medium">Departure Date</div>
        <div class="text-sm text-muted-foreground">{flight.departureDate}</div>
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
      <div class="font-medium">Flight Details</div>
      <div>{flight.detail}</div>
    </div>
  </div>
</div>
