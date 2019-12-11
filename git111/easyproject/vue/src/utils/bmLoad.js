import store from '@/store';
import * as types from '../store/code-cacher/code-type';

//表码前端缓存加载对象
let api={
  list(codeId) {
    store.commit(types.LOAD_CODE_DATA, codeId);
    return store.state.codeCacher.codeCacheData.list[codeId];
  },
  name(codeId, bhs) {
    if ($t.isEmpty(bhs)) {
      return "";
    }
    store.commit(types.LOAD_CODE_DATA, codeId);
    var r = "";
    var map = store.state.codeCacher.codeCacheData.map[codeId];
    var arr = bhs instanceof Array ? bhs : $.trim(bhs).split(",");
    $.each(arr, function (i, v) {
      r += map[v] ? map[v].codeName : v;
      r += i == arr.length - 1 ? "" : ",";
    });
    return r;
  },
  clear(codeId) {
    store.commit(types.CLEAR_CODE_DATA, codeId);
    return store.state.codeCacher.codeCacheData.list[codeId];
  },
  reload(codeId) {
    store.commit(types.RELOAD_CODE_DATA, codeId);
    return store.state.codeCacher.codeCacheData.list[codeId];
  },
  update(codeId, data) {
    store.commit(types.UPDATE_CODE_DATA, {codeId: codeId, data: data});
    return store.state.codeCacher.codeCacheData.list[codeId];
  }
};
export default api;
