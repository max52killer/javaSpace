<template>
  <div id="not-page" class="not-page">
    <i class="el-icon-question"></i>
    <p>{{desc}}</p>
    <el-drawer></el-drawer>
  </div>
</template>

<script>
    import ElDrawer from "element-ui/packages/drawer/src/main";

    export default {
        name: "not-page",
        props: {
          desc:{type:String,default:"暂无数据"}
        },
        computed: {},
        watch: {},
        //引用其它组件注册
        components: {ElDrawer},
        created() {
        },
        mounted() {
        },
        //设置当前全局使用的变量
        data() {
            return {};
        },
        //定义组件中调用的函数
        methods: {},
        //过滤器，用于对展示的数据进行处理  value|method
        filters: {},
        updated() {
        },
        destroyed() {
        }
    }
</script>

<style scoped>
  .not-page{
    text-align: center;
    font-size: 22px;
    color: #d5d5d5;
    position: absolute;
    cursor: default;
    top: 120px;
    left: 0;
    right: 0;
    bottom: 0;
    margin: auto;
  }
</style>
