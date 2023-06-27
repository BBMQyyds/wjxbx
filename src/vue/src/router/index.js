// 导入用来创建路由和确定路由模式的两个方法
import {createRouter, createWebHashHistory} from 'vue-router'

const routes = [
    {
        path: '/',
        redirect: '/login',
        component: () => import('@/views/login'),
        meta: {
            keepAlive: false,
            title: '问卷星不星-登录'
        }
    },
    {
        name: 'index',
        path: '/index',
        component: () => import('@/views/index'),
        meta: {
            keepAlive: false,
            title: '问卷星不星-首页'
        }
    },
    {
        name: 'login',
        path: '/login',
        component: () => import('@/views/login'),
        meta: {
            keepAlive: false,
            title: '问卷星不星-登录'
        }
    },
    {
        name: 'project',
        path: '/project',
        component: () => import('@/views/home/project'),
        meta: {
            keepAlive: false,
            title: '问卷星不星-项目管理'
        }
    },
    {
        name: 'questionnaire',
        path: '/questionnaire',
        component: () => import('@/views/home/questionnaire'),
        meta: {
            keepAlive: false,
            title: '问卷星不星-问卷管理'
        }
    },
    {
        name: 'question',
        path: '/question',
        component: () => import('@/views/home/question'),
        meta: {
            keepAlive: false,
            title: '问卷星不星-题目管理'
        }
    },
    {
        name: 'answer',
        path: '/answer',
        component: () => import('@/views/home/answer'),
        meta: {
            keepAlive: false,
            title: '问卷星不星-问卷填写'
        }
    },
    {
        name: 'user',
        path: '/user',
        component: () => import('@/views/home/user'),
        meta: {
            keepAlive: false,
            title: '问卷星不星-用户管理'
        }
    },
    {
        name: 'answerList',
        path: '/answerList',
        component: () => import('@/views/home/answerList'),
        meta: {
            keepAlive: false,
            title: '问卷星不星-答卷列表'
        }
    },
    {
        name: 'register',
        path: '/register',
        component: () => import('@/views/register'),
        meta: {
            keepAlive: false,
            title: '问卷星不星-注册'
        }
    },
    {
        name: 'updatePwd',
        path: '/updatePwd',
        component: () => import('@/views/updatePwd'),
        meta: {
            keepAlive: false,
            title: '问卷星不星-修改密码'
        }
    }
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