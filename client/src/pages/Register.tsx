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
import { motion } from "framer-motion";
// Define your validation schemas

const firstFormSchema = z.object({
  aestheticType: z.string().min(1, "Aesthetic type is required"),
  description: z.string(),
  gender: z.string().min(1, "Gender is required"),
  age: z.string().min(1, "Age is required"),
  files: z.instanceof(FileList),
});

const secondFormSchema = z.object({
  preferedDate: z.date(),
  preferedCity: z.string().min(1),
  neededAccommodation: z.boolean(),
  neededTransportation: z.boolean(),
});

const thirdFormSchema = z.object({
  name: z.string().min(1, "First name is required"),
  surname: z.string().min(1, "Last name is required"),
  email: z.string().email("Invalid email"),
  password: z.string().min(6, "Password must be at least 6 characters"),
  phone: z.string().min(1, "Phone is required"),
  country: z.string().min(1, "Country is required"),
});

export function Register() {
  const [activeForm, setActiveForm] = useState(1);
  const setAccessToken = useAuth((state) => state.setAccessToken);
  const setUser = useAuth((state) => state.setUser);

  const firstForm = useForm({
    defaultValues: {
      aestheticType: "",
      description: "",
      gender: "",
      age: "",
      files: typeof window === "undefined" ? z.any() : z.instanceof(FileList),
    },
    resolver: zodResolver(firstFormSchema),
  });

  const secondForm = useForm({
    defaultValues: {
      preferedDate: null,
      preferedCity: "",
      neededAccommodation: false,
      neededTransportation: false,
    },
    resolver: zodResolver(secondFormSchema),
  });

  const thirdForm = useForm({
    defaultValues: {
      name: "",
      surname: "",
      email: "",
      password: "",
      phone: "",
      country: "",
    },
    resolver: zodResolver(thirdFormSchema),
  });

  const handleFirstFormSubmit = (data) => {
    console.log("data", data);
    // firstForm.setValue("files", e.target[4].files);
    // console.log("firstForm.getValues()", firstForm.getValues());
    setActiveForm(2);
  };

  const handleSecondFormSubmit = (data) => {
    setActiveForm(3);
  };
  const handleThirdFormSubmit = async (data) => {
    console.log("data", data);
    const { age, gender } = firstForm.getValues();
    data.age = age;
    data.gender = gender;

    console.log("data", data);
    await register(data);
    const loginCredentials = {
      emailAddress: data.email,
      password: data.password,
    };

    const loginResponse = await login(loginCredentials);

    setAccessToken(loginResponse.data.accessToken);
    const user = await getSignedUser();
    setUser({ ...user });
    const partnerRequestData = {
      ...firstForm.getValues(),
      ...secondForm.getValues(),
    };
    console.log("partnerRequestData", partnerRequestData);
    const res = await findPartnerRequest(partnerRequestData);
    console.log("res", res);
  };
  const fileRef = firstForm.register("files");

  return (
    <div className="flex justify-center items-center h-screen bg-gray-100">
      {activeForm === 1 && (
        <motion.div
          initial={{ x: activeForm === 1 ? "50%" : "-50%", opacity: 0 }}
          animate={{ x: 0, opacity: 1 }}
          transition={{ duration: 0.3, ease: "easeInOut" }}
        >
          <Card className="mx-auto  max-w-xl w-full">
            <CardHeader>
              <CardTitle className="text-xl">Your wants</CardTitle>
              <CardDescription>
                Enter your information to find a partner and create an account
              </CardDescription>
            </CardHeader>
            <CardContent>
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
                      control={firstForm.control}
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
                    <FormField
                      control={firstForm.control}
                      name="files"
                      render={({ field }) => (
                        <FormItem>
                          <FormLabel>Files</FormLabel>
                          <FormControl>
                            <Input
                              multiple
                              type="file"
                              placeholder="Files"
                              {...fileRef}
                            />
                          </FormControl>
                          <FormMessage />
                        </FormItem>
                      )}
                    />
                  </div>
                  <Button type="submit" className="w-full">
                    Next
                  </Button>
                </form>
              </Form>

              <div className="mt-4 text-center text-sm">
                Already have an account?{" "}
                <Link to="/" className="underline">
                  Sign in
                </Link>
              </div>
            </CardContent>
          </Card>
        </motion.div>
      )}

      {activeForm === 2 && (
        <motion.div
          initial={{ x: activeForm === 2 ? "50%" : "-50%", opacity: 0 }}
          animate={{ x: 0, opacity: 1 }}
          transition={{ duration: 0.3, ease: "easeInOut" }}
        >
          <Card className="mx-auto max-w-xl w-full">
            <CardHeader>
              <CardTitle className="text-xl">Your Needs</CardTitle>
              <CardDescription>
                Enter your information to find a partner and create an account
              </CardDescription>
            </CardHeader>
            <CardContent>
              <Form {...secondForm}>
                <form
                  onSubmit={secondForm.handleSubmit(handleSecondFormSubmit)}
                  className="space-y-8"
                >
                  <div className="grid grid-cols-1 md:grid-cols-2 gap-8">
                    <FormField
                      control={secondForm.control}
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
                            {/* <FormDescription>
                              Your date of birth is used to calculate your age.
                            </FormDescription> */}
                          </div>
                          <FormMessage />
                        </FormItem>
                      )}
                    />
                    <FormField
                      control={secondForm.control}
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
                      control={secondForm.control}
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
                      control={secondForm.control}
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

                  <Button className="w-full">Next</Button>
                  <Button
                    onClick={() => setActiveForm(1)}
                    variant="outline"
                    className="w-full"
                  >
                    Previous
                  </Button>
                </form>
              </Form>

              <div className="mt-4 text-center text-sm">
                Already have an account?{" "}
                <Link to="/" className="underline">
                  Sign in
                </Link>
              </div>
            </CardContent>
          </Card>
        </motion.div>
      )}

      {activeForm === 3 && (
        <motion.div
          initial={{ x: activeForm === 3 ? "50%" : "-50%", opacity: 0 }}
          animate={{ x: 0, opacity: 1 }}
          transition={{ duration: 0.3, ease: "easeInOut" }}
        >
          <Card className="mx-auto min-w-2xl max-w-2xl w-full">
            <CardHeader>
              <CardTitle className="text-xl">Find a partner</CardTitle>
              <CardDescription>
                Enter your information to find a partner and create an account
              </CardDescription>
            </CardHeader>
            <CardContent>
              <Form {...thirdForm}>
                <form
                  onSubmit={thirdForm.handleSubmit(handleThirdFormSubmit)}
                  className="space-y-8"
                >
                  <div className="grid grid-cols-1 md:grid-cols-2 gap-8">
                    <FormField
                      control={thirdForm.control}
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
                      control={thirdForm.control}
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
                      control={thirdForm.control}
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
                      control={thirdForm.control}
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
                      control={thirdForm.control}
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
                      control={thirdForm.control}
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
                  </div>

                  <Button type="submit" className="w-full">
                    Find Partner
                  </Button>
                  <Button
                    onClick={() => setActiveForm(2)}
                    variant="outline"
                    className="w-full"
                  >
                    Previous
                  </Button>
                </form>
              </Form>

              <div className="mt-4 text-center text-sm">
                Already have an account?{" "}
                <Link to="/" className="underline">
                  Sign in
                </Link>
              </div>
            </CardContent>
          </Card>
        </motion.div>
      )}
    </div>
  );
}
