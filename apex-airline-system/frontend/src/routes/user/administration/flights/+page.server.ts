import { PUBLIC_BASE_URL } from '$env/dynamic/public';

export async function load({ locals }) {
  async function getAllFlights() {
    const response = await fetch(`${PUBLIC_BASE_URL}/get-all-flights`, {
      method: "GET",
    });

    const result = await response.json();
    return result;
  }

  return { user: locals.user, flights: getAllFlights()};
}
