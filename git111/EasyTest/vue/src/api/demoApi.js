import axios from 'axios';
import qs from 'qs.js';

const BASE_URL="/api";
let mp ="";

let api={
  queryZjsk(){
    return axios.get(`http://127.0.0.1:8888/api3.do`, {
      headers: {
        'Content-Type': 'application/x-www-form-urlencoded;charset=UTF-8'
      }
    });
  },
  /**
   * 登陆
   */
  loginIn: function (data) {
    return axios.post(`http://127.0.0.1:8888/login.do`, data,{
      headers: {
        'Content-Type': 'application/json'
      }
    });
  },
  upload(file){
    //组装请求参数，并返回请求实例
    let formData = new FormData();
    formData.append("file", file);
    return axios.post(`http://127.0.0.1:8888/test/upload.do`, formData, {
      headers: {
        'Content-Type': 'multipart/form-data'
      }
    });
  }

};

export default api;
