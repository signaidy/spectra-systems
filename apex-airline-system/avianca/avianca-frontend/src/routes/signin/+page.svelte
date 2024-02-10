<script lang="ts">
  import { Label } from "$lib/components/ui/label";
  import { Input } from "$lib/components/ui/input";
  import { Button } from "$lib/components/ui/button";

  let email: string;
  let password: string;
  let firstName: string;
  let lastName: string;
  let age: string;
  let originCountry: string;
  let passportNumber: string;

  async function handleSubmit() {
    const result = await fetch("http://localhost:8080/create-user", {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify({
        email,
        password,
        firstName,
        lastName,
        age,
        originCountry,
        passportNumber,
      }),
    });
  }
</script>

<div
  class="min-h-[calc(100vh-5rem)] bg-[url('$lib/assets/home-background.jpg')] bg-cover bg-fixed"
>
  <div class="flex container p-8 justify-center items-center h-full">
    <form
      on:submit|preventDefault={handleSubmit}
      class="flex flex-col gap-y-5 rounded-lg p-8 bg-background"
    >
      <div class="flex flex-col">
        <div class="font-bold text-xl">Sign In</div>
        <Button variant="link" class="p-0 self-baseline" href="/login"
          >Already have an account? Log In</Button
        >
      </div>
      <div class="grid grid-cols-2 gap-x-10">
        <div class="flex flex-col gap-y-5">
          <div class="flex flex-col gap-y-2">
            <Label for="email">Email</Label>
            <Input bind:value={email} id="email" type="email" required />
          </div>
          <div class="flex flex-col gap-y-2">
            <Label for="firstName">Names</Label>
            <Input bind:value={firstName} id="firstName" required />
          </div>
          <div class="flex flex-col gap-y-2">
            <Label for="lastName">Last Names</Label>
            <Input bind:value={lastName} id="lastName" required />
          </div>
          <div class="flex flex-col gap-y-2">
            <Label for="age">Age</Label>
            <Input bind:value={age} id="age" type="number" required />
          </div>
        </div>
        <div class="flex flex-col gap-y-5">
          <div class="flex flex-col gap-y-2">
            <Label for="originCountry">Country of Origin</Label>
            <Input bind:value={originCountry} id="originCountry" required />
          </div>
          <div class="flex flex-col gap-y-2">
            <Label for="passportNumber">Passport Number</Label>
            <Input
              bind:value={passportNumber}
              id="passportNumber"
              type="number"
              required
            />
          </div>
          <div class="flex flex-col gap-y-2">
            <Label for="password">Password</Label>
            <Input
              bind:value={password}
              id="password"
              type="password"
              required
            />
          </div>
          <div class="flex flex-col gap-y-2">
            <Label for="captcha">Captcha</Label>
            <Input id="captcha" />
          </div>
        </div>
      </div>
      <Button type="submit">Sign In</Button>
    </form>
  </div>
</div>
