import { refresh } from "@/services/Auth";
import { getSignedUser } from "@/services/User";
import useAuth from "@/store/store";
import axios from "axios";

const BASE_URL = import.meta.env.VITE_API_URL;

const service = axios.create({ baseURL: BASE_URL, withCredentials: true });
export const serviceForLogin = axios.create({
  baseURL: BASE_URL,
  withCredentials: true,
});

const createSession = async () => {
  console.log("create session");
  const response = await refresh();
  const accessToken = "Bearer " + response.data.accessToken;
  useAuth.setState({ accessToken });
  const user = await getSignedUser();
  useAuth.setState({ user: { ...user.broker } });
  return accessToken;
};

let isGetActiveSessionRequest = false;
let requestQueue = [];

const callRequestsFromQueue = (token) => {
  requestQueue.forEach((sub) => sub(token));
};
const addRequestToQueue = (sub) => {
  requestQueue.push(sub);
};
const clearQueue = () => {
  requestQueue = [];
};

service.interceptors.response.use(null, (error) => {
  const { response = {}, config: sourceConfig } = error;

  if (response.status === 403) {
    if (!isGetActiveSessionRequest) {
      isGetActiveSessionRequest = true;
      createSession()
        .then((token) => {
          isGetActiveSessionRequest = false;
          callRequestsFromQueue(token);
          clearQueue(); // and clean queue
        })
        .catch((e) => {
          isGetActiveSessionRequest = false; // Very important!
          console.error("Create session error %s", e.message);
          clearQueue();
        });
    }

    const retryRequest = new Promise((resolve) => {
      addRequestToQueue((token) => {
        console.log("token", token);
        console.log(
          "Retry with new session context %s request to %s",
          sourceConfig.method,
          sourceConfig.url
        );
        sourceConfig.headers["Authorization"] = token;
        resolve(axios(sourceConfig));
      });
    });

    return retryRequest;
  } else {
    return Promise.reject(error);
  }
});

service.interceptors.request.use(
  (config) => {
    console.log("config", config);
    if (!config.headers["Authorization"]) {
      const accessToken = useAuth.getState().accessToken;
      console.log("accessToken", accessToken);
      config.headers["Authorization"] = accessToken;
    }
    return config;
  },
  (error) => {
    return Promise.reject(error);
  }
);

export default service;
