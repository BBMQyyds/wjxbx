import axios from 'axios'
import {eventBus} from '@ai-zen/event-bus';

const request = axios.create({
    timeout: 10000,
    withCredentials: true,
    headers: {'Content-Type': 'application/json;charset=utf-8'}
})

request.interceptors.response.use(response => {
    // 对响应数据做处理
    return response;
}, error => {
    // 对响应错误做处理
    if (error.response.status === 302) {
        // 处理302状态码的操作
        eventBus.emit('login');
    }
    if (error.response.status === 403) {
        // 处理403状态码的操作
        eventBus.emit('role');
    }
    return Promise.reject(error);
});

const plainRequest = axios.create({
    timeout: 10000,
    withCredentials: true,
    headers: {'Content-Type': 'text/plain;charset=utf-8'}
})

plainRequest.interceptors.response.use(response => {
    // 对响应数据做处理
    return response;
}, error => {
    // 对响应错误做处理
    if (error.response.status === 302) {
        // 处理302状态码的操作
        eventBus.emit('login');
    }
    if (error.response.status === 403) {
        // 处理403状态码的操作
        eventBus.emit('role');
    }
    return Promise.reject(error);
});

export default request
export {plainRequest}
