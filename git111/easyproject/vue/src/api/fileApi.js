import axios from 'axios';
import qs from 'qs.js';

const BASE_URL = "/api";
let mp = "";

let api = {
  exportMb(fileName) {
    window.location.href = `/api/file/downloadTemp/${fileName}`;
  },
  //对解析后的信息传入后台处理
  importYhxx(yhxxList) {
    return axios.post(`/api/file/uploadList`, yhxxList, {
      headers: {
        'Content-Type': 'application/json'
      }
    });
  },
  //上传文件传入后台处理
  importYhxx01(file) {
    //组装请求参数，并返回请求实例
    let formData = new FormData();
    formData.append("file", file);
    return axios.post(`/api/file/uploadFile`, formData, {
      headers: {
        'Content-Type': 'multipart/form-data'
      }
    });
  },
  exportDatas(excelVo) {
    axios.post(`/api/file/exportExcel`, excelVo, {
      headers: {
        'Content-Type': 'application/json;charset=UTF-8'
      },
      responseType: "arraybuffer"
    }).then(
      function (response) {
        let filename = excelVo.fileName + ".xls";
        this.fileDownload(response.data, filename);
      }.bind(this)
    ).catch(
      function (error) {
        alert("网络请求出错");
      }.bind(this)
    );
  },
  fileDownload(data, filename) {
    let blob = new Blob([data], {
      type: "application/octet-stream"
    });
    if (typeof window.navigator.msSaveBlob !== "undefined") {
      window.navigator.msSaveBlob(blob, filename);
    } else {
      let blobURL = window.URL.createObjectURL(blob);
      let tempLink = document.createElement("a");
      tempLink.style.display = "none";
      tempLink.href = blobURL;
      tempLink.setAttribute("download", filename);
      if (typeof tempLink.download === "undefined") {
        tempLink.setAttribute("target", "_blank");
      }
      document.body.appendChild(tempLink);
      tempLink.click();
      document.body.removeChild(tempLink);
      window.URL.revokeObjectURL(blobURL);
    }
  },
};

export default api;
