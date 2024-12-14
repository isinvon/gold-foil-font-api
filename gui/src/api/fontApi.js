// src/api/fontApi.js
import axios from "axios";
import {ElMessage} from "element-plus";

const apiClient = axios.create({
    baseURL: "http://localhost:8080",
    timeout: 10000,
});

const handleError = (error) => {
    const message = error.response?.data || "服务器连接失败，请稍后重试";
    ElMessage.error(message);
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

/**
 * 传入一个字体名称, 判断字体是否存在
 * @returns {Promise<boolean>}
 * @param fontType
 */
export const fontIsExist = async (fontType) => {
    try {
        const response = await apiClient.get("/font/fontIsExist", {
            params: {fontType} // 使用 params 传递参数
        });
        // console.log(response) // debug
        return response.status === 200; // 如果 200 表示字体存在，可以保留
    } catch (error) {
        console.error("字体检测出错:", error);
        return false; // 遇到异常默认返回 false
    }
};
