"use client";
import { useState } from "react";
import { useToast } from "@/components/ui/use-toast";
import { createHotel, deleteHotel } from "@/lib/actions";
import { Button } from "@/components/ui/button";

import {
  AlertDialog,
  AlertDialogAction,
  AlertDialogCancel,
  AlertDialogContent,
  AlertDialogDescription,
  AlertDialogFooter,
  AlertDialogHeader,
  AlertDialogTitle,
  AlertDialogTrigger,
} from "@/components/ui/alert-dialog";

export function DeleteHotel({ id }: { id: string }) {
  const { toast } = useToast();

  const handleDelete = async () => {
    const result = await deleteHotel(id);
    if (result) {
      console.error(result.error);
      return toast({
        variant: "destructive",
        title: "Uh oh! Something went wrong.",
        description: result.message,
        // action: <ToastAction altText="Try again">Try again</ToastAction>,
      });
    }
    toast({
      description: "Hotel has been successfully deleted.",
    });
  };

  return (
    <AlertDialog>
      <AlertDialogTrigger asChild>
        <Button variant="destructive">Delete Hotel</Button>
      </AlertDialogTrigger>
      <AlertDialogContent>
        <AlertDialogHeader>
          <AlertDialogTitle>Are you absolutely sure?</AlertDialogTitle>
          <AlertDialogDescription>
            This action cannot be undone. This will permanently delete the hotel
            and remove the data from our servers.
          </AlertDialogDescription>
        </AlertDialogHeader>
        <AlertDialogFooter>
          <AlertDialogCancel>Cancel</AlertDialogCancel>
          <AlertDialogAction onClick={handleDelete}>Continue</AlertDialogAction>
        </AlertDialogFooter>
      </AlertDialogContent>
    </AlertDialog>
  );
}
export function CreateHotel() {
  const { toast } = useToast();

  const handleCreate = async () => {
    const result = await createHotel();
    if (result) {
      console.error(result.error);
      return toast({
        variant: "destructive",
        title: "Uh oh! Something went wrong.",
        description: result.message,
        // action: <ToastAction altText="Try again">Try again</ToastAction>,
      });
    }
    toast({
      description: "Hotel has been successfully added.",
    });
  };

  return <Button onClick={handleCreate}>Add Hotel</Button>;
}
