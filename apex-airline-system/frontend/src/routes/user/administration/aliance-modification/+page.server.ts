import { PUBLIC_BASE_URL } from '$env/static/public';

export async function load({ locals }) {
    async function getAliances() {
      const response = await fetch(`${PUBLIC_BASE_URL}/getAliances`, {
        method: "GET",
      });
  
      const result = await response.json();
      return result;
    }
    return { user: locals.user, aliances: getAliances()};
  }
  