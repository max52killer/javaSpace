<template>
  <div id="tools-demo" class="qb-content">
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
    <el-row>
      <el-col :span="4">
        <el-menu
          :default-active="selectMenu"
          class="el-menu-vertical-demo"
          @select="handleSelect"
          align="center"
          background-color="#1CC6F1"
          text-color="#fff"
          active-text-color="#ffd04b">
          <el-menu-item v-for="tab in tabs" :index="tab.bh" :key="tab.bh">
            <span slot="title">{{tab.mc}}</span>
          </el-menu-item>
        </el-menu>
      </el-col>
      <el-col :span="20">
        <yhgl v-if="selectMenu=='yhgl'"></yhgl>
        <pzgl v-if="selectMenu=='pzgl'"></pzgl>
      </el-col>
    </el-row>
  </div>
</template>

<script>
  import QbTips from "@/components/tips/index";
  import yhgl from "./yhgl/index";
  import pzgl from "./pzgl/index";

  export default {
    name: "index",
    props: {},
    //设置当前全局使用的变量
    data() {
      return {
        selectMenu:"yhgl",
        isCollapse: true,
        tabs: [
          {bh: "yhgl", mc: "用户管理"},
          {bh: "pzgl", mc: "配置管理"}
          ],
      };
    },
    computed: {},
    watch: {},
    //引用其它组件注册
    components: {QbTips, yhgl, pzgl},
    //定义组件中调用的函数
    methods: {
      handleSelect(index){
        this.selectMenu=index;
      }
    },
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
  .el-col {
    padding-right: 5px;
  }
</style>
