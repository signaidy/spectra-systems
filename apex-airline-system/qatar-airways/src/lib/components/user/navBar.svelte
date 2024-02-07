<script lang="ts">
  import { Button } from "$lib/components/ui/button";
  import {
    LayoutDashboard,
    BedDouble,
    Table2,
    Bolt,
    WalletCards,
    AreaChart,
  } from "lucide-svelte";

  const routes = [
    {
      value: "dashboard",
      label: "Dashboard",
      href: "/dashboard",
      icon: LayoutDashboard,
    },
    {
      value: "flights",
      label: "Flights",
      href: "/reservations",
      icon: BedDouble,
    },
    {
      value: "inventory",
      label: "Inventory",
      href: "/inventory",
      icon: Table2,
    },
    {
      value: "administration",
      label: "Administration",
      href: "/administration",
      icon: Bolt,
    },
    {
      value: "purchase-logs",
      label: "Purchase Logs",
      href: "/purchase-logs",
      icon: WalletCards,
    },
    {
      value: "analytics",
      label: "Analytics",
      href: "/analytics",
      icon: AreaChart,
    },
  ];
</script>

<nav
  class="flex flex-col gap-y-3 py-8 pr-8 border-r sticky top-0 overflow-y-auto"
>
  {#each routes as route}
    <Button href={route.href} class="justify-normal">
      <svelte:component this={route.icon} class="shrink-0 mr-2 h-4 w-4" />
      {route.label}
    </Button>
  {/each}
</nav>
