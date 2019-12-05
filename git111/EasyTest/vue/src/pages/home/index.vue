<template>

  <div class="revert" title="返回主页">
    <router-link to="/index">
      <img class="item-icon" src="static/images/icons/revert.png"/>
    </router-link>
  </div>
</template>

<script>
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
    components: {},
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

  .revert{
    position: absolute;
    top:0px;
    left:10px;
    width: 30px;
    height: 30px;
  }
  .item-icon{
    width: 60px;
    height: 60px;
  }
</style>
