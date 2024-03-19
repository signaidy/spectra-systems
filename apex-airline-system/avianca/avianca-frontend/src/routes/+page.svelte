<script lang="ts">
  import SearchBar from "$lib/components/searchBar/searchBar.svelte";
  import FeaturedFlights from "$lib/components/home/featuredFlights.svelte";
  import Footer from "$lib/components/footer/footer.svelte";
  import { onMount } from "svelte";
  export let data;

  let background;

  onMount(async () => {
    fetch("http://localhost:8080/home")
      .then((response) => response.json())
      .then((homeinformation) => {
        background = homeinformation.Background;
        return homeinformation;
      });
  });
</script>

<div class="h-[calc(100vh-5rem)] bg-[url({background})] bg-cover">
  <div class="flex flex-col container justify-center items-center h-full">
    <SearchBar cities={data.cities} />
  </div>
</div>
<FeaturedFlights />
<Footer />
