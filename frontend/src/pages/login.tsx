import Auth from "@/components/Auth/Auth";
import Chat from "@/components/Chat/Chat";
import Profile from "@/components/Profile/Profile";
import { getSession, useSession } from "next-auth/react";
import { useRouter } from "next/router";
import { useEffect } from "react";

const Login = () => {
  const { data: session } = useSession();
  const router = useRouter();
  const reloadSession = () => {};
  useEffect(() => {
    if (session?.user?.name === "admin" /* CHANGE IT!!!!*/) {
      router.push("/profile");
      return;
    } else if (session?.user) {
      router.push("/user-form");
    }
  }, [session?.user]);

  return (
    <div className="">
      {!session?.user ? (
        <Auth session={session} reloadSession={reloadSession} />
      ) : (
        <div>Loading Page</div>
      )}
    </div>
  );
};

export async function getServerSideProps(context: any) {
  const session = await getSession(context);
  return {
    props: { session },
  };
}
export default Login;
