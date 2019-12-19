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
    </el-form>
    <el-table
      v-loading="loading"
      :data="userData"
      empty-text="还没有用户信息，注册一个吧"
      border
      stripe
      style="width: 100%">
      <el-table-column
        label="照片"
        width="90" align="center">
      </el-table-column>
      <el-table-column
        label="用户信息"
        width="360" align="center">
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
              <el-col>
                <el-form-item prop="passwrod" label="密码：">
                  <el-input v-model="scope.row.password" size="mini" show-password></el-input>
                </el-form-item>
              </el-col>
              <el-col>
                <el-form-item prop="passwrods" label="确认密码：">
                  <el-input v-model="scope.row.passwords" size="mini" show-password></el-input>
                </el-form-item>
              </el-col>
            </el-row>
          </el-form>
        </template>
      </el-table-column>
      <el-table-column
        label="身份信息"
        width="90" align="center">
        <template slot-scope="scope">
          <el-form :model="scope.row" :rules="rules" :ref="'user_form_sfxx_'+scope.row.id" label-width="90px"
                   :key="'sfxx_'+scope.row.id">
            <el-form-item prop="sfzh" label="身份证号：">
              <el-input v-model="scope.row.sfzh" size="mini"></el-input>
            </el-form-item>
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
            <el-form-item prop="passwrods" label="确认密码：">
              <el-input v-model="scope.row.passwords" size="mini" show-password></el-input>
            </el-form-item>
          </el-form>
        </template>
      </el-table-column>
      <el-table-column
        prop="sfzh"
        label="身份证号"
        width="180" align="center">
        <template slot-scope="scope">
          <el-input v-model="scope.row.sfzh" size="mini"></el-input>
        </template>
      </el-table-column>
      <el-table-column
        prop="dept"
        label="部门"
        width="180" align="center">
        <template slot-scope="scope">
          <el-select size="mini" v-model="scope.row.dept" placeholder="请选择性别" clearable>
            <el-option
              v-for="item in $bm.list('BM_DEPT')"
              :key="item.code"
              :label="item.codeName"
              :value="item.code">
            </el-option>
          </el-select>
        </template>
      </el-table-column>
      <el-table-column
        prop="sex"
        label="性别"
        width="90" align="center">
        <template slot-scope="scope">
          <el-select size="mini" v-model="scope.row.sex" placeholder="请选择性别" clearable>
            <el-option
              v-for="item in $bm.list('BM_XB0000')"
              :key="item.code"
              :label="item.codeName"
              :value="item.code">
            </el-option>
          </el-select>
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
      this.loadData();
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
        currentPage4: 1,
        loading: true,
        rules:{},
      };
    },
    methods: {
      loadData() {
        userApi.queryList().then(resp => {
          console.log("请求后端的数据：：：", resp);
          if (resp) {
            this.userData = resp.data;
            this.loading = false;
          }
        });
      },
      handleRegister() {
        this.$router.push("/user-edit");
      },
      handleSave(row) {
        console.log("修改的数据：",row);
        this.loading=true;
        userApi.update(row).then(resp=>{
          if(resp){
            this.loadData();
          }
        });
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
