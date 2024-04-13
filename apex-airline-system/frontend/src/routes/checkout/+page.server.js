import { PUBLIC_BASE_URL } from '$env/static/public';

export const actions = {

  default: async ({ request }) => {
    const formData = await request.formData();
    let method = formData.get("paymenth_method");
    let amount = formData.get("passengers");
    let discount = formData.get("discount");

    const makePurchase = async (flightId, category) => {
      if (flightId) {
        const purchaseData = {
          user_id: formData.get("user_id"),
          state: formData.get("state"),
          type: category,
          flight_id: flightId,
        };
        try {
          const response = await fetch(`${PUBLIC_BASE_URL}/purchase/${amount}/${method}/${discount}`, {
            method: "POST",
            headers: {
              "Content-Type": "application/json",
            },
            body: JSON.stringify(purchaseData),
          });
          console.log(`Purchase for flight ID ${flightId} (category: ${category}): ${response.ok ? 'Success' : 'Failed'}`);
        } catch (error) {
          console.error(`Error during purchase for flight ID ${flightId}:`, error);
        }
      }
    };

    const purchasePromises = [
      makePurchase(formData.get("flight_id"), formData.get("category")),
      formData.get("second_flightid") && makePurchase(formData.get("third_flightid"), formData.get("category3")),
      formData.get("second_flightid") && makePurchase(formData.get("second_flightid"), formData.get("category2")),
      formData.get("first_flightid") && makePurchase(formData.get("first_flightid"), formData.get("category1")),
    ];
    console.log(purchasePromises); 

    try {
      await Promise.all(purchasePromises);
      console.log("All purchases attempted.");
    } catch (error) {
      console.error("Error during purchase attempts:", error);
    }

    return {};
  },
};
