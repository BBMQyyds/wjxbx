<template>
  <div id="app">
      <NavigationBar></NavigationBar>
    <router-view v-if="isRouterAlive"/>
  </div>
</template>

<script>
    import NavigationBar from './components/NavigationBar.vue';
export default {
  name: 'App',
  provide() {
    return {
      reload: this.reload
    }
  },
  data() {
    return {
      isRouterAlive: true,
    }
  },
  methods: {
    reload() {
      this.isRouterAlive = false;
      this.$nextTick(function () {
        this.isRouterAlive = true;
      });
    }
  },
  components:{
    NavigationBar
  }
}
</script>

<style scoped>
</style>
