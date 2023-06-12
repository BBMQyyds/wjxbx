<template>
    <div>
    <div>
        <el-button class="buildButton" @click="buildQuestionnaire">
            <span id="plus">+</span>
            创建问卷
        </el-button>
    </div>
    <div class="vertical-navbar">
        <el-menu :default-active="activeMenu" class="menu" @select="handleMenuSelect">
            <el-menu-item index="home" :class="{'active-item': activeMenu === 'home'}">
                <i class="el-icon-house"></i>
                <span>全部问卷</span>
            </el-menu-item>
            <el-menu-item index="star" :class="{'active-item': activeMenu === 'star'}">
                <i class="el-icon-star-off"></i>
                <span>星标问卷</span>
            </el-menu-item>
            <el-menu-item index="files" :class="{'active-item': activeMenu === 'files'}">
                <i class="el-icon-files"></i>
                <span>文件夹</span>
            </el-menu-item>
            <el-menu-item index="delete-solid" :class="{'active-item': activeMenu === 'delete-solid'}">
                <i class="el-icon-delete-solid"></i>
                <span>回收站</span>
            </el-menu-item>
        </el-menu>
    </div>
    </div>
</template>

<script>
    export default {
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
        width: 275px; /* 导航栏的宽度 */
    }

    .buildButton{
        text-align: left;
        padding-left: 72px;
        width: 275px;
        height: 65px;
        font-size: 23px;
        margin-bottom: 40px;
        color: white;
        font-weight: bold;
        background-color: #399ffd;
    }

    .menu {
    }
    .el-menu-item{

        font-size: larger;
        height: 45px;
        line-height: 50px;
    }

    #plus{

        font-size: 40px;
        font-weight: bold;
    }
    .active-item {
        background-color: #f5f5f5; /* 设置选中时的背景颜色 */
        font-weight: bold; /* 设置选中时的字体加粗 */
    }

</style>
