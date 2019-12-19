<template>
  <a-table :dataSource="data"
           :loading="loading"
           :pagination="pagination"
           @change="handleTableChange"
           :rowKey="data => data.key">
    <a-table-column-group>
      <span slot="title" style="color: #1890ff">Name</span>
      <a-table-column
        dataIndex="firstName"
        key="firstName"
      >
        <span slot="title" style="color: #1890ff">First Name</span>
      </a-table-column>
      <a-table-column
        title="Last Name"
        dataIndex="lastName"
        key="lastName"
      />
    </a-table-column-group>
    <a-table-column
      title="Age"
      dataIndex="age"
      key="age"
    />
    <a-table-column
      title="Address"
      dataIndex="address"
      key="address"
    />
    <a-table-column
      title="Tags"
      dataIndex="tags"
      key="tags"
    >
      <template slot-scope="tags">
        <span>
          <a-tag v-for="tag in tags" color="blue" :key="tag">{{tag}}</a-tag>
        </span>
      </template>
    </a-table-column>
    <a-table-column
      title="Action"
      key="action"
    >
      <template slot-scope="text, record">
        <span>
          <a href="javascript:;">Action 一 {{record.firstName}}</a>
          <a-divider type="vertical"/>
          <a href="javascript:;">Delete</a>
        </span>
      </template>
    </a-table-column>
  </a-table>
</template>

<script>
  export default {
    created(){
      this.getTableList();
    },
    data() {
      return {
        data: [{
          key: '1',
          firstName: 'John',
          lastName: 'Brown',
          age: 32,
          address: 'New York No. 1 Lake Park',
          tags: ['nice', 'developer'],
        }, {
          key: '2',
          firstName: 'Jim',
          lastName: 'Green',
          age: 42,
          address: 'London No. 1 Lake Park',
          tags: ['loser'],
        }, {
          key: '3',
          firstName: 'Joe',
          lastName: 'Black',
          age: 32,
          address: 'Sidney No. 1 Lake Park',
          tags: ['cool', 'teacher'],
        }, {
          key: '4',
          firstName: 'Joe',
          lastName: 'Black',
          age: 32,
          address: 'Sidney No. 1 Lake Park',
          tags: ['cool', 'teacher'],
        }, {
          key: '5',
          firstName: 'Joe',
          lastName: 'Black',
          age: 32,
          address: 'Sidney No. 1 Lake Park',
          tags: ['cool', 'teacher'],
        }, {
          key: '6',
          firstName: 'Joe',
          lastName: 'Black',
          age: 32,
          address: 'Sidney No. 1 Lake Park',
          tags: ['cool', 'teacher'],
        }, {
          key: '7',
          firstName: 'Joe',
          lastName: 'Black',
          age: 32,
          address: 'Sidney No. 1 Lake Park',
          tags: ['cool', 'teacher'],
        }],
        pagination: {
          total: 0,
          pageSize: 5,//每页中显示10条数据
          showSizeChanger: true,
          pageSizeOptions: ["5", "10", "20", "50"],//每页中显示的数据
          showTotal: total => `共有 ${total} 条数据`,  //分页中显示总的数据
        },
        loading: true,
        // 查询参数
        queryParam: {
          page: 1,//第几页
          size: 10,//每页中显示数据的条数
          hosName: "",
          hosCode: "",
          province: "",
          city: ""
        },
      }
    },
    methods: {
      handleTableChange(pagination) {
        this.pagination.current = pagination.current;
        this.pagination.pageSize = pagination.pageSize;
        this.queryParam.page = pagination.current;
        this.queryParam.size = pagination.pageSize;
        this.getTableList();
      },
      //调用查询接口为dataSource 赋值
      getTableList() {
        this.loading = true;
        const pagination = {...this.pagination};
        pagination.total = this.data.length;
        this.tableDatas = this.data;
        this.pagination = pagination;
        this.loading = false;
      },
    }
  }
</script>
