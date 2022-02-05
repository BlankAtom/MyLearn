import axios from "axios";
import {
    baseURL,
    messageDuration,
    requestTimeout,
} from "../config/settings";
import { Loading, Message } from "element-ui";
import qs from "qs";

const service = axios.create({
    baseURL,
    timeout: requestTimeout,
    headers: {
        "Content-Type": "application/x-www-form-urlencoded",
    },
});
let loadingInstance;
service.interceptors.request.use(
    (config) => {
        //config.headers["Content-Type"] = "application/x-www-form-urlencoded;charset=UTF-8";
        config.data = qs.stringify(config.data)
        return config;
    },
    (error) => {
        return Promise.reject(error);
    }
);

const errorMsg = (message) => {
    return Message({
        message: message,
        type: "error",
        duration: messageDuration,
    });
};

service.interceptors.response.use(
    (response) => {
        let {data} = response.data;
        return response;

    },
    (error) => {
        if (loadingInstance) {
            loadingInstance.close();
        }
        /*网络连接过程异常处理*/
        let { message } = error;
        console.log(message)
        switch (message) {
            case "Network Error":
                message = "后端接口连接异常";
                break;
            case "timeout":
                message = "后端接口请求超时";
                break;
            case "Request failed with status code":
                message = "后端接口" + message.substr(message.length - 3) + "异常";
                break;
        }
        errorMsg(message || "后端接口未知异常");
        return Promise.reject(error);
    }
);
export default service;
