<template>

  <el-container>
    <img id="bg" alt="bg" src="../../assets/img/login_bg.jpg">
    <el-main>
      <img id="logo" alt="logo" src="../../assets/img/logo高清.png">

      <el-button id="register_btn" type="primary" @click="register">注册</el-button>
      <el-button id="index_btn" type="primary" @click="toIndex">返回首页</el-button>

      <el-card class="box-card login-card">
        <span class="login-title">在线申请和审批系统</span>
        <span class="login-tip">20217033 梁朝阳</span>
        <span class="login-tip2">初始密码：123456</span>
        <el-form
            ref="user"
            :model="user"
            :rules="rules"
            label-width="auto">
          <el-form-item class="input" prop="username">
            <el-input
                v-model="user.account"
                placeholder="请输入账号">
            </el-input>
          </el-form-item>

          <el-form-item class="item" label="密码" prop="pwd">
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
              <a id="forget" @click="forget">忘记用户名/密码？</a>
            </div>
          </el-form-item>
          <el-form-item class="item">
            <el-button id="login" type="primary" @click="login">登录</el-button>
          </el-form-item>
          <el-form-item class="item">
            <a id="register" @click="register">立即注册</a>
          </el-form-item>
        </el-form>
      </el-card>
    </el-main>
  </el-container>
</template>

<script>

import request from "@/api";

export default {
  name: "login",
  inject: ['reload'],
  data() {
    return {
      name: '',
      user: {
        account: "",
        pwd: ""
      },
      auto: false,
      rules: {
        username: [
          {required: true, message: '昵称不能为空', trigger: 'blur'},
          {min: 2, max: 16, message: '长度在 2 到 23 个字符', trigger: 'blur'}
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
            password: this.user.password,
          })).then(res => {
            if ('username' in res.data) {
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
              }

              sessionStorage.setItem("username", this.user.username);
              sessionStorage.setItem("password", this.user.password);
            } else {
              this.$message({
                message: '用户名或密码不正确，请重新输入',
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
    register() {
      console.log("register");
    },
    forget() {
      console.log("forget");
    },
    toIndex() {
      console.log("toIndex");
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
  position: absolute;
  top: 4%;
  right: 12%;
  z-index: 15;
  background-color: transparent;
  border: 1px solid #ffffff;
  border-radius: 50px;
}

#index_btn {
  position: absolute;
  top: 4%;
  right: 4%;
  z-index: 15;
  background-color: transparent;
  border: 1px solid #ffffff;
  border-radius: 50px;
}

#register_btn:hover {
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

#choice {
  margin-left: 20px;
  margin-right: 20px;
}

.el-checkbox {
  color: #C0C0C0;
}

#forget {
  margin-left: 160px;
  font-size: 12px;
  color: #000000;
}

#forget:hover {
  color: #F39C12;
}

#login {
  margin: auto;
}

#register {
  margin: auto;
  font-size: 16px;
  color: #409EFF;
}

#register:hover {
  color: #308DEE;
}

:deep(input) {
  width: 350px;
  height: 40px;
  border: none;
}

a {
  text-decoration: underline;
}

a:hover {
  cursor: pointer;
}

</style>
