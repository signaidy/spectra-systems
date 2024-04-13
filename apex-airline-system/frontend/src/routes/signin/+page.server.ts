import { error, redirect } from "@sveltejs/kit";
import { fail } from "@sveltejs/kit";
import { JWT_SECRET } from "$env/static/private";
import jsonwebtoken from "jsonwebtoken";
import { PUBLIC_BASE_URL } from "$env/static/public";

const { sign } = jsonwebtoken;

export const actions = {
  default: async ({ cookies, request }) => {
    const data = await request.formData();
    const captcha = data.get("g-recaptcha-response");

    if (data.get("password") !== data.get("confirmedPassword")) {
      return fail(400, {
        error: "Passwords do not match",
      });
    }

    if (data.get("g-recaptcha-response") == "") {
      return fail(400, {
        error: "Fill up captcha verification",
      });
    }

    try {
      const response = await fetch(
        `${PUBLIC_BASE_URL}/create-user/${captcha}`,
        {
          method: "POST",
          headers: {
            "Content-Type": "application/json",
          },
          body: JSON.stringify({
            email: data.get("email"),
            password: data.get("password"),
            firstName: data.get("firstName"),
            lastName: data.get("lastName"),
            originCountry: data.get("originCountry"),
            passportNumber: data.get("passportNumber"),
            age: data.get("age"),
          }),
        }
      );
      const result = await response.json();

      if (result.error) {
        throw new Error(result.error);
      }

      const token = sign(result, JWT_SECRET);

      cookies.set("token", token, { path: "/", secure: false });
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
