import service from "@/api/axios";

export const getPartnerRequests = async () => {
  const params = { page: 0, size: 10 };
  const response = await service.get("/find-partner-request/all", { params });
  console.log("response", response);
  return response;
};
