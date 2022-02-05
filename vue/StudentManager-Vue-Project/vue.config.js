module.exports = {
    outputDir: 'dist',  // 输出文件目录
    devServer: {
        //baseUrl: "http://localhost:8080/", //  用用程序默认部署位置，‘/’根目录，存放子目录ex：‘/web/’
        port: 8334,
        proxy: {
            '/api': {
                'target': 'http://localhost:8080/api',
                ws: true,
                changeOrigin: true,//允许跨域
                pathRewrite: {
                    '^/api':' '
                }
            }
        }
    }
};
