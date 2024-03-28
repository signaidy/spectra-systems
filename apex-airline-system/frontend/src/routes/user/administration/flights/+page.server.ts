export async function load({ locals }) {
  async function getAllFlights() {
    const response = await fetch("http://localhost:8080/get-all-flights", {
      method: "GET",
    });

    const result = await response.json();
    return result;
  }

  return { user: locals.user, flights: getAllFlights()};
}
