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
    return axios.get(`/api/user/queryUserList.do`, {
      headers: {
        'Content-Type': 'application/x-www-form-urlencoded;charset=UTF-8'
      }
    });
  },
  /**
   * 新增数据
   */
  save: function (data) {
    return axios.post(`/api/user/saveUser.do`, data,{
      headers: {
        'Content-Type': 'application/json'
      }
    });
  },
  /**
   * 登陆
   * @param data
   * @returns {Promise<AxiosResponse<T>>}
   */
  login:function (data) {
    return axios.post(`/api/user/loginUser.do`, data,{
      headers: {
        'Content-Type': 'application/json'
      }
    });
  },
  /**
   * 单个用户信息修改
   * @param data
   * @returns {Promise<AxiosResponse<T>>}
   */
  update:function (data) {
    return axios.post(`/api/user/updateUser.do`, data,{
      headers: {
        'Content-Type': 'application/json'
      }
    });
  },
  /**
   * 删除指定id的用户
   * @param id
   * @returns {Promise<AxiosResponse<T>>}
   */
  delUser:function (id) {
    return axios.get(`/api/user/delUserById.do/`+id, {
      headers: {
        'Content-Type': 'application/x-www-form-urlencoded;charset=UTF-8'
      }
    });
  },
  /**
   * 获取当前登陆用户
   * @returns {Promise<AxiosResponse<T>>}
   */
  current:function () {
    return axios.get(`/api/user/current.do`, {
      headers: {
        'Content-Type': 'application/x-www-form-urlencoded;charset=UTF-8'
      }
    });
  }
};

export default api;
