<script lang="ts">
  import { Label } from "$lib/components/ui/label";
  import { Input } from "$lib/components/ui/input";
  import { Button } from "$lib/components/ui/button";
</script>

<div
  class="min-h-[calc(100vh-5rem)] bg-[url('$lib/assets/home-background.jpg')] bg-cover bg-fixed"
>
  <div class="flex container p-8 justify-center items-center h-full">
    <form class="flex flex-col gap-y-5 rounded-lg p-8 bg-background">
      <div class="flex flex-col">
        <div class="font-bold text-xl">Sign In</div>
        <Button variant="link" class="p-0 self-baseline" href="/login">Already have an account? Log In</Button>
      </div>
      <div class="grid grid-cols-2 gap-x-10">
        <div class="flex flex-col gap-y-5">
          <div class="flex flex-col gap-y-2">
            <Label for="email">Email</Label>
            <Input id="email" type="email" />
          </div>
          <div class="flex flex-col gap-y-2">
            <Label for="names">Names</Label>
            <Input id="names" />
          </div>
          <div class="flex flex-col gap-y-2">
            <Label for="last-names">Last Names</Label>
            <Input id="last-names" />
          </div>
          <div class="flex flex-col gap-y-2">
            <Label for="age">Age</Label>
            <Input id="age" type="number" />
          </div>
        </div>
        <div class="flex flex-col gap-y-5">
          <div class="flex flex-col gap-y-2">
            <Label for="origin-country">Country of Origin</Label>
            <Input id="origin-country" />
          </div>
          <div class="flex flex-col gap-y-2">
            <Label for="passport-number">Passport Number</Label>
            <Input id="passport-number" />
          </div>
          <div class="flex flex-col gap-y-2">
            <Label for="password">Password</Label>
            <Input id="password" type="password" />
          </div>
          <div class="flex flex-col gap-y-2">
            <Label for="captcha">Captcha</Label>
            <Input id="captcha" />
          </div>
        </div>
      </div>
      <Button>Sign In</Button>
    </form>
  </div>
</div>
