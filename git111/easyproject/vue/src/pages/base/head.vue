<template>
  <div id="qb-head" class="qb-head-box">
    <el-row :gutter="20">
      <el-col :span="4" class="qb-head-title">
        <span>示例演示</span>
      </el-col>
      <el-col :span="16">
        <el-menu
          :default-active="activeIndex"
          class="el-menu-demo"
          mode="horizontal"
          background-color="#545c64"
          text-color="#fff"
          active-text-color="#ffd04b" router>
          <el-menu-item v-for="(item,index) in $router.options.routes[0]['children']" :index="item.path" :key="item.path" v-if="!item.meta.hide">
            <i class="icon" :class="item.meta.icon"></i>
            <span slot="title">{{item.meta.title}}</span>
          </el-menu-item>
        </el-menu>
      </el-col>
      <el-col :span="4" class="qb-head-info">
        <el-dropdown>
          <span class="el-dropdown-link">
            <el-avatar icon="el-icon-user-solid"></el-avatar>
            <i class="el-icon-arrow-down el-icon--right"></i>
          </span>
          <el-dropdown-menu slot="dropdown">
            <el-dropdown-item><span @click="handleEdit"><i class="el-icon-edit"></i>编辑资料</span></el-dropdown-item>
            <el-dropdown-item><span @click="handleLogout"><i class="el-icon-switch-button"></i>退出</span>
            </el-dropdown-item>
          </el-dropdown-menu>
        </el-dropdown>
      </el-col>
    </el-row>
  </div>
</template>

<script>
  export default {
    name: "qb-head",
    props: {},
    //设置当前全局使用的变量
    data() {
      return {
        activeIndex: '/system-manage',
      };
    },
    computed: {},
    watch: {},
    //引用其它组件注册
    components: {},
    //定义组件中调用的函数
    methods: {
      handleEdit() {
        console.log("编辑信息。。。");
      },
      handleLogout() {
        this.$confirm("是否退出", "提示", {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(resp => {
            sessionStorage.removeItem("auth_token");
            this.$router.push("/login");
          }
        );
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
  .qb-head-box {
    padding: 0;
    margin: 0;
  }

  .qb-head-title {
    text-align: center;
    line-height: 61px;
  }

  .qb-head-info {
    text-align: right;
    line-height: 61px;
  }

  .el-dropdown-link {
    cursor: pointer;
    display: flex;
    justify-content: center;
  }

  .el-dropdown {
    margin-right: 20px;
  }

  .el-dropdown-link .el-icon--right {
    line-height: 30px;
    /*position: absolute;*/
    /*left: 8px;*/
    /*top: 18px;*/
  }
</style>
