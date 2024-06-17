import { zodResolver } from "@hookform/resolvers/zod";
import { useForm } from "react-hook-form";
import { z } from "zod";
import { useState } from "react";
import { Calendar } from "@/components/ui/calendar";
import {
  Popover,
  PopoverContent,
  PopoverTrigger,
} from "@/components/ui/popover";
import { toast } from "@/components/ui/use-toast";
import { format } from "date-fns";
import { Checkbox } from "@/components/ui/checkbox";

import {
  Accordion,
  AccordionContent,
  AccordionItem,
  AccordionTrigger,
} from "@/components/ui/accordion";
import { Button } from "@/components/ui/button";
import {
  Card,
  CardContent,
  CardDescription,
  CardHeader,
  CardTitle,
} from "@/components/ui/card";
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
import { Link } from "react-router-dom";
import { ModeToggle } from "@/components/theme-switcher";
import { Textarea } from "@/components/ui/textarea";
import { cn } from "@/lib/utils";
import { CalendarIcon } from "lucide-react";
import { login, register } from "@/services/Auth";
import useAuth from "@/store/store";
import { getSignedUser } from "@/services/User";
import { findPartnerRequest } from "@/services/Partner";

// Define your validation schemas
const firstFormSchema = z.object({
  aestheticType: z.string().min(1, "Aesthetic type is required"),
  description: z.string(),
  preferedDate: z.date(),
  preferedCity: z.string().min(1),
  neededAccommodation: z.boolean(),
  neededTransportation: z.boolean(),
});

const secondFormSchema = z.object({
  name: z.string().min(1, "First name is required"),
  surname: z.string().min(1, "Last name is required"),
  email: z.string().email("Invalid email"),
  password: z.string().min(6, "Password must be at least 6 characters"),
  age: z.string().min(1, "Age is required"),
  phone: z.string().min(1, "Phone is required"),
  country: z.string().min(1, "Country is required"),
  gender: z.string().min(1, "Gender is required"),
});

export function Register() {
  const [activeAccordion, setActiveAccordion] = useState("item-1");
  const setAccessToken = useAuth((state) => state.setAccessToken);
  const setUser = useAuth((state) => state.setUser);

  const firstForm = useForm({
    defaultValues: {
      aestheticType: "",
      description: "",
      preferedDate: null,
      preferedCity: "",
      neededAccommodation: false,
      neededTransportation: false,
    },
    resolver: zodResolver(firstFormSchema),
  });

  const secondForm = useForm({
    defaultValues: {
      name: "",
      surname: "",
      email: "",
      password: "",
      age: "",
      phone: "",
      country: "",
      gender: "",
    },
    resolver: zodResolver(secondFormSchema),
  });

  const handleFirstFormSubmit = (data) => {
    setActiveAccordion("item-2");
  };

  const handleSecondFormSubmit = async (data) => {
    console.log("(data)", data);
    await register(data);
    const loginCredentials = {
      emailAddress: data.email,
      password: data.password,
    };

    const loginResponse = await login(loginCredentials);

    setAccessToken(loginResponse.data.accessToken);
    const user = await getSignedUser();
    setUser({ ...user });
    const res = await findPartnerRequest(firstForm.getValues());
    console.log("res", res);
  };

  return (
    <div className="flex justify-center items-center h-screen bg-gray-100">
      <Card className="mx-auto max-w-4xl w-full">
        <CardHeader>
          <CardTitle className="text-xl">Find a partner</CardTitle>
          <CardDescription>
            Enter your information to find a partner and create an account
          </CardDescription>
        </CardHeader>
        <CardContent>
          <Accordion
            type="single"
            collapsible
            className="mx-auto w-full"
            value={activeAccordion}
            onValueChange={setActiveAccordion}
          >
            <AccordionItem value="item-1">
              <AccordionTrigger>How can we help you?</AccordionTrigger>
              <AccordionContent>
                <Form {...firstForm}>
                  <form
                    onSubmit={firstForm.handleSubmit(handleFirstFormSubmit)}
                    className="space-y-8"
                  >
                    <div className="grid grid-cols-1 md:grid-cols-3 gap-8">
                      <FormField
                        control={firstForm.control}
                        name="aestheticType"
                        render={({ field }) => (
                          <FormItem>
                            <FormLabel>Aesthetic type</FormLabel>
                            <FormControl>
                              <Input placeholder="Type" {...field} />
                            </FormControl>
                            <FormMessage />
                          </FormItem>
                        )}
                      />

                      <FormField
                        control={firstForm.control}
                        name="preferedDate"
                        render={({ field }) => (
                          <FormItem>
                            <FormLabel>Preferred Date</FormLabel>
                            <div className="flex flex-col">
                              <Popover>
                                <PopoverTrigger asChild>
                                  <FormControl>
                                    <Button
                                      variant={"outline"}
                                      className={cn(
                                        "w-[240px] pl-3 text-left font-normal",
                                        !field.value && "text-muted-foreground"
                                      )}
                                    >
                                      {field.value ? (
                                        format(field.value, "PPP")
                                      ) : (
                                        <span>Pick a date</span>
                                      )}
                                      <CalendarIcon className="ml-auto h-4 w-4 opacity-50" />
                                    </Button>
                                  </FormControl>
                                </PopoverTrigger>
                                <PopoverContent
                                  className="w-auto p-0"
                                  align="start"
                                >
                                  <Calendar
                                    mode="single"
                                    selected={field.value}
                                    onSelect={field.onChange}
                                    disabled={(date) =>
                                      date > new Date() ||
                                      date < new Date("1900-01-01")
                                    }
                                    initialFocus
                                  />
                                </PopoverContent>
                              </Popover>
                              <FormDescription>
                                Your date of birth is used to calculate your
                                age.
                              </FormDescription>
                            </div>
                            <FormMessage />
                          </FormItem>
                        )}
                      />
                      <FormField
                        control={firstForm.control}
                        name="preferedCity"
                        render={({ field }) => (
                          <FormItem>
                            <FormLabel>Preferred City</FormLabel>
                            <FormControl>
                              <Input placeholder="Type" {...field} />
                            </FormControl>
                            <FormMessage />
                          </FormItem>
                        )}
                      />
                      <FormField
                        control={firstForm.control}
                        name="description"
                        render={({ field }) => (
                          <FormItem className="col-span-2">
                            <FormLabel>Description</FormLabel>
                            <FormControl>
                              <Textarea
                                placeholder="Please explain what you need."
                                className="resize-none"
                                {...field}
                              />
                            </FormControl>
                            <FormMessage />
                          </FormItem>
                        )}
                      />
                      <div className="col-span-1 space-y-8">
                        <FormField
                          control={firstForm.control}
                          name="neededAccommodation"
                          render={({ field }) => (
                            <FormItem className="flex flex-row items-start space-x-3 space-y-0">
                              <FormLabel>Needed Accommodation</FormLabel>
                              <FormControl>
                                <Checkbox
                                  checked={field.value}
                                  onCheckedChange={field.onChange}
                                />
                              </FormControl>
                              <FormMessage />
                            </FormItem>
                          )}
                        />
                        <FormField
                          control={firstForm.control}
                          name="neededTransportation"
                          render={({ field }) => (
                            <FormItem className="flex flex-row items-start space-x-3 space-y-0">
                              <FormLabel>Needed Transportation</FormLabel>
                              <FormControl>
                                <Checkbox
                                  checked={field.value}
                                  onCheckedChange={field.onChange}
                                />
                              </FormControl>
                              <FormMessage />
                            </FormItem>
                          )}
                        />
                      </div>
                    </div>
                    <Button type="submit" className="w-full">
                      Next
                    </Button>
                  </form>
                </Form>
              </AccordionContent>
            </AccordionItem>
            <AccordionItem value="item-2">
              <AccordionTrigger>Your information</AccordionTrigger>
              <AccordionContent>
                <Form {...secondForm}>
                  <form
                    onSubmit={secondForm.handleSubmit(handleSecondFormSubmit)}
                    className="space-y-8"
                  >
                    <div className="grid grid-cols-1 md:grid-cols-3 gap-8">
                      <FormField
                        control={secondForm.control}
                        name="name"
                        render={({ field }) => (
                          <FormItem>
                            <FormLabel>First name</FormLabel>
                            <FormControl>
                              <Input placeholder="First name" {...field} />
                            </FormControl>
                            <FormMessage />
                          </FormItem>
                        )}
                      />
                      <FormField
                        control={secondForm.control}
                        name="surname"
                        render={({ field }) => (
                          <FormItem>
                            <FormLabel>Last name</FormLabel>
                            <FormControl>
                              <Input placeholder="Last name" {...field} />
                            </FormControl>
                            <FormMessage />
                          </FormItem>
                        )}
                      />
                      <FormField
                        control={secondForm.control}
                        name="email"
                        render={({ field }) => (
                          <FormItem>
                            <FormLabel>Email</FormLabel>
                            <FormControl>
                              <Input
                                type="email"
                                placeholder="Email"
                                {...field}
                              />
                            </FormControl>
                            <FormMessage />
                          </FormItem>
                        )}
                      />
                      <FormField
                        control={secondForm.control}
                        name="password"
                        render={({ field }) => (
                          <FormItem>
                            <FormLabel>Password</FormLabel>
                            <FormControl>
                              <Input
                                type="password"
                                placeholder="Password"
                                {...field}
                              />
                            </FormControl>
                            <FormMessage />
                          </FormItem>
                        )}
                      />
                      <FormField
                        control={secondForm.control}
                        name="age"
                        render={({ field }) => (
                          <FormItem>
                            <FormLabel>Your age</FormLabel>
                            <FormControl>
                              <Input placeholder="Age" {...field} />
                            </FormControl>
                            <FormMessage />
                          </FormItem>
                        )}
                      />
                      <FormField
                        control={secondForm.control}
                        name="phone"
                        render={({ field }) => (
                          <FormItem>
                            <FormLabel>Phone</FormLabel>
                            <FormControl>
                              <Input placeholder="Phone" {...field} />
                            </FormControl>
                            <FormMessage />
                          </FormItem>
                        )}
                      />
                      <FormField
                        control={secondForm.control}
                        name="country"
                        render={({ field }) => (
                          <FormItem>
                            <FormLabel>Country</FormLabel>
                            <FormControl>
                              <Input placeholder="Country" {...field} />
                            </FormControl>
                            <FormMessage />
                          </FormItem>
                        )}
                      />
                      <FormField
                        control={secondForm.control}
                        name="gender"
                        render={({ field }) => (
                          <FormItem>
                            <FormLabel>Gender</FormLabel>
                            <FormControl>
                              <Input placeholder="Gender" {...field} />
                            </FormControl>
                            <FormMessage />
                          </FormItem>
                        )}
                      />
                    </div>

                    <Button type="submit" className="w-full">
                      Create an account
                    </Button>
                    <Button variant="outline" className="w-full">
                      Sign up with GitHub
                    </Button>
                  </form>
                </Form>
                <div className="mt-4 text-center text-sm">
                  Already have an account?{" "}
                  <Link to="/" className="underline">
                    Sign in
                  </Link>
                </div>
              </AccordionContent>
            </AccordionItem>
          </Accordion>
        </CardContent>
      </Card>
    </div>
  );
}
