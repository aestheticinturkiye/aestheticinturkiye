import userResolvers from "./user";
import merge from "lodash.merge";

const resolvers = merge({}, userResolvers); // merge all resolvers

export default resolvers;
