module.exports = {
    chainWebpack: config => {
        config.module.rules.delete('eslint');
    },
    
    devServer: {
        port: 3000,
    }
}