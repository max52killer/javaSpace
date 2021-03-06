// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import router from './router'
//引入element-ui
import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'
import axios from 'axios'
import sysUtils from './utils/sys-utils';
import utils from "./utils/utils";
import store from "./store/index";
import bmLoader from "./utils/bmLoad";
//引入其它样式文件
import './assets/css/style.css';
import './assets/css/layout.css'

Vue.use(ElementUI);
Vue.prototype.$axios = axios;   //全局注册，使用方法为:this.$axios
Vue.prototype.$sysUtils=sysUtils;
Vue.prototype.$t=utils;
Vue.prototype.$bm=bmLoader;//将表码对象注册到全局 可使用$bm的形式调用其中的方法


Vue.config.productionTip = false;

/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  store,
  components: { App },
  template: '<App/>'
});
