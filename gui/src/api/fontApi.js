// src/api/fontApi.js
import axios from "axios";

const apiClient = axios.create({
    baseURL: "http://localhost:8080",
    timeout: 10000,
});

const handleError = (error) => {
    console.error("API 请求出错:", error);
    throw error;
};

export const getSystemFonts = async (params) => {
    try {
        const response = await apiClient.get("/font/getSystemFonts");
        // console.log("getSystemFonts 请求成功"); // debug
        return response.data;
    } catch (error) {
        handleError(error);
    }
};

