<template>
    <a-table :dataSource="dataSource" :columns="columns" bordered>
      <template slot="options" slot-scope="text,record">
        <a-button @click="toDeletes(record)">删除</a-button>
        <a-button @click="toEdit(record)">编辑</a-button>
      </template>
    </a-table>
</template>

<script>
  export default {
    name: "index1",
    props: {},
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
        dataSource: [
          {
            key: '1',
            name: '胡彦斌',
            age: 32,
            address: '西湖区湖底公园1号',
          },
          {
            key: '2',
            name: '胡彦祖',
            age: 42,
            address: '西湖区湖底公园1号',
          },
        ],
        columns: [
          {
            title: '姓名',
            dataIndex: 'name',
            key: 'name',
          },
          {
            title: '年龄',
            dataIndex: 'age',
            key: 'age',
          },
          {
            title: '住址',
            dataIndex: 'address',
            key: 'address',
          },
          {
            title:"操作",
            dataIndex:"options",
            key:"options",
            scopedSlots: { customRender: 'options' },
            render:(text,record)=>({

            })
          }
        ]
      };
    },
    //定义组件中调用的函数
    methods: {
      toDeletes(key){
        console.log("delete",key);
      },
      toEdit(key){
        console.log(key);
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

</style>
