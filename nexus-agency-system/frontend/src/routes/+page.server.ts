export function load({ locals }) {
  async function getCities() {
    const response = await fetch("http://localhost:42069/nexus/flights/avianca/cities", {
      method: "GET",
    });

    const result = await response.json();
    return result;
  }

  async function getCitiesHotels() {
    const response = await fetch("http://localhost:42069/nexus/reservations/cities", {
      method: "GET",
    });

    const result = await response.json();
    return result;
  }

  return {
    user: locals.user,
    cities: getCities(),
    citieshotels: getCitiesHotels()
  };
}
