<script lang="ts">
  import Section from "$lib/components/footer/section.svelte";
  import { Button } from "$lib/components/ui/button";
  import { Copyright } from "lucide-svelte";
  import logo from "$lib/assets/logo.png";

  const aboutUsLinks = [
    { name: "We are avianca", href: "/aboutus" },
    { name: "Destinations", href: "/" },
    { name: "Boarding Rules", href: "/" },
    { name: "Seat Types", href: "/" },
    { name: "Carriage", href: "/" },
    { name: "Partners", href: "/" },
  ];

  const quickLinks = [
    { name: "Legal Information", href: "/" },
    { name: "Privacy Policy", href: "/" },
  ];
</script>

<footer class="flex flex-col border-t">
  <div class="flex container flex-col p-8 gap-y-10">
    <img src={logo} alt="Logo" class="w-[202px] h-[60px]" />
    <div class="flex gap-x-20">
      <Section title="About Us">
        {#each aboutUsLinks as item}
          <Button variant="link" class="p-0 justify-normal" href={item.href}>
            {item.name}
          </Button>
        {/each}
      </Section>
      <Section title="Quick Links">
        {#each quickLinks as item}
          <Button variant="link" class="p-0 justify-normal" href={item.href}>
            {item.name}
          </Button>
        {/each}
      </Section>
      <Section title="Contact Us">
        <Button variant="link" class="p-0 justify-normal">
          Carretera a Fraijanes, Finca Santa Isabel, Fraijanes, Guatemala
        </Button>
        <Button variant="link" class="p-0 justify-normal">
          +502 6665-3700
        </Button>
      </Section>
    </div>
  </div>
  <div class="border-t">
    <div
      class="flex container py-3 px-8 items-center text-muted-foreground text-xs"
    >
      Avianca 2024 <Copyright class="mx-1 w-3 h-3" />All rights reserved.
    </div>
  </div>
</footer>
