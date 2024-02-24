export async function load({ fetch }) {
    const response = await fetch("http://localhost:8080/aboutus");
    const aboutus = await response.json();
    return {
       aboutus,
    };
  }
 