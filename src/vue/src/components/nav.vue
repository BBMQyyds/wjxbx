<template>
  <div id="all">
    <div class="header">
      <div class="box">
        <h1 @click="logout">
          <img src="../assets/img/icon/icon高清.png" alt="logo">
          <span>问卷星不星</span>
        </h1>
      </div>
      <nav>
        <img class="png icon-png" src="../assets/img/png/关于.png" alt="关于">
        <img class="png icon-png" src="../assets/img/png/文件夹.png" alt="文件夹">
        <img class="png icon-png" src="../assets/img/png/消息.png" alt="消息">
        <Avatar></Avatar>
      </nav>
    </div>
  </div>
</template>


<script>
import Avatar from "./avatar";
import router from "@/router";

export default {
  name: "nav",
  components: {
    Avatar
  },
  methods: {
    logout() {
      if (localStorage.getItem("auto") != null) {
        localStorage.removeItem("auto");
      }
      sessionStorage.removeItem("username");
      sessionStorage.removeItem("password");
      router.push({path: '/login'});
    }
  }
}
</script>

<style scoped>

#all {
  width: 100%;
  display: inline-block;
}

.header {
  display: flex;
  align-items: center;
  justify-content: space-between;
  height: 55px;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.2); /* 添加阴影效果 */
  background-color: #FFFFFF;
}

.box {
  display: flex;
  align-items: center;
  margin-left: 12%;
}

h1 {
  display: flex;
  align-items: center;
  font-size: 16px;
  font-weight: normal;
}

h1:hover {
  cursor: pointer;
}

img {
  width: 50px;
}

nav {
  display: flex;
  align-items: center;
  margin-right: 12%;
}


.icon-png {
  margin-right: 25px;
  display: inline-block;
  color: #999;
}

.icon-png:hover {
  color: #0085ff;
  box-shadow: 0 16px 32px 0 rgba(48, 55, 66, 0.15); /* 鼠标悬浮时盒子出现的阴影 */
  transform: translate(0, -3px); /* 鼠标悬浮时盒子上移10px */
}

Avatar:hover {
  color: #0085ff;
  box-shadow: 0 16px 32px 0 rgba(48, 55, 66, 0.15); /* 鼠标悬浮时盒子出现的阴影 */
  transform: translate(0, -3px); /* 鼠标悬浮时盒子上移10px */
}

.png {
  width: 30px;
  height: 30px;
}

.png:hover {
  cursor: pointer;
}

</style>
