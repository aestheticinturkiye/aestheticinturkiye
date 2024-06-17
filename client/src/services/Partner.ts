import service from "@/api/axios";

const getFilesArray = (files) => {
  const filesArray = [];
  for (let i = 0; i < files.length; i++) {
    console.log("files[i]", files[i]);
    filesArray.push(files[i]);
  }
  return filesArray;
};

export const findPartnerRequest = async (data) => {
  const formData = new FormData();

  // Append all first form data
  const partnerReqData = {
    aestheticType: data.aestheticType,
    description: data.description,
    preferedDate: data.preferedDate,
    preferedCity: data.preferedCity,
    neededAccommodation: data.neededAccommodation,
    neededTransportation: data.neededTransportation,
  };

  formData.append("partnerReq", JSON.stringify(partnerReqData));
  // formData.append("description", data.description);

  // Append files
  const filesArray = getFilesArray(data.files);
  filesArray.forEach((file, index) => {
    formData.append(`files`, file);
  });

  // Append all second form data
  // formData.append("preferedDate", data.preferedDate.toISOString());
  // formData.append("preferedCity", data.preferedCity);
  // formData.append("neededAccommodation", data.neededAccommodation);
  // formData.append("neededTransportation", data.neededTransportation);

  const response = await service.post("/find-partner-request", formData, {
    headers: {
      "Content-Type": "multipart/form-data",
    },
  });
  console.log("response", response);
  return response;
};
