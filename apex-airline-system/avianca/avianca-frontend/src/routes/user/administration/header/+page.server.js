
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

// export const actions = {
//    default: async({request}) => {
//        const formData = await request.formData(); 

//        const response = await fetch("http://localhost:8080/update-aboutus", {
//        method: "POST",
//        headers: {
//          "Content-Type": "application/json",
//        },
//        body: JSON.stringify({
//          slogan: formData.get("slogan"),
//          gif: formData.get("gif"),
//          yt: formData.get("yt"),
//          cards_amoun: formData.get("cardsamount"),
//          title_one: formData.get("t1"), 
//          text_one: formData.get("tx1"), 
//          img_one: formData.get("img1"), 
//          title_two: formData.get("t2"), 
//          text_two: formData.get("tx2"), 
//          img_two: formData.get("img2"), 
//          title_three: formData.get("t3"), 
//          text_three: formData.get("tx3"), 
//          img_three: formData.get("img3"), 
//          title_four: formData.get("t4"), 
//          text_four: formData.get("tx4"), 
//          img_four: formData.get("img4"), 

//        }),
//      });
//      const result = await response.json();
//      if(!result.ok){
//        return {
//            message: "About us information updated"
//        }
//      } else {
//        return{ 
//            message: "Error while updating the information"
//        }
//      }


//    }
// }