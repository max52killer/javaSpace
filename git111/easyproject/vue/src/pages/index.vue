<template>
  <el-container class="qb-main">
    <el-container class="qb-main-container">
      <el-header>
        <qb_head></qb_head>
      </el-header>
      <el-main class="sidebar">
        <transition name="fade" mode="out-in">
          <router-view class="child-view" :key="$route.fullPath"></router-view>
        </transition>
      </el-main>
      <el-footer height="45px">
        <qb_footer></qb_footer>
      </el-footer>
    </el-container>
  </el-container>
</template>

<script>
  import qb_head from "./base/head";
  import qb_footer from "./base/footer";

  export default {
    name: "index",
    props: {},
    //设置当前全局使用的变量
    data() {
      return {};
    },
    computed: {},
    watch: {},
    //引用其它组件注册
    components: {qb_head, qb_footer},
    //定义组件中调用的函数
    methods: {},
    //过滤器，用于对展示的数据进行处理  value|method
    filters: {},
    created() {
    },
    mounted() {
    },
    updated() {
    },
    destroyed() {
    }
  }
</script>

<style scoped>
  .el-container .el-header {
    padding: 0;
    overflow: hidden;
    background-color: #1debe8;
  }

  .el-container .el-footer {
    padding: 0;
    overflow: hidden;
    background-color: #1debe8;
  }
</style>
