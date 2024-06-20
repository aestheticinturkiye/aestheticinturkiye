import service from "@/api/axios";
import urls from "@/api/urls/proposals";

export const sendProposal = async (params) => {
  const response = await service.post(urls.proposal, params);
  return response;
};

export const getProposals = async () => {
  const response = await service.get(urls.proposal);
  return response.data;
};
