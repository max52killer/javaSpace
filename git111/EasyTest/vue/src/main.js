// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import router from './router'
import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'
import axios from 'axios';
import VueWorker from 'vue-worker';
// import qs from 'qs.js'
import {codemirror} from 'vue-codemirror-lite'
import $ from './assets/js/jquery_vendor'
import 'jquery-ui'
import store from './store/index'

//引入css




var VueCodeMirror = require('vue-codemirror-lite')
Vue.use(ElementUI);
Vue.prototype.$axios = axios;   //全局注册，使用方法为:this.$axios
// Vue.prototype.qs = qs           //全局注册，使用方法为:this.qs
Vue.use(VueWorker);     //全局注册 this.$worker


Vue.config.productionTip = false

/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  store,//将store注入this 后面可通过this.$store使用store
  components: {App},
  template: '<App/>'
});
// 添加请求拦截器，在请求头中加token
axios.interceptors.request.use(
  config => {
    if (localStorage.getItem('Authorization')) {
      config.headers.Authorization = localStorage.getItem('Authorization');
    }

    return config;
  },
  error => {
    return Promise.reject(error);
  }
);
