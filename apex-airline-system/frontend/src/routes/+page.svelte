<script>
	import { goto } from '$app/navigation';
  
	async function handleRedirect() {
	  await goto('/home'); 
	}
  </script>
  
  {#await handleRedirect() then}
	<p>Redirecting...</p>
  {/await}
  