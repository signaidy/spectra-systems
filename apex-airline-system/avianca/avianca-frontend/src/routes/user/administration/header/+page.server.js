
export async function load({ fetch }) {
    const response = await fetch("http://localhost:8080/header");
    const head = await response.json();
    return {
       head,
    };
  }
 
  export const actions = {
    default: async({request}) => {
        const formData = await request.formData(); 

        const response = await fetch("http://localhost:8080/update-header", {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify({
          Text_Logo: formData.get("Text_Logo"),
          Logo: formData.get("Logo"),
          Section: formData.get("Section"),
          Link_Section: formData.get("Link_Section"),
          Link_Profile: formData.get("Link_Profile"), 
          Link_Login: formData.get("Link_Login") 
        }),
      });
      const result = await response.json();
      if(!result.ok){
        return {
            message: "Head information updated"
        }
      } else {
        return{ 
            message: "Error while updating the information"
        }
      }


    }
}