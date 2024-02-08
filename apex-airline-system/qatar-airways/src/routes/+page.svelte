<script lang="ts">
  import SearchBar from "$lib/components/searchBar/searchBar.svelte";
</script>

<div
  class="h-[calc(100vh-5rem)] bg-[url('$lib/assets/home-background.jpg')] bg-cover"
>
  <div class="flex flex-col container justify-center items-center h-full">
    <SearchBar />
  </div>
</div>
