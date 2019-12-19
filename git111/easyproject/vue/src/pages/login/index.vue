<template>
  <div class="login-body">
    <!--  particles  -->
    <div id="particles-js"></div>
    <!-- //particles -->
    <div class="w3ls-pos">
      <h1>{{$sysUtils.sysParam.title}}</h1>
      <div class="w3ls-login box">
        <div class="login-form">
          <div class="agile-field-txt">
            <label>
              <i class="el-icon-user-solid"></i>
              用户名：
            </label>
            <input type="text" v-model="username" placeholder="用户名，没有会自动注册哦" required="" @keyup.enter="handleLogin" autocomplete="off"/>
          </div>
          <div class="agile-field-txt">
            <label>
              <i class="el-icon-unlock"></i>
              密码：
            </label>
            <input type="password" v-model="password" placeholder="密码" required="" @keyup.enter="handleLogin" autocomplete="off"/>
          </div>
          <div class="w3ls-bot">
            <input type="submit" @click="handleLogin" value="登陆"/>
          </div>
        </div>
      </div>
      <div class="copy-wthree">
        <p>{{$sysUtils.sysParam.copyright}}
        </p>
      </div>
      <!--//copyright-->
    </div>
  </div>
</template>

<script>
  import userApi from "../../api/userApi";
  export default {
    name: "login",
    props: {},
    created() {
    },
    //设置当前全局使用的变量
    data() {
      return {
        username:"",
        password:""
      };
    },
    computed: {},
    watch: {},
    //引用其它组件注册
    components: {},

    mounted() {
      this.showCanvas();
      $(".login-body").parent().parent().css({overflow:"hidden",margin:0});
    },
    //定义组件中调用的函数
    methods: {
      handleLogin(){
        if(this.username.trim()===''){
          this.$message.warning("用户名不能为空！");
          return false;
        }
        if(this.password.trim()===''){
          this.$message.warning("密码不能为空！");
          return false;
        }
        let loginForm={username:this.username,password:this.password};
        userApi.login(loginForm).then(resp=>{
          if(resp.data.code==='200'){
            //用户输入之后将其写入session
            sessionStorage.setItem("auth_token","username="+this.username+"&&password="+this.password);
            this.$router.push('/');
            this.$message.success("登陆成功！");
          }else if(resp.data.code==='500'){
            this.$confirm('用户不存在，是否自动创建?', '提示', {
              confirmButtonText: '确定',
              cancelButtonText: '取消',
              type: 'warning'
            }).then(()=>{
              userApi.save(loginForm).then(resp1=>{
                if(resp1.data){
                  sessionStorage.setItem("auth_token","username="+this.username+"&&password="+this.password);
                  this.$router.push('/');
                  this.$message.success("自动创建，登陆成功！");
                }
              });
            });
          }else{
            this.$message.success("查询异常！");
          }
        });
      },
      showCanvas(){
        /* ---- particles.js config ---- */
        particlesJS("particles-js", {
          "particles": {
            "number": {
              "value": 100,
              "density": {
                "enable": true,
                "value_area":1000
              }
            },
            "color": {
              "value": ["#aa73ff", "#f8c210", "#83d238", "#33b1f8"]
            },

            "shape": {
              "type": "circle",
              "stroke": {
                "width": 0,
                "color": "#fff"
              },
              "polygon": {
                "nb_sides": 5
              },
              "image": {
                "src": "img/github.svg",
                "width": 100,
                "height": 100
              }
            },
            "opacity": {
              "value": 0.6,
              "random": false,
              "anim": {
                "enable": false,
                "speed": 1,
                "opacity_min": 0.1,
                "sync": false
              }
            },
            "size": {
              "value": 2,
              "random": true,
              "anim": {
                "enable": false,
                "speed": 40,
                "size_min": 0.1,
                "sync": false
              }
            },
            "line_linked": {
              "enable": true,
              "distance": 120,
              "color": "#ffffff",
              "opacity": 0.4,
              "width": 1
            },
          },
          "interactivity": {
            "detect_on": "canvas",
            "events": {
              "onhover": {
                "enable": true,
                "mode": "grab"
              },
              "onclick": {
                "enable": false
              },
              "resize": true
            },
            "modes": {
              "grab": {
                "distance": 140,
                "line_linked": {
                  "opacity": 1
                }
              },
              "bubble": {
                "distance": 400,
                "size": 40,
                "duration": 2,
                "opacity": 8,
                "speed": 3
              },
              "repulse": {
                "distance": 200,
                "duration": 0.4
              },
              "push": {
                "particles_nb": 4
              },
              "remove": {
                "particles_nb": 2
              }
            }
          },
          "retina_detect": true
        });
      }
    },
    destroyed() {
      $(".login-body").parent().parent().removeAttr("style");
    }
  }
</script>

<style scoped>
  /*.login-body{*/
    /*width: 100%;*/
    /*height: 100%;*/
    /*text-align: center;*/
  /*}*/
  /*.box{*/
    /*display: inline-block;*/
  /*}*/
  ::-webkit-input-placeholder { /* WebKit browsers */
    color:    #999;
    opacity:0.5;
  }
  :-moz-placeholder { /* Mozilla Firefox 4 to 18 */
    color:    #999;
  }
  ::-moz-placeholder { /* Mozilla Firefox 19+ */
    color:    #999;
  }
  :-ms-input-placeholder { /* Internet Explorer 10+ */
    color:    #999;
  }
</style>
