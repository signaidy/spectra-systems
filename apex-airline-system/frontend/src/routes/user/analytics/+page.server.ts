import { PUBLIC_BASE_URL } from '$env/static/public';

export function load({ locals }) {
  async function datagraph2() {
    const response = await fetch(`${PUBLIC_BASE_URL}/citysearchgraph`, {
      method: "GET",
    });

    const result = await response.json();
    return result;
  }

  async function datagraph1() {
    const response = await fetch(`${PUBLIC_BASE_URL}/typesearch`, {
      method: "GET",
    });

    const result = await response.json();
    return result;
  }

  async function datagraph3() {
    const response = await fetch(`${PUBLIC_BASE_URL}/userspurchasedata`, {
      method: "GET",
    });

    const result = await response.json();
    return result;
  }



  return {
    user: locals.user,
    G1: datagraph1(), 
    G2: datagraph2(),
    G3: datagraph3()
  };
}


