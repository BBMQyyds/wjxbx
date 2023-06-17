<template>
  <div id="main">
    <nav-bar id="nav"></nav-bar>
    <div id="con">
      <side-bar id="side"></side-bar>
      <div id="top">
        <span id="title">项目列表</span>
        <el-button-group id="sort">
          <el-button type="primary" :disabled="sortType === 'count'" @click="changeSortType('count')">答卷
          </el-button>
          <el-button type="primary" :disabled="sortType === 'time'" @click="changeSortType('time')">时间
          </el-button>
        </el-button-group>
        <el-input id="search" v-model="searchKeyword" placeholder="请输入项目名进行搜索..."></el-input>
      </div>
      <div class="project-list">
        <el-row>
          <el-col :span="15" v-for="(project, index) in projectList" :key="index">
            <el-card class="project-card">
              <div class="project-title">标题：{{ project.title }}</div>
              <div class="project-type_author">标签：{{ project.type }}&emsp;作者：{{ project.user_name }}</div>
              <div>浏览数：{{ project.watch_count }}&emsp;点赞数：{{ project.like_count }}</div>
              <div>收藏数：{{ project.collect_count }}&emsp;评论数：{{ project.comment_count }}</div>
              <div class="project-date">时间：{{ project.create_time }}</div>
            </el-card>
          </el-col>
        </el-row>
      </div>
    </div>
  </div>
</template>

<script>
import navBar from "../../components/nav";
import sideBar from "../../components/project/side";

export default {
  name: "home",
  components: {
    navBar,
    sideBar
  },
  data() {
    return {
      searchKeyword: "",
      sortType: '',
      projectList: [
        {
          title: "项目1",
          type: "类型1",
          user_name: "作者1",
          watch_count: 1,
          like_count: 1,
          collect_count: 1,
          comment_count: 1,
          create_time: "2020-01-01"
        },
        {
          title: "项目2",
          type: "类型2",
          user_name: "作者2",
          watch_count: 2,
          like_count: 2,
          collect_count: 2,
          comment_count: 2,
          create_time: "2020-01-02"
        },
        {
          title: "项目3",
          type: "类型3",
          user_name: "作者3",
          watch_count: 3,
          like_count: 3,
          collect_count: 3,
          comment_count: 3,
          create_time: "2020-01-03"
        },
        {
          title: "项目4",
          type: "类型4",
          user_name: "作者4",
          watch_count: 4,
          like_count: 4,
          collect_count: 4,
          comment_count: 4,
          create_time: "2020-01-04"
        },
        {
          title: "项目5",
          type: "类型5",
          user_name: "作者5",
          watch_count: 5,
          like_count: 5,
          collect_count: 5,
          comment_count: 5,
          create_time: "2020-01-05"
        },
        {
          title: "项目6",
          type: "类型6",
          user_name: "作者6",
          watch_count: 6,
          like_count: 6,
          collect_count: 6,
          comment_count: 6,
          create_time: "2020-01-06"
        },
        {
          title: "项目7",
          type: "类型7",
          user_name: "作者7",
          watch_count: 7,
          like_count: 7,
          collect_count: 7,
          comment_count: 7,
          create_time: "2020-01-07"
        }
      ]
    };
  },
  methods: {
    changeSortType(type) {
      this.sortType = type;
    }
  }
}
</script>

<style scoped>

#con {
  overflow-y: scroll;
  padding-top: 40px; /* 顶栏高度 */
}

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
  flex: 1;
  display: flex;
  flex-wrap: wrap;
}

#side {
  position: fixed;
  margin-top: 3%;
  margin-left: 12%;
}

#top {
  margin-left: 35%;
  margin-top: 3.5%;
  display: flex;
  flex: 1;
}

#title {
  width: 350px;
  font-size: 24px;
  font-weight: bold;
  margin-right: 450px;
}

#sort {
  display: flex;
  width: 200px;
  font-size: 20px;
  padding-top: 6px;
  margin-right: 40px;
}

.el-button {
  padding-top: 8px;
}

#search {
  width: 300px;
  margin-right: 10px;
}

.project-list {
  margin-left: 35%;
  margin-right: 0;
  margin-top: 2%;
}

.project-card {
  margin-bottom: 20px;
  width: 975px;
  height: 140px;
  border: #dddddd solid 1px;
  border-radius: 0;
}

:deep(input) {
  border: none;
}

</style>