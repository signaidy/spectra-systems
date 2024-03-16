export async function load({ fetch }) {
    const response = await fetch("http://localhost:8080/partners");
    const partners = await response.json();
    return {
       partners,
    };
  }