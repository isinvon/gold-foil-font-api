// src/api/goldFoilApi.js
import axios from "axios";

const apiClient = axios.create({
  baseURL: "http://localhost:8080",
  timeout: 10000,
});

const handleError = (error) => {
  console.error("API 请求出错:", error);
  throw error;
};

export const generateGoldFoilImage = async (params) => {
  try {
    const response = await apiClient.get("/api/gold-foil-image", {
      params,
      responseType: 'blob',  // 确保返回的是Blob类型
    });
    // console.log("Image 请求成功"); // debug
    return response.data;  // 返回的就是Blob类型
  } catch (error) {
    handleError(error);
  }
};


export const generateGoldFoilSVG = async (params) => {
  try {
    const response = await apiClient.get("/api/gold-foil-svg", {
      params,
      responseType: 'text',  // 确保返回的是文本类型
    });
    // console.log("SVG 请求成功"); // debug
    return response.data;  // 返回的是SVG的文本内容
  } catch (error) {
    handleError(error);
  }
};
