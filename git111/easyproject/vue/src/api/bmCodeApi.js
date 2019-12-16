import axios from 'axios';
import qs from 'qs.js';

const BASE_URL="/api";
let mp ="";

let api={
  getByType(data){
    return axios.get(`/api/getByType/`+data, {
      headers: {
        'Content-Type': 'application/x-www-form-urlencoded;charset=UTF-8'
      }
    });
  },
  /**
   * 获取所有表码数据
   * @returns {Promise<AxiosResponse<T>>}
   */
  queryBmList(){
    return axios.get(`/api/queryBmList`, {
      headers: {
        'Content-Type': 'application/x-www-form-urlencoded;charset=UTF-8'
      }
    });
  },
  /**
   * 新增数据
   */
  save: function (data) {
    return axios.post(`/api/saveBm`, data,{
      headers: {
        'Content-Type': 'application/json'
      }
    });
  },
  /**
   * 更新数据
   * @param data
   * @returns {Promise<AxiosResponse<T>>}
   */
  updateBm:function (data) {
    return axios.post(`/api/updateBm`, data,{
      headers: {
        'Content-Type': 'application/json'
      }
    });
  },
  //删除指定的表码
  deleteBm:function (data) {
    return axios.get(`/api/delByType/`+data, {
      headers: {
        'Content-Type': 'application/x-www-form-urlencoded;charset=UTF-8'
      }
    });
  }
};

export default api;
