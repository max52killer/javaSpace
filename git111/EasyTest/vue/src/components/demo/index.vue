<template>
  <div class="hello">
    <button @click="exportJSON">导出jsonn</button>
  </div>
</template>

<script>
  import FileSaver from 'file-saver';
  export default {
    data () {
      return {
        // 待导出的json数据
        CfgInfo: {
          CAN: {
            Chn: 0,
            name: 'CAN通道'
          },
          LIN: {
            Chn: 1,
            name: 'LIN通道'
          }
        }
      }
    },
    methods: {
      exportJSON () {
        // 将json转换成字符串
        const data = JSON.stringify(this.CfgInfo);
        // const blob = new Blob([data], {type: ''});
        var file = new File([data], "C://aaa.json", {type: "text/plain;charset=utf-8"});
        FileSaver.saveAs(file);
      }
    }
  }
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
</style>
