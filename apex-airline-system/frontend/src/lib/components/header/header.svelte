<script lang="ts">
  import { Button } from "$lib/components/ui/button";
  import { CircleUserRound } from "lucide-svelte";
  import logo from "$lib/assets/logo.png";
  import imageTab from "$lib/assets/Image-Tab.png";

  export let data: { user: User | null };

  let headerdata = getHeaderInformation();

async function getHeaderInformation() {
  const response = await fetch("http://localhost:8080/header");
  const headerdata = await response.json();
  return headerdata;
}
</script>

<link class="tabimage" rel="icon" type="image/png" href={imageTab}/> 
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Satisfy&display=swap" rel="stylesheet">

{#await headerdata}
<header class="h-20 border-b shadow-md p-4 animate-pulse">
  <div class="flex h-full container justify-between items-center bg-gray-300 rounded mb-2">
    <a href="/">
      <div class="flex">
    </div>
    </a>
    <nav class="flex gap-x-3 text-sm font-bold">
  </div>
</header>

{:then datahead}
<header class="h-20 border-b">
  <div class="flex h-full container justify-between items-center">
    <a href="/">
      <div class="flex">
      <img src={datahead.Logo} alt="Avianca Logo" class="w-[55px] h-[50px]" /><p class="satisfy-regular">{datahead.Text_Logo}</p>
    </div>
    </a>
    <nav class="flex gap-x-3 text-sm font-bold">
      <Button href={datahead.Link_Section} variant="ghost" class="font-bold">{datahead.Section}</Button>
    </nav>
    {#if data.user}
      <Button href={datahead.Link_Profile} variant="ghost" class="font-bold">
        <CircleUserRound class="mr-2" />{data.user.firstName +
          " " +
          data.user.lastName}
      </Button>
    {:else}
      <Button href={datahead.Link_Login}>
        Join | Sign In <CircleUserRound class="ml-2" />
      </Button>
    {/if}
  </div>
</header>
{/await}

<style>
  .satisfy-regular {
  font-family: "Satisfy", cursive;
  font-weight: 400;
  font-size: 35px;
  margin-left: 10px;
  font-style: normal;
}
</style>