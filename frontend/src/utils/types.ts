export type CreateUserData = {
  createUser: {
    succes: boolean;
    error: string;
  };
};
export type CreateUserVariables = {
  name: string;
  surname: string;
  gender: String;
  country: String;
  phone: String;
  hadOperationBefore: Boolean;
  currentOperation: String;
};
