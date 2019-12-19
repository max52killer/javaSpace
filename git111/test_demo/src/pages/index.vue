<template>
  <div id="IndexMain" class="Main">
    <h1>首页</h1>
    <button @click="qqGet">点击请求数据</button>
    <select v-model="sel">
      <option value="01">表格1</option>
      <option value="02">表格2</option>
    </select>
    <!--<ant-table></ant-table>-->
    <ant-table1 v-if="sel=='01'"></ant-table1>
    <ant-table v-if="sel=='02'"></ant-table>
  </div>
</template>

<script>
  import demoApi from "../api/demoApi";
  import antTable from "./a-table/index";
  import antTable1 from "./a-table/index1";
  export default {
    name: "index",
    props: {},
    computed: {},
    watch: {},
    //引用其它组件注册
    components: {antTable,antTable1},
    created() {
    },
    mounted() {
    },
    //设置当前全局使用的变量
    data() {
      return {
        sel:"",
      };
    },
    //定义组件中调用的函数
    methods: {
      qqGet() {
        demoApi.queryAll().then(resp=>{
          console.log("请求结果：",resp);
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
