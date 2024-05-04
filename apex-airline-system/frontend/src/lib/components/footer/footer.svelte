<script lang="ts">
  import Section from "$lib/components/footer/section.svelte";
  import { Button } from "$lib/components/ui/button";
  import { Copyright } from "lucide-svelte";
  import { onMount } from "svelte";
  import { PUBLIC_BASE_URL } from '$env/static/public';

  let Logo; 
  let Text_Logo; 
  let footer = getFooterInformation();

  async function getFooterInformation() {
  const response = await fetch(`${PUBLIC_BASE_URL}/footer`);

  const footerdata = await response.json();
  aboutUsLinks.push(
    { name: footerdata.Section_1, href: footerdata.L1 },
    { name: footerdata.Section_2, href: footerdata.L2 },
    { name: footerdata.Section_3, href: footerdata.L3 },
    { name: footerdata.Section_4, href: footerdata.L4 },
    { name: footerdata.Section_5, href: footerdata.L5 },
    { name: footerdata.Section_6, href: footerdata.L6 },
  );

  quickLinks.push(
    { name: footerdata.Q_1, href: footerdata.Link_quick_1 },
    { name: footerdata.Q_2, href: footerdata.Link_quick_2 }
  );
  return footerdata;
  }

  const aboutUsLinks = [];

  const quickLinks = [];

onMount(async () => {
    const response = await fetch(`${PUBLIC_BASE_URL}/header`);
    const data = await response.json();
    Logo = data.Logo;
    Text_Logo = data.Text_Logo; 
  });
</script>

{#await footer}
<div class="flex items-center justify-center w-full h-full mt-10">
  <div
    class="flex justify-center items-center space-x-1 text-xl text-gray-700"
  >
    <svg
      fill="none"
      class="w-8 h-8 animate-spin"
      viewBox="0 0 32 32"
      xmlns="http://www.w3.org/2000/svg"
    >
      <path
        clip-rule="evenodd"
        d="M15.165 8.53a.5.5 0 01-.404.58A7 7 0 1023 16a.5.5 0 011 0 8 8 0 11-9.416-7.874.5.5 0 01.58.404z"
        fill="currentColor"
        fill-rule="evenodd"
      />
    </svg>
    <div>Loading ...</div>
  </div>
</div>
{:then datafoot}

<footer class="flex flex-col border-t">
  <div class="flex container flex-col p-8 gap-y-10">
    <div class="flex">
      <img src={Logo} alt="Logo" class="w-[55px] h-[50px]" /><p class="satisfy-regular">{Text_Logo}</p>
    </div>
    <div class="flex gap-x-20">
      <Section title={datafoot.Title_1}>
        {#each aboutUsLinks as item}
          <Button variant="link" class="p-0 justify-normal" href={item.href}>
            {item.name}
          </Button>
        {/each}
      </Section>
      <Section title={datafoot.Title_2}>
        {#each quickLinks as item}
          <Button variant="link" class="p-0 justify-normal" href={item.href}>
            {item.name}
          </Button>
        {/each}
      </Section>
      <Section title={datafoot.Title_3}>
        <Button variant="link" class="p-0 justify-normal">
          {datafoot.C_1}
        </Button>
        <Button variant="link" class="p-0 justify-normal">
          {datafoot.C_2}
        </Button>
      </Section>
    </div>
  </div>
  <div class="border-t">
    <div
      class="flex justify-center container py-3 px-8 items-center text-muted-foreground text-xs"
    >
      {datafoot.Copyright} <Copyright class="mx-1 w-3 h-3" />All rights reserved.
    </div>
  </div>
</footer>
{/await}

<style>
  .satisfy-regular {
  font-family: "Satisfy", cursive;
  font-weight: 400;
  font-size: 35px;
  margin-left: 10px;
  font-style: normal;
}
</style>