<template>
  <div id="bm-list">
    单个表码列表界面（在此新增表码内容）
  </div>
</template>

<script>
  export default {
    name: "index",
    props: {
      type: {type: String, default: ""}
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

</style>
