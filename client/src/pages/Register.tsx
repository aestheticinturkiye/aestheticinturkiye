import { zodResolver } from "@hookform/resolvers/zod";
import { useForm } from "react-hook-form";
import * as z from "zod";
import { useNavigate } from "react-router-dom";

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
import { FindPartnerForm } from "./FindPartner/FindPartnerForm";

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
    country: z.string().min(2, {
      message: "Name must be filled",
    }),
    phoneNumber: z.string().min(2, {
      message: "Name must be filled",
    }),
  })
  .refine((data) => data.password === data.rePassword, {
    message: "Passwords don't match",
    path: ["rePassword"], // path of error
  });

export function Register() {
  // 1. Define your form.
  const navigate = useNavigate();

  const form = useForm<z.infer<typeof formSchema>>({
    resolver: zodResolver(formSchema),
    defaultValues: {
      email: "",
      password: "",
      rePassword: "",
    },
  });

  const formFileds = { name: "qwefhgqwe" };

  // 2. Define a submit handler.
  function onSubmit(values: z.infer<typeof formSchema>) {
    // Do something with the form values.
    // ✅ This will be type-safe and validated.
    console.log(values);
  }

  return (
    <div className="overflow-hidden h-full bg-background">
      <div className="container relative flex-col min-h-screen items-center justify-center md:grid lg:max-w-none lg:grid-cols-2 lg:px-0">
        <Button
          className="absolute right-4 top-4 md:right-8 md:top-8"
          variant="ghost"
          onClick={() => {
            navigate("/login");
          }}
        >
          {" "}
          Login
        </Button>

        <div className="relative hidden min-h-full flex-col bg-muted p-10   text-white lg:flex dark:border-r">
          <div className="absolute inset-0">
            <img
              width={"100%"}
              height={"100%"}
              src="src/assets/register.jpeg"
              alt=""
            />
          </div>
          <div className="relative z-20 flex items-center text-2xl font-semibold tracking-tight">
            Esthomy
          </div>
        </div>

        <div className="lg:p-8">
          <div className="mx-auto flex w-full flex-col justify-center space-y-6 sm:w-[350px]">
            <Form {...form}>
              <form
                onSubmit={form.handleSubmit(onSubmit)}
                className="space-y-3"
              >
                <div className="flex flex-col space-y-2 text-center">
                  <h1 className="text-2xl font-semibold tracking-tight">
                    Create an account
                  </h1>
                  <p className="text-sm text-muted-foreground">
                    Enter your informations below to create your account
                  </p>
                </div>
                {/* Email Field */}

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
                        <Input
                          type="password"
                          placeholder="********"
                          {...field}
                        />
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
                        <Input
                          type="password"
                          placeholder="********"
                          {...field}
                        />
                      </FormControl>
                      <FormDescription>
                        Please re-enter your password.
                      </FormDescription>
                      <FormMessage />
                    </FormItem>
                  )}
                />
                <FindPartnerForm {...form} />

                <Button className="w-full" type="submit">
                  Submit
                </Button>
              </form>
            </Form>
          </div>
        </div>
      </div>
    </div>
  );
}
