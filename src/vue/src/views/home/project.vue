<template>
  <div id="main">
    <nav-bar id="nav"></nav-bar>
    <div id="con" v-infinite-scroll="load">
      <side-bar id="side" @create="insertProject" @select="flush"></side-bar>
      <div id="content">
        <div id="top" :style="{ height: '200px' }">
          <span id="title">项目列表</span>
          <el-button-group v-if="menuItem!=='deleted'" id="sort">
            <el-button :disabled="sortType === 'questionnaire_count'" type="primary"
                       @click="changeSortType('questionnaire_count')">问卷数量
            </el-button>
            <el-button :disabled="sortType === 'creation_date'" type="primary"
                       @click="changeSortType('creation_date')">创建时间
            </el-button>
            <el-button :disabled="sortType === 'last_update_date'" type="primary"
                       @click="changeSortType('last_update_date')">更新时间
            </el-button>
          </el-button-group>
          <el-radio-group v-if="menuItem!=='deleted'" id="radio" v-model="sort" @change="search">
            <el-radio label="desc">降序</el-radio>
            <el-radio label="asc">升序</el-radio>
          </el-radio-group>
          <el-button v-if="menuItem==='deleted'" id="clearProjects" type="danger" @click="clearProjects">清空回收站
          </el-button>
          <el-input id="search" v-model="searchKeyWord" placeholder="请输入项目名进行搜索..."
                    size="default" @keyup.enter.native="search">
            <template v-slot:prefix>
              <i class="el-input__icon el-icon-search" @click="search"></i>
            </template>
          </el-input>
        </div>
        <div class="project-list">
          <el-row>
            <el-col v-for="project in projectList" :key="project" :span="15">
              <el-card class="project-card">
                <div id="first">
                  <div :title="'项目描述：\n' + project.projectContent" class="project-name"
                       @click="toQuestionnaire(project.id)">
                    {{ project.projectName }}
                  </div>
                  <div id="star">
                    <div class="project-info">
                      <div :title="project.id" class="project-id">ID：{{ project.id.substr(0, 8) }}...</div>
                      <div class="project-qnc" @click="toAnswerList(project.id)">
                        问卷数：{{ project.questionnaireCount }}
                      </div>
                    </div>
                    <el-icon v-if="project.star===1&&menuItem!=='deleted'" id="on" class="el-icon-star-on"
                             @click="starOff(project)"></el-icon>
                    <el-icon v-if="project.star===0&&menuItem!=='deleted'" id="off" class="el-icon-star-on"
                             @click="starOn(project)"></el-icon>
                  </div>
                </div>
                <hr class="hr-solid">
                <div id="last">
                  <div id="time">
                    <div class="project-create">创建时间：{{ convertToGMT0(project.creationDate) }}</div>
                    <div class="project-update">更新时间：{{ convertToGMT0(project.lastUpdateDate) }}</div>
                  </div>
                  <div class="project-button">
                    <el-button type="primary" @click="updateProject(project)">
                      <el-icon class="el-icon-edit"></el-icon>
                      编辑
                    </el-button>
                    <el-button v-if="menuItem!=='deleted'" type="warning" @click="copyProject(project.id)">
                      <el-icon class="el-icon-document-copy"></el-icon>
                      复制
                    </el-button>
                    <el-button v-if="menuItem==='deleted'" type="success" @click="updateDeletedOffProject(project.id)">
                      <el-icon class="el-icon-refresh-left"></el-icon>
                      还原
                    </el-button>
                    <el-button v-if="menuItem!=='deleted'" type="danger" @click="updateDeletedOnProject(project.id)">
                      <el-icon class="el-icon-delete"></el-icon>
                      删除
                    </el-button>
                    <el-button v-if="menuItem==='deleted'" type="danger" @click="deleteProject(project.id)">
                      <el-icon class="el-icon-close"></el-icon>
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
               title="创建项目" @close="insertHandleClose">
      <el-form ref="insertFormData" :model="insertFormData" :rules="rules" label-width="80px">
        <el-form-item label="项目名称" prop="projectName">
          <el-input v-model="insertFormData.projectName" class="dialog-input"></el-input>
        </el-form-item>
        <el-form-item label="项目描述" prop="projectContent">
          <el-input v-model="insertFormData.projectContent" :resize="'none'"
                    :rows="3" class="dialog-textarea" type="textarea"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="insertHandleCancel">取消</el-button>
        <el-button type="primary" @click="insertHandleConfirm">确认</el-button>
      </div>
    </el-dialog>
    <el-dialog v-model="updateDialogVisible" destroy-on-close
               title="编辑项目" @close="updateHandleClose">
      <el-form ref="updateFormData" :model="updateFormData" :rules="rules" label-width="80px">
        <el-form-item label="项目名称" prop="projectName">
          <el-input v-model="updateFormData.projectName" class="dialog-input"></el-input>
        </el-form-item>
        <el-form-item label="项目描述" prop="projectContent">
          <el-input v-model="updateFormData.projectContent" :resize="'none'"
                    :rows="3" class="dialog-textarea" type="textarea"></el-input>
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
import sideBar from "../../components/project/side";
import request, {plainRequest} from "@/api";
import router from "@/router";

export default {
  name: "project",
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
      sortType: 'questionnaire_count',
      sort: "desc",
      currentPage: 1,
      pageSize: 5,
      projectList: [],
      insertDialogVisible: false,
      updateDialogVisible: false,
      insertFormData: {},
      updateFormData: {},
      menuItem: "home",
      rules: {
        projectName: [
          {required: true, message: '请输入项目名称', trigger: 'blur'},
          {min: 2, max: 16, message: '长度在 2 到 16 个字符', trigger: 'blur'}
        ],
        projectContent: [
          {required: true, message: '请输入项目描述', trigger: 'blur'},
          {min: 5, max: 50, message: '长度在 5 到 50 个字符', trigger: 'blur'}
        ]
      }
    };
  },
  methods: {
    send(data, callback) {
      request.post("/selectProjectByPage", JSON.stringify(data)).then(res => {
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
      this.currentPage = 1;
      let data = {
        id: this.$route.query.id,//用户id
        searchKeyWord: this.searchKeyWord,//搜索关键字
        sortType: this.sortType,//按什么分类（questionnaire_count,creation_date,last_update_date）
        sort: this.sort,//升序还是降序（asc,desc）
        currentPage: this.currentPage,//当前页
        pageSize: this.pageSize,//每页显示多少条
      };
      if (this.menuItem !== 'home') {
        data.type = this.menuItem;
      }
      this.send(data, (res) => {
        this.projectList = res;
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
      let data = {
        id: this.$route.query.id,//用户id
        searchKeyWord: this.searchKeyWord,//搜索关键字
        sortType: this.sortType,//按什么分类（questionnaire_count,creation_date,last_update_date）
        sort: this.sort,//升序还是降序（asc,desc）
        currentPage: this.currentPage,//当前页
        pageSize: this.pageSize//每页显示多少条
      };
      if (this.menuItem !== 'home') {
        data.type = this.menuItem;
      }
      this.send(data, (res) => {
        this.projectList.push(...res);
      });
    },
    flush(val) {
      console.log(val);
      this.menuItem = val;
      if (val === 'home') {
        console.log("home");
        this.search();
      } else if (val === 'star') {
        console.log("star");
        this.currentPage = 1;
        let data = {
          id: this.$route.query.id,//用户id
          searchKeyWord: this.searchKeyWord,//搜索关键字
          sortType: this.sortType,//按什么分类（questionnaire_count,creation_date,last_update_date）
          sort: this.sort,//升序还是降序（asc,desc）
          currentPage: this.currentPage,//当前页
          pageSize: this.pageSize,//每页显示多少条
          type: 'star'
        };
        this.send(data, (res) => {
          this.projectList = res;
        });
      } else if (val === 'deleted') {
        console.log("deleted");
        this.currentPage = 1;
        let data = {
          id: this.$route.query.id,//用户id
          searchKeyWord: this.searchKeyWord,//搜索关键字
          sortType: this.sortType,//按什么分类（questionnaire_count,creation_date,last_update_date）
          sort: this.sort,//升序还是降序（asc,desc）
          currentPage: this.currentPage,//当前页
          pageSize: this.pageSize,//每页显示多少条
          type: 'deleted'
        };
        this.send(data, (res) => {
          this.projectList = res;
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
    starOn(project) {
      console.log("starOn:" + project.id);
      plainRequest.post("/updateStarOnProject", project.id).then(res => {
        if (res.data === 1) {
          this.$message({
            message: '星标成功',
            type: 'success'
          });
          project.star = 1;
        } else {
          this.$message({
            message: '星标失败，请稍后重试',
            type: 'error'
          });
        }
      }).catch(err => {
        console.log(err);
        this.$message({
          message: '星标失败，请稍后重试',
          type: 'error'
        });
      });
    },
    starOff(project) {
      console.log("starOff:" + project.id);
      plainRequest.post("/updateStarOffProject", project.id).then(res => {
        if (res.data === 1) {
          this.$message({
            message: '取消星标成功',
            type: 'success'
          });
          project.star = 0;
        } else {
          this.$message({
            message: '取消星标失败，请稍后重试',
            type: 'error'
          });
        }
      }).catch(err => {
        console.log(err);
        this.$message({
          message: '取消星标失败，请稍后重试',
          type: 'error'
        });
      });
    },
    toAnswerList(id) {
      router.push({
        path: '/answerList',
        query: {
          id: id,
        },
      });
    },
    toQuestionnaire(id) {
      console.log("toQuestionnaire:" + id);
      router.push({
        path: '/questionnaire',
        query: {
          id: this.$route.query.id,
          project_id: id
        }
      });
    },
    insertProject() {
      console.log("insertProject");
      this.insertFormData.projectName = "";
      this.insertFormData.projectContent = "";
      this.insertShowDialog();
    },
    updateProject(project) {
      console.log("updateProject:" + project.id);
      this.updateFormData.projectName = project.projectName;
      this.updateFormData.projectContent = project.projectContent;
      this.updateShowDialog(project);
    },
    copyProject(id) {
      console.log("copyProject:" + id);
      //弹出确认框
      this.$confirm('此操作将复制该项目, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning',
        customClass: 'message-box'
      }).then(() => {
        plainRequest.post("/insertCopyProject", id).then(res => {
          if (res.data === 1) {
            this.$message({
              message: '复制成功',
              type: 'success'
            });
            this.flush(this.menuItem);
          } else {
            this.$message({
              message: '复制失败，请稍后重试',
              type: 'error'
            });
          }
        }).catch(err => {
          console.log(err);
          this.$message({
            message: '复制失败，请稍后重试',
            type: 'error'
          });
        });
      }).catch(() => {
        this.$message({
          type: 'info',
          message: '取消复制'
        });
      });
    },
    updateDeletedOnProject(id) {
      console.log("updateDeletedOnProject:" + id);
      //弹出确认框
      this.$confirm('此操作将删除该项目, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning',
        customClass: 'message-box'
      }).then(() => {
        plainRequest.post("/updateDeletedOnProject", id).then(res => {
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
    updateDeletedOffProject(id) {
      console.log("updateDeletedOffProject:" + id);
      //弹出确认框
      this.$confirm('此操作将还原该项目, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning',
        customClass: 'message-box'
      }).then(() => {
        plainRequest.post("/updateDeletedOffProject", id).then(res => {
          if (res.data === 1) {
            this.$message({
              message: '还原成功',
              type: 'success'
            });
            this.flush(this.menuItem);
          } else {
            this.$message({
              message: '还原失败，请稍后重试',
              type: 'error'
            });
          }
        }).catch(err => {
          console.log(err);
          this.$message({
            message: '还原失败，请稍后重试',
            type: 'error'
          });
        });
      }).catch(() => {
        this.$message({
          type: 'info',
          message: '已取消还原'
        });
      });
    },
    deleteProject(id) {
      console.log("deleteProject:" + id);
      //弹出确认框
      this.$confirm('此操作将永久删除该项目, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning',
        customClass: 'message-box'
      }).then(() => {
        plainRequest.post("/deleteProject", id).then(res => {
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
    clearProjects() {
      console.log("deleteAllProjectRecycled");
      //弹出确认框
      this.$confirm('此操作将永久删除所有项目, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning',
        customClass: 'message-box'
      }).then(() => {
        plainRequest.post("/deleteAllProjectRecycled", this.$route.query.id).then(res => {
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
          request.post("/insertProject", JSON.stringify({
            projectName: this.insertFormData.projectName,
            projectContent: this.insertFormData.projectContent,
            userId: this.$route.query.id,
            createdBy: this.$route.query.id
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
    updateShowDialog(project) {
      // 显示对话框
      this.updateDialogVisible = true;
      this.updateFormData.id = project.id;
      this.updateFormData.projectName = project.projectName;
      this.updateFormData.projectContent = project.projectContent;
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
          request.post("/updateProject", JSON.stringify({
            id: this.updateFormData.id,
            projectName: this.updateFormData.projectName,
            projectContent: this.updateFormData.projectContent,
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

#clearProjects {
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

.project-list {
  margin-left: 36%;
  margin-top: 2%;
}

.project-card {
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

.project-name {
  font-size: 24px;
  font-weight: bold;
  margin-left: 20px;
}

.project-name:hover {
  cursor: pointer;
  color: #207EFF;
}

.project-qnc:hover {
  cursor: pointer;
  color: #207EFF;
}

.hr-solid {
  background-color: #eeeeee;
  height: 1px;
  border: none;
}

#star {
  margin-right: 20px;
  display: flex;
  align-items: center;
}

.project-info {
  display: flex;
  align-items: center;
}

.project-id {
  margin-right: 20px;
  font-size: 16px;
}

.project-id:hover {
  cursor: pointer;
}

.project-qnc {
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

.project-button {
  margin-right: 20px;
}

#on {
  color: #207EFF;
  font-size: 36px;
}

#off {
  color: #dddddd;
  font-size: 36px;
}

.el-icon-star-on:hover {
  cursor: pointer;
}

:deep(input) {
  border: none;
}

:deep(.el-dialog) {
  display: flex;
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
  display: flex;
  justify-content: center;
  align-items: center;
}

.el-form-item {
  margin-left: 40px;
}

.dialog-footer {
  display: flex;
  justify-content: center;
}
</style>

<style lang="scss">
.message-box {
  .el-button {
    padding-top: 8px;
  }
}

.dialog-input .el-input__inner {
  width: 100% !important;
}

.dialog-textarea .el-textarea__inner {
  width: 85% !important;
}

</style>

