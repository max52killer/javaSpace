import Vue from 'vue'
import $ from 'jquery' ;//引入jquery
import Router from 'vue-router'
import Index from '../pages/index'
import login from '../pages/login/index';

Vue.use(Router);

let routeData = [
  {
    path: '/',
    name: 'Index',
    component: Index,
    //重定向路由地址
    redirect: '/system-manage',
    children:[
      {
        path:"/system-manage",
        name:"system-manage",
        component:resolve => require(['../pages/system-manage/index.vue'], resolve),
        meta:{
          title:"系统管理",
          icon:"el-icon-s-ticket",
          description:"包含系统信息相关的信息管理"
        }
      },
      {
        path:"/api_documents",
        name:"api_documents",
        component:resolve => require(['../pages/api-documents/index.vue'], resolve),
        meta:{
          title:"文档管理",
          icon:"el-icon-document",
          description:"包含一些文档信息"
        }
      },
      {
        path:"/prototype_demo",
        name:"prototype_demo",
        component:resolve => require(['../pages/prototype-demo/index.vue'], resolve),
        meta:{
          title:"原型示例",
          icon:"el-icon-s-promotion",
          description:"包含一些特效原型的示例"
        }
      },
    ]
  },
  {
    path: '/login',
    name: 'login',
    component: login
  }
];

const router = new Router({
  routes: routeData
});


router.beforeEach((to, from, next) => {
    if(to.path=="/login"){
      next();
    }else{
      let token = sessionStorage.getItem('auth_token');
      if(token&&token!=""){
        next();
      }else{
        next("/login");
      }
    }
});
export default router;
