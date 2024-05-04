import { PUBLIC_BASE_URL } from '$env/static/public';

export function load({ locals }) {
  async function getCities() {
    const response = await fetch(`${PUBLIC_BASE_URL}/get-cities`, {
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
