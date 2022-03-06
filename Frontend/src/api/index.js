import axios from "axios";
import { BH_API_BASE_URL, APT_API_BASE_URL } from "@/config";

function bhApiInstance() {
  const instance = axios.create({
    baseURL: BH_API_BASE_URL,
    headers: {
      "Content-type": "application/json",
    },
  });
  return instance;
}

function aptApiInstance() {
  const instance = axios.create({
    baseURL: APT_API_BASE_URL,
    headers: {
      "Content-type": " application/json",
    },
  });
  return instance;
}

export { bhApiInstance, aptApiInstance };
