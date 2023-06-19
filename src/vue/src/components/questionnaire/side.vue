<template>
  <el-menu :default-active="activeMenu" class="menu" @select="handleMenuSelect">
    <el-button class="createButton" @click="createQuestionnaire">
      <span id="plus">+</span>
      创建问卷
    </el-button>
    <el-menu-item :class="{'active-item': activeMenu === 'home'}" index="home">
      <i class="el-icon-house"></i>
      <span>全部问卷</span>
    </el-menu-item>
    <el-menu-item :class="{'active-item': activeMenu === 'star'}" index="star">
      <i class="el-icon-star-off"></i>
      <span>星标问卷</span>
    </el-menu-item>
    <el-menu-item :class="{'active-item': activeMenu === 'delete-solid'}" index="delete">
      <i class="el-icon-delete"></i>
      <span>回收站</span>
    </el-menu-item>
  </el-menu>
</template>

<script>
export default {
  name: 'side',
  emits: ['select','create'], // 声明事件
  data() {
    return {
      activeMenu: 'home', // 默认选中的菜单项
    };
  },
  methods: {
    handleMenuSelect(index) {
      // 处理菜单项选择事件
      this.activeMenu = index; // 更新选中的菜单项
      this.$emit('select', index); // 通过事件向父组件传递选中的菜单项
    },
    createQuestionnaire() {
      // 创建问卷
      console.log('create questionnaire');
      this.$emit('create', 'questionnaire'); // 通过事件向父组件传递创建问卷的事件
    },
  },
};
</script>

<style scoped>

.createButton {
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

.createButton:hover {
  background-color: #3a8ee6;
}

#plus {
  font-size: 35px;
  font-weight: bold;
}


.menu {
  width: 260px; /* 导航栏的宽度 */
  background-color: #eeeeee;
  border: none;
}

.el-menu-item {
  width: 260px;
  font-size: 16px;
  height: 50px;
  border-bottom: #dddddd 1px solid;
}

.active-item {
  background-color: #dddddd; /* 设置选中时的背景颜色 */
  font-weight: bold; /* 设置选中时的字体加粗 */
  color: #000000;
}

</style>
