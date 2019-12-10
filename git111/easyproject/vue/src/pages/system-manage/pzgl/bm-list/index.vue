<template>
  <div id="bm-list">
    <el-card class="box-card" align="center">
      <div slot="header" class="clearfix">
        <i class="el-icon-info"></i>
        <span>您正在处理【{{typeName}}({{type}})】的数据，请避免编号重复</span>
      </div>
      <el-row>
        <el-table
          :data="codeList"
          border stripe size="small"
          style="width: 100%">
          <el-table-column
            label="编号">
            <template slot-scope="scope">
              <el-input v-model="scope.row.code" @blur="handleCodeBlur(scope.row.code,scope.$index)"
                        placeholder="请输入代码值" size="mini"></el-input>
            </template>
          </el-table-column>
          <el-table-column
            label="名称"
            width="450">
            <template slot-scope="scope">
              <el-input v-model="scope.row.codeName" placeholder="请输入代码名称" size="mini"></el-input>
            </template>
          </el-table-column>
          <el-table-column
            label="排序编号">
            <template slot-scope="scope">
              <el-input v-model="scope.row.no" placeholder="请输入排序编号" size="mini"></el-input>
            </template>
          </el-table-column>
          <el-table-column label="操作" align="center" width="150px">
            <template slot-scope="scope">
              <el-button type="danger" icon="el-icon-delete" title="点击删除该条数据"
                         @click="handleDel(scope.$index)" size="mini" circle></el-button>
            </template>
          </el-table-column>
        </el-table>
      </el-row>
      <el-row>
        <el-button type="primary" @click="handleUpdate" size="mini">更新数据</el-button>
      </el-row>
    </el-card>
  </div>
</template>

<script>
  import bmCodeApi from "../../../../api/bmCodeApi";

  export default {
    name: "index",
    props: {},
    computed: {},
    //引用其它组件注册
    components: {},
    created() {
    },
    mounted() {
    },
    //设置当前全局使用的变量
    data() {
      return {
        codeList: [{}],
        type: "",
        typeName: ""
      };
    },
    //定义组件中调用的函数
    methods: {
      load(type, typeName) {
        this.type = type;
        this.typeName = typeName;
        console.log("typeNamessssss:",this.typeName);
        this.reloadList();
      },
      reloadList() {
        bmCodeApi.getByType(this.type).then(resp => {
          console.log("根据表码取到的值：", resp);
          this.codeList = resp.data;
        });
      },
      handleDel(index) {
        console.log(index);
        if (index < 1) {
          this.$message.warning("必须有一条数据");
          return false;
        }
        this.removeCode(index);
      },
      removeCode(index) {
        this.codeList.splice(index, 1)
      },
      //后端更新时先将当前type的所有表码全部删除  然后再将最新提交的数据执行插入
      handleUpdate() {
        let req = {type: this.type, sysCodes: []};
        if (this.codeList.length > 0) {
          for (let i in this.codeList) {
            let syscode = this.codeList[i];
            if (syscode.code) {
              req.sysCodes.push({
                type: this.type,
                typeName: this.typeName,
                code: syscode.code,
                codeName: syscode.codeName,
                no: syscode.no
              })
            }
          }
          bmCodeApi.updateBm(req).then(resp => {
            if (resp) {
              this.$message.success("数据更新成功！");
              console.log("更新后的数据：", resp);
              this.reloadList();
            }
          });
        }
      },
      handleCodeBlur(code, index) {
        this.$nextTick(() => {
          if (!$t.isEmpty(code)) {
            var isDuplicate = false;
            var count = 0;
            for (var i = 0, len = this.codeList.length; i < len; i++) {
              if (code == this.codeList[i].code) {
                count++;
              }
              if (count > 1) {
                isDuplicate = true;
                break;
              }
            }
          }
          if (isDuplicate) {
            this.removeCode(index);
          }
        });
      },
    },
    watch: {
      codeList() {
        if (this.codeList.length == 0) {
          this.codeList.push({});
        } else {
          var isHaseEmptyRow = false;
          this.codeList.forEach(code => {
            if ($t.isEmpty(code.code) && $t.isEmpty(code.codeName)) {
              isHaseEmptyRow = true;
            }
          });
          if (!isHaseEmptyRow) {
            this.codeList.push({});
          }
        }
      }
    },
  }
</script>

<style scoped>
  .el-row {
    margin-bottom: 20px;
  }
</style>
