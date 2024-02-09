<script lang="ts">
  import { Label } from "$lib/components/ui/label";
  import { Input } from "$lib/components/ui/input";
  import { Button } from "$lib/components/ui/button";
</script>

<div
  class="min-h-[calc(100vh-5rem)] bg-[url('$lib/assets/home-background.jpg')] bg-cover bg-fixed"
>
  <div class="container flex h-full p-8 justify-center items-center">
    <form
      class="flex flex-col gap-y-5 bg-background p-8 rounded-lg w-[34.125rem]"
    >
      <div class="flex flex-col">
        <div class="font-bold text-xl">Log In</div>
        <Button variant="link" class="p-0 self-baseline" href="/signin">
          Don't have an account? Sign In
        </Button>
      </div>
      <div class="flex flex-col gap-y-2">
        <Label for="email">Email</Label>
        <Input id="email" type="email" />
      </div>
      <div class="flex flex-col gap-y-2">
        <Label for="password">Password</Label>
        <Input id="password" type="password" />
      </div>
      <Button>Log In</Button>
    </form>
  </div>
</div>
