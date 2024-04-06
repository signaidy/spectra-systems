export function load({ locals, url }) {
  const userId = locals.user.userId;
  const user = locals.user;
  async function getUserReservations() {
    const response = await fetch(
      `http://localhost:42069/nexus/reservations/user/${userId}`,
      {
        method: "GET"
      }
    );
    const result = await response.json();
    console.log(result);
    return result;
  }

  return {
    user: user,
    reservations: getUserReservations(),
  };
}
