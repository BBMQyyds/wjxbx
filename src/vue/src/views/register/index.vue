<template>

  <el-container>
    <img id="bg" alt="bg" src="../../assets/img/bg/login_bg.jpg">
    <el-main>
      <!--<img id="logo" alt="logo" src="../../assets/img/logo高清.png" @click="toIndex">-->
      <img id="logo" alt="logo" src="../../assets/img/logo/logo高清.png">
      <el-button id="index_btn" disabled type="primary" @click="toIndex">返回首页</el-button>
      <el-button id="login_btn" type="primary" @click="toLogin">登录</el-button>

      <el-card class="box-card register-card">
        <span class="register-title">注册</span>
        <el-form
            ref="user"
            :model="user"
            :rules="rules"
            label-width="auto">
          <el-form-item class="input" prop="username">
            <el-input
                v-model="user.username"
                placeholder="请输入用户名">
            </el-input>
          </el-form-item>

          <el-form-item class="input" prop="password">
            <el-input
                v-model="user.password"
                placeholder="设置密码（6-32位字母、数字或特殊符号）"
                show-password
                type="password">
            </el-input>
          </el-form-item>

          <el-form-item class="input" prop="confirm">
            <el-input
                v-model="user.confirm"
                placeholder="确认密码"
                show-password
                type="password">
            </el-input>
          </el-form-item>
          <el-form-item class="item">
            <el-button id="create" type="primary" @click="register">创建用户</el-button>
          </el-form-item>
          <el-form-item class="item">
            <a id="register">
              <span id="a1">已有账号？</span>
              <span id="a2" @click="toLogin">立即登录</span>
            </a>
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
  name: "register",
  inject: ['reload'],
  data() {
    return {
      name: '',
      user: {
        username: "",
        password: "",
        confirm: "",
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
        confirm: [
          {required: true, message: '密码不能为空', trigger: 'blur'},
          {min: 6, max: 32, message: '长度在 6 到 32 个字符', trigger: 'blur'}
        ],
      }
    };
  },
  methods: {
    register() {
      //进行表单验证
      this.$refs.user.validate(valid => {
        //表单验证成功
        if (valid) {
          if (this.user.password !== this.user.confirm) {
            this.$message({
              message: '两次输入的密码不一致，请重新输入',
              type: 'error'
            });
            this.reload();
            return;
          }
          request.post('/insertUser', JSON.stringify({
            username: this.user.username,
            password: CryptoJS.MD5(this.user.password).toString(),
            createdBy: this.user.username,
            lastUpdatedBy: this.user.username,
          })).then(res => {
            if (res.data === 1) {
              this.$message({
                message: '创建成功！',
                type: 'success',
                offset: 50,
                duration: 500
              });
              router.push({
                path: '/login',
              })
            } else {
              this.$message({
                message: '该用户名已存在，请重新输入',
                type: 'error'
              });
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
    toLogin() {
      console.log("toLogin");
      router.push({
        path: '/login',
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

.register-card {
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

.register-title {
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


#login_btn {
  float: right;
  margin-right: 20px;
  margin-top: 20px;
  position: relative;
  z-index: 15;
  background-color: transparent;
  border: 1px solid #ffffff;
  border-radius: 50px;
}

#index_btn {
  float: right;
  margin-right: 40px;
  margin-top: 20px;
  position: relative;
  z-index: 15;
  background-color: transparent;
  border: 1px solid #ffffff;
  border-radius: 50px;
}

#login_btn:hover {
  border: 2px solid #ffffff;
}

#index_btn:hover {
  border: 2px solid #ffffff;
}

.input {
  margin-bottom: 20px;
}

.item {
  margin-bottom: 30px;
  text-align: center;
}

#create {
  margin: auto;
}

#create:hover {
  background-color: #FF8C00;
  border: #FF8C00;
}

#register {
  margin: auto;
  font-size: 16px;
  color: #409EFF;
}

#register:hover {
  color: #519EFF;
}

:deep(input) {
  width: 350px;
  height: 40px;
  border: none;
}

#a1 {
  color: #000000;
}

#a2 {
  text-decoration: underline;
}

#a2:hover {
  cursor: pointer;
  color: #207EFF;
}

</style>
