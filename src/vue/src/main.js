import {createApp} from 'vue'
import App from './App.vue'
import router from '@/router'
import ElementPlus from 'element-plus'
import VueCookies from 'vue-cookies';
import 'element-plus/dist/index.css'
import '@/assets/style/common.css'


let app = createApp(App);

const debounce = (fn, delay) => {
    let timer = null;
    return function () {
        let context = this;
        let args = arguments;
        clearTimeout(timer);
        timer = setTimeout(function () {
            fn.apply(context, args);
        }, delay);
    }
}

const _ResizeObserver = window.ResizeObserver;
window.ResizeObserver = class ResizeObserver extends _ResizeObserver {
    constructor(callback) {
        callback = debounce(callback, 16);
        super(callback);
    }
}

// 全局安装路由组讲
app.use(router).use(ElementPlus).mount('#app');
app.use(VueCookies);