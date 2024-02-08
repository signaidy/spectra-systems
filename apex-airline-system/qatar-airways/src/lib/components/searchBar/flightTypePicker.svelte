<script lang="ts">
  import { Label } from "$lib/components/ui/label";
  import * as RadioGroup from "$lib/components/ui/radio-group";
</script>

<div class="p-3">
  <div class="p-3 bg-background w-fit rounded-full">
    <RadioGroup.Root value="round-trip" class="flex gap-x-3">
      <div class="flex items-center gap-x-2">
        <RadioGroup.Item value="round-trip" id="round-trip" />
        <Label for="round-trip">Round Trip</Label>
      </div>
      <div class="flex items-center gap-x-2">
        <RadioGroup.Item value="one-way" id="one-way" />
        <Label for="one-way">One Way</Label>
      </div>
    </RadioGroup.Root>
  </div>
</div>
