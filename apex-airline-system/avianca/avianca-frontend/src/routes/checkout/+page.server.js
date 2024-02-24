
export const actions = {
   default: async({request}) => {
       const formData = await request.formData(); 

       const response = await fetch("http://localhost:8080/purchase", {
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
           message: "Ticket bought succesfuly"
       }
     } else {
       return{ 
           message: "Error"
       }
     }


   }
}