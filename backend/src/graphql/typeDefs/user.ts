import { gql } from "apollo-server-core";

const typeDefs = gql`
  type User {
    id: String
    name: String
    surname: String
    gender: String
    country: String
    phone: String
    hadOperationBefore: Boolean
    currentOperation: String
  }

  type Query {
    searchUsers(id: String!): [User]
  }

  type Mutation {
    createUser(
      name: String!
      surname: String!
      gender: String!
      country: String!
      phone: String!
      hadOperationBefore: Boolean!
      currentOperation: String!
    ): CreateUserResponse
  }

  type CreateUserResponse {
    success: Boolean
    error: String
  }
`;

export default typeDefs;
