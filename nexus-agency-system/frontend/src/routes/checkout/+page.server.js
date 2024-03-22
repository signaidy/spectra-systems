
export const actions = {

   default: async({request, cookies}) => {
       const formData = await request.formData(); 
       let method = formData.get("paymenth_method");
       let amount = formData.get("passengers");
       const token = cookies.get('token');
       const flight = formData.get( "flight" );
      console.log(flight);

       const response = await fetch(`http://localhost:42069/nexus/flights/purchase/${amount}/${method}`, {
       method: "POST",
       headers: {
         "Content-Type": "application/json",
         "Authorization": `Bearer ${token}`
       },
       body: JSON.stringify({
         user_id: formData.get("user_id"),
         flight: flight,
         state: formData.get("state"),
         type: formData.get("category"),

       }),
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