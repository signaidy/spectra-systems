<script lang="ts">
  import SearchBar from "$lib/components/searchBar/searchBar.svelte";
  import FeaturedFlights from "$lib/components/home/featuredFlights.svelte";
  import Footer from "$lib/components/footer/footer.svelte";
  import { onMount } from "svelte";
  import { PUBLIC_BASE_URL } from '$env/dynamic/public';

  export let data;

  let background; 

  onMount(async () => {
    fetch(`${PUBLIC_BASE_URL}/home`)
      .then((response) => response.json())
      .then((homeinformation) => {
        background = homeinformation.Background;
        console.log(background)
        return homeinformation;
      });
  });
</script>

<div class="h-[calc(100vh-5rem)] bg-cover" style="background-image: url({background});">
  <div class="flex flex-col container justify-center items-center h-full">
    <SearchBar cities={data.cities} />
  </div>
</div>
<FeaturedFlights />
<Footer />
