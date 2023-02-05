import { Session } from "@prisma/client";
import { CreateUserResponse, GraphQLContext } from "../../utils/types";

const resolvers = {
  Query: {
    searchUsers: () => {},
  },
  Mutation: {
    createUser: async (
      _: any,
      args: {
        name: string;
        surname: string;
        gender: string;
        country: string;
        phone: string;
        hadOperationBefore: boolean;
        currentOperation: string;
      },
      context: GraphQLContext
    ) => {
      const { session, prisma } = context;

      if (!session?.user) {
        return {
          error: "Not authorized.",
        };
      }
      console.log("typeof", typeof session);

      console.log("session123123", session);

      const sessionCopy = { ...session };
      console.log("typeof", typeof session);

      console.log("sessionCopy", sessionCopy?.user?.name);

      const name = session.user.name;
      console.log("sessionCopy2", sessionCopy?.user?.name);

      console.log("session", session);

      try {
        console.log(session.user);
        console.log("user id ", name);
        console.log("heyyyy");
        const existingUser = await prisma.user.findUnique({
          where: {
            phone: args.phone,
          },
        });
        if (existingUser) {
          return {
            error: "This phone number is already taken.",
          };
        }
        await prisma.user.update({
          where: {
            //id,
          },
          data: {
            name: args.name,
            surname: args.surname,
            gender: args.gender,
            country: args.country,
            phone: args.phone,
            hadOperationBefore: args.hadOperationBefore,
            currentOperation: args.currentOperation,
          },
        });
        return { success: true };
      } catch (error: any) {
        console.log("createUser Error", error);
        return {
          error: error?.message,
        };
      }
    },
  },
};
export default resolvers;
