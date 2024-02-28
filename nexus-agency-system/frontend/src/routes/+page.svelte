<script lang="ts">
  import SearchBar from "$lib/components/searchBar/searchBar.svelte";
  import FeaturedFlights from "$lib/components/home/featuredFlights.svelte";
  import Footer from "$lib/components/footer/footer.svelte";

  export let data;
</script>

<div
  class="h-[calc(100vh-5rem)] bg-[url('$lib/assets/home-background.jpg')] bg-cover"
>
  <div class="flex flex-col container justify-center items-center h-full">
    <SearchBar cities={data.cities}/>
  </div>
</div>
<FeaturedFlights />
<Footer />
