export const actions = {
  default: async({request, cookies}) => {
    const token = cookies.get('token');
      const formData = await request.formData(); 
      let hotelid = formData.get("hotelid");
      let hotelname = formData.get("hotelname");
      let user_id = formData.get("user_id");
      const checkin = formData.get("checkin");
      const checkout = formData.get("checkout");
      const price = formData.get("price")
      const random = formData.get("randomValue")
      const roomtype = formData.get("roomtype");
      const guests = formData.get("guests");
      const bedAmount = formData.get("bedAmount");
      let days = formData.get("days");
      let city = formData.get("city");
      let beds = formData.get("beds");
      const totalprice = formData.get("totalprice");
      const body = {
        userid: user_id,
        hotelId: hotelid,
        hotel:hotelname,
        dateStart: checkin,
        dateEnd: checkout,
        roomType: roomtype,
        reservationNumber: random,
        location: city,
        bedSize: beds, 
        price: price, 
        bedAmount: bedAmount,
        totalDays: days, 
        totalPrice: totalprice, 
        guests: guests
      };
      const response = await fetch(`http://localhost:42069/nexus/reservations`, {
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