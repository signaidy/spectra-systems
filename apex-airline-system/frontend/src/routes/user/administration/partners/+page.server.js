export async function load({ fetch }) {
    const response = await fetch("http://localhost:8080/partners");
    const partners = await response.json();
    return {
       partners,
    };
  }

  export const actions = {
    default: async({request}) => {
        const formData = await request.formData(); 

        const response = await fetch("http://localhost:8080/update-partners", {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify({
          Title: formData.get("Title"),
          Description: formData.get("Description"),
          Partner1: formData.get("Partner1"),
          L1: formData.get("L1"),
          Partner2: formData.get("Partner2"),
          L2: formData.get("L2"),
          Partner3: formData.get("Partner3"),
          L3: formData.get("L3"),
          Partner4: formData.get("Partner4"),
          L4: formData.get("L4"),
          Partner5: formData.get("Partner5"),
          L5: formData.get("L5"), 
        }),
      });
      const result = await response.json();
      if(!result.ok){
        return {
            message: "Partners information updated"
        }
      } else {
        return{ 
            message: "Error while updating the information"
        }
      }


    }
}