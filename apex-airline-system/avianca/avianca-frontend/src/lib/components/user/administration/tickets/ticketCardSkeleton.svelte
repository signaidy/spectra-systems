<script lang="ts">
  import { Receipt } from "lucide-svelte";
</script>

<div class="flex flex-col border rounded-lg w-fit animate-pulse bg-muted">
  <div class="flex gap-x-3 p-3 bg-muted items-center">
    <div class="font-bold">Ticket #</div>
    <div class="rounded-lg bg-muted-foreground h-2 w-16" />
  </div>
  <div class="flex flex-col p-3 gap-y-7">
    <div class="flex flex-col gap-y-1">
      <div class="font-medium">Flight ID</div>
      <div class="rounded-lg bg-muted-foreground h-2 w-20" />
    </div>
    <div class="flex flex-col gap-y-1">
      <div class="font-medium">Category</div>
      <div class="rounded-lg bg-muted-foreground h-2 w-20" />
    </div>
    <div class="flex flex-col gap-y-1">
      <div class="font-medium">Price</div>
      <div class="rounded-lg bg-muted-foreground h-2 w-20" />
    </div>
    <div class="flex flex-col gap-y-1">
      <div class="font-medium">Owned By</div>
      <div class="rounded-lg bg-muted-foreground h-2 w-20" />
    </div>
  </div>
</div>
