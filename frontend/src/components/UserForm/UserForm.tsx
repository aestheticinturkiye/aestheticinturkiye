import { CreateUserData, CreateUserVariables } from "@/utils/types";
import { useMutation } from "@apollo/client";
import React, { useState } from "react";
import UserOperations from "../../graphql/operations/user";

type Props = {};

const UserForm = (props: Props) => {
  const [name, setName] = useState("");
  const [createUser, { data, loading, error }] = useMutation<
    CreateUserData,
    CreateUserVariables
  >(UserOperations.Mutations.createUser);
  console.log(data, loading, error);

  const onSubmit = async () => {
    console.log(name);
    if (!name) {
      return;
    }
    try {
      await createUser({
        variables: {
          name: name,
          surname: "surname",
          gender: "gender",
          country: "country",
          phone: "phone",
          hadOperationBefore: true,
          currentOperation: "currentOperation",
        },
      });
      console.log(data, loading, error);
    } catch (error) {}
  };
  return (
    <div className=" container mx-auto px-16 pt-6 lg:px-32">
      <label
        htmlFor="price"
        className="block text-sm font-medium text-gray-700"
      >
        Name
      </label>
      <div className="relative mt-1 rounded-md shadow-sm ">
        <input
          type="text"
          name="name"
          id="name"
          className="block w-full rounded-md border-gray-300 pl-7 pr-12 focus:border-indigo-500 focus:ring-indigo-500 sm:text-sm"
          placeholder="name"
          value={name}
          onChange={(e) => setName(e.target.value)}
        />
      </div>
      <button
        type="submit"
        className="group relative flex w-full justify-center rounded-md border border-transparent bg-indigo-600 py-2 px-4 text-sm font-medium text-white hover:bg-indigo-700 focus:outline-none focus:ring-2 focus:ring-indigo-500 focus:ring-offset-2"
        onClick={onSubmit}
      >
        Send
      </button>{" "}
    </div>
  );
};

export default UserForm;
