<template>
  <div id="system-pzgl">
    <el-row>
      <el-col :span="4">
        <el-menu
          :default-active="selectMenu"
          class="el-menu-vertical-demo"
          @select="handleSelect"
          align="center">
          <el-menu-item v-for="bm in bmList" :index="bm.type" :key="bm.type">
            <span slot="title">{{bm.typeName}}({{bm.type}})</span>
          </el-menu-item>
          <el-menu-item index="add">
            <i class="el-icon-circle-plus" title="新增表码"></i>
          </el-menu-item>
        </el-menu>
      </el-col>
      <el-col :span="20">
        <bm-edit v-if="selectMenu=='add'"></bm-edit>
        <bm-list v-else></bm-list>
      </el-col>
    </el-row>
  </div>
</template>

<script>
  import bmEdit from "./bm-edit/index";
  import bmList from "./bm-list/index";
  export default {
    name: "index",
    props: {},
    created() {
      this.selectMenu=this.bmList[0].type;
    },
    mounted() {
    },
    //引用其它组件注册
    components: {bmEdit,bmList},
    //定义组件中调用的函数
    //设置当前全局使用的变量
    data() {
      return {
        bmList: [
          {type: "BM_DEPT", typeName: "部门表码"},
          {type: "BM_SEX", typeName: "性别表码"},
          {type: "BM_MZ0000", typeName: "民族表码"},
        ],
        selectMenu: "",
      };
    },
    methods: {
      handleSelect(index) {
        console.log("当前菜单：", index);
        this.selectMenu=index;
      }
    },
    computed: {},
    watch: {},
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
