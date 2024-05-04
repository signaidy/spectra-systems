import { redirect } from "@sveltejs/kit";
import { fail } from "@sveltejs/kit";
import {
  toCalendarDateTime,
  parseTime,
  parseDate,
} from "@internationalized/date";
import { PUBLIC_BASE_URL } from '$env/dynamic/public';

export async function load({ locals }) {
  const response = await fetch(`${PUBLIC_BASE_URL}/get-cities`, {
    method: "GET",
  });

  const result = await response.json();

  return { user: locals.user, cities: result };
}

export const actions = {
  default: async ({ request }) => {
    const data = await request.formData();

    for (const [, value] of data) {
      if (value === "" || value === "undefined") {
        return fail(400, {
          error: "Please fill in all the fields",
        });
      }
    }

    if (
      Number(data.get("touristCapacity")) > 800 ||
      Number(data.get("businessCapacity")) > 800 ||
      Number(data.get("touristQuantity")) > 800 ||
      Number(data.get("businessQuantity")) > 800
    ) {
      return fail(400, {
        error: "Capacity and Quantity cannot exceed 800 tickets",
      });
    }

    if (
      Number(data.get("touristQuantity")) >
        Number(data.get("touristCapacity")) ||
      Number(data.get("businessQuantity")) >
        Number(data.get("businessCapacity"))
    ) {
      return fail(400, {
        error: "The number of tickets cannot exceed the corresponding capacity",
      });
    }

    if (
      Number(data.get("touristPrice")) < 0 ||
      Number(data.get("businessPrice")) < 0
    ) {
      return fail(400, {
        error: "Price cannot be negative",
      });
    }

    try {
      const departureDate = toCalendarDateTime(
        parseDate(data.get("departureDay")),
        parseTime(data.get("departureTime"))
      );

      const arrivalDate = toCalendarDateTime(
        parseDate(data.get("arrivalDay")),
        parseTime(data.get("arrivalTime"))
      );

      if (departureDate.compare(arrivalDate) > 0) {
        return fail(400, {
          error: "Arrival date must be after departure date",
        });
      }

      const response = await fetch(`${PUBLIC_BASE_URL}/create-flight`, {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify({
          originCity: data.get("originCity"),
          destinationCity: data.get("destinationCity"),
          departureDate: departureDate.toString().replace("T", " "),
          arrivalDate: arrivalDate.toString().replace("T", " "),
          touristCapacity: data.get("touristCapacity"),
          businessCapacity: data.get("businessCapacity"),
          touristPrice: data.get("touristPrice"),
          businessPrice: data.get("businessPrice"),
          touristQuantity: data.get("touristQuantity"),
          businessQuantity: data.get("businessQuantity"),
          // type: data.get("type"),
          detail: data.get("detail"),
          type: 0,
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
    redirect(303, "/user/administration");
  },
};
