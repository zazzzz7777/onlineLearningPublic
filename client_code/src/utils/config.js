const config = {
  get() {
    return {
      url:
        process.env.VUE_APP_BASE_API_URL + process.env.VUE_APP_BASE_API + '/',
      name: process.env.VUE_APP_BASE_API,
      menuList: [
        {
          name: '作业信息管理',
          icon: '${frontMenu.fontClass}',
          child: [
            {
              name: '作业信息',
              url: '/index/zuoyexinxiList',
            },
          ],
        },
        {
          name: '我的收藏管理',
          icon: '${frontMenu.fontClass}',
          child: [
            {
              name: '我的收藏',
              url: '/index/storeupList',
            },
          ],
        },
        {
          name: '考试管理',
          icon: '${frontMenu.fontClass}',
          child: [
            {
              name: '试卷列表',
              url: '/index/exampaperList',
            },
          ],
        },
        {
          name: '课程信息管理',
          icon: '${frontMenu.fontClass}',
          child: [
            {
              name: '课程信息',
              url: '/index/kechengxinxiList',
            },
          ],
        },
        {
          name: '新闻资讯管理',
          icon: '${frontMenu.fontClass}',
          child: [
            {
              name: '公告信息',
              url: '/index/newsList',
            },
          ],
        },
      ],
    }
  },
  getProjectName() {
    return {
      projectName: '基于SpringBoot的在线学习管理平台',
    }
  },
}
export default config
