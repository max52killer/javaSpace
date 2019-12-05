<template>
  <!--流星效果展示-->
  <div id="meteor-shower">
    <div class="night-body">
      <div class="night">
        <div class="shooting_star"></div>
        <div class="shooting_star"></div>
        <div class="shooting_star"></div>
        <div class="shooting_star"></div>
        <div class="shooting_star"></div>
        <div class="shooting_star"></div>
        <div class="shooting_star"></div>
        <div class="shooting_star"></div>
        <div class="shooting_star"></div>
        <div class="shooting_star"></div>
        <div class="shooting_star"></div>
        <div class="shooting_star"></div>
        <div class="shooting_star"></div>
        <div class="shooting_star"></div>
        <div class="shooting_star"></div>
        <div class="shooting_star"></div>
        <div class="shooting_star"></div>
        <div class="shooting_star"></div>
        <div class="shooting_star"></div>
        <div class="shooting_star"></div>
      </div>
    </div>
    <revert></revert>
  </div>
</template>

<script>
  import revert from "@/pages/home/index"

  export default {
    name: "meteor-shower",
    props: {},
    //设置当前全局使用的变量
    data() {
      return {};
    },
    computed: {},
    watch: {},
    //引用其它组件注册
    components: {revert},
    //定义组件中调用的函数
    methods: {},
    //过滤器，用于对展示的数据进行处理  value|method
    filters: {},
    beforeCreate: function () {
    },
    created: function () {
    },
    beforeMount: function () {
    },
    mounted: function () {
    },
    beforeUpdate: function () {
    },
    updated: function () {
    },
    beforeDestroy: function () {
    },
    destroyed: function () {
    }
  }
</script>

<style scoped>
  @import "css/style.css";

</style>
