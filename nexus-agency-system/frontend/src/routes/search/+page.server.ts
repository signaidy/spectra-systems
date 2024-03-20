import { fail } from "@sveltejs/kit";

export function load({ locals, url }) {
  async function getOneWayFlights() {
    const response = await fetch(
      `http://localhost:42069/nexus/flights/avianca/one-way-flights?${url.searchParams.toString()}`,
      {
        method: "GET"
      }
    );

    const result = await response.json();
    return result;
  }

  async function getCities() {
    const response = await fetch("http://localhost:42069/nexus/flights/avianca/cities", {
      method: "GET",
    });

    const result = await response.json();
    return result;
  }

  return {
    user: locals.user,
    cities: getCities(),
    flights: getOneWayFlights(),
  };
}

export const actions = {
  createCommentary: async ({ request, cookies, locals }) => {
    const data = await request.formData();
    const token = cookies.get('token');
    const user = locals.user.firstName;
    let parentId = data.get("parentId") || 0;
    if (!parentId || parentId === "null") {
      parentId = 0;
    }
    console.log(parentId)
    try {
      const response = await fetch("http://localhost:42069/nexus/comments", {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
          "Authorization": `Bearer ${token}`

        },
        body: JSON.stringify({
          parentComment: parentId,
          userId: data.get("userId"),
          content: data.get("content"),
          flightId: data.get("flightId"),
          userName: user
        }),
      });
      const result = await response.json();

      if (result.error) {
        throw new Error(result.error);
      }
    } catch (error) {
      if (error instanceof Error) {
        return fail(500, {
          error: error.message,
        });
      }
    }
  },
  createRating: async ({ request }) => {
    const data = await request.formData();
    try {
      const response = await fetch("http://localhost:42069/nexus/flights/create-rating", {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify({
          userId: data.get("userId"),
          flightId: data.get("flightId"),
          value: data.get("rating"),
        }),
      });
      const result = await response.json();

      if (result.error) {
        throw new Error(result.error);
      }
    } catch (error) {
      if (error instanceof Error) {
        return fail(500, {
          error: error.message,
        });
      }
    }
  },
};
