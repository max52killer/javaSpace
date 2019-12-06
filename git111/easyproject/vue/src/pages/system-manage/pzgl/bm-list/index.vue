<template>
  <div id="bm-list">
    <el-card class="box-card" align="center">
      <div slot="header" class="clearfix">
        <i class="el-icon-info"></i>
        <span>您正在处理【{{bmData1.typeName}}({{bmData1.type}})】的数据，请避免编号重复</span>
      </div>
      <el-row>
        <el-table
          :data="bmData1.list"
          border
          style="width: 100%">
          <el-table-column
            label="编号">
            <template slot-scope="scope">
              <el-input v-model="scope.row.code"></el-input>
            </template>
          </el-table-column>
          <el-table-column
            label="名称"
            width="450">
            <template slot-scope="scope">
              <el-input v-model="scope.row.codeName"></el-input>
            </template>
          </el-table-column>
          <el-table-column
            label="排序编号">
            <template slot-scope="scope">
              <el-input v-model="scope.row.no"></el-input>
            </template>
          </el-table-column>
          <el-table-column label="操作" align="center" width="150px">
            <template slot-scope="scope">
              <el-button type="danger" icon="el-icon-delete" title="点击删除该条数据"
                         @click="handleDel(scope.$index)" size="mini" circle></el-button>
            </template>
          </el-table-column>
        </el-table>
      </el-row>
      <el-row>
        <el-button type="primary" @click="handleUpdate">更新数据</el-button>
      </el-row>
    </el-card>
  </div>
</template>

<script>
  export default {
    name: "index",
    props: {
      bmData: {type: Object, default: null}
    },
    computed: {},
    watch: {},
    //引用其它组件注册
    components: {},
    created() {
      //默认添加一个空对象值
      this.addBlank();
    },
    mounted() {
    },
    //设置当前全局使用的变量
    data() {
      return {
        bmData1:{//传入的数据结构
          type:"BM_DEPT",typeName:"部门表码",list:[{code:"01",codeName:"厦门",no:"1"},{code:"02",codeName:"北京",no:"2"},]
        }
      };
    },
    //定义组件中调用的函数
    methods: {
      addBlank(){
        if(this.bmData1.list.length>1){
          this.bmData1.list.push({});
        }
      },
      handleDel(index){
        console.log(index);
        if(index<1){
          this.$message.warning("必须有一条数据");
          return false;
        }
        this.bmData1.list.splice(index,1);
      },
      //后端更新时先将当前type的所有表码全部删除  然后再将最新提交的数据执行插入
      handleUpdate(){

      }
    },
  }
</script>

<style scoped>
  .el-row{
    margin-bottom: 20px;
  }
</style>
