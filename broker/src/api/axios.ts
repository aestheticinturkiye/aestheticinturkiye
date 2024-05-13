import { refresh } from "@/services/Auth";
import useAuth from "@/store/store";
import axios from "axios";

const BASE_URL = import.meta.env.VITE_API_URL;

const service = axios.create({ baseURL: BASE_URL, withCredentials: true });

const createSession = async () => {
  console.log("create session");
  const response = await refresh();
  useAuth.setState({ accessToken: response.data.accessToken });
  return response.data.accessToken;
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

  if (response.status === 401) {
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
    if (!config.headers["Authorization"]) {
      const accessToken = useAuth((state) => state.accessToken);
      config.headers["Authorization"] = `Bearer ${accessToken}`;
    }
    return config;
  },
  (error) => {
    return Promise.reject(error);
  }
);

export default service;
