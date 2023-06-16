<template>
  <el-row class="block-col-2">
    <el-col :span="12">
      <el-dropdown>
        <span class="el-dropdown-link">
          <div class="demo-basic--circle">
            <div class="block">
              <el-avatar :size="50" :src="circleUrl"></el-avatar>
            </div>
          </div>
        </span>
        <el-dropdown-menu slot="dropdown">
          <el-dropdown-item icon="el-icon-search" @click.native="look_message">
            查看个人信息
          </el-dropdown-item>
          <el-dropdown-item icon="el-icon-circle-check" @click.native="handle_Logout">
            退出登录
          </el-dropdown-item>
        </el-dropdown-menu>
      </el-dropdown>
    </el-col>
  </el-row>
</template>
<script>
  export default {
    created() {
      // 获取登录数据
    },
    data() {
      return {
        circleUrl: "https://cube.elemecdn.com/3/7c/3ea6beec64369c2642b92c6726f1epng.png",
        squareUrl: "https://cube.elemecdn.com/9/c2/f0ee8a3c7c9638a54940382568c9dpng.png",
        sizeList: ["large"],
      };
    },
    methods: {
      handle_Logout() {
      },
      look_message() {
      }
    },
  };
</script>
<style scoped>
  .el-dropdown-link {
    cursor: pointer;
    color: #409eff;
  }
  .el-icon-arrow-down {
    font-size: 12px;
  }
  .demonstration {
    display: block;
    color: #8492a6;
    font-size: 14px;
    margin-bottom: 20px;
  }
</style>
