// 导入用来创建路由和确定路由模式的两个方法
import {createRouter, createWebHashHistory} from 'vue-router'

const routes = [
    {
        path: '/',
        redirect: '/login',
        component: () => import('@/views/login'),
        meta: {keepAlive: false}
    },
    {
        name: 'login',
        path: '/login',
        component: () => import('@/views/login'),
        meta: {keepAlive: false}
    },
]

// 创建路由实例并传递 `routes` 配置
// 我们在这里使用 html5 的路由模式，url中不带有#，部署项目的时候需要注意。
const router = createRouter({
    history: createWebHashHistory(),
    base: 'wjxbx',
    routes,
})

// 讲路由实例导出
export default router