import Vue from 'vue'
import Router from 'vue-router'

//引入其它组件
// import $ from 'jquery' ;//vue引入jquery
// import "bootstrap";//引入bootstrop
// import iView from 'view-design';//引入iview
// import 'view-design/dist/styles/iview.css';    // 使用 CSS
import Index from '../pages/index';
import store from "../store/index";

// 懒加载
const Home = () => import("@/pages/index.vue");
const ndlz = () => import("@/components/ndlz/ndlz.vue");
const meteor = () => import("@/components/meteor/meteor-shower.vue");
const stars_shining = () => import("@/components/stars/stars_shining.vue");
const gdsz = () => import("@/components/gdsz/gdsz.vue");
const yh01 = () => import("@/components/dzyh/yh01.vue");
const qrj01 = () => import("@/components/qrj/qrj01.vue");
const pxdw = () => import("@/components/pxdw/pxdw.vue");
const ele_container = () => import("@/components/element-ui/ele_container.vue");
const login = () => import("@/components/login/login.vue");
const demo=() => import("@/components/demo/demo.vue");


Vue.use(Router);

const routerData = [{
  name: '工具组件系统',
  path: '/',
  redirect: '/index',
  leaf: true,
  component: Index,
  children: []
}, {
  path: '/index',
  component: Home,
  name: '入口页'
}, {
  path: '/ndlz',
  component: ndlz,
  name: '牛頓粒子',
}, {
  path: '/meteor',
  component: meteor,
  name: '流星',
}, {
  path: '/stars_shining',
  component: stars_shining,
  name: '星空',
}, {
  path: '/yh01',
  component: yh01,
  name: '烟花',
}, {
  path: '/gdsz',
  component: gdsz,
  name: '滚动时钟',
}, {
  path: '/qrj01',
  component: qrj01,
  name: '情人节',
}, {
  path: '/ele_container',
  component: ele_container,
  name: 'element-UI',
},
  {
    path: '/login',
    component: login,
    name: '登陆',
  },
  {
    path: '/demo',
    component: demo,
    name: '示例练习',
  }
];

const router = new Router({
  // mode: 'history',
  routes: routerData
});
//访问路由设置登陆拦截
router.beforeEach((to, from, next) => {
  if (to.path === '/login') {
    next();
  } else {
    let token = localStorage.getItem('Authorization');
    console.log("token::",token);
    if (token === null || token === '') {
      next('/login');
    } else {
      next();
    }
  }
});
export default router;
