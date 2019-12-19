<template>
  <div>
    <div>登陆页</div>
    <div>
      <input type="text" v-model="loginForm.username" /><br/>
      <input type="text" v-model="loginForm.password" /><br/>
      <button @click="login">登陆</button>
    </div>
  </div>
</template>

<script>
  import demoApi from "../../api/demoApi";
  export default {
    name: "login-index",
    props: {},
    computed: {},
    watch: {},
    //引用其它组件注册
    components: {},
    created() {
    },
    mounted() {
    },
    //设置当前全局使用的变量
    data() {
      return {
        loginForm:{
          username:"",
          passwrod:""
        }
      };
    },
    //定义组件中调用的函数
    methods: {
      login(){
        demoApi.loginIn(this.loginForm).then(resp=>{
          if(resp){
            alert("登陆成功！");
            sessionStorage.setItem("auth_token","dasdsdasdasdasdatguyhjpfghjkl");
            this.$router.push("/");
          }
        });
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

</style>
