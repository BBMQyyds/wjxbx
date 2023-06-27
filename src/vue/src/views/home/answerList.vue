<template>
  <div id="main">
    <nav-bar id="nav"></nav-bar>
    <div id="con">
      <el-container id="content">
        <div class="project-name">
          <span class="name">{{ this.project.name }}</span>
        </div>
        <div class="project-description">
          <span>项目描述：{{ this.project.description }}</span>
        </div>
        <hr>
        <el-row class="row" style="text-align: center">
          <el-col :span="14">
            <el-input v-model="this.searchKeyWord" placeholder="搜索答卷" @keyup.enter.native="search(1)">
              <template v-slot:prefix>
                <i class="el-input__icon el-icon-search" @click="search(1)"></i>
              </template>
            </el-input>
          </el-col>
        </el-row>

        <el-table
            ref="table"
            :cell-style="{'text-align':'center'}"
            :data="this.project.answerList"
            :header-cell-style="{backgroundColor:'#dddddd','text-align':'center'}"
            cell-class-name="cell-class-name"
            class="table"
            style="margin-top: 40px;width: 80%;margin-left: 10%;margin-right: 10%;"
            @sort-change="changeTableSort">
          <el-table-column label="id" prop="id" v-if="false"></el-table-column>
          <el-table-column label="问卷" prop="questionnaireName"></el-table-column>
          <el-table-column label="答卷人" prop="username"></el-table-column>
          <el-table-column label="答卷时间" prop="createDate" sortable></el-table-column>
          <el-table-column label="操作">
            <template v-slot="scope">
              <el-button size="default" type="warning" @click="toAnswer(scope.row)">明细</el-button>
            </template>
          </el-table-column>
        </el-table>

        <el-row style="width: 100%;text-align: center">
          <el-col :span="8">
            <el-pagination
                style="margin-top: 20px;margin-left: 40%"
                :current-page="currentPage"
                :hide-on-single-page="false"
                :page-size="pageSize"
                :total="this.total"
                layout="prev, pager, next"
                @current-change="handleCurrentChange">
            </el-pagination>
          </el-col>
        </el-row>

      </el-container>
    </div>
  </div>
</template>

<script>
import navBar from "../../components/nav";
import request, {plainRequest} from "@/api";
import router from "@/router";

export default {
  name: "answerList",
  inject: ['reload'],
  components: {
    navBar
  },
  created() {
    plainRequest.post('/selectProjectById', this.$route.query.id).then(res => {
      if (res.data != null && res.data !== '') {
        this.project.name = res.data.projectName;
        this.project.description = res.data.projectContent;
      }
    });
    this.search(1);
  },
  data() {
    return {
      searchKeyWord: '',//搜索关键字
      sort: 'desc',//排序方式（desc,asc）
      currentPage: 1,//当前页
      pageSize: 5,//每页显示多少条
      total: 0,//总条数
      project: {
        name: '项目名称',
        description: '项目描述',
        answerList: [],
      }
    };
  },
  methods: {
    changeTableSort(column) {
      this.sort = column.order;
      this.search(1);
    },
    handleCurrentChange(val) {
      this.currentPage = val;
      this.search(this.currentPage);
    },
    search(val) {
      this.currentPage = val;
      let data = {
        id: this.$route.query.id,
        searchKeyWord: this.searchKeyWord,
        sort: this.sort === 'ascending' ? 'asc' : 'desc',
        currentPage: this.currentPage,
        pageSize: this.pageSize
      };
      request.post('/selectAnswerByPage', data).then(res => {
        if (res.data != null && res.data !== '') {
          this.project.answerList = res.data;
          for (let i = 0; i < this.project.answerList.length; i++) {
            this.project.answerList[i].createDate = this.convertToGMT0(this.project.answerList[i].createDate);
            if (this.project.answerList[i].username === 'public') {
              this.project.answerList[i].username = '匿名用户';
            }
          }
        }
      });
      plainRequest.post('/selectAnswerCount', this.$route.query.id).then(res => {
        if (res.data != null && res.data !== '') {
          this.total = res.data;
        }
      });
    },
    toAnswer(row) {
      router.push({
        path: '/answer',
        query: {
          id: row.id,
          detail: true
        }
      });
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
  }
}
</script>

<style scoped>

:deep(input) {
  border: none;
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
}

#con {
  /* 顶栏高度 */
  margin: 5% auto auto;
  display: flex;
  justify-content: space-between;
  align-items: center;
  width: 60%;
  height: 88%;
}

#content {
  overflow-y: auto;
  flex-basis: calc(70% - 10px); /* 分别占据50%宽度，减去5像素的间隔 */
  height: 100%;
  background-color: #FFFFFF;
  display: block;
}

hr {
  margin-top: 20px;
  margin-bottom: 20px;
  background-color: #dddddd;
  color: #dddddd;
  border: none;
  height: 1px;
}

span {
  font-size: 16px;
}

.el-icon-search:hover {
  color: #207EFF;
}

.el-button {
  padding-top: 8px;
}

.project-name {
  text-align: center;
  margin: 30px 0 20px 0;
}

.name {
  font-size: 25px;
  font-weight: bold;
}

.project-description {
  font-size: 16px;
  margin-left: 40px;
  margin-bottom: 20px;
}

.row {
  margin-top: 40px;
}

.el-table {
  border: #C0C0C0 solid 1px;
}

</style>
