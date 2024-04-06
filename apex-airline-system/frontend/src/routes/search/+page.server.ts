import { fail } from "@sveltejs/kit";
import { PUBLIC_BASE_URL } from '$env/static/public';

export function load({ locals, url }) {
  async function getOneWayFlights() {
    console.log(url.searchParams.toString()); 
    const response = await fetch(
      `${PUBLIC_BASE_URL}/get-one-way-flights?${url.searchParams.toString()}`,
      {
        method: "GET",
      }
    );

    const result = await response.json();

    if (!response.ok) { 
      return []; 
    }

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

    if(!response.ok){
      return [];
    } else{
      return result ;
    }
  }

  async function getCities() {
    const response = await fetch(`${PUBLIC_BASE_URL}/get-cities`, {
      method: "GET",
    });

    const result = await response.json();
    return result;
  }

  async function scaleFlights() {
    const response = await fetch(
      `${PUBLIC_BASE_URL}/scale-flights?${url.searchParams.toString()}`,
      {
        method: "GET",
      }
    );

    const result = await response.json();
    console.log(result); 

    if (!response.ok) { 
      return []; 
    }

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

    if (Array.isArray(result)) {
    result.forEach((flight: Flight) => {
      const arrangedCommentaries = assignChildren(flight.commentaries);
      flight.commentaries = arrangedCommentaries;
    });}

    if(!response.ok){
      return [];
    } else{
      return result ;
    }
     
  }


  return {
    user: locals.user,
    cities: getCities(),
    flights: getOneWayFlights(),
    scaleflights: scaleFlights(), 
  };
}

export const actions = {
  createCommentary: async ({ request }) => {
    const data = await request.formData();
    try {
      const response = await fetch(`${PUBLIC_BASE_URL}/create-commentary`, {
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
      const response = await fetch(`${PUBLIC_BASE_URL}/create-rating`, {
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
