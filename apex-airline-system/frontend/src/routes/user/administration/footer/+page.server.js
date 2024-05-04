import { PUBLIC_BASE_URL } from '$env/dynamic/public';

export async function load({ fetch }) {
    const response = await fetch(`${PUBLIC_BASE_URL}/footer`);
    const footer = await response.json();
    return {
        footer,
    };
  }

export const actions = {
  default: async ({request}) => {
    const formData = await request.formData();

    const response = await fetch(`${PUBLIC_BASE_URL}/update-footer`, {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify({
        Title_1: formData.get("title1"),
        Section_1: formData.get("contentsection1"),
        L1: formData.get("link_1"),
        Section_2: formData.get("contentsection2"),
        L2: formData.get("link_2"),
        Section_3: formData.get("contentsection3"),
        L3: formData.get("link_3"),
        Section_4: formData.get("contentsection4"),
        L4: formData.get("link_4"),
        Section_5: formData.get("contentsection5"),
        L5: formData.get("link_5"),
        Section_6: formData.get("contentsection6"),
        L6: formData.get("link_6"),
        Title_2: formData.get("title2"),
        Quick_Section_1: formData.get("conten1"),
        Link_quick_1: formData.get("quicklink_1"),
        Quick_Section_2: formData.get("conten2"),
        Link_quick_2: formData.get("quicklink_2"),
        Title_3: formData.get("title3"),
        Contact_1: formData.get("contactus1"),
        Contact_2: formData.get("contactus2"),
        copyright: formData.get("copyright")
      }),
    });
    const result = await response.json();
    if (!result.ok) {
      return {
        message: "Footer information updated",
      }
    } else {
      return {
        message: "Error while updating the information",
      }
    }
  }
}
