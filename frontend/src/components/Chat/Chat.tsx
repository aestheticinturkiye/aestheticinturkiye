import { signOut } from "next-auth/react";

type ChatProps = {};

const Chat = (props: ChatProps) => {
  return (
    <div>
      Chat
      <button onClick={() => signOut()}>LOGOUT</button>
    </div>
  );
};

export default Chat;
