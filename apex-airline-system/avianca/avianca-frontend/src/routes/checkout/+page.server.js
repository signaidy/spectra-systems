export const actions = {

   default: async({request}) => {
       const formData = await request.formData(); 
       let method = formData.get("paymenth_method");
       let amount = formData.get("passengers");
       console.log(amount); 
       let discount = formData.get("discount");
       console.log(discount); 


       const response = await fetch(`http://localhost:8080/purchase/${amount}/${method}/${discount}`, {
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