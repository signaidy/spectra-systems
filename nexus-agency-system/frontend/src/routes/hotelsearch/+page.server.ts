import { fail, redirect } from "@sveltejs/kit";

export function load({ locals, url }) {
    async function getCitiesHotels() {
        const response = await fetch("http://localhost:42069/nexus/reservations/cities", {
          method: "GET",
        });
    
        const result = await response.json();
        return result;
      }

    async function getHotels() {
        const response = await fetch(
          `http://localhost:42069/nexus/reservations/hotelsearch?${url.searchParams.toString()}`,
          {
            method: "GET"
          }
        );
    
        const result = await response.json();
        return result;
      }

  return {
    user: locals.user,
    citieshotels: getCitiesHotels(),
    hotels: getHotels()
  };
}
