import React from "react";
import { Nav } from "@/components/navbar";
import {
  AlertCircle,
  Archive,
  ArchiveX,
  File,
  Inbox,
  MessagesSquare,
  PenBox,
  Search,
  Send,
  ShoppingCart,
  Trash2,
  Users2,
} from "lucide-react";

const Home = () => {
  return (
    <Nav
      isCollapsed={true}
      links={[
        {
          title: "Inbox",
          label: "128",
          icon: Inbox,
          variant: "default",
        },
        {
          title: "Drafts",
          label: "9",
          icon: File,
          variant: "ghost",
        },
        {
          title: "Sent",
          label: "",
          icon: Send,
          variant: "ghost",
        },
        {
          title: "Junk",
          label: "23",
          icon: ArchiveX,
          variant: "ghost",
        },
        {
          title: "Trash",
          label: "",
          icon: Trash2,
          variant: "ghost",
        },
        {
          title: "Archive",
          label: "",
          icon: Archive,
          variant: "ghost",
        },
      ]}
    />
  );
};

export default Home;
