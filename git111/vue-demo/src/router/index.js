import Vue from 'vue'
import Router from 'vue-router'
import Index from '../pages/index';
import login from '../pages/login/index';

Vue.use(Router);

let routeData = [
  {
    path: '/',
    name: 'Index',
    component: Index
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
  if(to.path=="/login"||to.path==='/'){
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
