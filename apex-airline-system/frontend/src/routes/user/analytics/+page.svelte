<script lang="ts">
  import Graphics from "$lib/components/Graphics/+page.svelte";

  let selection = "";

</script>

<div class="flex items-center mt-4 mb-8 gap-x-3">
  <h1 class="text-xl font-bold">Analytics</h1>
</div>
<div class="items-center text-center mb-10">
  <button
    class="middle none center rounded-lg bg-indigo-500 py-3 px-6 font-sans text-xs font-bold uppercase text-white shadow-md shadow-pink-500/20 transition-all hover:shadow-lg hover:shadow-pink-500/40 focus:opacity-[0.85] focus:shadow-none active:opacity-[0.85] active:shadow-none disabled:pointer-events-none disabled:opacity-50 disabled:shadow-none"
    data-ripple-light="true"
    on:click={() => selection = "Graphics"}
  >
    Graphics
  </button>
  <button
    class="middle none center rounded-lg bg-indigo-500 py-3 px-6 font-sans text-xs font-bold uppercase text-white shadow-md shadow-pink-500/20 transition-all hover:shadow-lg hover:shadow-pink-500/40 focus:opacity-[0.85] focus:shadow-none active:opacity-[0.85] active:shadow-none disabled:pointer-events-none disabled:opacity-50 disabled:shadow-none"
    data-ripple-light="false"
    on:click={() => selection = "List"}
  >
    List
  </button>
</div>
{#if selection == "Graphics"}
<Graphics/>
{:else}
<p>LIST HERE</p>
{/if}
