import { PUBLIC_BASE_URL } from '$env/dynamic/public';

export async function load({ locals }) {
  async function getAllTickets() {
    const response = await fetch(`${PUBLIC_BASE_URL}/get-all-tickets`, {
      method: "GET",
    });

    const result = await response.json();
    return result;
  }

  return { user: locals.user, tickets: getAllTickets()};
}
