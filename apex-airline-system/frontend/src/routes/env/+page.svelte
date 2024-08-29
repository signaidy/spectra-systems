<script lang="ts">
  import { enhance } from "$app/forms";

  import { Label } from "$lib/components/ui/label";
  import { Input } from "$lib/components/ui/input";
  import { Button } from "$lib/components/ui/button";
  import { Loader2 } from "lucide-svelte";

  export let form;

  let loading = false;
</script>

<div
  class="min-h-[calc(100vh-5rem)] bg-[url('$lib/assets/home-background.jpg')] bg-cover bg-fixed"
>
  <div class="container flex h-full p-8 justify-center items-center">
    <div class="text-5xl font-bold underline bg-white p-3 rounded-lg">Apex Airline System</div>
    <div class="text-xl bg-white p-3 rounded-lg">No flights available.</div>
  </div>
</div>
