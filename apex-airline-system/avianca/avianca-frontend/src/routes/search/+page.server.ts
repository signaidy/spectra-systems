export function load({ locals, url }) {
  async function getOneWayFlights() {
    const response = await fetch(
      `http://localhost:8080/get-one-way-flights?${url.searchParams.toString()}`,
      {
        method: "GET",
      }
    );

    const result = await response.json();
    return result;
  }

  async function getCities() {
    const response = await fetch("http://localhost:8080/get-cities", {
      method: "GET",
    });

    const result = await response.json();
    return result;
  }

  return {
    user: locals.user,
    cities: getCities(),
    flights: getOneWayFlights(),
  };
}
