import { authenticateToken } from "$lib/server/auth";
import { redirect, type Handle } from "@sveltejs/kit";

export const handle: Handle = async ({ event, resolve }) => {
  console.log("handle");
  // Stage 1
  event.locals.user = authenticateToken(event);

  if (event.url.pathname.startsWith("/user")) {
    if (!event.locals.user) {
      throw redirect(303, "/login");
    }
    if (
      event.url.pathname.startsWith("/user/inventory") ||
      event.url.pathname.startsWith("/user/administration") ||
      event.url.pathname.startsWith("/user/purchase-logs") ||
      event.url.pathname.startsWith("/user/analytics")
    ) {
      if (event.locals.user.role !== "admin") {
        throw redirect(303, "/login");
      }
    }
  }

  // Stage 2
  const response = await resolve(event);

  //Stage 3
  return response;
};
