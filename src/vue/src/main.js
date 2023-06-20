import {createApp} from 'vue';
import App from './App.vue';
import router from '@/router';
import ElementPlus from 'element-plus';
import VueCookies from 'vue-cookies';
import 'element-plus/dist/index.css';
import 'element-ui/lib/theme-chalk/index.css'
import '@/assets/style/common.css';
import VueWechatTitle from 'vue-wechat-title';


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
    };
};

const _ResizeObserver = window.ResizeObserver;
window.ResizeObserver = class ResizeObserver extends _ResizeObserver {
    constructor(callback) {
        callback = debounce(callback, 16);
        super(callback);
    }
};

app.use(router).use(ElementPlus).use(VueCookies).use(VueWechatTitle).mount('#app');
