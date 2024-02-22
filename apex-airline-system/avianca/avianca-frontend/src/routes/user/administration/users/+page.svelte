<script lang="ts">
  import UserCard from "$lib/components/user/users/UserCard.svelte";
  import {MoveLeft} from 'lucide-svelte';

  export let data;
  export let form;
</script>

<a href="/user/administration"><MoveLeft/></a>
<h1 class="text-xl font-bold mt-4 mb-8">Users Administration</h1>
<div class="flex flex-col gap-y-3">
  {#each data.users as user}
    <UserCard {form} user={user} />
  {/each}
</div>
