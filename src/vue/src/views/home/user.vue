<template>
  <div id="main">
    <nav-bar id="nav"></nav-bar>
    <div id="con" v-infinite-scroll="load">
      <side-bar id="side" @create="insertUser" @select="flush"></side-bar>
      <div id="content">
        <div id="top" :style="{ height: '200px' }">
          <span id="title">用户列表</span>
          <el-button-group v-if="menuItem!=='forbidden'" id="sort">
            <el-button :disabled="sortType === 'creation_date'" type="primary"
                       @click="changeSortType('creation_date')">创建时间
            </el-button>
            <el-button :disabled="sortType === 'last_update_date'" type="primary"
                       @click="changeSortType('last_update_date')">更新时间
            </el-button>
          </el-button-group>
          <el-radio-group v-if="menuItem!=='forbidden'" id="radio" v-model="sort" @change="search">
            <el-radio label="desc">降序</el-radio>
            <el-radio label="asc">升序</el-radio>
          </el-radio-group>
          <el-button v-if="menuItem==='forbidden'" id="clearUsers" type="danger" @click="clearUsers">清空列表
          </el-button>
          <el-input id="search" v-model="searchKeyWord" placeholder="请输入用户名进行搜索..."
                    size="default" @keyup.enter.native="search">
            <template v-slot:prefix>
              <i class="el-input__icon el-icon-search" @click="search"></i>
            </template>
          </el-input>
        </div>
        <div class="user-list">
          <el-row>
            <el-col v-for="user in userList" :key="user" :span="15">
              <el-card class="user-card">
                <div id="first">
                  <div :title="'密码（MD5加密）：\n' + user.password" class="user-name">
                    {{ user.username }}
                  </div>
                  <div class="user-info">
                    <div :title="user.id" class="user-id">ID：{{ user.id.substr(0, 16) }}...</div>
                  </div>
                </div>
                <hr class="hr-solid">
                <div id="last">
                  <div id="time">
                    <div class="user-create">创建时间：{{ convertToGMT0(user.creationDate) }}</div>
                    <div class="user-update">更新时间：{{ convertToGMT0(user.lastUpdateDate) }}</div>
                  </div>
                  <div class="user-button">
                    <el-button type="primary" @click="updateUser(user)">
                      <el-icon class="el-icon-edit"></el-icon>
                      编辑
                    </el-button>
                    <el-button v-if="menuItem==='forbidden'" type="success" @click="enableUser(user.id)">
                      <el-icon class="el-icon-refresh-left"></el-icon>
                      解禁
                    </el-button>
                    <el-button v-if="menuItem!=='forbidden'" type="danger" @click="disableUser(user.id)">
                      <el-icon class="el-icon-lock"></el-icon>
                      禁用
                    </el-button>
                    <el-button v-if="menuItem==='forbidden'" type="danger" @click="deleteUser(user.id)">
                      <el-icon class="el-icon-delete"></el-icon>
                      删除
                    </el-button>
                  </div>
                </div>
              </el-card>
            </el-col>
          </el-row>
        </div>
      </div>
    </div>
    <el-dialog v-model="insertDialogVisible" destroy-on-close
               title="创建用户" @close="insertHandleClose">
      <el-form ref="insertFormData" :model="insertFormData" :rules="rules" label-width="80px">
        <el-form-item label="用户名" prop="username">
          <el-input v-model="insertFormData.username" class="dialog-input"></el-input>
        </el-form-item>
        <el-form-item label="登录密码" prop="password">
          <el-input v-model="insertFormData.password" class="dialog-input"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="insertHandleCancel">取消</el-button>
        <el-button type="primary" @click="insertHandleConfirm">确认</el-button>
      </div>
    </el-dialog>
    <el-dialog v-model="updateDialogVisible" destroy-on-close
               title="编辑用户" @close="updateHandleClose">
      <el-form ref="updateFormData" :model="updateFormData" :rules="rules" label-width="80px">
        <el-form-item label="用户名" prop="username">
          <el-input v-model="updateFormData.username" class="dialog-input"></el-input>
        </el-form-item>
        <el-form-item label="登录密码" prop="password">
          <el-input v-model="updateFormData.password" class="dialog-input"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="updateHandleCancel">取消</el-button>
        <el-button type="primary" @click="updateHandleConfirm">确认</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import navBar from "../../components/nav";
import sideBar from "../../components/user/side";
import request, {plainRequest} from "@/api";
import CryptoJS from "crypto-js";
import router from "@/router";

export default {
  name: "user",
  inject: ['reload'],
  components: {
    navBar,
    sideBar
  },
  created() {
    this.search();
  },
  data() {
    return {
      searchKeyWord: "",
      sortType: 'creation_date',
      sort: "asc",
      currentPage: 1,
      pageSize: 5,
      userList: [],
      insertDialogVisible: false,
      updateDialogVisible: false,
      insertFormData: {},
      updateFormData: {},
      menuItem: "home",
      rules: {
        username: [
          {required: true, message: '请输入用户名', trigger: 'blur'},
          {min: 2, max: 16, message: '长度在 2 到 16 个字符', trigger: 'blur'}
        ],
        password: [
          {required: true, message: '请输入登录密码', trigger: 'blur'},
          {min: 6, max: 32, message: '长度在 6 到 32 个字符', trigger: 'blur'}
        ]
      }
    };
  },
  methods: {
    send(data, callback) {
      request.post("/selectUserByPage", JSON.stringify(data)).then(res => {
        if (res.data !== null && res.data.length > 0) {
          this.currentPage++;
          callback(res.data);
        } else {
          this.$message({
            message: '没有更多了',
            type: 'warning'
          });
          callback([]);
        }
      }).catch(err => {
        console.log(err);
        this.$message({
          message: '查询失败，请稍后重试',
          type: 'error'
        });
        callback([]);
      });
      callback([]);
    },
    search() {
      console.log("search");
      if (sessionStorage.getItem('token') === null) {
        this.$message({
          message: '登录已过期，请重新登录',
          type: 'error'
        });
        router.push('/login');
        return;
      }
      this.currentPage = 1;
      let data = {
        id: this.$route.query.username,//用户名
        searchKeyWord: this.searchKeyWord,//搜索关键字
        sortType: this.sortType,//按什么分类（project_count,creation_date,last_update_date）
        sort: this.sort,//升序还是降序（asc,desc）
        currentPage: this.currentPage,//当前页
        pageSize: this.pageSize,//每页显示多少条
      };
      if (this.menuItem !== 'home') {
        data.type = this.menuItem;
      }
      this.send(data, (res) => {
        this.userList = res;
      });
    },
    load() {
      const el = document.getElementById('con'); // 替换为你的容器元素的ID或引用
      if (el) {
        if (el.scrollHeight <= el.clientHeight) {
          // 无滚动条
          return;
        }
      }
      console.log("load");
      if (sessionStorage.getItem('token') === null) {
        this.$message({
          message: '登录已过期，请重新登录',
          type: 'error'
        });
        router.push('/login');
        return;
      }
      let data = {
        id: this.$route.query.username,//用户名
        searchKeyWord: this.searchKeyWord,//搜索关键字
        sortType: this.sortType,//按什么分类（project_count,creation_date,last_update_date）
        sort: this.sort,//升序还是降序（asc,desc）
        currentPage: this.currentPage,//当前页
        pageSize: this.pageSize//每页显示多少条
      };
      if (this.menuItem !== 'home') {
        data.type = this.menuItem;
      }
      this.send(data, (res) => {
        this.userList.push(...res);
      });
    },
    flush(val) {
      console.log(val);
      this.menuItem = val;
      if (val === 'home') {
        console.log("home");
        this.search();
      } else if (val === 'forbidden') {
        console.log("forbidden");
        this.currentPage = 1;
        let data = {
          id: this.$route.query.id,//用户id
          searchKeyWord: this.searchKeyWord,//搜索关键字
          sortType: this.sortType,//按什么分类（project_count,creation_date,last_update_date）
          sort: this.sort,//升序还是降序（asc,desc）
          currentPage: this.currentPage,//当前页
          pageSize: this.pageSize,//每页显示多少条
          type: 'forbidden'
        };
        this.send(data, (res) => {
          this.userList = res;
        });
      } else if (val === 'files') {
        console.log("files");
      } else {
        console.log("error");
      }
    },
    changeSortType(type) {
      this.sortType = type;
      this.search();
    },
    insertUser() {
      console.log("insertUser");
      this.insertFormData.username = "";
      this.insertFormData.password = "";
      this.insertShowDialog();
    },
    updateUser(user) {
      console.log("updateUser:" + user.id);
      this.updateFormData.username = user.username;
      this.updateFormData.password = user.password;
      this.updateShowDialog(user);
    },

    enableUser(id) {
      console.log("enableUser:" + id);
      //弹出确认框
      this.$confirm('此操作将启用该用户, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning',
        customClass: 'message-box'
      }).then(() => {
        plainRequest.post("/enableUser", id).then(res => {
          if (res.data === 1) {
            this.$message({
              message: '启用成功',
              type: 'success'
            });
            this.flush(this.menuItem);
          } else {
            this.$message({
              message: '启用失败，请稍后重试',
              type: 'error'
            });
          }
        }).catch(err => {
          console.log(err);
          this.$message({
            message: '启用失败，请稍后重试',
            type: 'error'
          });
        });
      }).catch(() => {
        this.$message({
          type: 'info',
          message: '已取消启用'
        });
      });
    },

    disableUser(id) {
      console.log("disableUser:" + id);
      //弹出确认框
      this.$confirm('此操作将禁用该用户, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning',
        customClass: 'message-box'
      }).then(() => {
        plainRequest.post("/disableUser", id).then(res => {
          if (res.data === 1) {
            this.$message({
              message: '禁用成功',
              type: 'success'
            });
            this.flush(this.menuItem);
          } else {
            this.$message({
              message: '禁用失败，请稍后重试',
              type: 'error'
            });
          }
        }).catch(err => {
          console.log(err);
          this.$message({
            message: '禁用失败，请稍后重试',
            type: 'error'
          });
        });
      }).catch(() => {
        this.$message({
          type: 'info',
          message: '已取消禁用'
        });
      });
    },

    deleteUser(id) {
      console.log("deleteUser:" + id);
      //弹出确认框
      this.$confirm('此操作将永久删除该用户, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning',
        customClass: 'message-box'
      }).then(() => {
        plainRequest.post("/deleteUserById", id).then(res => {
          if (res.data === 1) {
            this.$message({
              message: '删除成功',
              type: 'success'
            });
            this.flush(this.menuItem);
          } else {
            this.$message({
              message: '删除失败，请稍后重试',
              type: 'error'
            });
          }
        }).catch(err => {
          console.log(err);
          this.$message({
            message: '删除失败，请稍后重试',
            type: 'error'
          });
        });
      }).catch(() => {
        this.$message({
          type: 'info',
          message: '已取消删除'
        });
      });
    },
    clearUsers() {
      console.log("deleteUserByStatus");
      //弹出确认框
      this.$confirm('此操作将永久删除所有用户, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning',
        customClass: 'message-box'
      }).then(() => {
        if (sessionStorage.getItem('token') === null) {
          this.$message({
            message: '登录已过期，请重新登录',
            type: 'error'
          });
          router.push('/login');
          return;
        }
        plainRequest.post("/deleteUserByStatus", sessionStorage.getItem('token')).then(res => {
          if (res.data !== 0) {
            this.$message({
              message: '删除成功',
              type: 'success'
            });
            this.flush(this.menuItem);
          } else {
            this.$message({
              message: '删除失败，请稍后重试',
              type: 'error'
            });
          }
        }).catch(err => {
          console.log(err);
          this.$message({
            message: '删除失败，请稍后重试',
            type: 'error'
          });
        });
      }).catch(() => {
        this.$message({
          type: 'info',
          message: '已取消删除'
        });
      });
    },
    insertShowDialog() {
      // 显示对话框
      this.insertDialogVisible = true;
    },
    insertHandleClose() {
      // 处理关闭按钮逻辑
      console.log('关闭按钮点击');
      // 关闭对话框
      this.insertDialogVisible = false;
    },
    insertHandleConfirm() {
      // 处理确认按钮逻辑
      console.log('确认按钮点击');
      this.$refs.insertFormData.validate((valid) => {
        if (valid) {
          request.post("/insertUser", JSON.stringify({
            username: this.insertFormData.username,
            password: CryptoJS.MD5(this.insertFormData.password).toString(),
            createdBy: this.$route.query.username,
            lastUpdatedBy: this.$route.query.username
          })).then(res => {
            if (res.data === 1) {
              this.$message({
                message: '添加成功',
                type: 'success'
              });
              this.flush(this.menuItem);
            } else {
              this.$message({
                message: '添加失败，请稍后重试',
                type: 'error'
              });
            }
          }).catch(err => {
            console.log(err);
            this.$message({
              message: '添加失败，请稍后重试',
              type: 'error'
            });
          });
        } else {
          this.$message({
            message: '输入格式不正确',
            type: 'error'
          });
        }
      });
      // 关闭对话框
      this.insertDialogVisible = false;
    },
    insertHandleCancel() {
      // 处理取消按钮逻辑
      console.log('取消按钮点击');
      // 关闭对话框
      this.insertDialogVisible = false;
    },
    updateShowDialog(user) {
      // 显示对话框
      this.updateDialogVisible = true;
      this.updateFormData.id = user.id;
      this.updateFormData.username = user.username;
      this.updateFormData.password = '';
    },
    updateHandleClose() {
      // 处理关闭按钮逻辑
      console.log('关闭按钮点击');
      // 关闭对话框
      this.updateDialogVisible = false;
    },
    updateHandleConfirm() {
      // 处理确认按钮逻辑
      console.log('确认按钮点击');
      this.$refs.updateFormData.validate((valid) => {
        if (valid) {
          request.post("/updateUser", JSON.stringify({
            id: this.updateFormData.id,
            username: this.updateFormData.username,
            password: CryptoJS.MD5(this.updateFormData.password).toString(),
            lastUpdatedBy: this.$route.query.id
          })).then(res => {
            if (res.data === 1) {
              this.$message({
                message: '编辑成功',
                type: 'success'
              });
              this.flush(this.menuItem);
            } else {
              this.$message({
                message: '编辑失败，请稍后重试',
                type: 'error'
              });
            }
          }).catch(err => {
            console.log(err);
            this.$message({
              message: '编辑失败，请稍后重试',
              type: 'error'
            });
          });
        } else {
          this.$message({
            message: '输入格式不正确',
            type: 'error'
          });
        }
      });
      // 关闭对话框
      this.updateDialogVisible = false;
    },
    updateHandleCancel() {
      // 处理取消按钮逻辑
      console.log('取消按钮点击');
      // 关闭对话框
      this.updateDialogVisible = false;
    },
    convertToGMT0(dateTimeString) {
      const date = new Date(dateTimeString);
      const utcOffset = 0;

      // 转换为目标时区的时间
      const targetDate = new Date(date.getTime() + utcOffset * 60 * 60 * 1000);

      // 获取年、月、日、小时、分钟和秒
      const year = targetDate.getFullYear();
      const month = (targetDate.getMonth() + 1).toString().padStart(2, '0');
      const day = targetDate.getDate().toString().padStart(2, '0');
      const hours = targetDate.getHours().toString().padStart(2, '0');
      const minutes = targetDate.getMinutes().toString().padStart(2, '0');
      const seconds = targetDate.getSeconds().toString().padStart(2, '0');
      // 拼接日期时间字符串
      return `${year}-${month}-${day} ${hours}:${minutes}:${seconds}`;
    }
  },
}
</script>

<style scoped>

#main {
  display: flex;
  flex-direction: column;
  height: 100vh; /* 设置容器高度为视口高度 */
  background-color: #eeeeee;
}

#nav {
  position: fixed;
  top: 0;
  left: 0;
  width: 100%;
  z-index: 999;
}

#con {
  overflow-y: auto; /* 添加滚动条 */
  margin-top: 55px; /* 顶栏高度 */
  flex: 1;
  display: flex;
  flex-wrap: wrap;
}

#side {
  position: fixed;
  margin-top: 3%;
  margin-left: 12%;
}

#content {
  margin-left: 28%;
  display: flex;
  flex-direction: column;
  align-items: center;
  width: 50%;
}

#top {
  margin-left: 45%;
  margin-top: 6%;
  display: flex;
  align-items: center;
  height: 60px !important;
}

#title {
  width: 350px;
  font-size: 24px;
  font-weight: bold;
  margin-right: 300px;
}

#clearUsers {
  margin-right: 50px;
}

#sort {
  display: flex;
  width: 200px;
  font-size: 20px;
  margin-right: 140px;
}

#radio {
  height: 50px;
  margin-right: 10px;
}

.el-button {
  padding-top: 8px;
}

#search {
  flex: 1;
  width: 300px;
  margin-right: 10px;
}

.el-icon-search:hover {
  color: #207EFF;
}

.user-list {
  margin-left: 36%;
  margin-top: 2%;
}

.user-card {
  margin-bottom: 20px;
  width: 1100px;
  height: 150px;
  border: #dddddd solid 1px;
  border-radius: 0;
}

#first {
  display: flex;
  align-items: center;
  justify-content: space-between;
}

.user-name {
  font-size: 24px;
  font-weight: bold;
  margin-left: 20px;
}

.hr-solid {
  background-color: #eeeeee;
  height: 1px;
  border: none;
}

.user-info {
  display: flex;
  align-items: center;
}

.user-id {
  margin-right: 20px;
  font-size: 16px;
}

.user-id:hover {
  cursor: pointer;
}

.user-qnc {
  margin-right: 20px;
  font-size: 16px;
}

#last {
  margin-top: 20px;
  display: flex;
  align-items: center;
}

#time {
  flex: 1;
  font-size: 16px;
}

.user-button {
  margin-right: 20px;
}

:deep(input) {
  border: none;
}

:deep(.el-dialog) {
  display: block;
  flex-direction: column;
  margin: 0 !important;
  position: absolute;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
  max-height: calc(80% - 30px);
  max-width: calc(80% - 30px);
  width: 30%;
}

el-dialog .el-dialog__body {
  display: block;
  text-align: center;
  align-items: center;
}

.el-form-item {
  margin-left: 100px;
}

.dialog-footer {
  text-align: center;
}
</style>

<style lang="scss">
.message-box {
  .el-button {
    padding-top: 8px;
  }
}
</style>

