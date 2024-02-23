export function load({ locals, url }) {
  // async function getCities() {
  //   const response = await fetch("http://localhost:8080/get-cities", {
  //     method: "GET",
  //   });

  //   const result = await response.json();
  //   return result;
  // }
  console.log(url);
  return {
    user: locals.user,
    //   cities: getCities(),
  };
}
