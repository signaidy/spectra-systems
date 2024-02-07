<script lang="ts">
  import LocationPicker from "$lib/components/searchBar/locationPicker.svelte";
  import DateRangePicker from "$lib/components/searchBar/dateRangePicker.svelte";
  import NumberPicker from "$lib/components/searchBar/numberPicker.svelte";
  import { Button } from "$lib/components/ui/button";
</script>

<div class="bg-white rounded-md p-3 shadow border flex gap-x-3 flex-wrap gap-y-3">
  <div class="flex flex-col gap-y-2">
    <div class="font-bold">From?</div>
    <LocationPicker />
  </div>
  <div class="flex flex-col gap-y-2">
    <div class="font-bold">To?</div>
    <LocationPicker />
  </div>
  <div class="flex flex-col gap-y-2">
    <div class="font-bold">When?</div>
    <DateRangePicker />
  </div>
  <div class="flex flex-col gap-y-2">
    <div class="font-bold">How Many?</div>
    <NumberPicker />
  </div>
  <div class="flex flex-col gap-y-2">
    <div class="text-background">Find</div>
    <Button>
      Find Flights
    </Button>
  </div>
</div>
