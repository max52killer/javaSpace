import * as types from './code-type';
import sysCodeApi from "../../api/bmCodeApi";

import Vue from 'vue'

var vm = new Vue();

const state={
  codeCacheData:{
    list:{},
    map:{}
  }
};

const getters={};
// actions 改变状态的操作
const actions = {
  loadCodeData({commit}, codeId) {
    commit(types.LOAD_CODE_DATA, codeId);
  },
  clearCodeData({commit}, codeId) {
    commit(types.CLEAR_CODE_DATA, codeId);
  },
  reloadCodeData({commit}, codeId) {
    commit(types.RELOAD_CODE_DATA, codeId);
  },
  updateCodeData({commit}, data) {
    commit(types.UPDATE_CODE_DATA, data);
  }
};

let loadingSwatch = {};
// mutations 进行数据状态的变化
const mutations = {
  //使用 ES2015 风格的计算属性命名功能来使用一个常量作为函数名
  [types.LOAD_CODE_DATA](state, codeId) {
    if (!state.codeCacheData.list[codeId]) {
      this.commit(types.UPDATE_CODE_DATA, {codeId: codeId, data: []});
      this.commit(types.RELOAD_CODE_DATA, codeId);
    }
  },
  [types.CLEAR_CODE_DATA](state, codeId) {
    vm.$set(state.codeCacheData.list, codeId, []);
    vm.$set(state.codeCacheData.map, codeId, {});
  },
  [types.RELOAD_CODE_DATA](state, codeId) {
    if (!loadingSwatch[codeId]) {
      loadingSwatch[codeId] = true;
      sysCodeApi.getByType(codeId).then(res => {
        this.commit(types.UPDATE_CODE_DATA, {codeId: codeId, data: res.data});
        loadingSwatch[codeId] = false;
      });
    }
  },
  [types.UPDATE_CODE_DATA](state, data) {
    let codeId = data.codeId;
    let codeList = data.data;
    vm.$set(state.codeCacheData.list, codeId, codeList);
    var codeMap = {};
    codeList.forEach(item => {
      codeMap[item.code] = item;
    });
    vm.$set(state.codeCacheData.map, codeId, codeMap);
  }

}


export default {
  state,
  getters,
  actions,
  mutations
}
