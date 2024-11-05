const config = {
  get() {
    return {
      url:
        process.env.VUE_APP_BASE_API_URL + process.env.VUE_APP_BASE_API + '/',
      name: process.env.VUE_APP_BASE_API,
      // 退出到首页链接
      indexUrl: 'http://localhost:8080/onlineLearning/front/index.html',
    }
  },
  getProjectName() {
    return {
      projectName: '基于SpringBoot 的在线学习管理平台开发',
    }
  },
}
export default config
