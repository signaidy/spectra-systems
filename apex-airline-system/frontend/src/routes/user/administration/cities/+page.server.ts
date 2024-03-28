export async function load({ locals }) {
    async function getAllcities() {
      const response = await fetch("http://localhost:8080/get-cities", {
        method: "GET",
      });
  
      const result = await response.json();
      return result;
    }
    console.log(getAllcities); 
    return { user: locals.user, cities: getAllcities()};
  }
  