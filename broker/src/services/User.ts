import service from "@/api/axios";
import urls from "@/api/urls/user";

export const getSignedUser = async () => {
  const response = await service.get(urls.getSignedUser);
  return response.data;
};
