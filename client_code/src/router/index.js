import { createRouter, createWebHashHistory } from 'vue-router'
import index from '../views'
import home from '../views/pages/home.vue'
import login from '../views/pages/login.vue'
import newsList from '@/views/pages/news/list'
import xueshengList from '@/views/pages/xuesheng/list'
import xueshengDetail from '@/views/pages/xuesheng/formModel'
import xueshengAdd from '@/views/pages/xuesheng/formAdd'
import xueshengRegister from '@/views/pages/xuesheng/register'
import xueshengCenter from '@/views/pages/xuesheng/center'
import jiaoshiList from '@/views/pages/jiaoshi/list'
import jiaoshiDetail from '@/views/pages/jiaoshi/formModel'
import jiaoshiAdd from '@/views/pages/jiaoshi/formAdd'
import banjiList from '@/views/pages/banji/list'
import banjiDetail from '@/views/pages/banji/formModel'
import banjiAdd from '@/views/pages/banji/formAdd'
import zuoyexinxiList from '@/views/pages/zuoyexinxi/list'
import zuoyexinxiDetail from '@/views/pages/zuoyexinxi/formModel'
import zuoyexinxiAdd from '@/views/pages/zuoyexinxi/formAdd'
import storeupList from '@/views/pages/storeup/list'
import zuoyetijiaoList from '@/views/pages/zuoyetijiao/list'
import zuoyetijiaoDetail from '@/views/pages/zuoyetijiao/formModel'
import zuoyetijiaoAdd from '@/views/pages/zuoyetijiao/formAdd'
import kaoshichengjiList from '@/views/pages/kaoshichengji/list'
import kaoshichengjiDetail from '@/views/pages/kaoshichengji/formModel'
import kaoshichengjiAdd from '@/views/pages/kaoshichengji/formAdd'
import zuoyeleixingList from '@/views/pages/zuoyeleixing/list'
import zuoyeleixingDetail from '@/views/pages/zuoyeleixing/formModel'
import zuoyeleixingAdd from '@/views/pages/zuoyeleixing/formAdd'
import kechengleixingList from '@/views/pages/kechengleixing/list'
import kechengleixingDetail from '@/views/pages/kechengleixing/formModel'
import kechengleixingAdd from '@/views/pages/kechengleixing/formAdd'
import kechengxinxiList from '@/views/pages/kechengxinxi/list'
import kechengxinxiDetail from '@/views/pages/kechengxinxi/formModel'
import kechengxinxiAdd from '@/views/pages/kechengxinxi/formAdd'
import kechengxuexiList from '@/views/pages/kechengxuexi/list'
import kechengxuexiDetail from '@/views/pages/kechengxuexi/formModel'
import kechengxuexiAdd from '@/views/pages/kechengxuexi/formAdd'
import exampaperList from '@/views/pages/exam/exampaperList'
import examination from '@/views/pages/exam/examination'
import examrecordList from '@/views/pages/exam/examrecordList'
import examfailrecord from '@/views/pages/exam/examfailrecord'

const routes = [{
		path: '/',
		redirect: '/index/home'
	},
	{
		path: '/index',
		component: index,
		children: [{
			path: 'home',
			component: home
		}
		, {
			path: 'newsList',
			component: newsList
		}
		, {
			path: 'xueshengList',
			component: xueshengList
		}, {
			path: 'xueshengDetail',
			component: xueshengDetail
		}, {
			path: 'xueshengAdd',
			component: xueshengAdd
		}
		, {
			path: 'xueshengCenter',
			component: xueshengCenter
		}
		, {
			path: 'jiaoshiList',
			component: jiaoshiList
		}, {
			path: 'jiaoshiDetail',
			component: jiaoshiDetail
		}, {
			path: 'jiaoshiAdd',
			component: jiaoshiAdd
		}
		, {
			path: 'banjiList',
			component: banjiList
		}, {
			path: 'banjiDetail',
			component: banjiDetail
		}, {
			path: 'banjiAdd',
			component: banjiAdd
		}
		, {
			path: 'zuoyexinxiList',
			component: zuoyexinxiList
		}, {
			path: 'zuoyexinxiDetail',
			component: zuoyexinxiDetail
		}, {
			path: 'zuoyexinxiAdd',
			component: zuoyexinxiAdd
		}
		, {
			path: 'storeupList',
			component: storeupList
		}
		, {
			path: 'zuoyetijiaoList',
			component: zuoyetijiaoList
		}, {
			path: 'zuoyetijiaoDetail',
			component: zuoyetijiaoDetail
		}, {
			path: 'zuoyetijiaoAdd',
			component: zuoyetijiaoAdd
		}
		, {
			path: 'kaoshichengjiList',
			component: kaoshichengjiList
		}, {
			path: 'kaoshichengjiDetail',
			component: kaoshichengjiDetail
		}, {
			path: 'kaoshichengjiAdd',
			component: kaoshichengjiAdd
		}
		, {
			path: 'zuoyeleixingList',
			component: zuoyeleixingList
		}, {
			path: 'zuoyeleixingDetail',
			component: zuoyeleixingDetail
		}, {
			path: 'zuoyeleixingAdd',
			component: zuoyeleixingAdd
		}
		, {
			path: 'kechengleixingList',
			component: kechengleixingList
		}, {
			path: 'kechengleixingDetail',
			component: kechengleixingDetail
		}, {
			path: 'kechengleixingAdd',
			component: kechengleixingAdd
		}
		, {
			path: 'kechengxinxiList',
			component: kechengxinxiList
		}, {
			path: 'kechengxinxiDetail',
			component: kechengxinxiDetail
		}, {
			path: 'kechengxinxiAdd',
			component: kechengxinxiAdd
		}
		, {
			path: 'kechengxuexiList',
			component: kechengxuexiList
		}, {
			path: 'kechengxuexiDetail',
			component: kechengxuexiDetail
		}, {
			path: 'kechengxuexiAdd',
			component: kechengxuexiAdd
		}
		, {
			path: 'exampaperList',
			component: exampaperList
		}, {
			path: 'examrecordList',
			component: examrecordList
		}, {
			path: 'examfailrecord',
			component: examfailrecord
		}
		]
	},
	{
		path: '/login',
		component: login
	}
	,{
		path: '/xueshengRegister',
		component: xueshengRegister
	}
	, {
		path: '/examination',
		component: examination
	}
]

const router = createRouter({
  history: createWebHashHistory(process.env.BASE_URL),
  routes
})

export default router
