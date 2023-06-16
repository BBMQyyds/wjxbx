<template>
    <div class="vertical-navbar">
      <el-menu :default-active="activeMenu" class="menu" @select="handleMenuSelect">
        <el-button class="buildButton" @click="buildQuestionnaire">
          <span id="plus">+</span>
          创建项目
        </el-button>
        <el-menu-item :class="{'active-item': activeMenu === 'home'}" index="home">
          <i class="el-icon-house"></i>
          <span>全部项目</span>
        </el-menu-item>
        <el-menu-item :class="{'active-item': activeMenu === 'star'}" index="star">
          <i class="el-icon-star-off"></i>
          <span>星标项目</span>
        </el-menu-item>
        <el-menu-item :class="{'active-item': activeMenu === 'files'}" index="files">
          <i class="el-icon-files"></i>
          <span>文件夹</span>
        </el-menu-item>
        <el-menu-item :class="{'active-item': activeMenu === 'delete-solid'}" index="delete-solid">
          <i class="el-icon-delete"></i>
          <span>回收站</span>
        </el-menu-item>
      </el-menu>
  </div>
</template>

<script>
export default {
  name: 'side',
  data() {
    return {
      activeMenu: 'home', // 默认选中的菜单项
    };
  },
  methods: {
    handleMenuSelect(index) {
      // 处理菜单项选择事件
      this.activeMenu = index; // 更新选中的菜单项
    },
    buildQuestionnaire() {
      // 创建问卷
      console.log('创建问卷');
    },
  },
};
</script>

<style scoped>

.vertical-navbar {
  width: 260px; /* 导航栏的宽度 */
  height: 60px;
}

.buildButton {
  text-align: center;
  width: 260px;
  height: 56px;
  font-size: 17px;
  margin-bottom: 20px;
  padding-top: 10px;
  color: white;
  font-weight: 550;
  background-color: #399ffd;
  border-radius: 2px;
}

.buildButton:hover {
  background-color: #3a8ee6;
}

#plus {
  font-size: 35px;
  font-weight: bold;
}


.menu {
  background-color: #eeeeee;
  border: none;
}

.el-menu-item {
  font-size: 16px;
  height: 40px;
  border-bottom: #dddddd 1px solid;
}

.active-item {
  background-color: #dddddd; /* 设置选中时的背景颜色 */
  font-weight: bold; /* 设置选中时的字体加粗 */
  color: #000000;
}

</style>
