import { signIn, useSession } from "next-auth/react";
export default function Home() {
  const { data } = useSession();
  console.log(data);
  return <button onClick={() => signIn("google")}>Sign in</button>;
}
