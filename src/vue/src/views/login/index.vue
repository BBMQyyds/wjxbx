<template>

  <el-container>
    <el-main>
      <el-card class="box-card login-card">
          <Icon name="riqi" class="icon1" />
        <span class="login-title">在线申请和审批系统</span>
        <span class="login-tip">20217033 梁朝阳</span>
        <span class="login-tip2">初始密码：123456</span>
        <el-form
            ref="user"
            :model="user"
            :rules="rules"
            label-width="auto">
          <el-form-item class="item" label="账号" prop="account">
            <el-input
                v-model="user.account"
                placeholder="请输入账号">
            </el-input>
          </el-form-item>

          <el-form-item class="item" label="密码" prop="pwd">
            <el-input
                v-model="user.pwd"
                placeholder="请输入密码"
                show-password
                type="password">
            </el-input>
          </el-form-item>
          <el-form-item id="radios" class="item">
            <el-checkbox v-model="memory">记住密码</el-checkbox>
            <el-checkbox v-model="auto">自动登录</el-checkbox>
            <el-button id="btn" size="small" type="text" @click="changePwd">修改密码</el-button>
          </el-form-item>
          <el-form-item class="item">
            <el-button id="left" type="primary" @click="login">登录</el-button>
            <el-button id="right" type="primary" @click="register">注册</el-button>
          </el-form-item>
        </el-form>
      </el-card>
    </el-main>

    <el-dialog title="修改密码" v-model="dialogVisible" width="auto"
               align-center destroy-on-close>
      <el-form ref="form" :model="form" :rules="f_rules" label-width="auto">
        <el-form-item label="账号" prop="account">
          <el-input v-model="form.account" placeholder="请输入账号"></el-input>
        </el-form-item>
        <el-form-item label="旧密码" prop="oldPassword">
          <el-input
              v-model="form.oldPassword"
              placeholder="请输入旧密码"
              show-password
              type="password"
          ></el-input>
        </el-form-item>
        <el-form-item label="新密码" prop="newPassword">
          <el-input
              v-model="form.newPassword"
              placeholder="请输入新密码"
              show-password
              type="password"
          ></el-input>
        </el-form-item>
      </el-form>

      <span slot="footer" class="dialog-footer">
        <el-button id="change" type="primary" @click="submitForm">提交</el-button>
      </span>
    </el-dialog>

  </el-container>
</template>

<script>

import request from "@/api";
import router from "@/router";
import CryptoJS from 'crypto-js'
import {eventBus} from "@ai-zen/event-bus";

export default {
  name: "login",
  inject: ['reload'],
  created() {
    request.post("/selectUserById", JSON.stringify("c92ec58b-f098-41f9-99f3-9d50a31270ce")).then(res => {
      console.log(res.data)
    })
  },
  data() {
    return {
      name: '',
      user: {
        account: "",
        pwd: ""
      },
      memory: false,
      auto: false,
      dialogVisible: false,
      form: {
        account: '',
        oldPassword: '',
        newPassword: ''
      },
      f_rules: {
        account: [
          {required: true, message: '昵称不能为空', trigger: 'blur'},
          {min: 2, max: 20, message: '长度在 2 到 23 个字符', trigger: 'blur'}
        ],
        oldPassword: [
          {required: true, message: '密码不能为空', trigger: 'blur'},
          {min: 6, max: 32, message: '长度在 6 到 32 个字符', trigger: 'blur'}
        ],
        newPassword: [
          {required: true, message: '密码不能为空', trigger: 'blur'},
          {min: 6, max: 32, message: '长度在 6 到 32 个字符', trigger: 'blur'}
        ],
      },
      rules: {
        account: [
          {required: true, message: '昵称不能为空', trigger: 'blur'},
          {min: 2, max: 20, message: '长度在 2 到 23 个字符', trigger: 'blur'}
        ],
        pwd: [
          {required: true, message: '密码不能为空', trigger: 'blur'},
          {min: 6, max: 32, message: '长度在 6 到 32 个字符', trigger: 'blur'}
        ],
      }
    };
  },
  methods: {
    changePwd() {
      this.dialogVisible = true;
    },
    login() {
      //进行表单验证
      this.$refs.user.validate(valid => {
        //表单验证成功
        if (valid) {
          request.post('/login', JSON.stringify({
            account: this.user.account,
            pwd: CryptoJS.MD5(this.user.pwd).toString(),
          })).then(res => {
            if ('account' in res.data) {
              this.$message({
                message: '登录成功！',
                type: 'success',
                offset: 50,
                duration: 500
              });

              //记住密码
              if (this.memory) {
                localStorage.setItem("account", this.user.account);
                localStorage.setItem("pwd", this.user.pwd);
              } else {
                localStorage.removeItem("account");
                localStorage.removeItem("pwd");
              }
              //自动登录
              if (this.auto) {
                localStorage.setItem("account", this.user.account);
                localStorage.setItem("pwd", this.user.pwd);
                localStorage.setItem("auto", "true");
              } else {
                localStorage.removeItem("auto");
              }

              sessionStorage.setItem("account", this.user.account);
              sessionStorage.setItem("pwd", this.user.pwd);

              sessionStorage.setItem("name", res.data.name);

              //获得用户名字
              request.post('/getUserName', JSON.stringify(this.user.account)).then(res2 => {
                if (res2.data !== '') {
                  sessionStorage.setItem("name", res2.data);
                } else {
                  this.$message({
                    type: 'error',
                    message: '获取用户名字失败'
                  });
                }
              }).catch(err => {
                console.log(err)
              })

              if (res.data.account.startsWith('s')) {
                router.push({
                  path: '/apply',
                  query: {
                    account: res.data.account,
                  },
                });
                eventBus.emit('updateSelected', '/apply');
              } else if (res.data.account.startsWith('t')) {
                router.push({
                  path: '/approve',
                  query: {
                    account: res.data.account,
                  },
                });
                eventBus.emit('updateSelected', '/approve');
              } else if (res.data.account.startsWith('a')) {
                router.push({
                  path: '/userManage',
                  query: {
                    account: res.data.account,
                  },
                });
                eventBus.emit('updateSelected', '/userManage');
              }
            } else {
              this.$message({
                message: '账号或密码不正确，请重新输入',
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
      router.push({name: 'register'})
    },
    submitForm() {
      this.$refs.form.validate(valid => {
        if (valid) {
          request.post('/changePwd', JSON.stringify({
            account: this.form.account,
            pwd: CryptoJS.MD5(this.form.oldPassword).toString(),
            new_pwd: CryptoJS.MD5(this.form.newPassword).toString(),
          })).then(async res => {
            if (res.data === 1) {
              this.$message({
                message: '修改成功！',
                type: 'success',
                offset: 50,
                duration: 500
              });
              // 关闭对话框
              this.dialogVisible = false;
              this.reload();
            } else {
              this.$message({
                message: '修改失败，请检查账号密码是否正确',
                type: 'error',
                offset: 50,
                duration: 500
              });
            }
          });
        } else {
          // 表单验证不通过，进行相应的提示
          this.$message.error('表单验证失败，请检查输入内容');
        }
      });
    },
  },
};

</script>

<style scoped>

el-main {
  position: center;
}

.login-card {
  background-color: #CCE3F5;
  background-image: linear-gradient(135deg, #CCE3F5 0%, #D6DAF0 100%);
  position: absolute;
  top: 0;
  left: 0;
  bottom: 0;
  right: 0;
  margin: auto;
  width: 490px;
  height: 450px;
  padding: 50px;
  border: 2px solid #4158D0;
  border-radius: 24px;
}

.login-title {
  font-size: 32px;
  color: #333333;
  line-height: 60px;
  letter-spacing: 1px;
  font-weight: 800;
  display: block;
  text-align: center;
}

.login-tip {
  font-size: 30px;
  font-weight: 400;
  color: #999999;
  line-height: 60px;
  letter-spacing: 1px;
  display: block;
  text-align: center;
}

.login-tip2 {
  font-size: 20px;
  font-weight: 400;
  color: #999999;
  display: block;
  text-align: center;
  margin-bottom: 30px;
}

#left {
  margin-top: 20px;
  margin-right: 100px;
  margin-left: 50px;
}

#right {
  margin-top: 20px;
  margin-left: 100px;
  margin-right: 50px;
}

.item {
  margin-bottom: 40px;
}

:deep(.el-form-item__label) {
  font-size: 20px;
  font-weight: 400;
  color: #333333;
  letter-spacing: 1px;
  display: block;
  text-align: center;
}

:deep(input) {
  width: 325px;
  border: none;
}

.el-button {
  font-size: 16px;
  padding-top: 8px;
  display: block;
  text-align: center;
  border-radius: 5px;
  border: 1px solid #4158D0;
}

#btn {
  margin-left: 30px;
  padding-top: 5px;
  font-size: 15px;
  display: block;
  text-align: center;
  border: none;
}

#radios {
  height: 10px;
  margin-left: 90px;
}

#change {
  margin: auto;
}

/deep/ .el-dialog {
  background-color: #97D9E1 !important;
}

</style>
