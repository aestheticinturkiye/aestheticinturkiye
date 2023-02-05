/* eslint-disable import/no-anonymous-default-export */
import { gql } from "@apollo/client";

export default {
  Queries: {},
  Mutations: {
    createUser: gql`
      mutation CreateUser(
        $name: String
        $surname: String
        $gender: String
        $country: String
        $phone: String
        $hadOperationBefore: Boolean
        $currentOperation: String
      ) {
        createUser(
          name: $name
          surname: $surname
          gender: $gender
          country: $country
          phone: $phone
          hadOperationBefore: $hadOperationBefore
          currentOperation: $currentOperation
        ) {
          success
          error
        }
      }
    `,
  },
  Subscriptions: {},
};
