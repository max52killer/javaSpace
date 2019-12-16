<template>
  <div id="system-pzgl">
    <el-row>
      <el-col :span="4">
        <el-menu
          :default-active="selectMenu"
          class="el-menu-vertical-demo"
          @select="handleSelect"
          align="center">
          <el-menu-item v-for="(bm,index) in showList" :index="bm.type" :key="bm.type">
            <span slot="title">{{bm.typeName}}({{bm.type}})<i class="el-icon-delete el-icon--right" title="删除表码" @click="handleBmDel(bm)"></i></span>
          </el-menu-item>
          <el-menu-item index="isEmp" v-if="showList.length<=0">
            <i class="el-icon-question" title="暂无表码"></i>
          </el-menu-item>
          <el-menu-item index="add">
            <i class="el-icon-circle-plus" title="新增表码"></i>
          </el-menu-item>
        </el-menu>
      </el-col>
      <el-col :span="20">
        <not-page desc="暂无表码，点击“+”可新增表码" v-if="selectMenu==='isEmp'"></not-page>
        <bm-list ref="editor"></bm-list>
      </el-col>
    </el-row>
    <el-dialog title="添加表码" :visible.sync="dialogFormVisible">
      <el-form :model="bmForm" ref="bmForm" align="center" :rules="rules">
        <el-form-item label="表码类型：" label-width="120px">
          <el-input v-model="bmForm.type" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="表码类型名称：" label-width="120px">
          <el-input v-model="bmForm.typeName" autocomplete="off"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取 消</el-button>
        <el-button type="primary" @click="handleFromSubmit">确 定</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
  import bmEdit from "./bm-edit/index";
  import bmList from "./bm-list/index";
  import bmCodeApi from "../../../api/bmCodeApi";
  import notPage from "@/components/not-page/index";
  export default {
    name: "index",
    props: {},
    created() {

    },
    mounted() {
      this.initSelectMenu();
    },
    //引用其它组件注册
    components: {bmEdit,bmList,notPage},
    //定义组件中调用的函数
    //设置当前全局使用的变量
    data() {
      return {
        bmList: [],
        selectMenu: "",
        bmArr:null,
        dialogFormVisible:false,
        bmForm:{
          code:"",
          codeName:"",
          type:"",
          typeName:"",
          no:"",
        },
        rules:{},
        showList:[],
        mergeBmObj:{},
        codeMap:{},
        bmType:"",
        bmTypeName:""
      };
    },
    methods: {
      initSelectMenu(index) {
        bmCodeApi.queryBmList().then(resp=>{
          console.log("后端提取的表码数据：",resp);
          if(resp&&resp.data){
            this.bmList=resp.data.object;
            this.handleBmList();
            if(index){
              this.selectMenu=index;
            }else{
              if(this.bmList.length>0){
                this.selectMenu=this.bmList[0].type;
              }else{
                this.selectMenu="isEmp";
              }
            }
            this.bmType=index?index:this.selectMenu;
            let name=this.codeMap[this.selectMenu];
            this.bmTypeName=name?name:this.bmList[0].typeName;
            this.$refs.editor.load(this.bmType,this.bmTypeName);
          }
        });
      },
      handleSelect(index) {
        console.log("当前菜单：", index);
        this.selectMenu=index;
        if(index==='add'){
          this.dialogFormVisible=true;
        }else{
          this.bmType=index;
          this.bmTypeName=this.codeMap[index];
          this.$refs.editor.load(this.bmType,this.bmTypeName);
        }
      },
      handleFromSubmit(){
        console.log("表码添加提交：",this.bmForm);
        let type=this.bmForm.type;
        let typeName=this.bmForm.typeName;
        if(!type||type===""){
          this.$message.warning("请输入表码类型");
          return false;
        }
        if(!typeName||typeName===""){
          this.$message.warning("请输入表码类型名称");
          return false;
        }
        bmCodeApi.save(this.bmForm).then(resp=>{
          if(resp){
            console.log("保存返回：",resp);
            this.$message.success("保存成功！");
            this.initSelectMenu(this.bmForm.type);
            this.$refs.bmForm.resetFields();
          }
        });
        this.dialogFormVisible = false;
      },
      handleBmList(){
        let dataList=[];
        let obj={};
        let typeObj={};
        this.bmList.forEach((item,index)=>{
          if(!obj[item.type]){
            this.$set(obj,item.type,[item]);
            this.$set(typeObj,item.type,item.typeName);
          }else{
            obj[item.type].push(item);
          }
        });
        this.mergeBmObj=obj;
        this.codeMap=typeObj;
        for(let key in obj){
          dataList.push({type:key,typeName:typeObj[key],list:obj[key]});
        }
        this.showList=dataList;
      },
      handleBmDel(bm){
        this.$confirm('确定删除('+bm.typeName+'【'+bm.type+'】)?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(resp=>{
          //点击确定后执行
          console.log("删除表码：",bm);
          bmCodeApi.deleteBm(bm.type).then(resp=>{
            if(resp){
              this.$message.success("表码删除成功");
              this.initSelectMenu();
            }
          });
        }).catch(()=>{});
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
