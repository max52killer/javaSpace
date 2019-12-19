import axios from 'axios';


let api={
  queryAll(){
    return axios.get(`/api/queryUserList.do`, {
      headers: {
        'Content-Type': 'application/x-www-form-urlencoded;charset=UTF-8'
      }
    });
  },
  /**
   * 登陆
   */
  loginIn: function (data) {
    return axios.post(`/api/loginUser.do`, data,{
      headers: {
        'Content-Type': 'application/json'
      }
    });
  },
};

export default api;
