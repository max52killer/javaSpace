<template>
  <div id="image-upload" class="image-upload">
    <el-avatar :shape="shape" :size="75" :src="url" v-if="url!=''"></el-avatar>
    <el-avatar icon="el-icon-user-solid" :size="75" :shape="shape" v-else></el-avatar>
    <el-upload ref="img"
               class="upload-demo"
               action="#"
               :multiple="false"
               :http-request="uploadImg"
               :show-file-list="false"
               :limit="1"
               accept=".json"
    >
      <el-button size="mini" type="primary">点击上传<i class="el-icon-upload el-icon--right"></i></el-button>
    </el-upload>
  </div>
</template>

<script>
  export default {
    name: "image-upload",
    props: {
      shape:{
        type:String,
        default:"square",
        validator(val) {
          return ['circle', 'square'].includes(val);
        }
      },
      url:{
        type:String,
        default:""
      },
      currentUser:{
        type:Object,
        default:null
      }
    },
    computed: {},
    watch: {},
    //引用其它组件注册
    components: {},
    created() {
    },
    mounted() {
    },
    //设置当前全局使用的变量
    data() {
      return {
        imgBase64:"",
      };
    },
    //定义组件中调用的函数
    methods: {
      uploadImg(param){
        let image = param.file;
        let size = image.size;
        if (size > 5 * 1024 * 1024) {
          this.$message({message: "文件上传失败，最大支持上传5MB的文件", type: "warning"});
          return false;
        }
        if (!/^image\/\w/g.test(image.type)) {
          _this.$message({message: "文件上传失败，仅支持上传图片文件", type: "warning"});
          return false;
        }
        //将文件使用文件上传接口上传
        let reader = new FileReader();
        reader.readAsDataURL(image);
        var _this = this;
        reader.onload = function () {
          _this.imgBase64 = this.result;
        };
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
  .image-upload {

  }
</style>
