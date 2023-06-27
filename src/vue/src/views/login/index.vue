<template>

  <el-container>
    <img id="bg" alt="bg" src="../../assets/img/bg/login_bg.jpg">
    <el-main>
      <!--<img id="logo" alt="logo" src="../../assets/img/logo高清.png" @click="toIndex">-->
      <img id="logo" alt="logo" src="../../assets/img/logo/logo高清.png">
      <el-button id="index_btn" disabled type="primary" @click="toIndex">返回首页</el-button>
      <el-button id="register_btn" type="primary" @click="toRegister">注册</el-button>

      <el-card class="box-card login-card">
        <span class="login-title">登录</span>
        <el-form
            ref="user"
            :model="user"
            :rules="rules"
            label-width="auto">
          <el-form-item class="input" prop="username">
            <el-input
                v-model="user.username"
                placeholder="用户名">
            </el-input>
          </el-form-item>

          <el-form-item class="input" prop="password">
            <el-input
                v-model="user.password"
                placeholder="请输入登录密码"
                show-password
                type="password">
            </el-input>
          </el-form-item>
          <el-form-item class="item">
            <div id="choice">
              <el-checkbox id="auto" v-model="auto">
                下次自动登录
              </el-checkbox>
              <a id="update" @click="toUpdate">修改密码</a>
            </div>
          </el-form-item>
          <el-form-item class="item">
            <el-button id="login" type="primary" @click="login">登录</el-button>
          </el-form-item>
          <el-form-item class="item">
            <a id="register" @click="toRegister">立即注册</a>
          </el-form-item>
        </el-form>
      </el-card>
    </el-main>
  </el-container>
</template>

<script>

import request from "@/api";
import router from "@/router";
import CryptoJS from 'crypto-js'

export default {
  name: "login",
  inject: ['reload'],
  created() {
    if (localStorage.getItem("username") !== null && localStorage.getItem("password") !== null) {
      this.user.username = localStorage.getItem("username");
      this.user.password = localStorage.getItem("password");
      if (localStorage.getItem("auto") !== null) {
        this.auto = true;
      }
    }
  },
  mounted() {
    if (localStorage.getItem("auto") !== null) {
      this.login();
    }
  },
  data() {
    return {
      name: '',
      user: {
        username: "",
        password: ""
      },
      auto: false,
      rules: {
        username: [
          {required: true, message: '用户名不能为空', trigger: 'blur'},
          {min: 2, max: 16, message: '长度在 2 到 16 个字符', trigger: 'blur'}
        ],
        password: [
          {required: true, message: '密码不能为空', trigger: 'blur'},
          {min: 6, max: 32, message: '长度在 6 到 32 个字符', trigger: 'blur'}
        ],
      }
    };
  },
  methods: {
    login() {
      //进行表单验证
      this.$refs.user.validate(valid => {
        //表单验证成功
        if (valid) {
          request.post('/login', JSON.stringify({
            username: this.user.username,
            password: CryptoJS.MD5(this.user.password).toString(),
          })).then(res => {
            if (res.data !== null && 'username' in res.data && res.data.username !== null) {
              this.$message({
                message: '登录成功！',
                type: 'success',
                offset: 50,
                duration: 500
              });
              //自动登录
              if (this.auto) {
                localStorage.setItem("username", this.user.username);
                localStorage.setItem("password", this.user.password);
                localStorage.setItem("auto", "true");
              } else {
                localStorage.removeItem("auto");
                localStorage.removeItem("username");
                localStorage.removeItem("password");
              }

              sessionStorage.setItem("token", res.data.id);

              router.push({
                path: '/project',
                query: {
                  id: res.data.id
                }
              })
            } else {
              this.$message({
                message: '用户名或密码不正确，请重新输入',
                type: 'error'
              });
              localStorage.removeItem("auto");
              localStorage.removeItem("username");
              localStorage.removeItem("password");
              this.reload();
            }
          }).catch(err => {
            console.log(err)
          })
        } else {
          this.$message({
            message: '输入格式不正确，请重新输入',
            type: 'error'
          });
          this.reload();
        }
      });
    },
    toRegister() {
      console.log("register");
      router.push({
        path: '/register',
      })
    },
    toUpdate() {
      console.log("updatePwd");
      router.push({
        path: '/updatePwd',
      })
    },
    toIndex() {
      console.log("toIndex");
      router.push({
        path: '/index',
      })
    }
  },
};

</script>

<style scoped>

el-main {
  position: center;
}

#bg {
  position: absolute;
  height: 100%;
  width: 100%;
  z-index: 5;
}


#logo {
  height: 90px;
  position: absolute;
  top: 3%;
  left: 2%;
  z-index: 10;
}

#logo:hover {
  cursor: pointer;
}

.login-card {
  position: absolute;
  top: 50%;
  left: 50%;
  transform: translateY(-50%) translateX(-50%);
  width: 450px;
  height: 400px;
  border-radius: 15px;
  text-align: center;
  z-index: 20;
  padding: 10px;
}

.login-title {
  font-size: 24px;
  font-weight: bold;
  color: #333333;
  display: block;
  text-align: left;
  margin-bottom: 30px;
  margin-left: 22px;
}

.el-button {
  font-size: 16px;
  background-color: #F39C12;
  border: #F39C12;
  height: 40px;
  display: block;
  text-align: center;
  border-radius: 5px;
  flex-basis: 90%;
}

#register_btn {
  float: right;
  margin-right: 20px;
  margin-top: 20px;
  position: relative;
  z-index: 15;
  color: #FFFFFF;
  background-color: transparent;
  border: 1px solid #FFFFFF;
  border-radius: 50px;
}

#index_btn {
  float: right;
  margin-right: 40px;
  margin-top: 20px;
  position: relative;
  right: 0;
  z-index: 15;
  color: #FFFFFF;
  background-color: transparent;
  border: 1px solid #FFFFFF;
  border-radius: 50px;
}

#register_btn:hover {
  border: 2px solid #FFFFFF;
}

#index_btn:hover {
  border: 2px solid #FFFFFF;
}

.input {
  margin-bottom: 20px;
}

.item {
  margin-bottom: 30px;
  text-align: center;
}

#choice {
  margin-left: 20px;
  margin-right: 20px;
}

.el-checkbox {
  color: #C0C0C0;
}

#update {
  margin-left: 210px;
  font-size: 12px;
}

#update:hover {
  cursor: pointer;
  color: #F39C12;
}

#login {
  margin: auto;
}

#login:hover {
  background-color: #FF8C00;
  border: #FF8C00;
}

#register {
  margin: auto;
  font-size: 16px;
  color: #409EFF;
}

#register:hover {
  cursor: pointer;
  color: #207EFF;
}

:deep(input) {
  width: 350px;
  height: 40px;
  border: none;
}

a {
  text-decoration: underline;
}

</style>
