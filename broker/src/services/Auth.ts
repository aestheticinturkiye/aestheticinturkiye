import service from "@/api/axios";
import useAuth from "@/store/store";
import axios from "axios";

export const login = async (values) => {
  const response = await axios(import.meta.env.VITE_API_URL + "/login", values);
  // return response
  return {
    accessToken: "ACCESS_TOKEN",
    user: {
      id: "123",
      name: "omer",
    },
  };
};

export const refresh = async () => {
  const reponse = await service("/refresh");
  return response;
};
