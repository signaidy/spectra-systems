export async function load({ fetch }) {
    const response = await fetch("http://localhost:8080/footer");
    const footer = await response.json();
    return {
       footer,
    };
  }