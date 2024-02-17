import { useEffect, useState } from "react";
import { Message } from "./components/message";
import { accounts, mails } from "./data";

export default function MailPage() {
  const [defaultLayout, setDefaultLayout] = useState();
  const [defaultCollapsed, setDefaultCollapsed] = useState();

  useEffect(() => {
    const cookies = document.cookie.split(";").map((cookie) => cookie.trim());
    const layoutCookie = cookies.find((cookie) =>
      cookie.startsWith("react-resizable-panels:layout=")
    );
    const collapsedCookie = cookies.find((cookie) =>
      cookie.startsWith("react-resizable-panels:collapsed=")
    );

    if (layoutCookie) {
      console.log("layoutCookie", layoutCookie);
      const layoutValue = layoutCookie.split("=")[1];
      setDefaultLayout(JSON.parse(layoutValue));
    }

    if (collapsedCookie) {
      console.log("collapsedCookie", collapsedCookie);
      const collapsedValue = collapsedCookie.split("=")[1];
      console.log("collapsedValue", collapsedValue);
      collapsedValue === "undefined"
        ? setDefaultCollapsed(undefined)
        : setDefaultCollapsed(JSON.parse(collapsedValue));
      // setDefaultCollapsed(JSON.parse("undefined"));
    }
  }, []);

  return (
    <>
      <div className="md:hidden">
        <img
          src="/examples/mail-dark.png"
          width={1280}
          height={727}
          alt="Mail"
          className="hidden dark:block"
        />
        <img
          src="/examples/mail-light.png"
          width={1280}
          height={727}
          alt="Mail"
          className="block dark:hidden"
        />
      </div>
      <div className="hidden flex-col md:flex h-screen">
        <Message
          accounts={accounts}
          mails={mails}
          defaultLayout={defaultLayout}
          defaultCollapsed={defaultCollapsed}
          navCollapsedSize={4}
        />
      </div>
    </>
  );
}
