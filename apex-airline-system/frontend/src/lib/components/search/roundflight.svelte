<script lang="ts">
  import { Pyramid } from "lucide-svelte";
  import FlightCardModal from "$lib/components/search/flightCardModal.svelte";
  import { page } from "$app/stores";
  export let flight: Flight;
  console.log(flight); 
  export let passengers: string | null;
  export let user: User;
  export let originCity: string | null;
  export let destinationCity: string | null;
  export let departureDate: string | null;
  export let type: string | null;
  export let form;
  export let isScaleFlight;

  let maindestination = $page.url.searchParams.get("maindestination");
  let phase : string | null = $page.url.searchParams.get("phase");
  let mainorigin = $page.url.searchParams.get("mainorigin");
  let f1 = $page.url.searchParams.get("f1");
  let c1 = $page.url.searchParams.get("c1");
  let f2 = $page.url.searchParams.get("f2");
  let c2 = $page.url.searchParams.get("c2");
  let f3 = $page.url.searchParams.get("f3");
  let c3 = $page.url.searchParams.get("c3");
  let returnDay = $page.url.searchParams.get("returnDay");
  console.log(returnDay); 
  console.log(typeof $page.url.searchParams.get("destinationCity"));
  console.log(typeof mainorigin);
  if (mainorigin === $page.url.searchParams.get("destinationCity")) {
    console.log("hola");
  }
</script>

<div class="rounded-lg my-3 grid grid-cols-2 bg-background shadow w-full">
  <!-- Left Container -->
  <div class="flex flex-col gap-y-5 p-5">
    <!-- Upper -->
    <div class="flex gap-x-3">
      <div class="flex flex-col">
        <div class="text-3xl font-bold">
          {flight.departureDate.split(" ")[1]}
        </div>
        <div class="text-muted-foreground">{flight.originCityName}</div>
      </div>
      <div class="flex flex-col justify-between w-96">
        <div class="flex items-center h-full">
          <hr class="grow" />
          <Pyramid class="mx-3 h-5 w-5" />
          <hr class="grow" />
        </div>
        {#if isScaleFlight == true}
          <div class="text-muted-foreground self-center">Scale</div>
        {:else}
          <div class="text-muted-foreground self-center">No stops</div>
        {/if}
      </div>
      <div class="flex flex-col">
        <div class="text-3xl font-bold">{flight.arrivalDate.split(" ")[1]}</div>
        <div class="text-muted-foreground">{flight.destinationCityName}</div>
      </div>
    </div>
    <!-- Lower -->
    <FlightCardModal {flight} {form} {user} />
  </div>
  <!-- Right Container -->
  <div class="flex p-5 gap-x-5 rounded-r-lg bg-muted">
    {#if Number(flight.touristQuantity) < Number(passengers)}
      <div
        class="flex flex-col border rounded-md p-3 w-1/2 gap-y-3 shadow bg-background"
      >
        <div class="text-sm text-muted-foreground">
          <div>Tourist</div>
          <div>{flight.touristQuantity} available</div>
        </div>
        <div class="text-3xl font-medium tracking-tighter">
          Not enough tickets available
        </div>
      </div>
    {:else if phase == "1"}
      <a
        href={`/search?originCity=${flight.destinationCityId}&destinationCity=${isScaleFlight ? maindestination : mainorigin}&departureDay=${isScaleFlight ? flight.arrivalDate.slice(0, 10) : returnDay}&passengers=${passengers}&type=${type}&phase=2
        &f1=${flight.flightId}&c1=economy&mainorigin=${mainorigin}&maindestination=${maindestination}&returnDay=${returnDay}`}
        class="flex flex-col border rounded-md p-3 w-1/2 gap-y-3 shadow bg-background"
      >
        <div class="text-sm text-muted-foreground">
          <div>Tourist</div>
          <div>{flight.touristQuantity} available</div>
        </div>
        <div class="text-3xl font-medium tracking-tighter">
          {flight.touristPrice} $
        </div>
      </a>
    {:else if phase.slice(0, 1) == "2"}
      <a
        href={mainorigin != String(flight.destinationCityId)
          ? `/search?originCity=${flight.destinationCityId}&destinationCity=${isScaleFlight == true ? flight.originCityId : mainorigin}&departureDay=${returnDay}&passengers=${passengers}&type=${type}&phase=3
        &f1=${f1}&c1=${c1}&f2=${flight.flightId}&c2=economy&mainorigin=${mainorigin}&maindestination=${maindestination}&returnDay=${returnDay}`
          : `/checkout?flightId=${flight.flightId}&passengers=${passengers}&category=economy&first_flightid=${f1}&category1=${c1}&type=${type}`}
        class="flex flex-col border rounded-md p-3 w-1/2 gap-y-3 shadow bg-background"
      >
        <div class="text-sm text-muted-foreground">
          <div>Tourist</div>
          <div>{flight.touristQuantity} available</div>
        </div>
        <div class="text-3xl font-medium tracking-tighter">
          {flight.touristPrice} $
        </div>
      </a>
    {:else if phase.slice(0, 1) == "3"}
      <a
        href={mainorigin != String(flight.destinationCityId)
          ? `/search?originCity=${flight.destinationCityId}&destinationCity=${mainorigin}&departureDay=${flight.arrivalDate.slice(0, 10)}&passengers=${passengers}&type=${type}&phase=4&f1=${f1}&c1=${c1}&f2=${f2}&c2=${c2}&f3=${flight.flightId}&c3=economy&mainorigin=${mainorigin}&maindestination=${maindestination}`
          : `/checkout?flightId=${flight.flightId}&passengers=${passengers}&category=economy&first_flightid=${f1}&category1=${c1}&second_flightid=${f2}&category2=${c2}&type=${type}`}
        class="flex flex-col border rounded-md p-3 w-1/2 gap-y-3 shadow bg-background"
      >
        <div class="text-sm text-muted-foreground">
          <div>Tourist</div>
          <div>{flight.touristQuantity} available</div>
        </div>
        <div class="text-3xl font-medium tracking-tighter">
          {flight.touristPrice} $
        </div>
      </a>
    {:else}
      <a
        href={`/checkout?flightId=${flight.flightId}&passengers=${passengers}&category=economy&first_flightid=${f1}&category1=${c1}&second_flightid=${f2}&category2=${c2}&third_flightid=${f3?.trim()}&category3=${c3}&type=${type}`}
        class="flex flex-col border rounded-md p-3 w-1/2 gap-y-3 shadow bg-background"
      >
        <div class="text-sm text-muted-foreground">
          <div>Tourist</div>
          <div>{flight.touristQuantity} available</div>
        </div>
        <div class="text-3xl font-medium tracking-tighter">
          {flight.touristPrice} $
        </div>
      </a>
    {/if}
    {#if Number(flight.businessQuantity) < Number(passengers)}
      <div
        class="flex flex-col border rounded-md p-3 w-1/2 gap-y-3 shadow bg-background"
      >
        <div class="text-sm text-muted-foreground">
          <div>Business</div>
          <div>{flight.businessQuantity} available</div>
        </div>
        <div class="text-3xl font-medium tracking-tighter">
          Not enough tickets available
        </div>
      </div>
    {:else if phase == "1"}
      <a
        href={`/search?originCity=${flight.destinationCityId}&destinationCity=${isScaleFlight ? maindestination : mainorigin}&departureDay=${isScaleFlight ? flight.arrivalDate.slice(0, 10) : returnDay}&passengers=${passengers}&type=${type}&phase=2
        &f1=${flight.flightId}&c1=premium&mainorigin=${mainorigin}&maindestination=${maindestination}&returnDay=${returnDay}`}
        class="flex flex-col border rounded-md p-3 w-1/2 gap-y-3 shadow bg-background"
      >
        <div class="text-sm text-muted-foreground">
          <div>Business</div>
          <div>{flight.businessQuantity} available</div>
        </div>
        <div class="text-3xl font-medium tracking-tighter">
          {flight.businessPrice} $
        </div>
      </a>
    {:else if phase.slice(0, 1) == "2"}
      <a
        href={mainorigin != String(flight.destinationCityId)
          ? `/search?originCity=${flight.destinationCityId}&destinationCity=${flight.originCityId}&departureDay=${returnDay}&passengers=${passengers}&type=${type}&phase=3
        &f1=${f1}&c1=${c1}&f2=${flight.flightId}&c2=premium&mainorigin=${mainorigin}&maindestination=${maindestination}`
          : `/checkout?flightId=${flight.flightId}&passengers=${passengers}&category=premium&first_flightid=${f1}&category1=${c1}&type=${type}`}
        class="flex flex-col border rounded-md p-3 w-1/2 gap-y-3 shadow bg-background"
      >
        <div class="text-sm text-muted-foreground">
          <div>Business</div>
          <div>{flight.businessQuantity} available</div>
        </div>
        <div class="text-3xl font-medium tracking-tighter">
          {flight.businessPrice} $
        </div>
      </a>
    {:else if phase.slice(0, 1) == "3"}
      <a
        href={mainorigin != String(flight.destinationCityId)
          ? `/search?originCity=${flight.destinationCityId}&destinationCity=${mainorigin}&departureDay=${flight.arrivalDate.slice(0, 10)}&passengers=${passengers}&type=${type}&phase=4&f1=${f1}&c1=${c1}&f2=${f2}&c2=${c2}&f3=${flight.flightId}&c3=premium&mainorigin=${mainorigin}&maindestination=${maindestination}`
          : `/checkout?flightId=${flight.flightId}&passengers=${passengers}&category=premium&first_flightid=${f1}&category1=${c1}&second_flightid=${f2}&category2=${c2}&type=${type}`}
        class="flex flex-col border rounded-md p-3 w-1/2 gap-y-3 shadow bg-background"
      >
        <div class="text-sm text-muted-foreground">
          <div>Business</div>
          <div>{flight.businessQuantity} available</div>
        </div>
        <div class="text-3xl font-medium tracking-tighter">
          {flight.businessPrice} $
        </div>
      </a>
    {:else}
      <a
      href={`/checkout?flightId=${flight.flightId}&passengers=${passengers}&category=premium&first_flightid=${f1}&category1=${c1}&second_flightid=${f2}&category2=${c2}&third_flightid=${f3?.trim()}&category3=${c3}&type=${type}`}
        class="flex flex-col border rounded-md p-3 w-1/2 gap-y-3 shadow bg-background"
      >
        <div class="text-sm text-muted-foreground">
          <div>Business</div>
          <div>{flight.businessQuantity} available</div>
        </div>
        <div class="text-3xl font-medium tracking-tighter">
          {flight.businessPrice} $
        </div>
      </a>
    {/if}
  </div>
</div>
