import { PUBLIC_BASE_URL } from '$env/static/public';

export function load({ locals }) {
  async function datagraph2() {
    const response = await fetch(`${PUBLIC_BASE_URL}/citysearchgraph`, {
      method: "GET",
    });

    const result = await response.json();
    return result;
  }



  return {
    user: locals.user,
    G2: datagraph2()
  };
}


