<script lang="ts">
  import { Button } from "$lib/components/ui/button";
  import { CircleUserRound } from "lucide-svelte";
  import logo from "$lib/assets/logo.png";
</script>

<header class="h-20 border-b">
  <div class="flex h-full container justify-between items-center">
    <a href="/">
      <img src={logo} alt="Avianca Logo" />
    </a>
    <nav class="flex gap-x-3 text-sm font-bold">
      <Button href="/administration" variant="ghost" class="font-bold">User Dashboard</Button>
    </nav>
    <Button href="/signin">Join | Sign In <CircleUserRound class="ml-2" /></Button>
  </div>
</header>
