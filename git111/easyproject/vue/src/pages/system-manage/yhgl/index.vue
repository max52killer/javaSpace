<template>
  <div id="system-yhgl">
    <el-form :inline="true" :model="condition" class="demo-form-inline">
      <el-form-item label="姓名：">
        <el-input size="mini" v-model="condition.nickName" placeholder="请输入用户姓名"></el-input>
      </el-form-item>
      <el-form-item label="单位：">
        <el-select size="mini" v-model="condition.dept" placeholder="请选择用户部门" clearable>
          <el-option
            v-for="item in $bm.list('BM_DEPT')"
            :key="item.code"
            :label="item.codeName"
            :value="item.code">
          </el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="性别：">
        <el-select size="mini" v-model="condition.sex" placeholder="请选择性别" clearable>
          <el-option
            v-for="item in $bm.list('BM_XB0000')"
            :key="item.code"
            :label="item.codeName"
            :value="item.code">
          </el-option>
        </el-select>
      </el-form-item>
      <el-form-item>
        <el-button size="mini" type="warning" @click="handleRegister">
          <i class="el-icon-s-check"></i>&nbsp;注册用户
        </el-button>
      </el-form-item>
      <el-form-item>
        <el-button size="mini" type="primary" @click="handleTempDownload">
          <i class="el-icon-tickets"></i>&nbsp;模板下载
        </el-button>
      </el-form-item>
      <el-form-item>
        <el-upload ref="ryxxFile"
                   class="upload-demo"
                   action="#"
                   :multiple="false"
                   :http-request="handleImport"
                   :show-file-list="false"
                   :limit="1"
                   accept=".xls,.xlsx"
                   v-loading.fullscreen.lock="fullscreenLoading"
                   element-loading-text="拼命上传中"
                   element-loading-spinner="el-icon-loading"
                   element-loading-background="rgba(0, 0, 0, 0.8)"
        >
          <el-button size="mini" type="primary">
            <i class="el-icon-upload2"></i>&nbsp;导入
          </el-button>
        </el-upload>
      </el-form-item>
      <el-form-item>
        <el-button size="mini" type="primary" @click="handleExport">
          <i class="el-icon-download"></i>&nbsp;导出
        </el-button>
      </el-form-item>
    </el-form>
    <el-table
      v-loading="loading"
      :data="pageData"
      empty-text="还没有用户信息，注册一个吧"
      border
      stripe
      style="width: 100%">
      <el-table-column
        label="照片"
        width="120" align="center">
        <img-avage></img-avage>
      </el-table-column>
      <el-table-column
        label="用户信息"
        width="400" align="center">
        <template slot-scope="scope">
          <el-form :model="scope.row" :rules="rules" :ref="'user_form_yhxx_'+scope.row.id" label-width="90px"
                   :key="'yhxx_'+scope.row.id">
            <el-row>
              <el-col :span="12">
                <el-form-item prop="username" label="用户名：">
                  <el-input v-model="scope.row.username" size="mini"></el-input>
                </el-form-item>
              </el-col>
              <el-col :span="12">
                <el-form-item prop="nickName" label="姓名：">
                  <el-input v-model="scope.row.nickName" size="mini"></el-input>
                </el-form-item>
              </el-col>
            </el-row>
            <el-row>
              <el-col :span="12">
                <el-form-item prop="passwrod" label="密码：">
                  <el-input v-model="scope.row.password" size="mini" show-password></el-input>
                </el-form-item>
              </el-col>
              <el-col :span="12">
                <el-form-item prop="passwrods" label="确认密码：">
                  <el-input v-model="scope.row.passwords" size="mini" show-password></el-input>
                </el-form-item>
              </el-col>
            </el-row>
          </el-form>
        </template>
      </el-table-column>
      <el-table-column label="身份信息" align="center">
        <template slot-scope="scope">
          <el-form :model="scope.row" :rules="rules" :ref="'user_form_sfxx_'+scope.row.id" label-width="90px"
                   :key="'sfxx_'+scope.row.id">
            <el-row>
              <el-col :span="12">
                <el-form-item prop="sfzh" label="身份证号：">
                  <el-input v-model="scope.row.sfzh" size="mini"></el-input>
                </el-form-item>
              </el-col>
              <el-col :span="12">
                <el-form-item prop="age" label="年龄：">
                  <el-input v-model="scope.row.age" size="mini"></el-input>
                </el-form-item>
              </el-col>
            </el-row>
            <el-row>
              <el-col :span="12">
                <el-form-item prop="dept" label="部门：">
                  <el-select size="mini" v-model="scope.row.dept" placeholder="请选择性别" clearable>
                    <el-option
                      v-for="item in $bm.list('BM_DEPT')"
                      :key="item.code"
                      :label="item.codeName"
                      :value="item.code">
                    </el-option>
                  </el-select>
                </el-form-item>
              </el-col>
              <el-col :span="12">
                <el-form-item prop="sex" label="性别：">
                  <el-select size="mini" v-model="scope.row.sex" placeholder="请选择性别" clearable>
                    <el-option
                      v-for="item in $bm.list('BM_XB0000')"
                      :key="item.code"
                      :label="item.codeName"
                      :value="item.code">
                    </el-option>
                  </el-select>
                </el-form-item>
              </el-col>
            </el-row>
          </el-form>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" width="150px">
        <template slot-scope="scope">
          <el-button type="success" icon="el-icon-check" @click="handleSave(scope.row)" size="mini"
                     title="点击进行编辑" circle></el-button>
          <el-button type="danger" icon="el-icon-delete" title="点击删除该条数据"
                     @click="handleDel(scope.row)" size="mini" circle></el-button>
        </template>
      </el-table-column>
    </el-table>
    <el-pagination
      :current-page="currentPage"
      :background="true"
      :page-size="pageSize"
      layout="prev, pager, next,total"
      :total="filterData.length"
      @current-change="handlePageChange"
      @prev-click="handlePageChange"
      @next-click="handlePageChange"
    >
    </el-pagination>
  </div>
</template>

<script>
  import userApi from "../../../api/userApi";
  import {mapActions, mapGetters} from 'vuex';
  import imgAvage from "@/components/img-avage/index";
  import XLSX from "xlsx";
  import fileApi from "../../../api/fileApi";

  export default {
    name: "index",
    props: {},
    created() {
      this.loadData();
    },
    mounted() {
    },
    computed: {
      ...mapGetters(['currentUser']),
      //数据监听做查询效果
      filterData() {
        this.currentData = [];
        for (var i = 0, len = this.userData.length; i < len; i++) {
          var user = this.userData[i];
          var filter = !$t.isEmpty(this.condition.nickName) && user.nickName.indexOf(this.condition.nickName) == -1;
          if (filter) {
            continue;
          }
          filter = !$t.isEmpty(this.condition.dept) && user.dept.indexOf(this.condition.dept) == -1;
          if (filter) {
            continue;
          }
          filter = !$t.isEmpty(this.condition.sex) && user.sex.indexOf(this.condition.sex) == -1;
          if (filter) {
            continue;
          } else {
            this.currentData.push(user);
          }
        }
        this.currentPage = 1;
        return this.currentData;
      },
      //对结果数据进行分页
      pageData() {
        let start = (this.currentPage - 1) * this.pageSize;
        return this.currentData.slice(start, start + this.pageSize);
      }
    },
    watch: {},
    //引用其它组件注册
    components: {imgAvage},
    //定义组件中调用的函数
    //设置当前全局使用的变量
    data() {
      return {
        userData: [],
        condition: {
          nickName: "",
          sex: "",
          dept: ""
        },
        pageSize: 3,
        currentPage: 1,
        currentData: [],
        loading: true,
        rules: {},
        fullscreenLoading: false,
        yhxxExcelHead:{username:"用户名",nickName:"姓名",sfzh:"身份证号",dept:"部门",age:"年龄",sex:"性别"},
        bmDeptMap:{},

      };
    },
    methods: {
      loadData() {
        userApi.queryList().then(resp => {
          console.log("请求后端的数据：：：", resp);
          if (resp) {
            this.userData = resp.data;
            this.userData.forEach((item, index) => {
              this.$set(item, "passwords", item.password);
            });
            this.loading = false;
          }
        });
      },
      handleRegister() {
        this.$router.push("/user-edit");
      },
      //人员信息导入模板下载
      handleTempDownload() {
        fileApi.exportMb("yhxxmb.xlsx");
      },
      //导入  前端解析excel文件   传入后台执行数据存储
      handleImport(param) {
        let self = this;
        self.fullscreenLoading = true;
        let file = param.file;
        let size = file.size;
        if (size > 5 * 1024 * 1024) {
          this.$message({message: "文件上传失败，最大支持上传5M的文件", type: "warning"});
          return false;
        }
        if (!/\.(xls)|\.(xlsx)$/.test(file.name)) {
          this.$message({message: "文件上传失败，仅支持上传xls或xlsx格式的文件", type: "warning"});
          return false;
        }
        //解析excel文件  封装成list发送到后端
        //定义reader，存放文件读取方法
        let reader = new FileReader();
        //启动函数
        reader.readAsBinaryString(file);
        reader.onload = function (e) {
          let ryxxList = [];
          //workbook存放excel的所有基本信息
          const workbook = XLSX.read(e.target.result, {type: 'binary'});
          //定义sheetList中存放excel表格的sheet表，就是最下方的tab
          let sheetList0 = workbook.SheetNames[0];// 工作表名称集合
//          sheetList.forEach((name) => {//循环每个sheet页的工作表格
          //暂时只取第一个tab作为提取数据域
          let worksheet = workbook.Sheets[sheetList0]; // 只能通过工作表名称来获取指定工作表
          let outSheetData = XLSX.utils.sheet_to_json(worksheet);
          let arr = [];
          outSheetData.map(v => {
            let obj = {};
            for (let key in self.yhxxExcelHead) {
              if (!obj[key]) {
                let val = v[self.yhxxExcelHead[key]];
                self.$set(obj, key, val || "");
              }
            }
            //设置密码
            self.$set(obj, "password", "123456");
            arr.push(obj);
          });
          console.log("解析完的值：", arr);
          ryxxList.push(arr);
          self.fullscreenLoading=false;
//          });


        };
      },
      /**
       *将文件发送到后台处理
       */
      handleImport01(param){
        let file = param.file;
        let size = file.size;
        fileApi.importYhxx01(file).then(resp=>{
          console.log("后台解析的文件：",resp);
        }).catch(error=>{
          this.$message.error("请求后端异常！");
        });
      },
      //导出
      handleExport() {

      },
      handleSave(row) {
        console.log("修改的数据：", row);
        this.loading = true;
        userApi.update(row).then(resp => {
          if (resp) {
            this.loadData();
          }
        });
      },
      handleDel(row) {

      },
      handlePageChange(currentPage) {
        this.currentPage = currentPage;
      },
    },
    //过滤器，用于对展示的数据进行处理  value|method
    filters: {},
  }
</script>

<style scoped>
  .el-form-item {
    margin-bottom: 10px;
  }

  .demo-form-inline {
    display: flex;
  }

  .yhgl-btn-area {
    justify-content: flex-end;
  }

  .yhgl-btn-area .el-button {
    margin-left: 10px;
    padding: 4px 10px;
    display: flex;
    justify-content: center;
    align-items: center;
    font-size: 14px;
    min-width: 80px;
  }
</style>
