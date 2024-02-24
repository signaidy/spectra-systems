<script lang="ts">
  import { Button } from "$lib/components/ui/button";
  import { MoveLeft } from "lucide-svelte";
</script>

<div class="flex items-center mt-4 mb-8 gap-x-3">
  <a href="/user/administration"><MoveLeft /></a>
  <h1 class="text-xl font-bold">Flights Administration</h1>
</div>
<Button href="/user/administration/create-flight">Create Flight</Button>
