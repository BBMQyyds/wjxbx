import request from "@/api";

// 查询用户列表
export function test(query) {
    return request({
        url: '/test',
        method: 'get',
        params: query
    })
}
