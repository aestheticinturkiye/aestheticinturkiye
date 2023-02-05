import { PrismaClient } from "@prisma/client";
import { Session } from "next-auth";

export type GraphQLContext = {
  session: Session | null;
  prisma: PrismaClient;
  //pubsub:
};

/**
 * Users
 */
// export type Session = {
//   user: User;
//   expires
// };
// export type User = {
//   id: string;
//   name: string;
//   surname: string;
//   image: string;
// };
export type CreateUserResponse = {
  success?: boolean;
  error?: string;
};
