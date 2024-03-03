export function load({ locals }) {
  async function getCities() {
    const response = await fetch("http://localhost:8080/nexus/flights/avianca/cities", {
      method: "GET",
    });

    const result = await response.json();
    return result;
  }

  return {
    user: locals.user,
    cities: getCities(),
  };
}
