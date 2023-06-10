const {defineConfig} = require('@vue/cli-service')

module.exports = defineConfig({
    publicPath: './',
    transpileDependencies: true,
    outputDir: 'wjxbx',
    devServer: {
        port: 80,
        proxy: 'http://localhost:8088',
    }
})