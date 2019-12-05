<template>
  <div id="system-yhgl">
    <el-form :inline="true" :model="condition" class="demo-form-inline">
      <el-form-item label="姓名：">
        <el-input size="mini" v-model="condition.nickName" placeholder="请输入用户姓名"></el-input>
      </el-form-item>
      <el-form-item label="单位：">
        <el-select size="mini" v-model="condition.dept" placeholder="请选择用户部门" clearable>
          <el-option
            v-for="item in BM_DEPT"
            :key="item.code"
            :label="item.codeName"
            :value="item.code">
          </el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="性别：">
        <el-select size="mini" v-model="condition.sex" placeholder="请选择性别" clearable>
          <el-option
            v-for="item in BM_SEX"
            :key="item.code"
            :label="item.codeName"
            :value="item.code">
          </el-option>
        </el-select>
      </el-form-item>
      <el-form-item>
        <el-button size="mini" type="warning" @click="handleRegister"><i class="el-icon-s-check"></i>&nbsp;注册用户
        </el-button>
      </el-form-item>
    </el-form>
    <el-table
      :data="userData"
      empty-text="还没有用户信息，注册一个吧"
      border
      stripe
      style="width: 100%">
      <el-table-column
        prop="username"
        label="用户名"
        width="180" align="center">
      </el-table-column>
      <el-table-column
        prop="nickName"
        label="姓名"
        width="180" align="center">
      </el-table-column>
      <el-table-column
        prop="age"
        label="年龄"
        width="90" align="center">
      </el-table-column>
      <el-table-column
        prop="sfzh"
        label="身份证号"
        width="180" align="center">
      </el-table-column>
      <el-table-column
        prop="dept"
        label="部门"
        width="90" align="center">
      </el-table-column>
      <el-table-column
        prop="sex"
        label="性别"
        width="90" align="center">
      </el-table-column>
      <el-table-column
        label="密码" align="center">
        <template slot-scope="scope">
          <el-input v-model="scope.row.password" disabled show-password></el-input>
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
      @size-change="handleSizeChange"
      @current-change="handleCurrentChange"
      :current-page="currentPage4"
      :page-sizes="[3, 5, 10, 15]"
      :page-size="3"
      layout="total, sizes, prev, pager, next, jumper"
      :total="userData.length">
    </el-pagination>
  </div>
</template>

<script>
  import userApi from "../../../api/userApi";

  export default {
    name: "index",
    props: {},
    created() {
      userApi.queryList().then(resp => {
        console.log("请求后端的数据：：：", resp);
        if (resp) {
          this.userData = resp.data;
        }
      });
    },
    mounted() {
    },
    computed: {},
    watch: {},
    //引用其它组件注册
    components: {},
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
        BM_DEPT: [
          {code: "01", codeName: "部门01"},
          {code: "02", codeName: "部门02"},
          {code: "03", codeName: "部门03"},
          {code: "04", codeName: "部门04"}
        ],
        BM_SEX: [
          {code: "M", codeName: "男"},
          {code: "W", codeName: "女"},
          {code: "MW", codeName: "人妖"},
          {code: "WZ", codeName: "未知"},
        ],
        currentPage4: 1
      };
    },
    methods: {
      handleRegister() {

      },
      handleSave(row) {

      },
      handleDel(row) {

      },
      handleSizeChange(val) {
        console.log(`每页 ${val} 条`);
      },
      handleCurrentChange(val) {
        console.log(`当前页: ${val}`);
      }
    },
    //过滤器，用于对展示的数据进行处理  value|method
    filters: {},
  }
</script>

<style scoped>

</style>
