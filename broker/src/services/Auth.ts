import service, { serviceForLogin } from "@/api/axios";
import useAuth from "@/store/store";
import axios from "axios";
const url = import.meta.env.VITE_API_URL;
export const login = async (values) => {
  const data = {
    emailAddress: values.email,
    password: values.password,
  };
  const response = await serviceForLogin.post(url + "/auth/login", data);
  useAuth.setState({ accessToken: "Bearer " + response.data.accessToken });
  console.log("reponse", response);
  return response;
};

export const refresh = async () => {
  const response = await service.post("/auth/authenticateWithRefreshToken");
  console.log("response", response);
  return response;
};
