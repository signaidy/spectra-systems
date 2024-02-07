<script lang="ts">
  import SearchBar from "$lib/components/searchBar/searchBar.svelte";
  import { Button } from "$lib/components/ui/button";
  import { ArrowRight, Pyramid } from "lucide-svelte";
</script>

<div
  class="min-h-[calc(100vh-5rem)] bg-[url('$lib/assets/home-background.jpg')] bg-cover bg-fixed"
>
  <div class="flex flex-col container items-center p-8 h-full">
    <SearchBar />
    <div class="rounded-lg my-10 grid grid-cols-2 bg-background shadow w-full">
      <!-- Left Container -->
      <div class="flex flex-col gap-y-5 p-5">
        <!-- Upper -->
        <div class="flex gap-x-3">
          <div class="flex flex-col">
            <div class="text-3xl font-bold">15:20</div>
            <div class="text-muted-foreground">BCN</div>
          </div>
          <div class="flex flex-col justify-between w-96">
            <div class="flex items-center h-full">
              <hr class="grow" />
              <Pyramid class="mx-3 h-5 w-5" />
              <hr class="grow" />
            </div>
            <div class="text-muted-foreground self-center">1 stop</div>
          </div>
          <div class="flex flex-col">
            <div class="text-3xl font-bold">06:20</div>
            <div class="text-muted-foreground">CDG</div>
          </div>
        </div>
        <!-- Lower -->
        <Button variant="link" class="group p-0 self-baseline">
          Flight Details
          <ArrowRight
            class="shrink-0 ml-2 h-4 w-4 transition-transform group-hover:-rotate-45"
          />
        </Button>
      </div>
      <!-- Right Container -->
      <div class="flex p-5 gap-x-5">
        <div class="flex flex-col border rounded-md p-3 w-1/2 gap-y-3 shadow">
          <div class="text-sm text-muted-foreground">Tourist</div>
          <div class="text-3xl font-medium tracking-tighter">500 $</div>
        </div>
        <div class="flex flex-col border rounded-md p-3 w-1/2 gap-y-3 shadow">
          <div class="text-sm text-muted-foreground">Business</div>
          <div class="text-3xl font-medium tracking-tighter">1000 $</div>
        </div>
      </div>
    </div>
  </div>
</div>
