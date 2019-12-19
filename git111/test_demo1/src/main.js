// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import router from './router'
import axios from 'axios';
import antDesign from 'ant-design-vue';
import 'ant-design-vue/dist/antd.css';

Vue.prototype.$axios = axios;    //全局注册，使用方法为:this.$axios
Vue.use(antDesign);


Vue.config.productionTip = false

/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  components: { App },
  template: '<App/>'
})
