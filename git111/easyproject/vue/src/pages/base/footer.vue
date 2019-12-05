<template>
  <div id="qb-footer" class="qb-footer">
    © Copyright xxxxxxxxx. 2019
  </div>
</template>

<script>
    export default {
        name: "footer",
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
  .qb-footer{
    text-align: center;
    line-height: 45px;
    color: rgba(65, 71, 17, 0.98);
  }
</style>
