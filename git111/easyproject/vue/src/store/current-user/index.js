import * as types from './current-user-type';
import userApi from "../../api/userApi";

import Vue from 'vue'

var vm = new Vue();

const state={
  userInfoData:{
    current:{}
  }
};

const getters={
  currentUser:state=>state.userInfoData.current
};
// actions 改变状态的操作
const actions = {
  loadCodeData({commit}, callback) {
    commit(types.LOAD_USER_DATA, callback);
  },
  reloadCodeData({commit}, callback) {
    commit(types.RELOAD_USER_DATA, callback);
  },
  updateCodeData({commit}, user) {
    commit(types.UPDATE_USER_DATA, user);
  }
};

let loadingUserInfo = false;
// mutations 进行数据状态的变化
const mutations = {
  //使用 ES2015 风格的计算属性命名功能来使用一个常量作为函数名
  [types.LOAD_USER_DATA](state, callback) {
    if (!state.userInfoData.current || !state.userInfoData.current.id) {
      this.commit(types.RELOAD_USER_DATA,callback);
    }else{
      callback && callback(state.userInfoData.current);
    }
  },
  [types.RELOAD_USER_DATA](state, callback) {
    if(!loadingUserInfo){
      loadingUserInfo=true;
      if(!this.userInfoData.current&&!this.userInfoData.current._id){
        userApi.current().then(user=>{
          this.commit(types.UPDATE_USER_DATA,user);
          callback && callback(user);
          loadingUserInfo = false;
        });
      }
    }
  },
  [types.UPDATE_USER_DATA](state, user) {
    vm.$Set(this.userInfoData,"current",user);
  }

};


export default {
  state,
  getters,
  actions,
  mutations
}
