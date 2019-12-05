<template>
  <div id="prototype-demo" class="qb-content">
    <qb-tips>
      <div slot="leftTips">
        <div class="tips-left-box">
          <el-avatar shape="square" size="large" icon="el-icon-magic-stick"></el-avatar>
          <span class="tips-title">{{$route.meta.title}}</span>
          <span class="tips-desc"><span>{{$route.meta.description}}</span></span>
        </div>
      </div>
      <span slot="rightTips">右边内容</span>
    </qb-tips>
  </div>
</template>

<script>
  import QbTips from "@/components/tips/index";

  export default {
    name: "index",
    props: {},
    //设置当前全局使用的变量
    data() {
      return {};
    },
    computed: {},
    watch: {},
    //引用其它组件注册
    components: {QbTips},
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

</style>
