<script lang="ts">
  import UserCard from "$lib/components/user/users/UserCard.svelte";

  export let data;
  export let form;
</script>

<h1 class="text-xl font-bold mb-8">Users Administration</h1>
<div class="flex flex-col gap-y-3">
  {#each data.users as user}
    <UserCard {form} user={user} />
  {/each}
</div>
