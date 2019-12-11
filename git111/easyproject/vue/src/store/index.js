import Vue from 'vue'
import Vuex from 'vuex'

//使用模块的方式引入表码动态全局加载模块
import codeCacher from "./code-cacher/index";


Vue.use(Vuex);

const debug = process.env.NODE_ENV !== 'production';

// 外围构建树结构
export default new Vuex.Store({
  modules: {//模块引入
    codeCacher
  },
  strict: debug
});
