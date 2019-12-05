<template>
  <div>
    <lzqs></lzqs>
    <section class="ksh-home-entry">
      <div class="ksh-home-entry-title"><img class="xtdh" src="../../static/images/icons/xtdh.png"/>系统导航</div>
      <ul class="ksh-home-entry-list b-clearfix">
        <li class="ksh-home-entry__item" v-for="(item, index) in entryList" :key="index">
          <router-link :to="item.link" class="ksh-home-entry__item-link">
            <span class="ksh-home-entry__icon">
              <img class="item-icon" :src="item.icon"/>
            </span>
            <p class="ksh-home-entry__item-name">{{item.name}}</p>
          </router-link>
        </li>
      </ul>
    </section>
    <!--<revert></revert>-->
  </div>

</template>

<script>
  import lzqs from "@/components/lzqs/lzqs"
  import revert from "@/pages/home/index";
  import demoApi from "@/api/demoApi"
  export default {
    name: "index",
    props: {},
    //设置当前全局使用的变量
    data() {
      return {
        entryList: [
          {
            name: '牛頓粒子',
            icon: '../../static/images/icons/ndlz.png',
            link: '/ndlz'
          },
          {
            name: '流星',
            icon: '../../static/images/icons/meteor.png',
            link: '/meteor'
          },{
            name: '星空',
            icon: '../../static/images/icons/star.png',
            link: '/stars_shining'
          },
          {
            name: '烟花',
            icon: '../../static/images/icons/yh.png',
            link: '/yh01'
          },
          {
            name: '滚动时钟',
            icon: '../../static/images/icons/timer.png',
            link: '/gdsz'
          },
          {
            name: '情人节',
            icon: '../../static/images/icons/qrj.png',
            link: '/qrj01'
          },
          //ele_container
          {
            name: 'element-ui',
            icon: '../../static/images/icons/ndlz.png',
            link:'/ele_container'
          },
          {
            name: 'demo',
            icon: '../../static/images/icons/demo.png',
            link:'/demo'
          }
        ]
      };
    },
    computed: {},
    watch: {},
    //引用其它组件注册
    components: {
      lzqs,revert
    },
    //定义组件中调用的函数
    methods: {},
    //过滤器，用于对展示的数据进行处理  value|method
    filters: {},
    created: function () {
      if($(".dg .a").length>0){//去除牛顿粒子中渲染的多余窗口
        $(".dg .a").remove();
      }
      // demoApi.queryZjsk().then(response=>{
      //   console.log("后台返回数据：",response);
      // })
    },
    mounted: function () {
      console.log("路由：",this.$route);
    },
  }
</script>

<style scoped>
  @import "scss/style.scss";
  .item-icon{
    width:60px;
    height:60px;
  }
  .xtdh{
    width:45px;
    height:30px;
  }
</style>
