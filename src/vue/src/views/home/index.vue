<template>
  <div id="main">
    <nav-bar></nav-bar>
    <div id="content">
      <side-bar id="side"></side-bar>
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
  }
}
</script>

<style scoped>

#main {
  width: 100%;
  height: 100%;
  background-color: #eeeeee;
}

#side{
  margin-top: 40px;
  margin-left: 240px;
}

</style>