import service from "@/api/axios";

export const findPartnerRequest = async () => {
  const response = await service.post("/find-partner-request");
  console.log("response", response);
  return response;
};
