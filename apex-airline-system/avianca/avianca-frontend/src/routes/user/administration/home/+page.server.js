export async function load({ fetch }) {
    const response = await fetch("http://localhost:8080/home");
    const home = await response.json();
    console.log(home); 
    return {
       home,
    };
  }

  export const actions = {
    default: async({request}) => {
        const formData = await request.formData(); 

        const response = await fetch("http://localhost:8080/update-home", {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify({
            Background: formData.get("background"),
            FlightImage1: formData.get("image1"),
            Title1: formData.get("title1"),
            Content1: formData.get("content1"),
            FlightImage2: formData.get("image2"),
            Title2: formData.get("title2"),
            Content2: formData.get("content2"),
            FlightImage3: formData.get("image3"),
            Title3: formData.get("title3"),
            Content3: formData.get("content3")
        }),
      });
      const result = await response.json();
      if(!result.ok){
        return {
            message: "Home information updated"
        }
      } else {
        return{ 
            message: "Error while updating the information"
        }
      }


    }
}
 