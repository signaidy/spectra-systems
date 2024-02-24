export async function load({ locals }) {
  async function getAllTickets() {
    const response = await fetch("http://localhost:8080/get-all-tickets", {
      method: "GET",
    });

    const result = await response.json();
    return result;
  }

  return { user: locals.user, tickets: getAllTickets() };
}
