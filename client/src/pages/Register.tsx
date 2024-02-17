"use client";

import { zodResolver } from "@hookform/resolvers/zod";
import { useForm, SubmitHandler } from "react-hook-form";
import * as z from "zod";
import { AnimatePresence } from "framer-motion";

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
import { useState } from "react";
import FormWrapper from "@/components/FormWrapper";

const formSchema: any = z
  .object({
    name: z.string().min(2, {
      message: "Name must be filled",
    }),
    surname: z.string().min(2, {
      message: "Surname must be filled",
    }),
    email: z.string().email({
      message: "Please enter a valid email address.",
    }),
    password: z.string().min(6, {
      message: "Password must be at least 6 characters.",
    }),
    rePassword: z.string(),
    disease: z.string().min(2, {
      message: "Disease must be filled",
    }),
    description: z.string().min(2, {
      message: "Description must be filled",
    }),
  })
  .refine((data) => data.password === data.rePassword, {
    message: "Passwords don't match",
    path: ["rePassword"], // path of error
  });

const steps = [
  {
    id: "Step 1",
    name: "Information",
    fields: ["disease", "description"],
  },
  //   {
  //     id: "Step 2",
  //     name: "Address",
  //     fields: ["country", "city"],
  //   },
  {
    id: "Step 3",
    name: "Personal Information",
    fields: ["name", "surname", "email", "password", "rePassword"],
  },
];

export function Register() {
  const [previousStep, setPreviousStep] = useState(0);
  const [currentStep, setCurrentStep] = useState(0);
  const delta = currentStep - previousStep;
  const form = useForm<z.infer<typeof formSchema>>({
    resolver: zodResolver(formSchema),
    defaultValues: {
      email: "",
      password: "",
      rePassword: "",
      name: "",
      surname: "",
      disease: "",
      description: "",
    },
  });

  const processForm: SubmitHandler<any> = (data) => {
    console.log(data);
    form.reset();
  };

  const next = async () => {
    const fields = steps[currentStep].fields;
    console.log("fields", fields);
    const output = await form.trigger(fields, {
      shouldFocus: true,
    });
    console.log("output", output);
    if (!output) return;

    if (currentStep === steps.length - 1) {
      await form.handleSubmit(processForm)();
    }
    setPreviousStep(currentStep);
    setCurrentStep((step) => step + 1);
  };

  const prev = () => {
    if (currentStep > 0) {
      setPreviousStep(currentStep);
      setCurrentStep((step) => step - 1);
    }
  };
  return (
    <Form {...form}>
      <form onSubmit={form.handleSubmit(processForm)} className="space-y-3">
        <div className="flex flex-col space-y-2 text-center">
          <h1 className="text-2xl font-semibold tracking-tight">
            Create an account
          </h1>
          <p className="text-sm text-muted-foreground">
            Enter your informations below to create your account
          </p>
        </div>

        {/* Email Field */}

        {currentStep === 1 && (
          <FormWrapper>
            <FormField
              control={form.control}
              name="name"
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

            <FormField
              control={form.control}
              name="surname"
              render={({ field }) => (
                <FormItem>
                  <FormLabel>Surname</FormLabel>
                  <FormControl>
                    <Input placeholder="your surname" {...field} />
                  </FormControl>
                  <FormMessage />
                </FormItem>
              )}
            />
            <FormField
              control={form.control}
              name="email"
              render={({ field }) => (
                <FormItem>
                  <FormLabel>Email</FormLabel>
                  <FormControl>
                    <Input placeholder="example@example.com" {...field} />
                  </FormControl>
                  <FormMessage />
                </FormItem>
              )}
            />

            {/* Password Field */}
            <FormField
              control={form.control}
              name="password"
              render={({ field }) => (
                <FormItem>
                  <FormLabel>Password</FormLabel>
                  <FormControl>
                    <Input type="password" placeholder="********" {...field} />
                  </FormControl>
                  <FormDescription>
                    Password must be at least 6 characters.
                  </FormDescription>
                  <FormMessage />
                </FormItem>
              )}
            />

            {/* Re-enter Password Field */}
            <FormField
              control={form.control}
              name="rePassword"
              render={({ field }) => (
                <FormItem>
                  <FormLabel>Re-enter Password</FormLabel>
                  <FormControl>
                    <Input type="password" placeholder="********" {...field} />
                  </FormControl>
                  <FormDescription>
                    Please re-enter your password.
                  </FormDescription>
                  <FormMessage />
                </FormItem>
              )}
            />
          </FormWrapper>
        )}

        {currentStep === 0 && (
          <FormWrapper>
            <FormField
              control={form.control}
              name="disease"
              render={({ field }) => (
                <FormItem>
                  <FormLabel>Disease</FormLabel>
                  <FormControl>
                    <Input placeholder="Disease" {...field} />
                  </FormControl>
                  <FormMessage />
                </FormItem>
              )}
            />
            <FormField
              control={form.control}
              name="description"
              render={({ field }) => (
                <FormItem>
                  <FormLabel>Description</FormLabel>
                  <FormControl>
                    <Input placeholder="Describe your disease" {...field} />
                  </FormControl>
                  <FormMessage />
                </FormItem>
              )}
            />
          </FormWrapper>
        )}
        {/* {<FindPartnerForm {...form} />} */}

        {/* {currentStep === 3 && <Step3 {...form} />} */}

        {currentStep !== 0 && (
          <Button
            onClick={prev}
            type="button"
            variant="ghost"
            className="w-full visible p-0 text-neutral-200 hover:text-white"
          >
            Previous
          </Button>
        )}

        <Button onClick={next} className="w-full" type="button">
          {currentStep === 2 ? "Submit" : "Next"}
        </Button>
      </form>
    </Form>
  );
}
