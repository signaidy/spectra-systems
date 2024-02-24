<script lang="ts">
  import { Button } from "$lib/components/ui/button";
  import { ArrowRight, Pyramid } from "lucide-svelte";

  export let flight: Flight;
</script>

<div class="rounded-lg my-3 grid grid-cols-2 bg-background shadow w-full">
  <!-- Left Container -->
  <div class="flex flex-col gap-y-5 p-5">
    <!-- Upper -->
    <div class="flex gap-x-3">
      <div class="flex flex-col">
        <div class="text-3xl font-bold">{flight.departureDate.split(" ")[1]}</div>
        <div class="text-muted-foreground">{flight.originCityName}</div>
      </div>
      <div class="flex flex-col justify-between w-96">
        <div class="flex items-center h-full">
          <hr class="grow" />
          <Pyramid class="mx-3 h-5 w-5" />
          <hr class="grow" />
        </div>
        <div class="text-muted-foreground self-center">1 stop, 15h</div>
      </div>
      <div class="flex flex-col">
        <div class="text-3xl font-bold">{flight.arrivalDate.split(" ")[1]}</div>
        <div class="text-muted-foreground">{flight.destinationCityName}</div>
      </div>
    </div>
    <!-- Lower -->
    <Button variant="link" class="group p-0 self-baseline">
      Flight Details
      <ArrowRight
        class="shrink-0 ml-2 h-4 w-4 transition-transform group-hover:-rotate-45"
      />
    </Button>
  </div>
  <!-- Right Container -->
  <div class="flex p-5 gap-x-5 rounded-r-lg bg-muted">
    <div
      class="flex flex-col border rounded-md p-3 w-1/2 gap-y-3 shadow bg-background"
    >
    <div class="text-sm text-muted-foreground">
      <div>Tourist</div>
      <div>{flight.touristQuantity} available</div>
    </div>
      <div class="text-3xl font-medium tracking-tighter">{flight.touristPrice} $</div>
    </div>
    <div
      class="flex flex-col border rounded-md p-3 w-1/2 gap-y-3 shadow bg-background"
    >
    <div class="text-sm text-muted-foreground">
      <div>Business</div>
      <div>{flight.businessQuantity} available</div>
    </div>
      <div class="text-3xl font-medium tracking-tighter">{flight.businessPrice} $</div>
    </div>
  </div>
</div>
