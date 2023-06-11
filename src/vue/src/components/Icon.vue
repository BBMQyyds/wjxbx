<template>
    <div class="icon-wrapper">
        <svg class="icon" aria-hidden="true">
            <use :xlink:href="iconName"></use>
        </svg>
    </div>
</template>

<script>
    // 引入从iconfont 下载的symbox文件
    import '@/assets/icons/iconfont-svg.js'
    // 引入本地的svg文件
    // 定义一个加载目录的函数
    const requireAll = requireContext => requireContext.keys().map(requireContext)
    const req = require.context('@/assets/icons/svg', false, /\.svg$/)
    // 加载目录下的所有的 svg 文件
    requireAll(req)

    export default {
        name: 'Icon',
        props: {
            name: String,
            prefix: {
                type: String,
                default: 'icon-'
            }
        },
        computed: {
            iconName(){
                return `#${this.prefix}${this.name}`
            }
        }
    }
</script>

<style lang="scss" scoped>
  .icon-wrapper {
    display: inline-block;
  }
  .icon {
    width: 100%;
    height: 100%;
    vertical-align: -0.15em;
    fill: currentColor;
    overflow: hidden;
  }
</style>
