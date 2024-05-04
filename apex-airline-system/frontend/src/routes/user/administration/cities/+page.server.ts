import { PUBLIC_BASE_URL } from '$env/dynamic/public';

export async function load({ locals }) {
    async function getAllcities() {
      const response = await fetch(`${PUBLIC_BASE_URL}/get-cities`, {
        method: "GET",
      });
  
      const result = await response.json();
      return result;
    }
    console.log(getAllcities); 
    return { user: locals.user, cities: getAllcities()};
  }
  