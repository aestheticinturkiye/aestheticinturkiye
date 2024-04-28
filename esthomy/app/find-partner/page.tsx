// "use client";

import { zodResolver } from "@hookform/resolvers/zod";
import { useForm } from "react-hook-form";
import * as z from "zod";

import { Step, Stepper, useStepper } from "@/components/stepper";
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
import { toast } from "@/components/ui/use-toast";
import { useState } from "react";
import SignUpPage from "../signup/page";
import { createClient } from "@/utils/supabase/server";
import { findBroker } from "../actions/actions";

const steps = [
  { label: "About yourself", description: "Description 1" },
  { label: "Additional informations", description: "Description 2" },
  { lable: "Sign up", description: "Email and Password" },
];

const schema = z.object({});

export default async function StepperForm() {
  const supabase = createClient();

  const { data, error } = await supabase.auth.getUser();
  return (
    // <div className="mx-auto max-w-xxl">
    //   <Stepper orientation={"vertical"} initialStep={0} steps={steps}>
    //     {steps.map((stepProps, index) => {
    //       if (index === 0) {
    //         return (
    //           <Step key={stepProps.label} {...stepProps}>
    //             <div className="bg-black">
    //               <FirstStepForm />
    //             </div>
    //           </Step>
    //         );
    //       }
    //       if (index === 1) {
    //         return (
    //           <Step key={stepProps.label} {...stepProps}>
    //             <SecondStepForm />
    //           </Step>
    //         );
    //       }
    //       return (
    //         <Step key={stepProps.label} {...stepProps}>
    //           <SignUpPage />
    //         </Step>
    //       );
    //     })}
    //     <MyStepperFooter />
    //   </Stepper>
    // </div>

    <form action={findBroker}>
      <button>Send</button>
    </form>
  );
}

// const FirstFormSchema = z.object({
//   username: z.string().min(2, {
//     message: "Username must be at least 2 characters.",
//   }),
// });

// function FirstStepForm() {
//   const { nextStep } = useStepper();

//   const form = useForm<z.infer<typeof FirstFormSchema>>({
//     resolver: zodResolver(FirstFormSchema),
//     defaultValues: {
//       username: "",
//     },
//   });

//   function onSubmit(_data: z.infer<typeof FirstFormSchema>) {
//     nextStep();
//     toast({
//       title: "First step submitted!",
//     });
//   }

//   return (
//     <Form {...form}>
//       <form onSubmit={form.handleSubmit(onSubmit)} className="space-y-6">
//         <FormField
//           control={form.control}
//           name="username"
//           render={({ field }) => (
//             <FormItem>
//               <FormLabel>Username</FormLabel>
//               <FormControl>
//                 <Input placeholder="shadcn" {...field} />
//               </FormControl>
//               <FormDescription>
//                 This is your public display name.
//               </FormDescription>
//               <FormMessage />
//             </FormItem>
//           )}
//         />
//         <StepperFormActions />
//       </form>
//     </Form>
//   );
// }

// const SecondFormSchema = z.object({
//   password: z.string().min(8, {
//     message: "Password must be at least 8 characters.",
//   }),
// });

// function SecondStepForm() {
//   const { nextStep } = useStepper();

//   const form = useForm<z.infer<typeof SecondFormSchema>>({
//     resolver: zodResolver(SecondFormSchema),
//     defaultValues: {
//       password: "",
//     },
//   });

//   function onSubmit(_data: z.infer<typeof SecondFormSchema>) {
//     nextStep();
//     toast({
//       title: "Second step submitted!",
//     });
//   }

//   return (
//     <Form {...form}>
//       <form onSubmit={form.handleSubmit(onSubmit)} className="space-y-6">
//         <FormField
//           control={form.control}
//           name="password"
//           render={({ field }) => (
//             <FormItem>
//               <FormLabel>Password</FormLabel>
//               <FormControl>
//                 <Input type="password" {...field} />
//               </FormControl>
//               <FormDescription>This is your private password.</FormDescription>
//               <FormMessage />
//             </FormItem>
//           )}
//         />
//         <StepperFormActions />
//       </form>
//     </Form>
//   );
// }

// function StepperFormActions() {
//   const {
//     prevStep,
//     resetSteps,
//     isDisabledStep,
//     hasCompletedAllSteps,
//     isLastStep,
//     isOptionalStep,
//   } = useStepper();

//   return (
//     <div className="w-full flex justify-end gap-2">
//       {hasCompletedAllSteps ? (
//         <Button size="sm" onClick={resetSteps}>
//           Reset
//         </Button>
//       ) : (
//         <>
//           <Button
//             disabled={isDisabledStep}
//             onClick={prevStep}
//             size="sm"
//             variant="secondary"
//           >
//             Prev
//           </Button>
//           <Button size="sm">
//             {isLastStep ? "Finish" : isOptionalStep ? "Skip" : "Next"}
//           </Button>
//         </>
//       )}
//     </div>
//   );
// }

// function MyStepperFooter() {
//   const { activeStep, resetSteps, steps } = useStepper();

//   if (activeStep !== steps.length) {
//     return null;
//   }

//   return (
//     <div className="flex items-center justify-end gap-2">
//       <Button onClick={resetSteps}>Reset Stepper with Form</Button>
//     </div>
//   );
// }
