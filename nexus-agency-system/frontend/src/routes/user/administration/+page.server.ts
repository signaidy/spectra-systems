import { fail } from "@sveltejs/kit";

export async function load({ locals }) {
  async function getUsers() {
    const response = await fetch("http://localhost:8080/nexus/users", {
      method: "GET",
    });
    const result = await response.json();
    return result;
  }

  return { user: locals.user, users: getUsers() };
}

export const actions = {
  updateUser: async ({ request }) => {
    const data = await request.formData();

    for (const [, value] of data) {
      if (value === "" || value === "undefined") {
        return fail(400, {
          error: "Please fill in all the fields",
        });
      }
    }

    try {
      const response = await fetch(`http://localhost:8080/nexus/users/${data.get("userId")}`, {
        method: "PUT",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify({
          firstName: data.get("firstName"),
          lastName: data.get("lastName"),
          country: data.get("originCountry"),
          passport: data.get("passportNumber"),
          role: data.get("role"),
          age: data.get("age"),
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
