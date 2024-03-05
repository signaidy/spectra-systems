"use server";
import { redirect } from 'next/navigation'
import { revalidateTag } from "next/cache";

export async function createHotel() {
  try {
    await fetch(`${process.env.NEXT_PUBLIC_API_URL}/create-hotel`, {
      method: "POST",
    });
    revalidateTag("hotels");
  } catch (e) {
    if (e instanceof Error) {
      return {
        error: `${e.name}: ${e.message}`,
        message: "Database Error: Failed to Create Hotel.",
      };
    }
  }
}

export async function deleteHotel(id: string) {
  try {
    fetch(`${process.env.NEXT_PUBLIC_API_URL}/delete-hotel/${id}`, {
      method: "DELETE",
    });
    revalidateTag("hotels");
  } catch (e) {
    if (e instanceof Error) {
      return {
        error: `${e.name}: ${e.message}`,
        message: "Database Error: Failed to Delete Hotel.",
      };
    }
  }
}
