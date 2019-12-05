import axios from 'axios';
import qs from 'qs.js';

const BASE_URL="/api";
let mp ="";

let api={
  /**
   * 获取所当前用户有数据
   * @returns {Promise<AxiosResponse<T>>}
   */
  queryList(){
    return axios.get(`/api/queryUserList.do`, {
      headers: {
        'Content-Type': 'application/x-www-form-urlencoded;charset=UTF-8'
      }
    });
  },
  /**
   * 新增数据
   */
  save: function (data) {
    return axios.post(`/api/saveUser.do`, data,{
      headers: {
        'Content-Type': 'application/json'
      }
    });
  },
};

export default api;
