import { fail } from "@sveltejs/kit";

export function load({ locals, url, cookies }) {
  async function getOneWayFlights() {
    const token = cookies.get('token');
    const response = await fetch(
      `http://localhost:8080/nexus/flights/avianca/one-way-flights?${url.searchParams.toString()}`,
      {
        method: "GET",
        headers: {
          "Authorization": `Bearer ${token}`
        }
      }
    );

    const result = await response.json();
    console.log(result)

    function assignChildren(commentaries: Commentary[]): Commentary[] {
      const commentariesById: { [key: number]: Commentary } = {};

      commentaries.forEach((commentary) => {
        commentariesById[commentary.commentId] = commentary;
      });

      commentaries.forEach((commentary) => {
        const { parentCommentId } = commentary;
        if (parentCommentId !== 0) {
          const parentCommentary = commentariesById[parentCommentId];
          if (parentCommentary) {
            if (!parentCommentary.children) {
              parentCommentary.children = [];
            }
            parentCommentary.children.push(commentary);
          }
        }
      });

      return commentaries.filter(
        (commentary) => commentary.parentCommentId === 0
      );
    }

    result.forEach((flight: Flight) => {
      const arrangedCommentaries = assignChildren(flight.commentaries);
      flight.commentaries = arrangedCommentaries;
    });

    return result;
  }

  async function getCities() {
    const response = await fetch("http://localhost:8080/nexus/flights/avianca/cities", {
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
  createCommentary: async ({ request }) => {
    const data = await request.formData();
    try {
      const response = await fetch("http://localhost:8080/create-commentary", {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify({
          parentId: data.get("parentId"),
          userId: data.get("userId"),
          content: data.get("content"),
          flightId: data.get("flightId"),
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
      const response = await fetch("http://localhost:8080/create-rating", {
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
