import service from "@/api/axios";
import urls from "@/api/urls/User";

export const getSignedUser = async () => {
  //   const response = await service.get(urls.getSignedUser);
  const response = {
    name: "omer",
    surname: "colak",
  };
  return response;
};
