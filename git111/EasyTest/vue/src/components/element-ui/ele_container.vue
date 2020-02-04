<template>
  <div>
    <revert></revert>
    <div>
      <div class="content">
        <el-upload
          class="upload-demo"
          action="#"
          :multiple="false"
          :http-request="uploadFile"
          :show-file-list="true"
          :limit="1"
          accept=".xls;.xlsx"
        >
          <el-button size="mini" type="primary">点击上传<i class="el-icon-upload el-icon--right"></i></el-button>
        </el-upload>
      </div>
    </div>
  </div>
</template>

<script>
  import revert from "@/pages/home/index";
  import demoApi from "../../api/demoApi";

  export default {
    name: "ele_container",
    props: {},
    //设置当前全局使用的变量
    data() {
      return {
        fileList: []
      };
    },
    computed: {},
    watch: {},
    //引用其它组件注册
    components: {
      revert
    },
    //定义组件中调用的函数
    methods: {
      uploadFile(param) {
        let file = param.file;
        let size = file.size;
//        if (size > 5 * 1024 * 1024) {
//          this.$message({message: "文件上传失败，最大支持上传5M的文件", type: "warning"});
//          return false;
//        }
//        if (!/\.(json)$/.test(file.name)) {
//          this.$message({message: "文件上传失败，仅支持上传json格式的文件", type: "warning"});
//          return false;
//        }
        demoApi.upload(file).then(resp=>{
          console.log(resp);
          if(resp){
            this.$message({message: "文件上传成功", type: "success"});
          }
        });
        this.fileList.push(file);
        console.log(file);
      },
    },
    //过滤器，用于对展示的数据进行处理  value|method
    filters: {},
    created: function () {
    },
    mounted: function () {
    },
    destroyed: function () {
    }
  }
</script>

<style scoped>
  .content {
    position: absolute;
    left: 200px;
    width: 80%;
    border: 1px solid;
    height: 100%;
    text-align: center;
    background-color: #ccc;
  }

</style>
