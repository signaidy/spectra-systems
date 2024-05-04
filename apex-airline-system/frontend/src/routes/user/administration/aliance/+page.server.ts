import { PUBLIC_BASE_URL } from '$env/static/public';


  export const actions = {
    default: async({request}) => {
        const formData = await request.formData(); 

        const response = await fetch(`${PUBLIC_BASE_URL}/alianceinsertion`, {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify({
            IP: formData.get("IP"),
            endpoint: formData.get("endpoint"),
            key: formData.get("key")
        }),
      });
      const result = await response.json();
      if(!result.ok){
        return {
            message: "Aliance inserted"
        }
      } else {
        return{ 
            message: "Error"
        }
      }


    }
}
 