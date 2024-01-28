"use client";

import { zodResolver } from "@hookform/resolvers/zod";
import { useForm } from "react-hook-form";
import * as z from "zod";

import { Button } from "@/components/ui/button";
import {
  Form,
  FormControl,
  FormDescription,
  FormField,
  FormItem,
  FormLabel,
  FormMessage,
} from "@/components/ui/form";
import { Input } from "@/components/ui/input";

export function FindPartnerForm(prop: any) {
  // 1. Define your form.
  console.log("forming", prop);

  //   const form = useForm<z.infer<typeof formSchema>>({
  //     resolver: zodResolver(formSchema),
  //     defaultValues: {
  //       username: "",
  //     },
  //   });

  return (
    <>
      <FormField
        control={prop.control}
        name="username"
        render={({ field }) => (
          <FormItem>
            <FormLabel>Name</FormLabel>
            <FormControl>
              <Input placeholder="your name" {...field} />
            </FormControl>
            <FormMessage />
          </FormItem>
        )}
      />
    </>
  );
}
