	import {
		createRouter,
		createWebHashHistory
	} from 'vue-router'
	import news from '@/views/news/list'
	import xuesheng from '@/views/xuesheng/list'
	import examquestion from '@/views/exam/examquestion/list'
	import jiaoshi from '@/views/jiaoshi/list'
	import zuoyetijiao from '@/views/zuoyetijiao/list'
	import exampaper from '@/views/exampaper/list'
	import discusskechengxinxi from '@/views/discusskechengxinxi/list'
	import kechengxinxi from '@/views/kechengxinxi/list'
	import kechengleixing from '@/views/kechengleixing/list'
	import zuoyexinxi from '@/views/zuoyexinxi/list'
	import zuoyeleixing from '@/views/zuoyeleixing/list'
	import kaoshichengji from '@/views/kaoshichengji/list'
	import banji from '@/views/banji/list'
	import kechengxuexi from '@/views/kechengxuexi/list'
	import config from '@/views/config/list'
	import exampaperlist from '@/views/exam/exampaperlist/list'
	import examination from '@/views/exam/exampaperlist/examination'
	import examrecord from '@/views/exam/examrecord/list'
	import examfailrecord from '@/views/exam/examfailrecord/list'
	import jiaoshiRegister from '@/views/jiaoshi/register'
	import jiaoshiCenter from '@/views/jiaoshi/center'

export const routes = [{
		path: '/login',
		name: 'login',
		component: () => import('../views/login.vue')
	},{
		path: '/',
		name: '首页',
		component: () => import('../views/index'),
		children: [{
			path: '/',
			name: '首页',
			component: () => import('../views/HomeView.vue'),
			meta: {
				affix: true
			}
		}, {
			path: '/updatepassword',
			name: '修改密码',
			component: () => import('../views/updatepassword.vue')
		}
		
		,{
			path: '/jiaoshiCenter',
			name: '教师个人中心',
			component: jiaoshiCenter
		}
		,{
			path: '/news',
			name: '公告信息',
			component: news
		}
		,{
			path: '/xuesheng',
			name: '学生',
			component: xuesheng
		}
		,{
			path: '/examquestion',
			name: '试题管理',
			component: examquestion
		}
		,{
			path: '/jiaoshi',
			name: '教师',
			component: jiaoshi
		}
		,{
			path: '/zuoyetijiao',
			name: '作业提交',
			component: zuoyetijiao
		}
		,{
			path: '/exampaper',
			name: '试卷列表',
			component: exampaper
		}
		,{
			path: '/discusskechengxinxi',
			name: '课程信息评论',
			component: discusskechengxinxi
		}
		,{
			path: '/kechengxinxi',
			name: '课程信息',
			component: kechengxinxi
		}
		,{
			path: '/kechengleixing',
			name: '课程类型',
			component: kechengleixing
		}
		,{
			path: '/zuoyexinxi',
			name: '作业信息',
			component: zuoyexinxi
		}
		,{
			path: '/zuoyeleixing',
			name: '作业类型',
			component: zuoyeleixing
		}
		,{
			path: '/kaoshichengji',
			name: '考试成绩',
			component: kaoshichengji
		}
		,{
			path: '/banji',
			name: '班级',
			component: banji
		}
		,{
			path: '/kechengxuexi',
			name: '课程学习',
			component: kechengxuexi
		}
		,{
			path: '/config',
			name: '轮播图',
			component: config
		}
		, {
			path: '/exampaperlist',
			name: '考试列表',
			component: exampaperlist
		}, {
			path: '/examrecord',
			name: '考试记录',
			component: examrecord
		}, {
			path: '/examfailrecord',
			name: '错题本',
			component: examfailrecord
		}
		]
	},
	{
		path: '/jiaoshiRegister',
		name: '教师注册',
		component: jiaoshiRegister
	},
	{
		path: '/examination',
		name: '考试',
		component: examination
	},
]

const router = createRouter({
	history: createWebHashHistory(process.env.BASE_URL),
	routes
})

export default router
