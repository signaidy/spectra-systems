<script lang="ts">
  import { enhance } from "$app/forms";
  import { Button } from "$lib/components/ui/button";
  import {
    LayoutDashboard,
    BedDouble,
    Table2,
    Bolt,
    WalletCards,
    AreaChart,
    LogOut,
    FileClock
  } from "lucide-svelte";

  export let user: User;

  const routes = [
    {
      value: "dashboard",
      label: "Dashboard",
      href: "/user/dashboard",
      icon: LayoutDashboard,
    },
    {
      value: "flights",
      label: "Flights",
      href: "/user/flights",
      icon: BedDouble,
    },
    {
      value: "shopping_history",
      label: "Shopping History",
      href: "/user/shopping_history",
      icon: FileClock,
    }
  ];

  const adminRoutes = [
    {
      value: "inventory",
      label: "Inventory",
      href: "/user/inventory",
      icon: Table2,
    },
    {
      value: "administration",
      label: "Administration",
      href: "/user/administration/users",
      icon: Bolt,
    },
    {
      value: "purchase-logs",
      label: "Purchase Logs",
      href: "/user/purchase-logs",
      icon: WalletCards,
    },
    {
      value: "analytics",
      label: "Analytics",
      href: "/user/analytics",
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
  {#if user.role === "admin" || user.role === "enterprise"}
    <div class="text-sm border-b-primary border-b font-medium pb-1">
      Administrator
    </div>
    {#each adminRoutes as adminRoute}
      <Button href={adminRoute.href} class="justify-normal">
        <svelte:component
          this={adminRoute.icon}
          class="shrink-0 mr-2 h-4 w-4"
        />
        {adminRoute.label}
      </Button>
    {/each}
  {/if}
  <form
    method="POST"
    action="administration?/logOut"
    class="flex flex-col"
    use:enhance
  >
    <Button class="justify-normal" type="submit">
      <LogOut class="shrink-0 mr-2 h-4 w-4" />Log Out
    </Button>
  </form>
</nav>
