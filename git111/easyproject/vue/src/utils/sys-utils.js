let api={
  sysParam:{
    title:"Easy Demo",
    copyright:"Copyright ©2019 明杰集团 技术支持 建议使用1280*768及以上分辨率"
  },
  regex:{
    negativeInt: /^-\d+$/,  //负整数
    noNegativeInt: /^\d+$/,  //非负整数(正整数+0)
    positiveInt: /^[0-9]*[1-9][0-9]*$/,  //正整数
    noPositiveInt: /^((-\d+)|(0+))$/,//非正整数(负整数+0)
    integer: /^\d+$/,//整数
    negativeFloat: /^(-(([0-9]+\.[0-9]*[1-9][0-9]*)|([0-9]*[1-9][0-9]*\.[0-9]+)|([0-9]*[1-9][0-9]*)))$/,  //负浮点数
    noNegativeFloat: /^\d+(\.\d+)?$/,  //非负浮点数(正浮点数+0)
    positiveFloat: /^(([0-9]+\.[0-9]*[1-9][0-9]*)|([0-9]*[1-9][0-9]*\.[0-9]+)|([0-9]*[1-9][0-9]*))$/,  //正浮点数
    noPositiveFloat: /^((-\d+(\.\d+)?)|(0+(\.0+)?))$/,//非正浮点数(负浮点数+0)
    float: /^(-?\d+)(\.\d+)?$/,//浮点数
    letter: /^[A-Za-z]+$/,//由26个英文字母组成的字符串
    upperCaseLetter: /^[A-Z]+$/,//由26个英文字母的大写组成的字符串
    lowerCaseLetter: /^[a-z]+$/,//由26个英文字母的小写组成的字符串
    numberOrLetter: /^[A-Za-z0-9]+$/,//由数字和26个英文字母组成的字符串
    numberOrLetterOrUnderline: /^\w+$/,//由数字、26个英文字母或者下划线组成的字符串
    email: /^((([a-z]|\d|[!#\$%&'\*\+\-\/=\?\^_`{\|}~]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])+(\.([a-z]|\d|[!#\$%&'\*\+\-\/=\?\^_`{\|}~]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])+)*)|((\x22)((((\x20|\x09)*(\x0d\x0a))?(\x20|\x09)+)?(([\x01-\x08\x0b\x0c\x0e-\x1f\x7f]|\x21|[\x23-\x5b]|[\x5d-\x7e]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(\\([\x01-\x09\x0b\x0c\x0d-\x7f]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF]))))*(((\x20|\x09)*(\x0d\x0a))?(\x20|\x09)+)?(\x22)))@((([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.)+(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.?$/i,//email地址
    // url: /^[a-zA-z]+:\/\/(\w+(-\w+)*)(\.(\w+(-\w+)*))*(\?\S*)?$/,//url
    url: /(http|https|ftp):\/\/([\w.]+\/?)\S*/,//url
    phone: /^((0\d{2,3}-\d{7,8})|(0\d{2,3}\d{7,8})|(1[3456789]\d{9}))$/,//电话号码
    mobilePhone: /^(13[0-9]|14[0-9]|15[0-9]|18[0-9]|16[0-9]|17[0-9]|19[0-9])\d{8}$/i,//手机号码
    ipv4: /^((([01]?[0-9]{1,2})|(2[0-4][0-9])|(25[0-5]))[.]){3}(([0-1]?[0-9]{1,2})|(2[0-4][0-9])|(25[0-5]))$/,//IP地址
    chinese: /^[\u4e00-\u9fa5]+$/, //中文
    chinaZip: /^\d{6}$/, //邮政编码
    //车辆号码
    hphm: /^[京津沪渝冀豫云辽黑湘皖鲁新苏浙赣鄂桂甘晋蒙陕吉闽贵粤青藏川宁琼使领]{1}[A-Z]{1}[A-Z0-9]{4,5}[A-Z0-9挂学警港澳]{1}$/,
    qq: /^[1-9]\d{4,11}$/, //QQ号码
    password:/^.*(?=.{6,})(?=.*\d)(?=.*[A-Z])(?=.*[a-z])(?=.*[!@#$%^&*? ]).*$/,//密码强度，最少6位，包括至少1个大写字母，1个小写字母，1个数字，1个特殊字符（暂时不用）
    username:/^[a-zA-Z0-9_-]{4,16}$/,//用户名 4到16位（字母，数字，下划线，减号）
    nickname:/^[a-zA-Z0-9\u4e00-\u9fa5]+$/,//用户姓名  由中文、英文、数字组成
    projectname:/^[a-zA-Z0-9\u4e00-\u9fa5._-]+$/,//项目名  可由中文、英文、数字、点、下划线、减号组成
    version:/^[a-zA-Z0-9._-]+$/,//版本号  可由英文、数字、点、下划线、减号组成
    servicename:/^[a-zA-Z0-9_-]+$/,//服务名称  可由英文、数字、下划线、减号组成
  },
  isEmpty(obj) {
    try {
      if (window.$t.fun.isFunction(obj)) return false;
      if (obj === undefined) return true;
      if (obj === null) return true;
      if (obj.length === 0) return true;
      if (obj === "") return true;
      if (obj === "undefined") return true;
      if(obj==={}) return true;
      if (window.$t.string.trim(obj) === "null") return true;
      if (window.$t.string.trim(obj) === "NULL") return true;
      if (window.$t.string.trim(obj) === "") return true;
      if (obj.toString() === null) return true;
      if (obj.toString().length === 0) return true;
      if (window.$t.object.isObject(obj)) {
        for (var p in obj) {
          return false;
        }
        return true;
      }
      return false;
    } catch (e) {
      return false;
    }
  },
};
export default api;
