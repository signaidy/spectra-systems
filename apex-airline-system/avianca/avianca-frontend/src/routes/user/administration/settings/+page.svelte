<script lang="ts">
    import { MoveLeft } from "lucide-svelte";
  </script>

<div class="flex items-center mt-4 mb-8 gap-x-3">
    <a href="/user/administration"><MoveLeft /></a>
    <h1 class="text-xl font-bold">Page Settings</h1>
</div>
<div>
<a href="/user/administration/header">
    <button
    class="middle none center w-full rounded-lg bg-indigo-500 py-3 px-6 font-sans text-xs font-bold uppercase text-white shadow-md shadow-pink-500/20 transition-all hover:shadow-lg hover:shadow-pink-500/40 focus:opacity-[0.85] focus:shadow-none active:opacity-[0.85] active:shadow-none disabled:pointer-events-none disabled:opacity-50 disabled:shadow-none"
    data-ripple-light="true">
    Header
  </button>
</a>

<a href="/user/administration/aboutus">
    <button
    class="sp middle none center w-full rounded-lg bg-indigo-500 py-3 px-6 font-sans text-xs font-bold uppercase text-white shadow-md shadow-pink-500/20 transition-all hover:shadow-lg hover:shadow-pink-500/40 focus:opacity-[0.85] focus:shadow-none active:opacity-[0.85] active:shadow-none disabled:pointer-events-none disabled:opacity-50 disabled:shadow-none"
    data-ripple-light="true">
    About-us
  </button>
</a>
</div>

<style>
    .sp{
        margin-top: 10px;
    }
</style>
  