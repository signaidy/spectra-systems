<script lang="ts">
  import UserInfo from "$lib/components/user/dashboard/userInfo.svelte";
  import messi from "$lib/assets/messi.jpg";

  const userInfo = [
    { key: "Full Name", value: "John Doe" },
    { key: "Email", value: "johndoe@gmail.com" },
    { key: "Password", value: "password123" },
    { key: "Age", value: "23" },
    { key: "Country of Origin", value: "United States" },
    { key: "Passport Number", value: "18283901" },
  ];
</script>

<h1 class="text-xl font-bold">Dashboard</h1>
<div class="grid grid-cols-2 mt-8 gap-x-8">
  <div class="flex flex-col gap-y-3">
    <div class="font-bold">Profile</div>
    <div class="flex flex-col gap-y-2 border rounded-lg p-3">
      <img
        src={messi}
        alt="User Profile"
        class="rounded-full w-[4.5rem] h-[4.5rem] self-center object-cover animate-spin"
      />
      {#each userInfo as user}
        <UserInfo key={user.key} value={user.value} />
      {/each}
    </div>
  </div>
  <div class="flex flex-col gap-y-3">
    <div class="font-bold">Flights Overview</div>
    <div class="flex flex-col gap-y-2 border rounded-lg p-3">
      <div class="text-sm font-medium">No flights booked</div>
    </div>
  </div>
</div>
