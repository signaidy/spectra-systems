<script lang="ts">
  import NavBar from "$lib/components/user/navBar.svelte";
</script>

<div class="flex container h-[calc(100vh-5rem)] overflow-y-auto">
  <NavBar />
  <div class="flex flex-col py-8 pl-8">
    <slot />
  </div>
</div>
<slot />
