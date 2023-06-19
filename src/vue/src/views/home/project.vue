<template>
  <div id="main">
    <nav-bar id="nav"></nav-bar>
    <div id="con" v-infinite-scroll="load">
      <side-bar id="side" @select="flush"></side-bar>
      <div id="content">
        <div id="top" :style="{ height: '200px' }">
          <span id="title">项目列表</span>
          <el-button-group id="sort">
            <el-button type="primary" :disabled="sortType === 'questionnaire_count'"
                       @click="changeSortType('questionnaire_count')">问卷数量
            </el-button>
            <el-button type="primary" :disabled="sortType === 'creation_date'"
                       @click="changeSortType('creation_date')">创建时间
            </el-button>
            <el-button type="primary" :disabled="sortType === 'last_update_date'"
                       @click="changeSortType('last_update_date')">更新时间
            </el-button>
          </el-button-group>
          <el-radio-group v-model="sort" id="radio" @change="search">
            <el-radio label="desc">降序</el-radio>
            <el-radio label="asc">升序</el-radio>
          </el-radio-group>
          <el-input id="search" v-model="searchKeyword" size="default"
                    placeholder="请输入项目名进行搜索..." @keyup.enter.native="search">
            <template v-slot:prefix>
              <i class="el-input__icon el-icon-search" @click="search"></i>
            </template>
          </el-input>
        </div>
        <div class="project-list">
          <el-row>
            <el-col :span="15" v-for="project in projectList" :key="project">
              <el-card class="project-card">
                <div id="first">
                  <div class="project-name">{{ project.projectName }}</div>
                  <div id="star">
                    <div class="project-qnc">问卷数：{{ project.questionnaireCount }}</div>
                    <el-icon id="on" class="el-icon-star-on" v-if="project.star===1"
                             @click="starOn(project)"></el-icon>
                    <el-icon id="off" class="el-icon-star-on" v-if="project.star===0"
                             @click="starOff(project)"></el-icon>
                  </div>
                </div>
                <hr class="hr-solid">
                <div id="last">
                  <div id="time">
                    <div class="project-create">创建时间：{{ project.creationDate }}</div>
                    <div class="project-update">更新时间：{{ project.lastUpdateDate }}</div>
                  </div>
                  <div class="project-button">
                    <el-button type="primary" @click="toQuestionnaire(project.id)">
                      <el-icon class="el-icon-edit"></el-icon>
                      编辑
                    </el-button>
                    <el-button type="primary" @click="copyProject(project.id)">
                      <el-icon class="el-icon-document-copy"></el-icon>
                      复制
                    </el-button>
                    <el-button type="primary" @click="deleteProject(project.id)">
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
  </div>
</template>

<script>
import navBar from "../../components/nav";
import sideBar from "../../components/project/side";
import request from "@/api";
import router from "@/router";

export default {
  name: "project",
  inject: ['reload'],
  components: {
    navBar,
    sideBar
  },
  data() {
    return {
      searchKeyword: "",
      sortType: 'questionnaire_count',
      sort: "desc",
      currentPage: 1,
      pageSize: 5,
      projectList: [
        {
          id: 1,
          projectName: "项目1",
          projectContent: "项目1描述1111111111111111111111",
          questionnaireCount: 10,
          creationDate: "2023-06-02 12:24:01",
          lastUpdateDate: "2023-06-02 12:24:01",
          star: 1
        },
      ]
    };
  },
  methods: {
    send(data) {
      request.post("/queryProject", JSON.stringify(data)).then(res => {
        if (res.data !== null && res.data.length > 0) {
          this.currentPage++;
          return res.data;
        } else {
          this.$message({
            message: '没有更多了',
            type: 'warning'
          });
          return [];
        }
      }).catch(err => {
        console.log(err);
        this.$message({
          message: '查询失败，请稍后重试',
          type: 'error'
        });
        return [];
      });
      return [];
    },
    search() {
      console.log("search");
      this.currentPage = 1;
      let data = {
        searchKeyword: this.searchKeyword,//搜索关键字
        sortType: this.sortType,//按什么分类（questionnaire_count,creation_date,last_update_date）
        sort: this.sort,//升序还是降序（asc,desc）
        currentPage: this.currentPage,//当前页
        pageSize: this.pageSize//每页显示多少条
      };
      this.projectList = this.send(data);
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
        searchKeyword: this.searchKeyword,//搜索关键字
        sortType: this.sortType,//按什么分类（questionnaire_count,creation_date,last_update_date）
        sort: this.sort,//升序还是降序（asc,desc）
        currentPage: this.currentPage,//当前页
        pageSize: this.pageSize//每页显示多少条
      };
      this.projectList.push(...this.send(data));
    },
    flush(val) {
      console.log(val);
      if (val === 'home') {
        console.log("home");
        this.search();
      } else if (val === 'star') {
        console.log("star");
        this.currentPage = 1;
        let data = {
          searchKeyword: this.searchKeyword,//搜索关键字
          sortType: this.sortType,//按什么分类（questionnaire_count,creation_date,last_update_date）
          sort: this.sort,//升序还是降序（asc,desc）
          currentPage: this.currentPage,//当前页
          pageSize: this.pageSize,//每页显示多少条
          type: 'star'
        };
        this.projectList = this.send(data);
      } else if (val === 'delete') {
        console.log("delete");
        this.currentPage = 1;
        let data = {
          searchKeyword: this.searchKeyword,//搜索关键字
          sortType: this.sortType,//按什么分类（questionnaire_count,creation_date,last_update_date）
          sort: this.sort,//升序还是降序（asc,desc）
          currentPage: this.currentPage,//当前页
          pageSize: this.pageSize,//每页显示多少条
          type: 'delete'
        };
        this.projectList = this.send(data);
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
      request.post("/starProject", project.id).then(res => {
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
      request.post("/unstarProject", project.id).then(res => {
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
    copyProject(id) {
      console.log("copyProject:" + id);
      request.post("/copyProject", id).then(res => {
        if (res.data === 1) {
          this.$message({
            message: '复制成功',
            type: 'success'
          });
          this.reload();
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
    },
    deleteProject(id) {
      console.log("deleteProject:" + id);
      request.post("/deleteProject", id).then(res => {
        if (res.data === 1) {
          this.$message({
            message: '删除成功',
            type: 'success'
          });
          this.reload();
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
    },
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
  margin-left: 35%;
  margin-top: 6%;
  display: flex;
  align-items: center;
  height: 60px !important;
}

#title {
  width: 350px;
  font-size: 24px;
  font-weight: bold;
  margin-right: 180px;
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
  margin-left: 27.5%;
  margin-top: 2%;
}

.project-card {
  margin-bottom: 20px;
  width: 975px;
  height: 170px;
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

.project-qnc {
  margin-right: 20px;
  font-size: 16px;
}

#last {
  margin-top: 40px;
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

</style>