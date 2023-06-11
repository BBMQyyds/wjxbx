const {defineConfig} = require('@vue/cli-service')

module.exports = defineConfig({
    publicPath: './',
    transpileDependencies: true,
    outputDir: 'wjxbx',
    devServer: {
        port: 80,
        proxy: 'http://localhost:8088',
    },
    chainWebpack: (config) => {
        const svgRule = config.module.rule('svg');

        // 清除已有的所有加载器
        // 如果你只想处理svg文件，可以删除下一行代码
        svgRule.uses.clear();

        // 添加要使用的加载器
        svgRule
            .use('svg-sprite-loader')
            .loader('svg-sprite-loader')
            .options({
                symbolId: 'icon-[name]',
            });
    },
})
