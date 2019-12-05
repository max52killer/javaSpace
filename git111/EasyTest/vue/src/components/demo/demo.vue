<template>
  <div style="text-align: center;width: 800px;height: 600px">
    <hr/>
    <div>{{$store.state.count}}</div>
    <button @click="handleStore">点击改变count</button>
    <hr/>

  </div>

</template>

<script>
  import {mapMutations} from "vuex";
  import {mapGetters} from "vuex";
    export default {
        name: "demo",
        props: {},
        //设置当前全局使用的变量
        data() {
            return {};
        },
        computed: {
        },
        watch: {
        },
        //引用其它组件注册
        components: {},
        //定义组件中调用的函数
        methods: {
          ...mapMutations(['increment']),
          handleStore(){
            // this.$store.state.count++;
            // this.$store.commit("increment");
            this.increment(100000);
          },
          handleTodos(){
            let arr=[];
            for(let i=0;i<10;i++){
              arr.push({index:i+1,xm:"xm"+i,age:10+i,done:i%2>0?true:false})
            }
            // this.$store.todos=arr;
          }
        },
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
