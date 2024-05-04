<script lang="ts">
  import AlianceCard from "$lib/components/user/administration/aliance/alianceCard.svelte";
  export let data;

</script>

{#await data.aliances}
<p>Loading</p>
{:then aliance}
  {#each aliance as aliance}
    <AlianceCard {aliance} />
  {/each}
{:catch error}
  <p>{error.message}</p>
{/await}

<style>
  .dspl {
    margin-right: 85px;
  }
</style>
