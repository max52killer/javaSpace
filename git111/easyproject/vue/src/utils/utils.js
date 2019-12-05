//定义工具类对象
import Vue from 'vue';
import laytpl from "./laytpl/laytpl";
import lodash from "./lodash/lodash.min";
var vm = new Vue();


window.$t = window.$t || {};

/**
 * 常见数据类型
 */
window.$t.dataType = {
  "boolean": {'code': "boolean", 'desc': "Boolean类型的数据"},
  "number": {'code': "number", 'desc': "数字类型数据"},
  "string": {'code': "string", 'desc': "字符串类型数据"},
  "function": {'code': "function", 'desc': "函数类型数据"},
  "undefined": {'code': "undefined", 'desc': "未定义数据类型"},
  "null": {'code': "null", 'desc': "空"},
  "array": {'code': "array", 'desc': "数组类型"},
  "date": {'code': "date", 'desc': "日期类型数据"},
  "regexp": {'code': "regexp", 'desc': "正则表达式类型数据"},
  "object": {'code': "object", 'desc': "对象类型数据"},
  "error": {'code': "error", 'desc': "错误数据类型"}
};

/**
 * 正则表达式相关操作
 */
window.$t.regex = {
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
};

/**
 * 判断一个对象是否为空
 * @param    obj  待判断对象
 * @return true | false
 */
window.$t.isEmpty = function (obj) {
  try {
    if (window.$t.fun.isFunction(obj)) return false;
    if (obj === undefined) return true;
    if (obj === null) return true;
    if (obj.length === 0) return true;
    if (obj === "") return true;
    if (obj === "undefined") return true;
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
};

/**
 * 常用工具方法
 */
window.$t.utils = {
  /**
   * 验证对象的数据类型
   * @param obj 待验证的对象
   * @returns 验证结果，参考dataType属性
   */
  'type': function (obj) {
    var type = $.type(obj);
    return type.toLowerCase();
  },
  /**
   * 产生一个唯一的uuid
   * @param len 产生的字符串长度
   * @param radix 进制数
   */
  'uuid': function (len, radix) {
    var chars = '0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz'.split('');
    var uuid = [], i;
    radix = radix || chars.length;
    if (len) {
      for (i = 0; i < len; i++) {
        uuid[i] = chars[0 | Math.random() * radix];
      }
    } else {
      var r;
      uuid[8] = uuid[13] = uuid[18] = uuid[23] = '-';
      uuid[14] = '4';
      for (i = 0; i < 36; i++) {
        if (!uuid[i]) {
          r = 0 | Math.random() * 16;
          uuid[i] = chars[(i == 19) ? (r & 0x3) | 0x8 : r];
        }
      }
    }
    return uuid.join('');
  },
  /**
   * 产生一个32位16进制的UUID
   */
  'uuidHex': function () {
    return window.$t.utils.uuid(32, 16);
  },
  /**
   * 产生一个整型的随机数
   * @param maxNum  产生的最大整数
   * @returns 产生的随机数
   */
  'randomInt': function (maxNum) {
    var num = maxNum && maxNum ? maxNum : 1000000;
    return Math.ceil(Math.random() * num);
  },
  /**
   * 产生一个小数的随机数(0~1)
   * @returns 产生的随机数
   */
  'randomFloat': function () {
    return Math.random();
  },
  /**
   * 对url地址编码
   * @param url 需要编码的url地址
   * @returns 编码后的url
   */
  'encodeURI': function (url) {
    return encodeURI(url);
  },
  /**
   * 对url地址解码
   * @param url 需要解码的url地址
   * @returns 解码后的url
   */
  'decodeURI': function (url) {
    return decodeURI(url);
  },
  /**
   * 动态执行js
   * @param str js代码片段
   */
  'execScript': function (str) {
    return eval("(" + str + ")");
  },
  /**
   * 获取短编码(以结尾两位0截取)如：
   * 340100 -> 3401
   * 340000 -> 34
   * 341000 -> 3410
   * 341002 -> 341002
   *
   */
  'getShortNo': function (bh) {
    bh = bh + "";
    while (/00$/.test(bh)) {
      bh = bh.substring(0, bh.length - 2);
    }
    return bh;
  },
  'urlFormat': function (url, obj) {
    var user = window.$t.getSysUser() || {};
    var params = $.extend({}, user, obj);
    var str = url;
    for (var p in params) {
      var re = new RegExp('\\{' + p + '\\}', 'gm');
      str = str.replace(re, params[p]);
    }
    str = str.replace(/\%{\w*\}/gm, '');
    return encodeURI(str);
  }
};

/**
 * 替换字符串中所有要替换的字符
 */
window.String.prototype.replaceAll = function (reallyDo, replaceWith, ignoreCase) {
  if (!RegExp.prototype.isPrototypeOf(reallyDo)) {
    return this.replace(new RegExp(reallyDo, (ignoreCase ? "gi" : "g")),
      replaceWith);
  } else {
    return this.replace(reallyDo, replaceWith);
  }
};

/**
 * 字符串常用方法
 */
window.$t.string = {
  /**
   * 判断一个对象是否为字符串
   * @param    obj    要判断的对象
   * @returns    true | false
   */
  'isString': function (obj) {
    return obj && window.$t.utils.type(obj) === window.$t.dataType.string.code ? true : false;
  },
  /**
   * 去掉字符串前边空格
   * @param str 字符串
   * @returns    去空后字符串
   */
  'trimLeft': function (str) {
    return window.$t.string.isString(str) ? str.replace(/^\s*/g, '') : str;
  },
  /**
   * 去掉字符串后边空格
   * @param str 字符串
   * @returns    去空后字符串
   *
   */
  'trimRight': function (str) {
    return window.$t.string.isString(str) ? str.replace(/\s*$/, '') : str;
  },
  /**
   * 去掉字符串两边空格
   * @param  str 字符串
   * @returns    去空后字符串
   */
  'trim': function (str) {
    return window.$t.string.isString(str) ? str.replace(/(^\s*)|(\s*$)/g, '') : str;
  },
  /**
   * 替换字符串(只替换匹配的第一个)
   * @param str 字符串
   * @param oldStr 要替换的字符
   * @param newStr 新字符
   * @returns    替换后的字符串
   */
  'replace': function (str, oldStr, newStr) {
    return window.$t.string.isString(str) ? str.replace(oldStr, newStr) : str;
  },
  /**
   * 替换字符串中所有匹配的值
   * @param str 字符串
   * @param oldStr 要替换的字符
   * @param newStr 新字符
   * @param ignoreCase 是否忽略大小写
   * @returns    替换后的字符串
   */
  'replaceAll': function (str, oldStr, newStr, ignoreCase) {
    return window.$t.string.isString(str) ? str.replaceAll(oldStr, newStr, ignoreCase ? true : false) : str;
  },
  /**
   * 判断两字符串是否相等
   * @param str1 字符串1
   * @param str2 字符串2
   * @returns    true | false
   */
  'equals': function (str1, str2) {
    return window.$t.string.isString(str1) && window.$t.string.isString(str2) && str1 === str2 ? true : false;
  },
  /**
   * 判断两个字符串是否相等(忽略大小写)
   * @param str1 字符串1
   * @param str2 字符串2
   * @returns    true | false
   */
  'equalsIgnoreCase': function (str1, str2) {
    return window.$t.string.isString(str1) && window.$t.string.isString(str2) && str1.toUpperCase() === str2.toUpperCase() ? true : false;
  },
  /**
   * 检索某些字符在字符串中的位置(从前到后)
   * @param str 字符串
   * @param pattern 匹配字符串
   * @returns    索引位置，从0开始，未检索到返回-1
   */
  'indexOf': function (str, pattern) {
    return window.$t.string.isString(str) ? str.indexOf(pattern) : str;
  },
  /**
   * 检索某些字符在字符串中的位置(从后到前)
   * @param str 字符串
   * @param pattern 匹配字符串
   * @returns    索引位置，从0开始，未检索到返回-1
   */
  'lastIndexOf': function (str, pattern) {
    return window.$t.string.isString(str) ? str.lastIndexOf(pattern) : str;
  },
  /**
   * 判断字符串是否以什么开始
   * @param str 字符串
   * @param pattern 匹配字符串
   * @returns true | false
   */
  'startsWith': function (str, pattern) {
    return window.$t.string.isString(str) ? str.indexOf(pattern) === 0 : false;
  },
  /**
   * 判断字符串是否以什么结束
   * @param str 字符串
   * @param pattern 匹配字符串
   * @returns true | false
   */
  'endsWith': function (str, pattern) {
    if (!window.$t.string.isString(str)) return str;
    var d = str.length - pattern.length;
    return d >= 0 && str.lastIndexOf(pattern) === d;
  },
  /**
   * 截取字符串
   * @param str 要截取的字符串
   * @param start 从什么位置开始
   * @param end 到什么位置结束
   * @returns    截取后的字符串
   */
  'substring': function (str, start, end) {
    return window.$t.string.isString(str) ? str.substring(start, end) : str;
  },
  /**
   * 截取字符串
   * @param str 要截取的字符串
   * @param start 从什么位置开始
   * @param length 截取长度
   * @returns    截取后的字符串
   */
  'substr': function (str, start, length) {
    return window.$t.string.isString(str) ? str.substr(start, length) : str;
  },
  /**
   * 判断是否为空(空包括 null ，undefined，""，length=0)
   * @param obj 判断的对象
   * @returns    true | false
   */
  'isEmpty': function (obj) {
    try {
      if (obj === undefined) return true;
      if (obj === null) return true;
      if (obj.length === 0) return true;
      if (obj === "") return true;
      if (obj === "undefined") return true;
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
  /**
   * 将字符串分割成数组
   * @param str 要分割的字符串
   * @param separator 分隔符(默认,)
   * @returns    分割后的数组
   */
  'toArray': function (str, separator) {
    if (!window.$t.string.isString(str)) return str;
    var sp = separator ? separator : ",";
    return str.split(sp);
  },
  /**
   * 将一个字符串转换成整数
   * @param str 要转换的字符串
   * @param radix 进制数
   * @returns 转换后的数字
   */
  'parseInt': function (str, radix) {
    if (!window.$t.string.isString(str)) return str;
    return parseInt(str, radix ? radix : 10);
  },
  /**
   * 将一个字符串转换成小数
   * @param str 要转换的字符串
   * @param fixed 保留几位小数
   * @returns  转换后的小数
   */
  'parseFloat': function (str, fixed) {
    if (!window.$t.string.isString(str)) return str;
    if (fixed && fixed >= 0) {
      return parseFloat(parseFloat(str, 10).toFixed(fixed));
    } else {
      return parseFloat(str, 10);
    }
  },
  /**
   * 获取字符串某个位置的字符
   * @param str 字符串
   * @param index 索引位置(从0开始)
   * @returns 索引位置的字符串，无效返回null
   */
  'charAt': function (str, index) {
    if (!window.$t.string.isString(str)) return str;
    if (index >= str.length) {
      return null;
    } else {
      return window.$t.string.substr(str, index, 1);
    }
  },
  /**
   * 判断某个字符串是否包含某一字符串
   * @param str 字符串
   * @param s 包含的字符
   * @returns true | false
   */
  'contains': function (str, s) {
    if (!window.$t.string.isString(str) || !window.$t.string.isString(s)) return false;
    return window.$t.string.indexOf(str, s) == -1 ? false : true;
  },
  /**
   * 将一个字符串转换成小写
   * @param 字符串
   * @returns 转换后的字符串
   */
  'toLowerCase': function (str) {
    if (!window.$t.string.isString(str)) return str;
    return str.toLowerCase();
  },
  /**
   * 将一个字符串转换成大写
   * @param 字符串
   * @returns 转换后的字符串
   */
  'toUpperCase': function (str) {
    if (!window.$t.string.isString(str)) return str;
    return str.toUpperCase();
  },
  /**
   * 将一个对象转换成字符串
   */
  'valueOf': function (str) {
    if (str) {
      if (typeof str === 'object') {
        if (window.$t.date.isDate(str)) {
          return window.$t.date.formatDate(str, "yyyyMMddHHmmss");
        }
        return window.$t.json.jsonToStr(str);
      } else {
        return str + "";
      }
    } else {
      return "";
    }
  },
  /**
   * 将一个数组转换成字符串
   * @param arr 数组
   * @param separator 分隔符(默认,)
   * @returns 转换后的字符串
   */
  'arrayToString': function (arr, separator) {
    if (arr && arr.length > 0 && window.$t.array.isArray(arr)) {
      var sp = separator ? separator : ",";
      return arr.join(sp);
    } else {
      return "";
    }
  },
  /**
   * HTML转码，将 & < > ' " 替换
   * @param str 字符串
   * @returns 转换后的字符串
   */
  'htmlEncode': function (str) {
    if (!window.$t.string.isString(str) || window.$t.string.isEmpty(str)) return str;
    str = str.replace(/\&/g, "&amp;");
    str = str.replace(/</g, "&lt;");
    str = str.replace(/>/g, "&gt;");
    str = str.replace(/'/g, "&apos;");
    str = str.replace(/"/g, "&quot;");
    return str;
  },
  /**
   * HTML解码，将 & < > ' " 还原
   * @param str 字符串
   * @returns 转换后的字符串
   */
  'htmlDecode': function (str) {
    if (!window.$t.string.isString(str) || window.$t.string.isEmpty(str)) return str;
    str = str.replace(/\&amp;/g, "&");
    str = str.replace(/&lt;/g, "<");
    str = str.replace(/&gt;/g, ">");
    str = str.replace(/&apos;/g, "'");
    str = str.replace(/&quot;/g, "\"");
    return str;
  },
  /**
   * 用正则表达式匹配字符串
   * @param str 字符串
   * @param regex 匹配的正则表达式
   * @returns array 匹配后的数组对象
   */
  'matchArray': function (str, regex) {
    if (!window.$t.string.isString(str) || window.$t.string.isEmpty(str)) return str;
    return str.match(regex);
  },
  /**
   * 计算一个字符串的长度(中文占两个字符，英文一个)
   * @returns 字符串长度
   */
  'length': function (str) {
    if (window.$t.string.isEmpty(str)) {
      return 0;
    }
    str = str + "";
    return str.replace(/[^x00-xff]/g, "01").length;
  },
  /**
   * 将字符串转换为对象
   * 1:a.b.c=1 转换为 {a:{b:{c:1}}}
   */
  'toObj': function (str) {
    var objId = $t.utils.uuidHex();
    window[objId] = {};
    if (str && str.indexOf("=") != -1) {
      var props = str.split("=")[0];
      var value = str.split("=")[1];
      var propArr = props.split(".");
      var len = propArr.length;
      var propNames = "";
      for (var i = 0; i < len; i++) {
        if (i == (len - 1)) {
          eval("(window['" + objId + "']" + propNames + ")")[propArr[i]] = value;
        } else {
          eval("(window['" + objId + "']" + propNames + ")")[propArr[i]] = {};
        }
        propNames += "." + propArr[i];
      }
    }
    var obj = $t.object.clone(window[objId]);
    delete window[objId];
    return obj;
  },
  /**
   * 计算出字符串表达式内容
   * '[name] is [xb]',{name:'xxx', xb:'男'} => 'xxx is 男'
   */
  'evaluate': function (str, bean) {
    var result = str;
    var reg = /\{(\w+)\}/g;
    var arr;
    while ((arr = reg.exec(str)) != null) {
      result = result.replace(arr[0], $t.object.getBeanProperty(bean, arr[1]));
    }
    return result;
  },
  /**
   * 生成唯一id
   */
  'guid': function () {
    function S4() {
      return (((1 + Math.random()) * 0x10000) | 0).toString(16).substring(1);
    }

    return (S4() + S4() + "-" + S4() + "-" + S4() + "-" + S4() + "-" + S4() + S4() + S4());
  }
};

/**
 * 对象常用方法
 */
window.$t.object = {
  /**
   * 判断一个对象是否为对象类型
   * @param obj 要判断的对象
   * @returns true | false
   */
  'isObject': function (obj) {
    return obj && window.$t.utils.type(obj) === window.$t.dataType.object.code ? true : false;
  },
  //对象属性名转小写
  "lowerObjKey": function (obj) {
    var result = {};
    for (var key in obj) {
      result[key.toLowerCase()] = obj[key];
    }
    return result;
  },
  //对象属性名转大写
  "upperObjKey": function (obj) {
    var result = {};
    for (var key in obj) {
      result[key.toUpperCase()] = obj[key];
    }
    return result;
  },
  "newArray" : (obj,filter)=>{
    if(!window.$t.object.isObject(obj)){
      return [];
    }
    var data = [];
    for(var p in obj){
      var newData = filter(obj[p]);
      if(!window.$t.isEmpty(newData)){
        data.push(newData);
      }
    }
    return data;
  }
};

/**
 * 数组常用方法
 */
window.$t.array = {
  /**
   * 判断一个对象是不是数组
   * @param obj 待判断的对象
   * return true | false
   */
  'isArray': function (obj) {
    return obj && window.$t.utils.type(obj) === window.$t.dataType.array.code ? true : false;
  },
  /**
   * 去除数组中重复数据(不处理对象数组)
   * @param arr 要去重的数组
   * @returns 去重后的数组
   */
  'unique': function (arr) {
    var a = {};
    for (var i = 0; i < arr.length; i++) {
      a[arr[i]] = arr[i];
    }
    var data = [];
    for (var p in a) {
      data.push(a[p]);
    }
    return data;
  },
  /**
   * 对数字数组进行排序
   * @param arr 要排序的数组
   * @param asc 是否是升序，默认true
   * @returns 排序后的数组
   */
  'sortNum': function (arr, asc) {
    if (asc == undefined || asc == null) {
      asc = true;
    }
    var asc = asc ? true : false;
    return arr.sort(function (a, b) {
      if (asc) {
        return a - b;
      } else {
        return b - a;
      }
    });
  },
  /**
   * 对对象数组进行排序
   * @param arr 要排序的数组
   * @param property 要排序的属性
   * @param asc 是否是升序，默认true
   * @returns 排序后的数组
   */
  'sortObject': function (arr, property, asc) {
    if (asc == undefined || asc == null) {
      asc = true;
    }
    var asc = asc ? true : false;
    return arr.sort(function (a, b) {
      if (asc) {
        return a[property] - b[property];
      } else {
        return b[property] - a[property];
      }
    });
  },
  /**
   * 截取数组
   * @param arr 要截取的数组
   * @param start 开始位置(从0开始)
   * @param end 结束位置
   * @returns 截取后的数组
   */
  'subArray': function (arr, start, end) {
    var result = [];
    for (var i = start; i < end; i++) {
      if (arr[i]) {
        result.push(arr[i]);
      }
    }
    return result;
  },
  /**
   * 克隆数组
   * @param arr 要克隆的数组
   * @returns 克隆后的数组
   */
  'clone': function (arr) {
    var result = [];
    if (arr) {
      $.each(arr, function (i, item) {
        result.push(item);
      });
    }
    return result;
  },
  /**
   * 将数组转换成对象
   * @param arr 要转换的数组
   * @param key 要以数组中每个对象的哪个属性作为转换后对象的属性，没有指定，以p_序号作为转换后对象的属性
   * @returns 转换后的对象
   */
  'toObject': function (arr, key) {
    if (window.$t.array.isArray(arr)) {
      var result = {};
      $.each(arr, function (i, item) {
        if (key) {
          result[item[key]] = item;
        } else {
          result["p_" + i] = item;
        }
      });
      return result;
    } else {
      return {};
    }
  },
  /**
   * 转换为只包含指定列(keys)对象的数组
   */
  'subFieldArray': function (arr, keys, b) {
    if (window.$t.array.isArray(arr)) {
      var result = [];
      var keyArr = keys.split(",");
      $.each(arr, function (i, item) {
        var obj = {};
        $.each(keyArr, function (j, key) {
          if (key && !b) {
            obj[key] = item[key];
          } else if (key && b && keyArr.length == 1) {
            obj = item[key];
          }
        });
        result.push(obj);
      });
      return result;
    } else {
      return [];
    }
  },
  /**
   * 将一个对象数组合并成一个对象
   *    例如 [{aa:"aa",bb:"bb"},{cc:"cc"},{aa:"xx",dd:"dd"}]
   *    结果 {aa:"xx",bb:"bb",cc:"cc",dd:"dd"}
   * @param arr 要合并的数组
   * @returns 合并后的对象
   */
  'mergeObject': function (arr) {
    if (window.$t.array.isArray(arr)) {
      var result = {};
      $.each(arr, function (i, item) {
        if (window.$t.object.isObject(item)) {
          $.each(item, function (p, value) {
            result[p] = value;
          });
        }
      });
      return result;
    } else {
      return {};
    }
  },
  /**
   * 将数组按长度分成多个数组
   * @param arr
   *          要分组的数组
   * @param groupLen
   *          每组长度
   * @return 分组后的长度
   */
  "group": function (arr, groupLen) {
    if (window.$t.array.isArray(arr) && arr.length > 0) {
      var lastIndex = arr.length % groupLen;
      var size = parseInt(arr.length / groupLen, 10);
      var result = [];
      for (var i = 0; i < size; i++) {
        result.push(arr.slice(groupLen * i, (i + 1) * groupLen));
      }
      if (lastIndex != 0) {
        result.push(arr.slice(groupLen * size, arr.length));
      }
      return result;
    }
    return [];
  },
  /**
   * 计算数组中某个元素出现次数
   * @param arr
   * @param filter
   * @returns {number}
   */
  someCount:function (arr,filter) {
    var count = 0;
    arr.find(function (ele) {
      filter(ele) ? count++ : '';
    });
    return count;
  },
  /**
   * 提取数组里某个字段的数组值，且去重
   * @param prop
   * @returns {any[]}
   */
  uniqueArray : function (arr,prop) {
    var values = [];
    arr.forEach(ele => {
      if (window.$t.string.isString(prop)) {
        var val = ele[prop];
        if (values.indexOf(val) == -1 && !window.$t.isEmpty(val)) {
          values.push(val);
        }
      } else if (window.$t.fun.isFunction(prop)) {
        var val = prop(ele);
        if (values.indexOf(val) == -1 && !window.$t.isEmpty(val)) {
          values.push(val);
        }
      } else {
        if (values.indexOf(ele) == -1 && !window.$t.isEmpty(ele)) {
          values.push(ele);
        }
      }
    });
    return values;
  },
  /**
   * 根据属性去重
   * @param arr
   * @param prop
   * @returns {Array}
   */
  uniqueByProp : function (arr,prop) {
    var values = [];
    var valMap = {};
    arr.forEach(ele => {
      if (window.$t.string.isString(prop)) {
        var val = ele[prop];
        if ($t.isEmpty(valMap[val]) && !window.$t.isEmpty(val)) {
          values.push(ele);
          valMap[val]="1";
        }
      } else if (window.$t.fun.isFunction(prop)) {
        var val = prop(ele);
        if ($t.isEmpty(valMap[val]) && !window.$t.isEmpty(val)) {
          values.push(ele);
          valMap[val]="1";
        }
      } else {
        if ($t.isEmpty(valMap[val]) && !window.$t.isEmpty(ele)) {
          values.push(ele);
          valMap[val]="1";
        }
      }
    });
    return values;
  },
  /**
   * 提取数组里某个字段的数组值，且去重，返回用分隔符分割的字符串
   * @param prop
   * @param splitter 分割符，默认逗号
   * @returns {any[]}
   */
  uniqueValue:function (arr,prop,splitter) {
    if(window.$t.isEmpty(splitter)){
      splitter=",";
    }
    return window.$t.array.uniqueArray(arr,prop).join(splitter);
  },
  /**
   * 对象数组排序
   * @param orders [{"field":"asc"}]
   * @returns {string}
   */
  orderBy:function (arr,orders) {
    orders = orders || [];
    var fields = [];
    var order = [];
    orders.forEach(o=>{
      for(var p in o){
        fields.push(p);
        order.push(o[p]);
      }
    });
    return window._.orderBy(arr,fields,order);
  },
  newArray:function(filter){
    var data = [];
    this.forEach(item=>{
      var newData = filter(item);
      if(!window.$t.isEmpty(newData)){
        data.push(newData);
      }
    });
    return data;
  }
};

/**
 * 时间常用方法
 */
window.$t.date = {
  /**
   * 判断一个对象是否为日期类型
   * @param obj 要判断的对象
   */
  'isDate': function (obj) {
    return obj && window.$t.utils.type(obj) === window.$t.dataType.date.code ? true : false;
  },
  /**
   * 格式化日期成一个字符串
   * @param data 日期
   * @param fmt 格式化字符串
   *            "yyyy-MM-dd hh:mm:ss.S"==>2006-07-02 08:09:04.423
   *            "yyyy-MM-dd E HH:mm:ss"==>2009-03-10 二 20:09:04
   *            "yyyy-MM-dd EE HH:mm:ss"==>2009-03-10 周二 20:09:04
   *            "yyyy-MM-dd EEE HH:mm:ss"==>2009-03-10 星期二 20:09:04
   *            "yyyy-M-d h:m:s.S"==>2006-7-2 8:9:4.18
   * @returns 格式化后的字符串
   */
  'formatDate': function (date, fmt) {
    if (!window.$t.date.isDate(date)) return;
    var o = {
      "M+": date.getMonth() + 1,
      // 月份
      "d+": date.getDate(),
      // 日
      "h+": date.getHours() % 12 == 0 ? 12 : date.getHours() % 12,
      // 小时
      "H+": date.getHours(),
      // 小时
      "m+": date.getMinutes(),
      // 分
      "s+": date.getSeconds(),
      // 秒
      "q+": Math.floor((date.getMonth() + 3) / 3),
      // 季度
      "S": date.getMilliseconds()
      // 毫秒
    };
    var week = {
      "0": "日",
      "1": "一",
      "2": "二",
      "3": "三",
      "4": "四",
      "5": "五",
      "6": "六"
    };
    if (/(y+)/.test(fmt)) {
      fmt = fmt.replace(RegExp.$1, (date.getFullYear() + "").substr(4
        - RegExp.$1.length));
    }
    if (/(E+)/.test(fmt)) {
      fmt = fmt.replace(RegExp.$1, ((RegExp.$1.length > 1)
        ? (RegExp.$1.length > 2 ? "星期" : "周")
        : "")
        + week[date.getDay() + ""]);
    }
    for (var k in o) {
      if (new RegExp("(" + k + ")").test(fmt)) {
        fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1)
          ? (o[k])
          : (("00" + o[k]).substr(("" + o[k]).length)));
      }
    }
    return fmt;
  },
  /**
   * 将一个字符串格式化成特定的格式日期字符串
   * @param str 要格式化的字符串(默认yyyyMMddHHmmss,如果特殊格式请指定inFmt)
   * @param outFmt 格式化输出的字符串
   * @param inFmt  原本字符串日期格式
   * @returns 格式化后的字符串
   */
  'formatDateStr': function (str, fmt, inFmt) {
    if (window.$t.string.isString(str) && !window.$t.string.isEmpty(str)) {
      str = window.$t.date.getNumberString(str);
      inFmt = inFmt ? inFmt : "yyyyMMddHHmmss".substring(0, str.length);
      var date = window.$t.date.strToDate(str, inFmt);
      return window.$t.date.formatDate(date, fmt);
    } else if (window.$t.date.isDate(str)) {
      return window.$t.date.formatDate(str, fmt);
    } else {
      return str;
    }
  },
  /**
   * 字符串转换成日期类型
   * @param 要格式化的字符串(默认yyyyMMddHHmmss,如果特殊格式请指定inFmt)
   * @param inFmt  原本字符串日期格式
   * @returns 格式化后的日期(日期类型)
   */
  'strToDate': function (str, inFmt) {
    if (window.$t.string.isString(str) && !window.$t.string.isEmpty(str)) {
      inFmt = inFmt ? inFmt : "yyyyMMddHHmmss";
      var date = new Date();

      //获取年
      var yearStart = window.$t.string.indexOf(inFmt, "y");
      var yearEnd = window.$t.string.lastIndexOf(inFmt, "y");
      var year = window.$t.string.substring(str, yearStart, yearEnd + 1);
      year && date.setYear(window.$t.string.parseInt(year));

      //获取月
      var monthStart = window.$t.string.indexOf(inFmt, "M");
      var monthEnd = window.$t.string.lastIndexOf(inFmt, "M");
      var month = window.$t.string.substring(str, monthStart, monthEnd + 1);
      //bug:如果今天比转换月份最大天数大，会转换出错
      //month && date.setMonth(window.$t.string.parseInt(month)-1);
      if (month) {
        //获取当前月中的天数
        var nowDay = date.getDate();
        //获取待转换月份天数
        var dayInMonths = $t.date.getDaysInMonth(year, window.$t.string.parseInt(month));
        //如果当前月中天数大于待转换月份天数，转换月份天数设置为本月最后一天
        if (nowDay > dayInMonths) {
          date.setDate(dayInMonths);
        }
        date.setMonth(window.$t.string.parseInt(month) - 1);
      }

      //获取日
      var dayStart = window.$t.string.indexOf(inFmt, "d");
      var dayEnd = window.$t.string.lastIndexOf(inFmt, "d");
      var day = window.$t.string.substring(str, dayStart, dayEnd + 1);
      day && date.setDate(window.$t.string.parseInt(day));

      //获取时
      var hourStart = window.$t.string.indexOf(inFmt, "H");
      var hourEnd = window.$t.string.lastIndexOf(inFmt, "H");
      var hour = window.$t.string.substring(str, hourStart, hourEnd + 1);
      hour && date.setHours(window.$t.string.parseInt(hour));

      //获取分
      var minStart = window.$t.string.indexOf(inFmt, "m");
      var minEnd = window.$t.string.lastIndexOf(inFmt, "m");
      var min = window.$t.string.substring(str, minStart, minEnd + 1);
      min && date.setMinutes(window.$t.string.parseInt(min));

      //获取秒
      var secondStart = window.$t.string.indexOf(inFmt, "s");
      var secondEnd = window.$t.string.lastIndexOf(inFmt, "s");
      var second = window.$t.string.substring(str, secondStart, secondEnd + 1);
      second && date.setSeconds(window.$t.string.parseInt(second));

      return date;
    } else {
      return str;
    }
  },
  /**
   * 将一个日期或日期字符串格式化成纯数字，并获取前几位
   * @param date 日期字符串或日期
   * @param length 长度(前多少位)
   * @returns 格式化后的字符串
   */
  'getNumberString': function (date, length) {
    if (window.$t.date.isDate(date)) {
      var time = $t.date.formatDate(date, "yyyyMMddHHmmss");
      if (window.$t.isEmpty(length)) {
        return time;
      } else {
        return time.substring(0, length);
      }
    } else if (window.$t.string.isString(date)) {
      var time = date.replaceAll("年", "").replaceAll("月", "").replaceAll("日",
        "").replaceAll("时", "").replaceAll("分", "").replaceAll("秒", "")
        .replaceAll("-", "").replaceAll(":", "").replaceAll("/", "")
        .replaceAll("\\.", "").replaceAll(" ", "").replaceAll("　", "").replaceAll("T", "");
      if(time.indexOf("+")!=-1){
        time = time.substring(0,time.indexOf("+"));
      }
      if (window.$t.isEmpty(length)) {
        return time;
      } else {
        return time.substring(0, length);
      }
    } else {
      return date;
    }
  },
  /**
   * 获取某一年某一月是有多少天
   * @param year 年
   * @param month 月
   * @returns 天数
   */
  'getDaysInMonth': function (year, month) {
    month = parseInt(month, 10);
    var monthStartDate = new Date(year, month - 1, 1);
    var monthEndDate = new Date(year, month, 1);
    var days = (monthEndDate.getTime() - monthStartDate.getTime()) / (1000 * 60 * 60 * 24);
    return days;
  },
  /**
   * 获取当前时间
   * @param fmt  日期格式(默认yyyyMMddHHmmss)
   * @returns 格式化后的字符串
   */
  'getCurrentTime': function (fmt) {
    var pattern = fmt ? fmt : "yyyyMMddHHmmss";
    return window.$t.date.formatDate(new Date(), pattern);
  },
  /**
   * 判断某一年是否为闰年
   * @param year 年份
   * @returns true | false
   */
  'isLeapYear': function (year) {
    var flag = false;
    year = parseInt(year, 10);
    if ((year % 4 == 0 && year % 100 != 0)
      || (year % 400 == 0)) {
      flag = true;
    }
    return flag;
  },
  /**
   * 在指定日期下添加(减少)年份
   * @param date 指定日期(日期类型)
   * @param amount 数量(添加年份用正数，减少年份用负数)
   * @param outFmt 输出字符串格式，如果为空则返回日期类型
   * @returns 计算后的日期
   */
  'addYears': function (date, amount, outFmt) {
    if (window.$t.date.isDate(date)) {
      //获取年份
      var year = date.getFullYear();
      //获取月份
      var month = date.getMonth();
      //获取天数
      var day = date.getDate();
      //计算新的年份
      year += amount;
      date.setYear(year);
      var days = window.$t.date.getDaysInMonth(year, month + 1);
      if (days < day) {
        date.setDate(days);
      } else {
        date.setDate(day);
      }
      date.setMonth(month);
      if (window.$t.string.isEmpty(outFmt)) {
        return date;
      } else {
        return window.$t.date.formatDate(date, outFmt);
      }
    } else {
      return date;
    }
  },
  /**
   * 在指定日期下添加(减少)月份
   * @param date 指定日期(日期类型)
   * @param amount 数量(添加月份用正数，减少月份用负数)
   * @param outFmt 输出字符串格式，如果为空则返回日期类型
   * @returns 计算后的日期
   */
  'addMonths': function (date, amount, outFmt) {
    if (window.$t.date.isDate(date)) {
      //获取年份
      var year = date.getFullYear();
      //获取月份
      var month = date.getMonth();
      //获取天数
      var day = date.getDate();
      //计算新的月份
      month += amount;
      if (month < 0) {
        month = 12 + month;
        year--;
      }
      if (month > 11) {
        month = month - 12;
        year++;
      }
      date.setYear(year);
      var days = window.$t.date.getDaysInMonth(year, month + 1);
      if (days < day) {
        date.setDate(days);
      } else {
        date.setDate(day);
      }
      date.setMonth(month);

      if (window.$t.string.isEmpty(outFmt)) {
        return date;
      } else {
        return window.$t.date.formatDate(date, outFmt);
      }
    } else {
      return date;
    }
  },
  /**
   * 在指定日期下添加(减少)天数
   * @param date 指定日期(日期类型)
   * @param amount 数量(添加天数用正数，减少天数用负数)
   * @param outFmt 输出字符串格式，如果为空则返回日期类型
   * @returns 计算后的日期
   */
  'addDays': function (date, amount, outFmt) {
    if (window.$t.date.isDate(date)) {
      var time = date.getTime();
      var dtime = amount * 24 * 60 * 60 * 1000;
      time += dtime;
      date.setTime(time);
      if (window.$t.string.isEmpty(outFmt)) {
        return date;
      } else {
        return window.$t.date.formatDate(date, outFmt);
      }
    } else {
      return date;
    }
  },
  /**
   * 在指定日期下添加(减少)小时数
   * @param date 指定日期(日期类型)
   * @param amount 数量(添加小时数用正数，减少小时数用负数)
   * @param outFmt 输出字符串格式，如果为空则返回日期类型
   * @returns 计算后的日期
   */
  'addHours': function (date, amount, outFmt) {
    if (window.$t.date.isDate(date)) {
      var time = date.getTime();
      var dtime = amount * 60 * 60 * 1000;
      time += dtime;
      date.setTime(time);
      if (window.$t.string.isEmpty(outFmt)) {
        return date;
      } else {
        return window.$t.date.formatDate(date, outFmt);
      }
    } else {
      return date;
    }
  },
  /**
   * 在指定日期下添加(减少)分钟数
   * @param date 指定日期(日期类型)
   * @param amount 数量(添加分钟数用正数，减少分钟数用负数)
   * @param outFmt 输出字符串格式，如果为空则返回日期类型
   * @returns 计算后的日期
   */
  'addMinutes': function (date, amount, outFmt) {
    if (window.$t.date.isDate(date)) {
      var time = date.getTime();
      var dtime = amount * 60 * 1000;
      time += dtime;
      date.setTime(time);
      if (window.$t.string.isEmpty(outFmt)) {
        return date;
      } else {
        return window.$t.date.formatDate(date, outFmt);
      }
    } else {
      return date;
    }
  },
  /**
   * 在指定日期下添加(减少)秒数
   * @param date 指定日期(日期类型)
   * @param amount 数量(添加秒数用正数，减少秒数用负数)
   * @param outFmt 输出字符串格式，如果为空则返回日期类型
   * @returns 计算后的日期
   */
  'addSeconds': function (date, amount, outFmt) {
    if ($t.date.isDate(date)) {
      var time = date.getTime();
      var dtime = amount * 1000;
      time += dtime;
      date.setTime(time);
      if (window.$t.string.isEmpty(outFmt)) {
        return date;
      } else {
        return window.$t.date.formatDate(date, outFmt);
      }
    } else {
      return date;
    }
  },
  /**
   * 返回1970年1月1日至今的毫秒数
   * @param date 指定日期(日期类型)
   * @returns 毫秒数
   */
  'getTime': function (date) {
    if (window.$t.date.isDate(date)) {
      return date.getTime();
    } else {
      return date;
    }
  },
  /**
   * 距离现在几分钟
   * dateTimeStamp时间戳
   */
  timeago: function (dateString, inFmt) {
    let dateTimeStamp = -1;
    if (window.$t.string.isString(dateString) && !window.$t.string.isEmpty(dateString)) {
      inFmt = inFmt ? inFmt : "yyyyMMddHHmmss";
      dateTimeStamp = window.$t.date.strToDate(dateString, inFmt).getTime();
    } else if (window.$t.date.isDate(dateString)) {
      dateTimeStamp = dateString.getTime();
    }
    if (dateTimeStamp == -1) {
      return dateString;
    }
    let minute = 1000 * 60;
    let hour = minute * 60;
    let day = hour * 24;
    let week = day * 7;
    let halfamonth = day * 15;
    let month = day * 30;
    let now = new Date().getTime();   //获取当前时间毫秒
    let diffValue = now - dateTimeStamp;//时间差
    let result = "";
    if (diffValue < 0) {
      return;
    }
    let minC = diffValue / minute;  //计算时间差的分，时，天，周，月
    let hourC = diffValue / hour;
    let dayC = diffValue / day;
    let weekC = diffValue / week;
    let monthC = diffValue / month;
    if (monthC >= 1 && monthC < 3) {
      result = " " + parseInt(monthC) + "月前"
    } else if (weekC >= 1 && weekC < 3) {
      result = " " + parseInt(weekC) + "周前"
    } else if (dayC >= 1 && dayC < 6) {
      result = " " + parseInt(dayC) + "天前"
    } else if (hourC >= 1 && hourC < 24) {
      result = " " + parseInt(hourC) + "小时前"
    } else if (minC >= 1 && minC < 60) {
      result = " " + parseInt(minC) + "分钟前"
    } else if (diffValue >= 0 && diffValue < minute) {
      result = "刚刚"
    } else {
      let datetime = new Date();
      datetime.setTime(dateTimeStamp);
      let Nyear = datetime.getFullYear();
      let Nmonth = datetime.getMonth() + 1 < 10 ? "0" + (datetime.getMonth() + 1) : datetime.getMonth() + 1;
      let Ndate = datetime.getDate() < 10 ? "0" + datetime.getDate() : datetime.getDate();
      let Nhour = datetime.getHours() < 10 ? "0" + datetime.getHours() : datetime.getHours();
      let Nminute = datetime.getMinutes() < 10 ? "0" + datetime.getMinutes() : datetime.getMinutes();
      let Nsecond = datetime.getSeconds() < 10 ? "0" + datetime.getSeconds() : datetime.getSeconds();
      let curYeay = window.$t.date.getCurrentTime("yyyy");
      if (curYeay == Nyear) {
        result = Nmonth + "-" + Ndate
      } else {
        result = (Nyear + "").substring(2, 4) + "-" + Nmonth + "-" + Ndate
      }
    }
    return result;
  }
};

window.$t.fun = {
  /**
   * 判断一个对象是否为函数类型
   * @param obj 要判断的对象
   * @returns true | false
   */
  'isFunction': function (obj) {
    return obj && window.$t.utils.type(obj) === window.$t.dataType['function'].code ? true : false;
  }
};

/**
 * 常用数据验证方法
 */
window.$t.validate = window.$t.validate || {};

//扩展验证方法
//扩展验证方法
$.each(window.$t.regex, function (reg, regex) {
  var funName = reg.substring(0, 1).toUpperCase() + reg.substring(1, reg.length);
  window.$t.validate["is" + funName] = function (str) {
    return regex.test(str);
  };
});

//实现验证身份证号
window.$t.validate.isIdCard = function (num) {
  num = num.toUpperCase();
  //身份证号码为15位或者18位，15位时全为数字，18位前17位为数字，最后一位是校验位，可能为数字或字符X。
  if (!(/(^\d{15}$)|(^\d{17}([0-9]|X)$)/.test(num))) {
    //alert('输入的身份证号长度不对，或者号码不符合规定！\n15位号码应全为数字，18位号码末位可以为数字或X。');
    return false;
  }
  //校验位按照ISO 7064:1983.MOD 11-2的规定生成，X可以认为是数字10。
  //下面分别分析出生日期和校验位
  var len, re;
  len = num.length;
  if (len == 15) {
    re = new RegExp(/^(\d{6})(\d{2})(\d{2})(\d{2})(\d{3})$/);
    var arrSplit = num.match(re);

    //检查生日日期是否正确
    var dtmBirth = new Date('19' + arrSplit[2] + '/' + arrSplit[3] + '/'
      + arrSplit[4]);
    var bGoodDay;
    bGoodDay = (dtmBirth.getYear() == Number(arrSplit[2]))
      && ((dtmBirth.getMonth() + 1) == Number(arrSplit[3]))
      && (dtmBirth.getDate() == Number(arrSplit[4]));
    if (!bGoodDay) {
      // alert('输入的身份证号里出生日期不对！');
      return false;
    } else {
      //将15位身份证转成18位
      //校验位按照ISO 7064:1983.MOD 11-2的规定生成，X可以认为是数字10。
      var arrInt = new Array(7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5,
        8, 4, 2);
      var arrCh = new Array('1', '0', 'X', '9', '8', '7', '6', '5', '4',
        '3', '2');
      var nTemp = 0, i;
      num = num.substr(0, 6) + '19' + num.substr(6, num.length - 6);
      for (i = 0; i < 17; i++) {
        nTemp += num.substr(i, 1) * arrInt[i];
      }
      num += arrCh[nTemp % 11];
      return true;
    }
  }
  if (len == 18) {
    re = new RegExp(/^(\d{6})(\d{4})(\d{2})(\d{2})(\d{3})([0-9]|X)$/);
    var arrSplit = num.match(re);

    //检查生日日期是否正确
    var dtmBirth = new Date(arrSplit[2] + "/" + arrSplit[3] + "/"
      + arrSplit[4]);
    var bGoodDay;
    bGoodDay = (dtmBirth.getFullYear() == Number(arrSplit[2]))
      && ((dtmBirth.getMonth() + 1) == Number(arrSplit[3]))
      && (dtmBirth.getDate() == Number(arrSplit[4]));
    if (!bGoodDay) {
      //alert(dtmBirth.getYear());
      //alert(arrSplit[2]);
      // alert('输入的身份证号里出生日期不对！');
      return false;
    } else {
      //检验18位身份证的校验码是否正确。
      //校验位按照ISO 7064:1983.MOD 11-2的规定生成，X可以认为是数字10。
      var valnum;
      var arrInt = new Array(7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5,
        8, 4, 2);
      var arrCh = new Array('1', '0', 'X', '9', '8', '7', '6', '5', '4',
        '3', '2');
      var nTemp = 0, i;
      for (i = 0; i < 17; i++) {
        nTemp += num.substr(i, 1) * arrInt[i];
      }
      valnum = arrCh[nTemp % 11];
      if (valnum != num.substr(17, 1)) {
        //alert('18位身份证的校验码不正确！应该为：' + valnum);
        //alert('身份证号校验不合法');
        return false;
      }
      return true;
    }
  }
  return false;
};

window.$t.Base64 = {
  "_keyStr": "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/=",
  "encode": function (input) {
    var output = "";
    var chr1, chr2, chr3, enc1, enc2, enc3, enc4;
    var i = 0;
    input = window.$t.Base64._utf8_encode(input);
    while (i < input.length) {
      chr1 = input.charCodeAt(i++);
      chr2 = input.charCodeAt(i++);
      chr3 = input.charCodeAt(i++);
      enc1 = chr1 >> 2;
      enc2 = ((chr1 & 3) << 4) | (chr2 >> 4);
      enc3 = ((chr2 & 15) << 2) | (chr3 >> 6);
      enc4 = chr3 & 63;
      if (isNaN(chr2)) {
        enc3 = enc4 = 64;
      } else if (isNaN(chr3)) {
        enc4 = 64;
      }
      output = output +
        window.$t.Base64._keyStr.charAt(enc1) + window.$t.Base64._keyStr.charAt(enc2) +
        window.$t.Base64._keyStr.charAt(enc3) + window.$t.Base64._keyStr.charAt(enc4);
    }
    return output;
  },

  "decode": function (input) {
    var output = "";
    var _keyStr = window.$t.Base64._keyStr;
    var chr1, chr2, chr3;
    var enc1, enc2, enc3, enc4;
    var i = 0;
    input = input.replace(/[^A-Za-z0-9\+\/\=]/g, "");
    while (i < input.length) {
      enc1 = _keyStr.indexOf(input.charAt(i++));
      enc2 = _keyStr.indexOf(input.charAt(i++));
      enc3 = _keyStr.indexOf(input.charAt(i++));
      enc4 = _keyStr.indexOf(input.charAt(i++));
      chr1 = (enc1 << 2) | (enc2 >> 4);
      chr2 = ((enc2 & 15) << 4) | (enc3 >> 2);
      chr3 = ((enc3 & 3) << 6) | enc4;
      output = output + String.fromCharCode(chr1);
      if (enc3 != 64) {
        output = output + String.fromCharCode(chr2);
      }
      if (enc4 != 64) {
        output = output + String.fromCharCode(chr3);
      }
    }
    output = window.$t.Base64._utf8_decode(output);
    return output;
  },
  "_utf8_encode": function (string) {
    string = string.replace(/\r\n/g, "\n");
    var utftext = "";
    for (var n = 0; n < string.length; n++) {
      var c = string.charCodeAt(n);
      if (c < 128) {
        utftext += String.fromCharCode(c);
      } else if ((c > 127) && (c < 2048)) {
        utftext += String.fromCharCode((c >> 6) | 192);
        utftext += String.fromCharCode((c & 63) | 128);
      } else {
        utftext += String.fromCharCode((c >> 12) | 224);
        utftext += String.fromCharCode(((c >> 6) & 63) | 128);
        utftext += String.fromCharCode((c & 63) | 128);
      }

    }
    return utftext;
  },
  "_utf8_decode": function (utftext) {
    var string = "";
    var i = 0;
    var c = c1 = c2 = 0;
    while (i < utftext.length) {
      c = utftext.charCodeAt(i);
      if (c < 128) {
        string += String.fromCharCode(c);
        i++;
      } else if ((c > 191) && (c < 224)) {
        c2 = utftext.charCodeAt(i + 1);
        string += String.fromCharCode(((c & 31) << 6) | (c2 & 63));
        i += 2;
      } else {
        c2 = utftext.charCodeAt(i + 1);
        c3 = utftext.charCodeAt(i + 2);
        string += String.fromCharCode(((c & 15) << 12) | ((c2 & 63) << 6) | (c3 & 63));
        i += 3;
      }
    }
    return string;
  }
};

/**
 * 替换字符串中所有要替换的字符
 */
window.String.prototype.replaceAll = function (reallyDo, replaceWith, ignoreCase) {
  if (!RegExp.prototype.isPrototypeOf(reallyDo)) {
    return this.replace(new RegExp(reallyDo, (ignoreCase ? "gi" : "g")),
      replaceWith);
  } else {
    return this.replace(reallyDo, replaceWith);
  }
};

/**
 * 对一个字符串分割去重后返回一个字段串
 * @param splitter 分割符，默认逗号
 * @returns {any[]}
 */
window.String.prototype.uniqueValue = function (splitter,outSplitter) {
  if (window.$t.isEmpty(splitter)) {
    splitter = ",";
  }
  if (window.$t.isEmpty(outSplitter)) {
    outSplitter = splitter;
  }
  return this.uniqueArray(splitter).join(outSplitter);
};

/**
 * 对一个字符串分割后去重返回一个数组
 * @param splitter 分割符，默认逗号
 * @returns {any[]}
 */
window.String.prototype.uniqueArray = function (splitter) {
  if (window.$t.isEmpty(splitter)) {
    splitter = ",";
  }
  return window.$t.array.uniqueArray(this.split(splitter));
};

window.$t.laytpl = laytpl;
window.$t._ = lodash;

let TimerUtils = {};

TimerUtils.timerContainer = [];

TimerUtils.setInterval = function(code, milliseconds) {
  var timer = setInterval(code, milliseconds);
  TimerUtils.timerContainer.push(timer);
  return timer;
};

TimerUtils.setTimeout = function(code, milliseconds) {
  var timer = setTimeout(code, milliseconds);
  TimerUtils.timerContainer.push(timer);
  return timer;
};

TimerUtils.clear = function() {
  for (var i = 0, len = TimerUtils.timerContainer.length; i < len; i++) {
    var timer = TimerUtils.timerContainer[i];
    try {
      timer && clearInterval(timer);
    } catch (e) {

    }
    try {
      timer && clearTimeout(timer);
    } catch (e) {

    }
  }
};

window.TimerUtils = TimerUtils;

window.$t.ignoreCaseValue = function(item,prop){
  return item[prop] || item[prop.toUpperCase()] || item[prop.toLowerCase()];
}
export default window.$t;
