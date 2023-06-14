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
    if (error.response.status === 404) {
        // 处理404状态码的操作
        this.$message({
            message: '请求的资源不存在',
            type: 'error'
        });
    }
    return Promise.reject(error);
});

export default request
