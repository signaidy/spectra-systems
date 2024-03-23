export const actions = {
  default: async({request, cookies}) => {
    const token = cookies.get('token');
      const formData = await request.formData(); 
      let method = formData.get("paymenth_method");
      let amount = formData.get("passengers");
      // Extracting form data
      const userId = formData.get("user_id");
      const flightId = formData.get("flight_id");
      const type = formData.get("category");
      const state = formData.get("state");
      let departureDate = formData.get("departureDate");
      const departureLocation = formData.get("departureLocation");
      const arrivalLocation = formData.get("arrivalLocation");
      const price = formData.get("price");
      const rating = formData.get("rating");
      // Format departureDate to 'yyyy-MM-dd'
      departureDate = departureDate.split(" ")[0];
      // Constructing the body object
      const body = {
        user_id: userId,
        flight_id: flightId,
        type: type,
        state: state,
        userId: userId,
        flightId: flightId,
        departureDate: departureDate,
        departureLocation: departureLocation,
        arrivalLocation: arrivalLocation,
        price: price,
        returnDate: null, // Assuming returnDate is always null for this case
        rating: rating
      };
      console.log(body);
      const response = await fetch(`http://localhost:42069/nexus/flights/purchase/${amount}/${method}`, {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
        "Authorization": `Bearer ${token}`
      },
      body: JSON.stringify(body),
    });
    const result = await response.json();
    if(!result.ok){
      return {
          message: "Thanks for your purchase!"
      }
    } else {
      return{ 
          message: "Error"
      }
    }
  }
}