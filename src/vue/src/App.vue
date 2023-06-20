<template>
  <div id="app">
    <router-view v-if="isRouterAlive" v-wechat-title="$route.meta.title"/>
  </div>
</template>

<script>
import moment from 'moment'

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
    },
    // 编写日期格式化方法
    dateFormat: function (row, column) {
      console.log(row, column)
      const date = row[column.property]
      if (date === undefined) {
        return ''
      }
      // 这里的格式根据需求修改
      return moment(date).format('YYYY-MM-DD HH:mm:ss')
    }
  },
}
</script>

<style>

</style>
