import service from "@/api/axios";

export const findPartnerRequest = async (data) => {
  const response = await service.post("/find-partner-request", data);
  console.log("response", response);
  return response;
};
