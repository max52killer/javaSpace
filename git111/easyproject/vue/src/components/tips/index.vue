<template>
    <div id="qb-tips" class="qb-tips">
      <el-row>
        <el-col :span="4" style="width: 16%">
          &nbsp;
        </el-col>
        <el-col :span="16">
          <div class="tips-left">
            <slot name="leftTips"></slot>
          </div>
          <div class="tips-right">
            <slot name="rightTips"></slot>
          </div>
        </el-col>
        <el-col :span="4">
            &nbsp;
        </el-col>
      </el-row>
    </div>
</template>

<script>
    export default {
        name: "index",
        props: {

        },
        //设置当前全局使用的变量
        data() {
            return {};
        },
        computed: {},
        watch: {},
        //引用其它组件注册
        components: {},
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
  .qb-tips{
    height:100px;
    background-color: aqua;
    margin-bottom: 10px;
    line-height: 100px;
    box-shadow: 0 2px 4px rgba(0, 0, 0, .12), 0 0 6px rgba(0, 0, 0, .04)
  }
  .tips-left{
    float: left;
  }
  .tips-right{
    float: right;
  }
  .tips-title{
    position: absolute;
    bottom: 25px;
    font-family: 微软雅黑;
    font-size: 20px;
    color: blue;
    font-weight: bold;
  }
  .tips-left-box{
    position: absolute;
    top:12px;
  }
</style>
