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
      <!--<el-row>-->
        <!--<el-col :span="4" style="width:16%">-->
          <el-tabs type="border-card" tab-position="left" style="height: 83%">
            <el-tab-pane label="用户管理"><yhgl></yhgl></el-tab-pane>
            <el-tab-pane label="配置管理"><pzgl></pzgl></el-tab-pane>
            <el-tab-pane label="角色管理">角色管理</el-tab-pane>
            <el-tab-pane label="定时任务补偿">定时任务补偿</el-tab-pane>
          </el-tabs>
        <!--</el-col>-->
        <!--<el-col :span="20" style="width: 84%">右边</el-col>-->
      <!--</el-row>-->
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
              isCollapse: true,
            };
        },
        computed: {},
        watch: {},
        //引用其它组件注册
        components: {QbTips,yhgl,pzgl},
        //定义组件中调用的函数
        methods: {

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
  .el-col{
    padding-right: 5px;
  }
</style>
