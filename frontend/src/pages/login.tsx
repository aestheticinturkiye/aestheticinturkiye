import React from "react";
import { signIn, useSession } from "next-auth/react";
const Login = () => {
  const { data } = useSession();
  console.log(data);
  console.log(process.env.MONGODB_URI);
  return <button onClick={() => signIn("google")}>Sign in</button>;
};

export default Login;
