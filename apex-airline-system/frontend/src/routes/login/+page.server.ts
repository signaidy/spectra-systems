import { redirect } from "@sveltejs/kit";
import { fail } from "@sveltejs/kit";
import { PUBLIC_BASE_URL } from '$env/static/public';

import { JWT_SECRET } from "$env/static/private";
import jsonwebtoken from "jsonwebtoken";
const { sign } = jsonwebtoken;

export const actions = {
  default: async ({ cookies, request }) => {
    const data = await request.formData();
    try {
      const response = await fetch(`${PUBLIC_BASE_URL}/login`, {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify({
          email: data.get("email"),
          password: data.get("password"),
        }),
      });
      const result = await response.json();

      if (result.error) {
        throw new Error(result.error);
      }

      const token = sign(result, JWT_SECRET);

      cookies.set("token", token, { path: "/", secure: false});
    } catch (error) {
      if (error instanceof Error) {
        return fail(500, {
          error: error.message,
        });
      }
    }
    redirect(303, "/");
  },
};
