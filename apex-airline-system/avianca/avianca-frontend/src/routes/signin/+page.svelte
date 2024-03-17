<script lang="ts">
  import { enhance } from "$app/forms";
  import { Label } from "$lib/components/ui/label";
  import { Input } from "$lib/components/ui/input";
  import { Button } from "$lib/components/ui/button";
  import { Loader2 } from "lucide-svelte";
  import { onMount } from "svelte";
  export let form;
  let loading = false;

  // async function CreateUser() {
  //   const email = document.getElementById("email").value;
  //   const password = document.getElementById("password").value;
  //   const confirmedPassword = document.getElementById("confirmedPassword").value;
  //   const age = document.getElementById("age").value;
  //   const firstName = document.getElementById("firstName").value;
  //   const lastName = document.getElementById("lastName").value;
  //   const originCountry = document.getElementById("originCountry").value;
  //   const passportNumber = document.getElementById("passportNumber").value;

  //   if (password !== confirmedPassword) {
  //     return ({
  //       error: "Passwords do not match",
  //     });
  //   }
  //   try {
  //     const response = await fetch(`http://localhost:8080/create-user/${grecaptcha.getResponse()}`, {
  //       method: "POST",
  //       headers: {
  //         "Content-Type": "application/json",
  //       },
  //       body: JSON.stringify({
  //         email: email,
  //         password: password,
  //         firstName: firstName,
  //         lastName: lastName,
  //         originCountry: originCountry,
  //         passportNumber: passportNumber,
  //         age: age,
  //       }),
  //     });
  //   } catch (error) {
  //     console.error("API error:", error);
  //   }
  // }
</script>

<svelte:head>
  <script
    src="https://www.google.com/recaptcha/api.js"
    async
    defer
  >
  </script>
</svelte:head>

<div
  class="min-h-[calc(100vh-5rem)] bg-[url('$lib/assets/home-background.jpg')] bg-cover bg-fixed"
>
  <div class="flex container p-8 justify-center items-center h-full">
    <form
      method="POST"
      use:enhance={() => {
        loading = true;

        return async ({ update }) => {
          await update();
          const token = grecaptcha.getResponse();
          console.log(token);
          const formData = new FormData();
          formData.append("captcha", token); // Add token to form data
          loading = false;
        };
      }}
      class="flex flex-col gap-y-5 rounded-lg p-8 bg-background"
    >
      <div class="flex flex-col">
        <div class="flex items-center">
          <div class="font-bold text-xl">Sign In</div>
          <Loader2
            class="shrink-0 ml-3 h-4 w-4 animate-spin {!loading && 'hidden'}"
          />
        </div>
        <Button variant="link" class="p-0 self-baseline" href="/login">
          Already have an account? Log In
        </Button>
        {#if form?.error}
          <div class="text-sm text-red-500 font-medium">
            Error: {form.error}
          </div>
        {/if}
      </div>
      <div class="grid grid-cols-2">
        <div class="flex flex-col gap-y-5 border-r pr-5">
          <div class="flex flex-col gap-y-2">
            <Label for="email">Email</Label>
            <Input id="email" name="email" type="email" required />
          </div>
          <div class="flex flex-col gap-y-2">
            <Label for="password">Password</Label>
            <Input
              id="password"
              name="password"
              type="password"
              autocomplete="new-password"
              required
            />
          </div>
          <div class="flex flex-col gap-y-2">
            <Label for="confirmedPassword">Confirm Password</Label>
            <Input
              id="confirmedPassword"
              name="confirmedPassword"
              type="password"
              autocomplete="new-password"
              required
            />
          </div>
          <div class="flex flex-col gap-y-2">
            <Label for="age">Age</Label>
            <Input id="age" name="age" type="number" required />
          </div>
        </div>
        <div class="flex flex-col gap-y-5 pl-5">
          <div class="flex flex-col gap-y-2">
            <Label for="firstName">Names</Label>
            <Input id="firstName" name="firstName" required />
          </div>
          <div class="flex flex-col gap-y-2">
            <Label for="lastName">Last Names</Label>
            <Input id="lastName" name="lastName" required />
          </div>
          <div class="flex flex-col gap-y-2">
            <Label for="originCountry">Country of Origin</Label>
            <Input id="originCountry" name="originCountry" required />
          </div>
          <div class="flex flex-col gap-y-2">
            <Label for="passportNumber">Passport Number</Label>
            <Input
              id="passportNumber"
              name="passportNumber"
              type="number"
              required
            />
          </div>
        </div>
      </div>
      <div class="verification-container">
        <div class="flex flex-col gap-y-2 items-center">
          <div
            id ="cap"
            class="g-recaptcha"
            data-sitekey="6LfqapMpAAAAAHquHP9eo_lto-64CBjze61uwUVA"
          ></div>
        </div>
      </div>
      <!-- <p></p> -->
      <Button type="submit" disabled={loading} name="enviar">Sign In</Button>
    </form>
  </div>
</div>
