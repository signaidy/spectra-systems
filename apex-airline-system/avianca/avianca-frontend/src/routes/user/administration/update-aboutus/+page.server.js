

 export async function load({ fetch }) {
     const response = await fetch("http://localhost:8080/aboutus");
     const aboutus = await response.json();
     return {
        aboutus,
     };
   }
  

export const actions = {
    default: async({request}) => {
        const formData = await request.formData(); 

        const response = await fetch("http://localhost:8080/update-aboutus", {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify({
          slogan: formData.get("slogan"),
          gif: formData.get("gif"),
          yt: formData.get("yt"),
          cards_amoun: formData.get("cardsamount")
        }),
      });
      const result = await response.json();
      if(!result.ok){
        return {
            message: "About us information updated"
        }
      } else {
        return{ 
            message: "Error while updating the information"
        }
      }


    }
}