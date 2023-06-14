<template>
  <div>
    <div class="header">
      <div class="box">
        <h1>
          <img src="../assets/img/logo高清.png" alt="logo" @click="logout">
          <span>问卷星不星</span>
        </h1>
      </div>
      <nav>
        <el-tooltip class="item" effect="dark" content="查看消息" placement="bottom-end">
          <svg class="icon-link icon-pinglun" width="200px" height="200.00px" viewBox="0 0 1024 1024" version="1.1"
               xmlns="http://www.w3.org/2000/svg" aria-label="评论">
            <path
                d="M932.059429 102.4c24.722286 0 44.763429 20.041143 44.763428 44.763429v640a44.836571 44.836571 0 0 1-44.763428 44.763428H648.118857L545.865143 923.062857a44.836571 44.836571 0 0 1-60.489143-0.804571l-96.109714-90.331429H100.059429a44.836571 44.836571 0 0 1-44.836572-44.763428v-640c0-24.722286 20.114286-44.836571 44.836572-44.836572h832z m-44.836572 89.526857H144.822857v550.4h262.217143c11.410286 0 22.381714 4.388571 30.72 12.141714l79.067429 74.386286 84.406857-75.190857a44.836571 44.836571 0 0 1 29.842285-11.337143h256.146286V191.926857zM314.514286 403.163429a65.828571 65.828571 0 1 1 0 131.657142 65.828571 65.828571 0 0 1 0-131.657142z m197.485714 0a65.828571 65.828571 0 1 1 0 131.657142 65.828571 65.828571 0 0 1 0-131.657142z m197.485714 0a65.828571 65.828571 0 1 1 0 131.657142 65.828571 65.828571 0 0 1 0-131.657142z"/>
          </svg>
        </el-tooltip>
        <el-tooltip class="item" effect="dark" content="查看答卷提示" placement="bottom-end">
          <svg class="icon-link icon-youjian" width="200px" height="200.00px" viewBox="0 0 1024 1024"
               version="1.1"
               xmlns="http://www.w3.org/2000/svg" aria-label="邮件">
            <path
                d="M919.04 176.859429c24.722286 0 44.836571 20.041143 44.836571 44.836571v650.825143a44.763429 44.763429 0 0 1-44.836571 44.763428H100.278857a44.836571 44.836571 0 0 1-44.836571-44.763428V221.622857c0-24.795429 20.041143-44.836571 44.836571-44.836571h818.761143z m-44.763429 89.6h-729.234285v561.225142h729.234285V266.459429z m-128 47.177142a44.836571 44.836571 0 0 1 45.933715 76.946286l-257.243429 153.6a44.763429 44.763429 0 0 1-45.641143 0.146286l-261.412571-153.6a44.763429 44.763429 0 1 1 45.348571-77.238857l238.592 140.141714z"/>
          </svg>
        </el-tooltip>
        <el-tooltip class="item" effect="dark" content="查看日历" placement="bottom-end">
          <svg class="icon-link icon-riqi" aria-hidden="true" xmlns="http://www.w3.org/2000/svg"
               viewBox="0 0 1024 1024" aria-label="日期">
            <path
                d="M667.062857 54.857143c24.722286 0 44.763429 20.041143 44.763429 44.763428v47.542858h184.173714c24.722286 0 44.836571 20.114286 44.836571 44.836571v704a44.836571 44.836571 0 0 1-44.836571 44.836571H128a44.836571 44.836571 0 0 1-44.836571-44.836571V192c0-24.722286 20.114286-44.763429 44.836571-44.763429h152.064v-47.616c0-22.235429 16.237714-40.740571 37.522286-44.251428l7.314285-0.585143c24.722286 0 44.763429 20.114286 44.763429 44.836571v47.542858h252.562286v-47.542858c0-22.235429 16.310857-40.740571 37.595428-44.251428z m184.173714 401.334857H172.763429v394.971429h678.326857v-394.971429z m-233.398857 61.074286a38.4 38.4 0 1 1 55.076572 53.467428l-175.908572 181.248a38.4 38.4 0 0 1-55.588571-0.512L339.382857 642.633143a38.4 38.4 0 0 1 55.954286-52.516572l74.605714 79.506286zM280.064 236.763429H172.690286v129.828571h678.4V236.836571H711.826286V256a44.836571 44.836571 0 0 1-37.522286 44.178286l-7.314286 0.585143a44.763429 44.763429 0 0 1-44.763428-44.763429v-19.236571H369.590857l0.073143 19.236571a44.836571 44.836571 0 0 1-37.522286 44.178286l-7.314285 0.585143A44.763429 44.763429 0 0 1 280.137143 256v-19.236571z"/>
          </svg>
        </el-tooltip>
      </nav>
    </div>
    <div class="contents">
      <Avatar></Avatar>
    </div>
  </div>
</template>


<script>
import Avatar from "./avatar";
import router from "@/router";

export default {
  name: "BBSHeader",
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
.header {
  display: flex;
  align-items: center;
  justify-content: space-between;
  height: 65px;
  background-color: #f7f9fc;
  color: #100000;
  /*吸顶效果*/
  /* position: sticky;
   position: -webkit-sticky;    !*兼容 -webkit 内核的浏览器*!
   top: 0px;                    !*必须设一个值，否则不生效*!*/

  /*    阴影效果*/
  box-shadow: 0px 2px 4px rgba(0, 0, 0, 0.2); /* 添加阴影效果 */
}

.box {
  display: flex;
  align-items: center;
  margin-left: 160px;
}

h1 {
  display: flex;
  align-items: center;
  font: normal 28px Cookie, Arial, Helvetica, sans-serif;
  padding: 0px 20px;
}

img {
  width: 40px;
  height: 40px;
}

nav {
  display: flex;
  align-items: center;
  font: 16px Arial, Helvetica, sans-serif;
  margin-right: 180px;
}

nav a {
  padding: 0 15px;
  width: 32px;
  text-decoration: none;
  color: #050000;
  font-size: 16px;
  font-weight: normal;
  opacity: 0.9;
}

nav a:hover {
  opacity: 1;
}

.active {
  color: #608bd2;
  pointer-events: none;
  opacity: 1;
}

/*搜索框*/

.text {
  height: 22px;
  font-size: 14px;
  border: 1px solid #050000;
  padding: 3px 16px;
  border-bottom-left-radius: 20px;
  border-top-left-radius: 20px;
}

.text:focus {
  outline: none;
  border-color: rgba(82, 168, 236, 0.8);
  box-shadow: inset 0 2px 2px rgba(0, 0, 0, 0.075), 0 0 8px rgba(82, 168, 236, 0.6);
}

.button {
  width: 60px;
  height: 30px;
  font-size: 14px;
  margin-right: 35px;
  border: 1px solid #608bd2;
  background-color: #608bd2;
  border-top-right-radius: 20px;
  border-bottom-right-radius: 20px;
}

.contents {
  position: relative;
  display: flex;
  justify-content: center;
  margin-left: 1550px;
  margin-top: -50px;
}

.content {
  display: flex;
  width: 1400px;
  height: 1400px;
  /*background-color: #f0f2f3;*/
}

.icon-link {
  display: inline-block;
  margin-top: 3px;
  margin-right: 45px;
  color: #999;
}

.icon-link:hover {
  color: #0085ff;
  box-shadow: 0 16px 32px 0 rgba(48, 55, 66, 0.15); /* 鼠标悬浮时盒子出现的阴影 */
  transform: translate(0, -3px); /* 鼠标悬浮时盒子上移10px */
  /*    增加悬浮字*/
  text-decoration: underline;
}


.icon-link:hover::after {
  content: attr(aria-label);
  position: absolute;
  top: -30px; /* 调整悬浮字的垂直位置 */
  left: 50%; /* 调整悬浮字的水平位置 */
  transform: translateX(-50%);
  padding: 4px 8px;
  background-color: #000;
  color: #fff;
  font-size: 12px;
  border-radius: 4px;
}


.icon-message:before {
  content: "\f0e0"; /* 使用字体图标库的代码，如Font Awesome */
  font-family: "Font Awesome", serif;
}

.icon-message {
  width: 30px;
  height: 30px;
}

.icon-communication {
  width: 30px;
  height: 30px;
}

svg {
  width: 30px;
  height: 30px;
  /*    让它可视*/
  fill: currentColor;
}
</style>
