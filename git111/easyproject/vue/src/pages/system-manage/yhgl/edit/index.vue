<template>
  <div id="user-edit" class="qb-content">
    <qb-tips>
      <div slot="leftTips">
        <div class="tips-left-box">
          <el-avatar shape="square" size="large" icon="el-icon-share"></el-avatar>
          <span class="tips-title">{{$route.meta.title}}</span>
          <span class="tips-desc"><span>{{$route.meta.description}}</span></span>
        </div>
      </div>
      <span slot="rightTips">右边内容</span>
    </qb-tips>
    <div class="qb-content-body">
      <el-form ref="userForm" :model="userForm" label-width="80px" :rules="rules" label-position="top">
        <el-form-item label="姓名/部门" required>
          <el-col :span="5.2">
            <el-form-item prop="nickName">
              <el-input v-model="userForm.nickName" placeholder="请输入姓名" size="small" style="width: 200px;"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="1.5">&nbsp;/&nbsp;</el-col>
          <el-col :span="5.2">
            <el-form-item prop="department">
              <el-select v-model="userForm.dept" placeholder="请选择部门" size="small" style="width: 455px;">
                <el-option
                  v-for="item in $bm.list('BM_DEPT')"
                  :key="item.code"
                  :label="item.codeName"
                  :value="item.code">
                </el-option>
              </el-select>
            </el-form-item>
          </el-col>
        </el-form-item>
        <el-form-item label="用户名/密码/确认密码" required>
          <el-col :span="5.2">
            <el-form-item prop="name">
              <el-input v-model="userForm.username" placeholder="请输入用户名" size="small" style="width: 200px;"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="1.5">&nbsp;/&nbsp;</el-col>
          <el-col :span="5.2">
            <el-form-item prop="password">
              <el-input v-model="userForm.password" type="password" placeholder="请输入密码" size="small"
                        style="width:220px" show-password></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="1.5">&nbsp;/&nbsp;</el-col>
          <el-col :span="5.2">
            <el-form-item prop="passwords">
              <el-input v-model="userForm.passwords" type="password" placeholder="请输入确认密码" size="small"
                        style="width:220px" show-password></el-input>
            </el-form-item>
          </el-col>
        </el-form-item>
        <el-form-item label="身份证号/年龄/性别" required>
          <el-col :span="5.2">
            <el-form-item prop="sfzh">
              <el-input v-model="userForm.sfzh" placeholder="请输入身份证号" size="small" style="width: 200px;"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="1.5">&nbsp;/&nbsp;</el-col>
          <el-col :span="5.2">
            <el-form-item prop="age">
              <el-input v-model="userForm.age" placeholder="请输入年龄" size="small"
                        style="width:220px"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="1.5">&nbsp;/&nbsp;</el-col>
          <el-col :span="5.2">
            <el-form-item prop="sex">
              <el-select v-model="userForm.sex" placeholder="请选择性别" size="small">
                <el-option
                  v-for="item in $bm.list('BM_XB0000')"
                  :key="item.code"
                  :label="item.codeName"
                  :value="item.code">
                </el-option>
              </el-select>
            </el-form-item>
          </el-col>
        </el-form-item>
        <el-form-item align="center">
          <el-button size="mini" type="primary" @click="handleSave"><i
            class="el-icon-success"></i>&nbsp;{{userForm._id ? "更新信息" : "提交信息"}}
          </el-button>
          <el-button size="mini" type="info" @click="backPage(false)">
            <i class="el-icon-back"></i>&nbsp;返回
          </el-button>
        </el-form-item>
      </el-form>
      <div class="user-image-form">
        图片
      </div>
    </div>
  </div>
</template>

<script>
  import QbTips from "@/components/tips/index";
  import userApi from "../../../../api/userApi";

  export default {
    name: "index",
    props: {},
    computed: {},
    watch: {},
    //引用其它组件注册
    components: {QbTips},
    created() {
    },
    mounted() {
    },
    //设置当前全局使用的变量
    data() {
      return {
        userForm: {
          _id: "",
          username: "",
          nickName: "",
          password: "",
          passwords:"",
          sfzh: "",
          sex: "",
          age: "",
          dept: "",
          image: ""
        },
        rules: {},
      };
    },
    //定义组件中调用的函数
    methods: {
      handleSave(){
        console.log("提交内容：",this.userForm);
        userApi.save(this.userForm).then(resp=>{
          if(resp.data){
            this.$message.success("用户注册成功！");
            this.$router.push("/system-manage");
          }else{
            this.$message.error("用户注册失败！");
          }
        });
      },
      backPage(isNavite){
        this.$router.go(-1);
      }
    },
    //过滤器，用于对展示的数据进行处理  value|method
    filters: {},
    updated() {
    },
    destroyed() {
    }
  }
</script>

<style scoped>
  .el-form {
    width: 700px;
    float: left;
  }

  .user-image-form {
    width: 307px;
    float: right;
    padding-top: 30px;
    padding-right: 30px;
  }
</style>
