import { PUBLIC_BASE_URL } from '$env/static/public';

 export async function load({ fetch }) {
     const response = await fetch(`${PUBLIC_BASE_URL}/aboutus`);
     const aboutus = await response.json();
     return {
        aboutus,
     };
   }
  

export const actions = {
    default: async({request}) => {
        const formData = await request.formData(); 

        const response = await fetch(`${PUBLIC_BASE_URL}/update-aboutus`, {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify({
          slogan: formData.get("slogan"),
          gif: formData.get("gif"),
          yt: formData.get("yt"),
          cards_amoun: formData.get("cardsamount"),
          title_one: formData.get("t1"), 
          text_one: formData.get("tx1"), 
          img_one: formData.get("img1"), 
          title_two: formData.get("t2"), 
          text_two: formData.get("tx2"), 
          img_two: formData.get("img2"), 
          title_three: formData.get("t3"), 
          text_three: formData.get("tx3"), 
          img_three: formData.get("img3"), 
          title_four: formData.get("t4"), 
          text_four: formData.get("tx4"), 
          img_four: formData.get("img4"), 

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