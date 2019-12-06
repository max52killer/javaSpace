<template>
  <div id="login-body">
    <div id="bg">
      <canvas></canvas>
      <canvas></canvas>
      <canvas></canvas>
    </div>
    <h1>{{title}}</h1>
    <div class="sub-main-w3">
      <div class="login-form">
        <h2>登陆
          <i class="fa fa-long-arrow-down"></i>
        </h2>
        <div class="form-style-agile">
          <label>
            <i class="fa fa-user"></i>
            用户名：
          </label>
          <input style="border-radius: 5px" placeholder="用户名，没有会自动注册哦" v-model="loginForm.username" type="text" required="" @keyup.enter="login">
        </div>
        <div class="form-style-agile">
          <label>
            <i class="fa fa-unlock-alt"></i>
            密码：
          </label>
          <input style="border-radius: 5px" placeholder="密码" v-model="loginForm.password" type="password" required="" @keyup.enter="login">
        </div>
        <input type="submit" value="登陆" @click="login"/>
      </div>
    </div>
    <div class="footer">
      <p>{{copyright}}</p>
    </div>
  </div>
</template>
<script>
  import userLoginApi from '../../api/disdp/userLoginApi.js';

  export default {
    name: "Login",
    props: {},
    //设置当前全局使用的变量
    data() {
      return {
        title: window.systemParams.title,
        copyright:window.systemParams.copyright,
        loginForm: {
          username: '',
          password: ''
        }
      };
    },
    //引用其它组件注册
    components: {},
    //过滤器，用于对展示的数据进行处理  value|method
    filters: {},
    created() {
      let token = sessionStorage.getItem('auth_token');
      if (token != null && token != "") {
        this.$router.push("/");
      }else{
        userLoginApi.ip().then(response => {
          if (response.code == "0") {
            sessionStorage.setItem("auth_token",response.token);
            this.$router.push("/");
          }
        });
      }
      // window.addEventListener('keydown', this.handleKeyDown, true)//开启监听键盘按下事件
    },
    mounted() {
      this.showCavas();
      $("#login-body").parent().parent("body").css({"overflow-y": " hidden"})
    },
    destroyed: function () {
      $("#login-body").parent().parent("body").removeAttr("style");
    },
    methods: {
      login() {
        let username = this.loginForm.username;
        let password = this.loginForm.password;
        if($t.isEmpty(username)){
          this.$message({message:"用户名不能为空！",type:"warning"});
          return;
        }

        if($t.isEmpty(password)){
          this.$message({message:"密码不能为空！",type:"warning"});
          return;
        }

        if(!$t.validate.isUsername(username)){
          this.$message({message:"用户名为4到16位（字母，数字，下划线，减号）的符号！",type:"warning"});
          return;
        }

        if(password.length<6){
          this.$message({message:"密码长度不得小于6位！",type:"warning"});
          return;
        }

        userLoginApi.account(username, password).then(response => {
          if (response.code === "0") {//用户名和密码都正确直接登陆,跳转到主页
            sessionStorage.setItem("auth_token",response.token);
            this.$router.push("/");
          }else if (response.code === "1") {
            this.$notify.warning({
              title: 'error',
              message: '用户不存在',
              duration: 6000
            });
            this.$confirm('用户不存在，是否自动创建?', '提示', {
              confirmButtonText: '确定',
              cancelButtonText: '取消',
              type: 'warning'
            }).then(() => {
              let registerUser = {name: username, password: password};
              //点击确认请求创建用户  完成后自动登陆并跳转到用户完善信息界面（用户信息界面）
              userLoginApi.create(registerUser).then(user => {
                userLoginApi.account(username, password).then(res => {
                  if (res && res.code == "0") {
                    sessionStorage.setItem("auth_token",res.token);
                    this.$router.push("/");
                  }
                });
              });
            }).catch(()=>{});
          }else if(response.code==="2"){
            this.$message({message:"用户名密码错误，请重新输入！",type:"warning"});
          }else{
            sessionStorage.clear();
            this.$router.push("/");
            console.log("session会话过期！！！需要刷新token");
          }
        });
      },
      handleKeyDown(e){
        let path = this.$route.path;
        if(path=="/login"){
          let key = null;
          if (window.event === undefined) {
            key = e.keyCode;
          } else {
            key = window.event.keyCode;
          }
          if (key === 13) {
            this.login();
          }
        }
      },
      showCavas() {
        var canvas = $('#login-body #bg').children('canvas'),
          background = canvas[0],
          foreground1 = canvas[1],
          foreground2 = canvas[2],
          config = {
            circle: {
              amount: 18,
              layer: 3,
              color: [157, 97, 207],
              alpha: 0.3
            },
            line: {
              amount: 12,
              layer: 3,
              color: [255, 255, 255],
              alpha: 0.3
            },
            speed: 0.5,
            angle: 20
          };

        if (background.getContext) {
          var bctx = background.getContext('2d'),
            fctx1 = foreground1.getContext('2d'),
            fctx2 = foreground2.getContext('2d'),
            M = window.Math, // Cached Math
            degree = config.angle / 360 * M.PI * 2,
            circles = [],
            lines = [],
            wWidth, wHeight, timer;

          requestAnimationFrame = window.requestAnimationFrame ||
            window.mozRequestAnimationFrame ||
            window.webkitRequestAnimationFrame ||
            window.msRequestAnimationFrame ||
            window.oRequestAnimationFrame ||
            function (callback, element) {
              setTimeout(callback, 1000 / 60);
            };

          cancelAnimationFrame = window.cancelAnimationFrame ||
            window.mozCancelAnimationFrame ||
            window.webkitCancelAnimationFrame ||
            window.msCancelAnimationFrame ||
            window.oCancelAnimationFrame ||
            clearTimeout;

          var setCanvasHeight = function () {
            wWidth = $(window).width();
            wHeight = $(window).height(),

              canvas.each(function () {
                this.width = wWidth;
                this.height = wHeight;
              });
          };

          var drawCircle = function (x, y, radius, color, alpha) {
            var gradient = fctx1.createRadialGradient(x, y, radius, x, y, 0);
            gradient.addColorStop(0, 'rgba(' + color[0] + ',' + color[1] + ',' + color[2] + ',' + alpha + ')');
            gradient.addColorStop(1, 'rgba(' + color[0] + ',' + color[1] + ',' + color[2] + ',' + (alpha - 0.1) + ')');

            fctx1.beginPath();
            fctx1.arc(x, y, radius, 0, M.PI * 2, true);
            fctx1.fillStyle = gradient;
            fctx1.fill();
          };

          var drawLine = function (x, y, width, color, alpha) {
            var endX = x + M.sin(degree) * width,
              endY = y - M.cos(degree) * width,
              gradient = fctx2.createLinearGradient(x, y, endX, endY);
            gradient.addColorStop(0, 'rgba(' + color[0] + ',' + color[1] + ',' + color[2] + ',' + alpha + ')');
            gradient.addColorStop(1, 'rgba(' + color[0] + ',' + color[1] + ',' + color[2] + ',' + (alpha - 0.1) + ')');

            fctx2.beginPath();
            fctx2.moveTo(x, y);
            fctx2.lineTo(endX, endY);
            fctx2.lineWidth = 3;
            fctx2.lineCap = 'round';
            fctx2.strokeStyle = gradient;
            fctx2.stroke();
          };

          var drawBack = function () {
            bctx.clearRect(0, 0, wWidth, wHeight);

            var gradient = [];

            gradient[0] = bctx.createRadialGradient(wWidth * 0.3, wHeight * 0.1, 0, wWidth * 0.3, wHeight * 0.1, wWidth * 0.9);
            gradient[0].addColorStop(0, 'rgb(0, 26, 77)');
            gradient[0].addColorStop(1, 'transparent');

            bctx.translate(wWidth, 0);
            bctx.scale(-1, 1);
            bctx.beginPath();
            bctx.fillStyle = gradient[0];
            bctx.fillRect(0, 0, wWidth, wHeight);

            gradient[1] = bctx.createRadialGradient(wWidth * 0.1, wHeight * 0.1, 0, wWidth * 0.3, wHeight * 0.1, wWidth);
            gradient[1].addColorStop(0, 'rgb(0, 150, 240)');
            gradient[1].addColorStop(0.8, 'transparent');

            bctx.translate(wWidth, 0);
            bctx.scale(-1, 1);
            bctx.beginPath();
            bctx.fillStyle = gradient[1];
            bctx.fillRect(0, 0, wWidth, wHeight);

            gradient[2] = bctx.createRadialGradient(wWidth * 0.1, wHeight * 0.5, 0, wWidth * 0.1, wHeight * 0.5, wWidth * 0.5);
            gradient[2].addColorStop(0, 'rgb(40, 20, 105)');
            gradient[2].addColorStop(1, 'transparent');

            bctx.beginPath();
            bctx.fillStyle = gradient[2];
            bctx.fillRect(0, 0, wWidth, wHeight);
          };

          var animate = function () {
            var sin = M.sin(degree),
              cos = M.cos(degree);

            if (config.circle.amount > 0 && config.circle.layer > 0) {
              fctx1.clearRect(0, 0, wWidth, wHeight);
              for (var i = 0, len = circles.length; i < len; i++) {
                var item = circles[i],
                  x = item.x,
                  y = item.y,
                  radius = item.radius,
                  speed = item.speed;

                if (x > wWidth + radius) {
                  x = -radius;
                } else if (x < -radius) {
                  x = wWidth + radius
                } else {
                  x += sin * speed;
                }

                if (y > wHeight + radius) {
                  y = -radius;
                } else if (y < -radius) {
                  y = wHeight + radius;
                } else {
                  y -= cos * speed;
                }

                item.x = x;
                item.y = y;
                drawCircle(x, y, radius, item.color, item.alpha);
              }
            }

            if (config.line.amount > 0 && config.line.layer > 0) {
              fctx2.clearRect(0, 0, wWidth, wHeight);
              for (var j = 0, len = lines.length; j < len; j++) {
                var item = lines[j],
                  x = item.x,
                  y = item.y,
                  width = item.width,
                  speed = item.speed;

                if (x > wWidth + width * sin) {
                  x = -width * sin;
                } else if (x < -width * sin) {
                  x = wWidth + width * sin;
                } else {
                  x += sin * speed;
                }

                if (y > wHeight + width * cos) {
                  y = -width * cos;
                } else if (y < -width * cos) {
                  y = wHeight + width * cos;
                } else {
                  y -= cos * speed;
                }

                item.x = x;
                item.y = y;
                drawLine(x, y, width, item.color, item.alpha);
              }
            }

            timer = requestAnimationFrame(animate);
          };

          var createItem = function () {
            circles = [];
            lines = [];

            if (config.circle.amount > 0 && config.circle.layer > 0) {
              for (var i = 0; i < config.circle.amount / config.circle.layer; i++) {
                for (var j = 0; j < config.circle.layer; j++) {
                  circles.push({
                    x: M.random() * wWidth,
                    y: M.random() * wHeight,
                    radius: M.random() * (20 + j * 5) + (20 + j * 5),
                    color: config.circle.color,
                    alpha: M.random() * 0.2 + (config.circle.alpha - j * 0.1),
                    speed: config.speed * (1 + j * 0.5)
                  });
                }
              }
            }

            if (config.line.amount > 0 && config.line.layer > 0) {
              for (var m = 0; m < config.line.amount / config.line.layer; m++) {
                for (var n = 0; n < config.line.layer; n++) {
                  lines.push({
                    x: M.random() * wWidth,
                    y: M.random() * wHeight,
                    width: M.random() * (20 + n * 5) + (20 + n * 5),
                    color: config.line.color,
                    alpha: M.random() * 0.2 + (config.line.alpha - n * 0.1),
                    speed: config.speed * (1 + n * 0.5)
                  });
                }
              }
            }

            cancelAnimationFrame(timer);
            timer = requestAnimationFrame(animate);
            drawBack();
          };

          $(document).ready(function () {
            setCanvasHeight();
            createItem();
          });
          $(window).resize(function () {
            setCanvasHeight();
            createItem();
          });
        }
      }
    }
  }
</script>

<style scoped>
  ::-webkit-input-placeholder {
    opacity: 0.5;
  }

</style>
