
<template>
	<div>
		<div class="app-contain">
			<div class="list_search_view">
				<el-form :model="searchQuery" class="search_form" >
					<div class="search_view">
						<div class="search_label">
							作业名称：
						</div>
						<div class="search_box">
							<el-input class="search_inp" v-model="searchQuery.zuoyemingcheng" placeholder="作业名称"
								clearable>
							</el-input>
						</div>
					</div>
					<div class="search_view">
						<div class="search_label">
							教师工号：
						</div>
						<div class="search_box">
							<el-input class="search_inp" v-model="searchQuery.jiaoshigonghao" placeholder="教师工号"
								clearable>
							</el-input>
						</div>
					</div>
					<div class="search_view">
						<div class="search_label">
							班级：
						</div>
						<div class="search_box">
							<el-select
								class="search_sel"
								clearable
								v-model="searchQuery.banji" 
								placeholder="班级"
								>
								<el-option v-for="item in banjiLists" :label="item" :value="item"></el-option>
							</el-select>
						</div>
					</div>
					<div class="search_btn_view">
						<el-button class="search_btn" type="primary" @click="searchClick()" size="small">搜索</el-button>
					</div>
				</el-form>
				<br>
				<div class="btn_view">
					<el-button type="success" @click="addClick" v-if="btnAuth('zuoyexinxi','新增')">新增</el-button>
					<el-button  v-if=" btnAuth('zuoyexinxi','查看')" type="info"  :disabled="selRows.length==1?false:true" @click="infoClick(null)">详情</el-button>
					<el-button type="primary" :disabled="selRows.length==1?false:true" @click="editClick" v-if=" btnAuth('zuoyexinxi','修改')">修改</el-button>
					<el-button type="danger" :disabled="selRows.length?false:true" @click="delClick(null)"  v-if="btnAuth('zuoyexinxi','删除')">删除</el-button>
				</div>
			</div>
			<br>
			<el-table
				v-loading="listLoading"
				border 
				:stripe='true'
				@selection-change="handleSelectionChange" 
				ref="table"
				v-if="btnAuth('zuoyexinxi','查看')"
				:data="list"
				@row-click="listChange">
				<el-table-column :resizable='true' align="left" header-align="center" type="selection" width="55" />
				<el-table-column label="序号" width="70" :resizable='true' :sortable='true' align="left" header-align="center">
					<template #default="scope">{{ scope.$index + 1}}</template>
				</el-table-column>
				<el-table-column
					 :resizable='true' 
					 :sortable='true' 
					 align="left" 
					 header-align="center"
					label="作业名称">
					<template #default="scope">
						{{scope.row.zuoyemingcheng}}
					</template>
				</el-table-column>
				<el-table-column
					 :resizable='true' 
					 :sortable='true' 
					 align="left" 
					 header-align="center"
					label="作业类型">
					<template #default="scope">
						{{scope.row.zuoyeleixing}}
					</template>
				</el-table-column>
				<el-table-column label="封面" width="120" :resizable='true' :sortable='true' align="left" header-align="center">
					<template #default="scope">
						<div v-if="scope.row.fengmian">
							<el-image v-if="scope.row.fengmian.substring(0,4)=='http'" preview-teleported
								:preview-src-list="[scope.row.fengmian.split(',')[0]]"
								:src="scope.row.fengmian.split(',')[0]" style="width:100px;height:100px"></el-image>
							<el-image v-else preview-teleported
								:preview-src-list="[$config.url+scope.row.fengmian.split(',')[0]]"
								:src="$config.url+scope.row.fengmian.split(',')[0]" style="width:100px;height:100px">
							</el-image>
						</div>
						<div v-else>无图片</div>
					</template>
				</el-table-column>
				<el-table-column
					 :resizable='true' 
					 :sortable='true' 
					 align="left" 
					 header-align="center"
					label="发布时间">
					<template #default="scope">
						{{scope.row.fabushijian}}
					</template>
				</el-table-column>
				<el-table-column
					 :resizable='true' 
					 :sortable='true' 
					 align="left" 
					 header-align="center"
					label="教师工号">
					<template #default="scope">
						{{scope.row.jiaoshigonghao}}
					</template>
				</el-table-column>
				<el-table-column
					 :resizable='true' 
					 :sortable='true' 
					 align="left" 
					 header-align="center"
					label="教师姓名">
					<template #default="scope">
						{{scope.row.jiaoshixingming}}
					</template>
				</el-table-column>
				<el-table-column
					 :resizable='true' 
					 :sortable='true' 
					 align="left" 
					 header-align="center"
					label="班级">
					<template #default="scope">
						{{scope.row.banji}}
					</template>
				</el-table-column>
				<el-table-column label="操作" width="300" :resizable='true' :sortable='true' align="left" header-align="center">
					<template #default="scope">
						<el-button type="info" v-if=" btnAuth('zuoyexinxi','查看')" @click="infoClick(scope.row.id)">详情</el-button>
						<el-button v-if="btnAuth('zuoyexinxi','提交')" type="success" @click="zuoyetijiaoCrossAddOrUpdateHandler(scope.row,'cross','','','')">提交</el-button>
					</template>
				</el-table-column>
			</el-table>
			<el-pagination 
				background
				:layout="layouts.join(',')"
				:total="total" 
				:page-size="listQuery.limit"
				prev-text="<"
				next-text=">"
				:hide-on-single-page="true"
				:style='{"padding":"0","margin":"20px 0 0","whiteSpace":"nowrap","color":"#333","textAlign":"center","width":"100%","fontWeight":"500"}'
				@size-change="sizeChange"
				@current-change="currentChange" 
				@prev-click="prevClick"
				@next-click="nextClick"  />
		</div>
		<formModel ref="formRef" @formModelChange="formModelChange"></formModel>
		<zuoyetijiaoFormModel ref="zuoyetijiaoFormModelRef" @formModelChange="formModelChange"></zuoyetijiaoFormModel>
	</div>
</template>
<script setup>
	import axios from 'axios'
	import {
		reactive,
		ref,
		getCurrentInstance,
		nextTick,
		onMounted,
		watch,
	} from 'vue'
	import {
		useRoute,
		useRouter
	} from 'vue-router'
	import {
		ElMessageBox
	} from 'element-plus'
	const context = getCurrentInstance()?.appContext.config.globalProperties;
	import formModel from './formModel.vue'
	
	//基础信息
	const tableName = 'zuoyexinxi'
	const formName = '作业信息'
	const route = useRoute()
	//基础信息
	onMounted(()=>{
	})
	//列表数据
	const list = ref(null)
	const table = ref(null)
	const listQuery = ref({
		page: 1,
		limit: 20,
		sort: 'id',
		order: 'desc'
	})
	const searchQuery = ref({})
	const selRows = ref([])
	const listLoading = ref(false)
	const listChange = (row) =>{
		nextTick(()=>{
			table.value.clearSelection()
			table.value.toggleRowSelection(row)
		})
	}
	//列表
	const getList = () => {
		listLoading.value = true
		let params = JSON.parse(JSON.stringify(listQuery.value))
		params['sort'] = 'id'
		params['order'] = 'desc'
		if(searchQuery.value.zuoyemingcheng&&searchQuery.value.zuoyemingcheng!=''){
			params['zuoyemingcheng'] = '%' + searchQuery.value.zuoyemingcheng + '%'
		}
		params['sort'] = 'fabushijian';
		params['order'] = 'desc';
		if(searchQuery.value.jiaoshigonghao&&searchQuery.value.jiaoshigonghao!=''){
			params['jiaoshigonghao'] = '%' + searchQuery.value.jiaoshigonghao + '%'
		}
		if(searchQuery.value.banji&&searchQuery.value.banji!=''){
			params['banji'] = searchQuery.value.banji
		}
		context?.$http({
			url: `${tableName}/page`,
			method: 'get',
			params: params
		}).then(res => {
			listLoading.value = false
			list.value = res.data.data.list
			total.value = Number(res.data.data.total)
		})
	}
	//删
	const delClick = (id) => {
		let ids = ref([])
		if (id) {
			ids.value = [id]
		} else {
			if (selRows.value.length) {
				for (let x in selRows.value) {
					ids.value.push(selRows.value[x].id)
				}
			} else {
				return false
			}
		}
		ElMessageBox.confirm(`是否删除选中${formName}`, '提示', {
			confirmButtonText: '是',
			cancelButtonText: '否',
			type: 'warning',
		}).then(() => {
			context?.$http({
				url: `${tableName}/delete`,
				method: 'post',
				data: ids.value
			}).then(res => {
				context?.$toolUtil.message('删除成功', 'success',()=>{
					getList()
				})
			})
		})
	}
	//多选
	const handleSelectionChange = (e) => {
		selRows.value = e
	}
	//列表数据
	//分页
	const total = ref(0)
	const layouts = ref(["total","prev","pager","next","jumper"])
	const sizeChange = (size) => {
		listQuery.value.limit = size
		getList()
	}
	const currentChange = (page) => {
		listQuery.value.page = page
		getList()
	}
	const prevClick = () => {
		listQuery.value.page = listQuery.value.page - 1
		getList()
	}
	const nextClick = () => {
		listQuery.value.page = listQuery.value.page + 1
		getList()
	}
	//分页
	//权限验证
	const btnAuth = (e,a)=>{
		return context?.$toolUtil.isAuth(e,a)
	}
	//搜索
	const banjiLists = ref([])
	const getbanjiLists = () => {
		context?.$http({
			url: 'option/banji/banji',
			method:'get'
		}).then(res=>{
			banjiLists.value = res.data.data
		})
	}
	getbanjiLists()
	const searchClick = () => {
		listQuery.value.page = 1
		getList()
	}
	//表单
	const formRef = ref(null)
	const formModelChange=()=>{
		searchClick()
	}
	const addClick = ()=>{
		formRef.value.init()
	}
	const editClick = ()=>{
		if(selRows.value.length){
			formRef.value.init(selRows.value[0].id,'edit')
		}
	}
	
	const infoClick = (id=null)=>{
		if(id){
			formRef.value.init(id,'info')
		}
		else if(selRows.value.length){
			formRef.value.init(selRows.value[0].id,'info')
		}
	}
	// 表单
	// 预览文件
	const preClick = (file) =>{
		if(!file){
			context?.$toolUtil.message('文件不存在','error')
		}
		window.open(context?.$config.url + file)
		// const a = document.createElement('a');
		// a.style.display = 'none';
		// a.setAttribute('target', '_blank');
		// file && a.setAttribute('download', file);
		// a.href = context?.$config.url + file;
		// document.body.appendChild(a);
		// a.click();
		// document.body.removeChild(a);
	}
	// 下载文件
	const download = (file) => {
		if(!file){
			context?.$toolUtil.message('文件不存在','error')
		}
		let arr = file.replace(new RegExp('file/', "g"), "")
		axios.get((location.href.split(context?.$config.name).length>1 ? location.href.split(context?.$config.name)[0] :'') + context?.$config.name + '/file/download?fileName=' + arr, {
			headers: {
				token: context?.$toolUtil.storageGet('Token')
			},
			responseType: "blob"
		}).then(({
			data
		}) => {
			const binaryData = [];
			binaryData.push(data);
			const objectUrl = window.URL.createObjectURL(new Blob(binaryData, {
				type: 'application/pdf;chartset=UTF-8'
			}))
			const a = document.createElement('a')
			a.href = objectUrl
			a.download = arr
			// a.click()
			// 下面这个写法兼容火狐
			a.dispatchEvent(new MouseEvent('click', {
				bubbles: true,
				cancelable: true,
				view: window
			}))
			window.URL.revokeObjectURL(data)
		})
	}


	import zuoyetijiaoFormModel from '@/views/zuoyetijiao/formModel'
	const zuoyetijiaoFormModelRef = ref(null)
    const zuoyetijiaoCrossAddOrUpdateHandler = (row,type,crossOptAudit,statusColumnName,tips,statusColumnValue) => {
		if(statusColumnName!=''&&!statusColumnName.startsWith("[")) {
			var obj = row
			for (var o in obj){
				if(o==statusColumnName && obj[o]==statusColumnValue){
					context?.$toolUtil.message(tips,'error')
					return;
				}
			}
		}
		nextTick(()=>{
			zuoyetijiaoFormModelRef.value.init(row.id,'cross','提交',row,'zuoyexinxi',statusColumnName,tips,statusColumnValue)
		})
    }
	//初始化
	const init = () => {
		getList()
	}
	init()
</script>
<style lang="scss" scoped>
	
	// 操作盒子
	.list_search_view {
		margin: 0 0 20px;
		// 搜索盒子
		.search_form {
			display: flex;
			align-items: center;
			// 子盒子
			.search_view {
				margin: 0 14px 0 0;
				display: flex;
				align-items: center;
				// 搜索label
				.search_label {
					margin: 0 10px 0 0;
					color: #787878;
					font-weight: 500;
					display: inline-block;
					font-size: 14px;
					line-height: 40px;
					height: 40px;
				}
				// 搜索item
				.search_box {
					display: flex;
					width: calc(100% - 100px);
					// 输入框
					:deep(.search_inp) {
						border: none;
						border-radius: 20px;
						padding: 0 10px;
						width: 100%;
						outline-offset: 4px;
						line-height: 36px;
						box-sizing: border-box;
						//去掉默认样式
						.el-input__wrapper{
							border: none;
							box-shadow: none;
							background: none;
							border-radius: 0;
							height: 100%;
							padding: 0;
						}
						.is-focus {
							box-shadow: none !important;
						}
					}
					// 下拉框
					:deep(.search_sel) {
						border: 0px solid #ddd;
						border-radius: 20px;
						padding: 0 10px;
						background: #fff;
						width: 100%;
						line-height: 36px;
						box-sizing: border-box;
						//去掉默认样式
						.select-trigger{
							height: 100%;
							.el-input{
								height: 100%;
								.el-input__wrapper{
									border: none;
									box-shadow: none;
									background: none;
									border-radius: 0;
									height: 100%;
									padding: 0;
								}
								.is-focus {
									box-shadow: none !important;
								}
							}
						}
					}
				}
			}
			// 搜索按钮盒子
			.search_btn_view {
				width: 20%;
				display: flex;
				padding: 0 20px;
				// 搜索按钮
				.search_btn {
					border: 0;
					cursor: pointer;
					border-radius: 4px;
					padding: 0 24px;
					box-shadow: inset 0px 4px 10px 0px rgba(147,147,147,0.302);
					outline: 4px solid #EAF5FF;
					color: #2C2C2C;
					background: #96C6EE;
					width: auto;
					font-size: 14px;
					height: 40px;
				}
				// 搜索按钮-悬浮
				.search_btn:hover {
					background: rgba(150, 198, 238, 0.5);
				}
			}
		}
		//头部按钮盒子
		.btn_view {
			margin: 20px 0;
			display: flex;
			// 其他
			:deep(.el-button--default){
				border: none;
				border-radius: 4px;
				padding: 0 24px;
				box-shadow: inset 0px 4px 10px 0px rgba(147,147,147,0.302);
				outline: 4px solid #EAF5FF;
				color: #2C2C2C;
				background: #96C6EE;
				width: auto;
				font-size: 14px;
				height: 30px;
			}
			// 其他-悬浮
			:deep(.el-button--default:hover){
				background: rgba(150, 198, 238, 0.5);
			}
			// 新增
			:deep(.el-button--success){
				border: none;
				border-radius: 4px;
				padding: 0 24px;
				box-shadow: inset 0px 4px 10px 0px rgba(147,147,147,0.302);
				outline: 4px solid #EAF5FF;
				color: #2C2C2C;
				background: #96C6EE;
				width: auto;
				font-size: 14px;
				height: 30px;
			}
			// 新增-悬浮
			:deep(.el-button--success:hover){
				background: rgba(150, 198, 238, 0.5);
			}
			// 修改
			:deep(.el-button--primary){
				border: none;
				border-radius: 4px;
				padding: 0 24px;
				box-shadow: inset 0px 4px 10px 0px rgba(147,147,147,0.302);
				outline: 4px solid #EAF5FF;
				color: #2C2C2C;
				background: #96C6EE;
				width: auto;
				font-size: 14px;
				height: 30px;
			}
			// 修改-悬浮
			:deep(.el-button--primary:hover){
				background: rgba(150, 198, 238, 0.5);
			}
			// 详情
			:deep(.el-button--info){
				border: none;
				border-radius: 4px;
				padding: 0 24px;
				box-shadow: inset 0px 4px 10px 0px rgba(147,147,147,0.302);
				outline: 4px solid #EAF5FF;
				color: #2C2C2C;
				background: #96C6EE;
				width: auto;
				font-size: 14px;
				height: 30px;
			}
			// 详情-悬浮
			:deep(.el-button--info:hover){
				background: rgba(150, 198, 238, 0.5);
			}
			// 删除
			:deep(.el-button--danger){
				border: none;
				border-radius: 4px;
				padding: 0 24px;
				box-shadow: inset 0px 4px 10px 0px rgba(147,147,147,0.302);
				outline: 4px solid #EAF5FF;
				color: #2C2C2C;
				background: #96C6EE;
				width: auto;
				font-size: 14px;
				height: 30px;
			}
			// 删除-悬浮
			:deep(.el-button--danger:hover){
				background: rgba(150, 198, 238, 0.5);
			}
			// 统计
			:deep(.el-button--warning){
				border: none;
				border-radius: 4px;
				padding: 0 24px;
				box-shadow: inset 0px 4px 10px 0px rgba(147,147,147,0.302);
				outline: 4px solid #EAF5FF;
				color: #2C2C2C;
				background: #96C6EE;
				width: auto;
				font-size: 14px;
				height: 30px;
			}
			// 统计-悬浮
			:deep(.el-button--warning:hover){
				background: rgba(150, 198, 238, 0.5);
			}
		}
	}
	// 表格样式
	.el-table {
		border-radius: 10px;
		padding: 0;
		width: 100%;
		border-color: #eee;
		border-width: 0;
		border-style: solid;
		:deep(.el-table__header-wrapper) {
			thead {
				color: rgba(98, 184, 255, 1);
				background: #fff;
				font-weight: 500;
				width: 100%;
				tr {
					background: #fff;
					th {
						padding: 12px 0;
						border-color: #eee;
						border-width: 0;
						border-style: solid;
						text-align: left;
						.cell {
							padding: 0 10px;
							word-wrap: normal;
							word-break: break-all;
							white-space: normal;
							font-weight: bold;
							display: inline-block;
							vertical-align: middle;
							width: 100%;
							line-height: 24px;
							position: relative;
							text-overflow: ellipsis;
						}
					}
				}
			}
		}
		:deep(.el-table__body-wrapper) {
			tbody {
				width: 100%;
				tr {
					background: #fff;
					td {
						padding: 12px 0;
						color: #999;
						background: #fff;
						border-color: #eee;
						border-width: 0;
						border-style: solid;
						text-align: left;
						.cell {
							padding: 6px 10px;
							overflow: hidden;
							word-break: break-all;
							white-space: normal;
							line-height: 24px;
							text-overflow: ellipsis;
							// 编辑
							.el-button--primary {
								border: none;
								border-radius: 4px;
								padding: 0 24px;
								box-shadow: inset 0px 4px 10px 0px rgba(147,147,147,0.302);
								outline: 4px solid #EAF5FF;
								margin: 8px;
								color: #2c2c2c;
								background: #96C6EE;
								width: auto;
								font-size: 14px;
								height: 30px;
							}
							// 编辑-悬浮
							.el-button--primary:hover {
								background: rgba(150, 198, 238, 0.5);
							}
							// 详情
							.el-button--info {
								border: none;
								border-radius: 4px;
								padding: 0 24px;
								box-shadow: inset 0px 4px 10px 0px rgba(147,147,147,0.302);
								outline: 4px solid #EAF5FF;
								margin: 8px;
								color: #2c2c2c;
								background: #96C6EE;
								width: auto;
								font-size: 14px;
								height: 30px;
							}
							// 详情-悬浮
							.el-button--info:hover {
								background: rgba(150, 198, 238, 0.5);
							}
							// 删除
							.el-button--danger {
								border: none;
								border-radius: 4px;
								padding: 0 24px;
								box-shadow: inset 0px 4px 10px 0px rgba(147,147,147,0.302);
								outline: 4px solid #EAF5FF;
								margin: 8px;
								color: #2c2c2c;
								background: #96C6EE;
								width: auto;
								font-size: 14px;
								height: 30px;
							}
							// 删除-悬浮
							.el-button--danger:hover {
								background: rgba(150, 198, 238, 0.5);
							}
							// 跨表
							.el-button--success {
								border: none;
								border-radius: 4px;
								padding: 0 24px;
								box-shadow: inset 0px 4px 10px 0px rgba(147,147,147,0.302);
								outline: 4px solid #EAF5FF;
								margin: 8px;
								color: #2c2c2c;
								background: #96C6EE;
								width: auto;
								font-size: 14px;
								height: 30px;
							}
							// 跨表-悬浮
							.el-button--success:hover {
								background: rgba(150, 198, 238, 0.5);
							}
							// 操作
							.el-button--warning {
								border: none;
								border-radius: 4px;
								padding: 0 24px;
								box-shadow: inset 0px 4px 10px 0px rgba(147,147,147,0.302);
								outline: 4px solid #EAF5FF;
								margin: 8px;
								color: #2c2c2c;
								background: #96C6EE;
								width: auto;
								font-size: 14px;
								height: 30px;
							}
							// 操作-悬浮
							.el-button--warning:hover {
								background: rgba(150, 198, 238, 0.5);
							}
						}
					}
				}
				tr.el-table__row--striped {
					td {
						background: rgba(239, 241, 243, 1);
					}
				}
				tr:hover {
					td {
						padding: 12px 0;
						color: #fff;
						background: rgba(98, 184, 255, 1);
						border-color: #eee;
						border-width: 0;
						border-style: solid;
						text-align: left;
					}
				}
			}
		}
	}
	// 分页器
	.el-pagination {
		// 总页码
		:deep(.el-pagination__total) {
			margin: 0 10px 0 0;
			color: #666;
			font-weight: 400;
			display: inline-block;
			vertical-align: top;
			font-size: 13px;
			line-height: 28px;
			height: 28px;
		}
		// 上一页
		:deep(.btn-prev) {
			border: none;
			border-radius: 2px;
			padding: 0;
			margin: 0 5px;
			color: #666;
			background: #f4f4f5;
			display: inline-block;
			vertical-align: top;
			font-size: 13px;
			line-height: 28px;
			min-width: 35px;
			height: 28px;
		}
		// 下一页
		:deep(.btn-next) {
			border: none;
			border-radius: 2px;
			padding: 0;
			margin: 0 5px;
			color: #666;
			background: #f4f4f5;
			display: inline-block;
			vertical-align: top;
			font-size: 13px;
			line-height: 28px;
			min-width: 35px;
			height: 28px;
		}
		// 上一页禁用
		:deep(.btn-prev:disabled) {
			border: none;
			cursor: not-allowed;
			border-radius: 2px;
			padding: 0;
			margin: 0 5px;
			color: #C0C4CC;
			background: #f4f4f5;
			display: inline-block;
			vertical-align: top;
			font-size: 13px;
			line-height: 28px;
			height: 28px;
		}
		// 下一页禁用
		:deep(.btn-next:disabled) {
			border: none;
			cursor: not-allowed;
			border-radius: 2px;
			padding: 0;
			margin: 0 5px;
			color: #C0C4CC;
			background: #f4f4f5;
			display: inline-block;
			vertical-align: top;
			font-size: 13px;
			line-height: 28px;
			height: 28px;
		}
		// 页码
		:deep(.el-pager) {
			padding: 0;
			margin: 0;
			display: inline-block;
			vertical-align: top;
			// 数字
			.number {
				cursor: pointer;
				padding: 0 4px;
				margin: 0 5px;
				color: #666;
				display: inline-block;
				vertical-align: top;
				font-size: 13px;
				line-height: 28px;
				border-radius: 2px;
				background: #f4f4f5;
				text-align: center;
				min-width: 30px;
				height: 28px;
			}
			// 数字悬浮
			.number:hover {
				cursor: pointer;
				padding: 0 4px;
				margin: 0 5px;
				color: #fff;
				display: inline-block;
				vertical-align: top;
				font-size: 13px;
				line-height: 28px;
				border-radius: 2px;
				background: linear-gradient(133deg, #9CC8EB 0%, #61B7FE 100%);
				text-align: center;
				min-width: 30px;
				height: 28px;
			}
			// 选中
			.number.is-active {
				cursor: default;
				padding: 0 4px;
				margin: 0 5px;
				color: #FFF;
				display: inline-block;
				vertical-align: top;
				font-size: 13px;
				line-height: 28px;
				border-radius: 2px;
				background: linear-gradient(133deg, #9CC8EB 0%, #61B7FE 100%);
				text-align: center;
				min-width: 30px;
				height: 28px;
			}
		}
		// sizes
		:deep(.el-pagination__sizes) {
			display: inline-block;
			vertical-align: top;
			font-size: 13px;
			line-height: 28px;
			height: 28px;
			.el-select {
				border: 1px solid #DCDFE6;
				cursor: pointer;
				padding: 0;
				color: #606266;
				display: inline-block;
				font-size: 13px;
				line-height: 28px;
				border-radius: 3px;
				outline: 0;
				background: #FFF;
				width: 100%;
				text-align: center;
				height: 28px;
			}
		}
		// 跳页
		:deep(.el-pagination__jump) {
			margin: 0 0 0 24px;
			color: #606266;
			display: inline-block;
			vertical-align: top;
			font-size: 13px;
			line-height: 28px;
			height: 28px;
			// 输入框
			.el-input {
				border: 1px solid #DCDFE6;
				cursor: pointer;
				padding: 0 3px;
				color: #606266;
				display: inline-block;
				font-size: 14px;
				line-height: 28px;
				border-radius: 3px;
				outline: 0;
				background: #FFF;
				width: 100%;
				text-align: center;
				height: 28px;
				//去掉默认样式
				.el-input__wrapper{
					border: none;
					box-shadow: none;
					background: none;
					border-radius: 0;
					height: 100%;
					padding: 0;
				}
				.is-focus {
					box-shadow: none !important;
				}
			}
		}
	}
</style>
