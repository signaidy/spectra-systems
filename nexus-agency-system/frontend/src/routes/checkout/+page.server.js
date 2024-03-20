
export const actions = {

   default: async({request}) => {
       const formData = await request.formData(); 
       let method = formData.get("paymenth_method");
       let amount = formData.get("passengers");


       const response = await fetch(`http://localhost:42069/purchase/${amount}/${method}`, {
       method: "POST",
       headers: {
         "Content-Type": "application/json",
       },
       body: JSON.stringify({
         user_id: formData.get("user_id"),
         flight_id: formData.get("flight_id"),
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