<template>
    <div>
        <div class="header">
            <div class="box">
                <h1>
                    <img src="../assets/lanlan.jpg" alt="logo">
                    <span>问卷星不行</span>
                </h1>
            </div>
            <div class="box">
                <input class="text" type="text" placeholder="请输入关键词~~~" name="search">
                <input class="button" type="button" value="搜索">
                <nav>
                    <svg t="1686457624818"
                         class="icon-message"
                         viewBox="0 0 1024 1024"
                         version="1.1"
                         xmlns="http://www.w3.org/2000/svg"
                         p-id="879"
                         @click="handleIconClick"
                         @mouseover="handleIconMouseOver"
                         @mouseleave="handleIconMouseLeave"
                    >
                        <path d="M932.059429 102.4c24.722286 0 44.763429 20.041143 44.763428 44.763429v640a44.836571 44.836571 0 0 1-44.763428 44.763428H648.118857L545.865143 923.062857a44.836571 44.836571 0 0 1-60.489143-0.804571l-96.109714-90.331429H100.059429a44.836571 44.836571 0 0 1-44.836572-44.763428v-640c0-24.722286 20.114286-44.836571 44.836572-44.836572h832z m-44.836572 89.526857H144.822857v550.4h262.217143c11.410286 0 22.381714 4.388571 30.72 12.141714l79.067429 74.386286 84.406857-75.190857a44.836571 44.836571 0 0 1 29.842285-11.337143h256.146286V191.926857zM314.514286 403.163429a65.828571 65.828571 0 1 1 0 131.657142 65.828571 65.828571 0 0 1 0-131.657142z m197.485714 0a65.828571 65.828571 0 1 1 0 131.657142 65.828571 65.828571 0 0 1 0-131.657142z m197.485714 0a65.828571 65.828571 0 1 1 0 131.657142 65.828571 65.828571 0 0 1 0-131.657142z" p-id="880"></path></svg>
                    <svg
                            t="1686459476169"
                            class="icon-communication"
                            viewBox="0 0 1024 1024"
                            version="1.1"
                            xmlns="http://www.w3.org/2000/svg"
                            p-id="879"
                            width="200"
                            height="200"
                            @click="handleIconClick"
                            @mouseover="handleIconMouseOver"
                            @mouseleave="handleIconMouseLeave"
                            :style="{ color: iconCommunicationColor, cursor: iconCommunicationCursor }"
                    >
                        <!-- SVG 图标内容 -->
                    </svg>
                </nav>
            </div>
        </div>
        <div class="contents">
            <div class="content">
                <!--指定组件的位置-->
                <router-view></router-view>
            </div>
        </div>
    </div>
</template>


<script>
    export default {
        name: "BBSHeader",
        data() {
            return {
                iconMessageColor: "#999",
                iconCommunicationColor: "#999",
                iconMessageCursor: "pointer",
                iconCommunicationCursor: "pointer",
            };
        },
        methods: {
            handleIconClick() {
                // 处理图标点击事件，例如路由跳转
                // 使用 this.$router.push() 或者 this.$router.replace() 实现路由跳转
            },
            handleIconMouseOver(icon) {
                // 处理图标鼠标悬浮事件，例如修改颜色
                if (icon === "message") {
                    this.iconMessageColor = "#0085ff"; // 修改图标颜色
                } else if (icon === "communication") {
                    this.iconCommunicationColor = "#0085ff"; // 修改图标颜色
                }
            },
            handleIconMouseLeave(icon) {
                // 处理图标鼠标离开事件，例如恢复默认颜色
                if (icon === "message") {
                    this.iconMessageColor = "#999"; // 恢复默认图标颜色
                } else if (icon === "communication") {
                    this.iconCommunicationColor = "#999"; // 恢复默认图标颜色
                }
            },
        },
    }
</script>

<style scoped>
    .header{
        display: flex;
        align-items: center;
        justify-content: space-between;
        height:50px;
        background-color: #f7f9fc;
        color: #100000;
        /*吸顶效果*/
        /* position: sticky;
         position: -webkit-sticky;    !*兼容 -webkit 内核的浏览器*!
         top: 0px;                    !*必须设一个值，否则不生效*!*/

    }
    .box{
        display: flex;
        align-items: center;
        margin-left: 160px;
    }
    h1{
        display: flex;
        align-items: center;
        font: normal 28px Cookie, Arial, Helvetica, sans-serif;
        padding: 0px 20px;
    }
    img{
        width: 40px;
        height: 40px;
    }

    nav {
        display: flex;
        align-items: center;
        margin: 0px 40px;
        font:16px Arial, Helvetica, sans-serif;
    }
    nav a{
        padding: 0 15px;
        width: 32px;
        text-decoration:none;
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

    .text{
        height: 22px;
        font-size: 14px;
        border: 1px solid #050000;
        padding: 3px 16px;
        border-bottom-left-radius: 20px;
        border-top-left-radius: 20px;
    }
    .text:focus{
        outline: none;
        border-color: rgba(82, 168, 236, 0.8);
        box-shadow: inset 0 2px 2px rgba(0, 0, 0, 0.075), 0 0 8px rgba(82, 168, 236, 0.6);
    }
    .button{
        width: 60px;
        height: 30px;
        font-size: 14px;
        margin-right: 35px;
        border: 1px solid #608bd2;
        background-color: #608bd2;
        border-top-right-radius: 20px;
        border-bottom-right-radius: 20px;
    }

    .contents{
        display: flex;
        justify-content: center;
    }
    .content{
        display: flex;
        width: 1400px;
        height: 1400px;
        /*background-color: #f0f2f3;*/
    }
    .icon-link {
        display: inline-block;
        margin-right: 10px;
        color: #999;
    }

    .icon-link:hover {
        color: #0085ff;
    }

    .icon-message:before {
        content: "\f0e0"; /* 使用字体图标库的代码，如Font Awesome */
        font-family: "Font Awesome";
    }
    .icon-message{
        width: 30px;
        height: 30px;
    }
    .icon-communication{
        width: 30px;
        height: 30px;
        margin-left: 50px;
        margin-right: 50px;
    }

</style>
