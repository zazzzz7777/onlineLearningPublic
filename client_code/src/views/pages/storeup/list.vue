<template>
  <div
    class="app-contain"
    :style="{
      width: '86%',
      padding: '0',
      margin: '30px auto 50px',
      position: 'relative',
      borderRadius: '0px',
      background: 'none',
    }"
  >
    <div class="back_view" v-if="centerType">
      <el-button class="back_btn" @click="backClick" type="primary"
        >返回</el-button
      >
    </div>
    <div class="section_title">
      {{ formName }}
    </div>
    <el-form :inline="true" :model="searchQuery" class="list_search">
      <div class="search_view">
        <div class="search_label">名称：</div>
        <div class="search_box">
          <el-input
            class="search_inp"
            v-model="searchQuery.name"
            placeholder="名称"
            style="100%"
            size="small"
            clearable
          >
          </el-input>
        </div>
      </div>
      <div class="search_btn_view">
        <el-button class="search_btn" type="primary" @click="searchClick"
          >搜索</el-button
        >
      </div>
    </el-form>
    <div class="list_bottom">
      <div class="data_box">
        <div class="data_view">
          <div
            class="data_item animation_box"
            v-for="(item, index) in list"
            :key="index"
            @click="detailClick(item)"
          >
            <div
              class="data_img_box"
              v-if="item.picture && item.picture.substr(0, 4) == 'http'"
            >
              <img class="data_img" :src="item.picture" />
            </div>
            <div class="data_img_box" v-else>
              <img
                class="data_img"
                :src="
                  item.picture ? $config.url + item.picture.split(',')[0] : ''
                "
              />
            </div>
            <div class="data_content">
              <div class="data_title">{{ item.name }}</div>
            </div>
          </div>
        </div>
        <el-pagination
          background
          :layout="layouts.join(',')"
          :total="total"
          :page-size="listQuery.limit"
          prev-text="上一页"
          next-text="下一页"
          :hide-on-single-page="false"
          :style="{
            border: '0px solid #eee',
            padding: '4px 0',
            margin: '10px 0 20px',
            whiteSpace: 'nowrap',
            color: '#333',
            textAlign: 'center',
            flexWrap: 'wrap',
            background: 'none',
            display: 'flex',
            width: '100%',
            fontWeight: '500',
            justifyContent: 'center',
          }"
          @size-change="sizeChange"
          @current-change="currentChange"
          @prev-click="prevClick"
          @next-click="nextClick"
        />
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref, getCurrentInstance, nextTick } from 'vue'
import { useRoute, useRouter } from 'vue-router'
const context = getCurrentInstance()?.appContext.config.globalProperties
const router = useRouter()
const route = useRoute()
//基础信息
const tableName = 'storeup'
const formName = ref('')
//基础信息
const breadList = ref([
  {
    name: formName,
  },
])
const list = ref([])
const listQuery = ref({
  page: 1,
  limit: 20,
})
const total = ref(0)
const listLoading = ref(false)
//权限验证
const btnAuth = (e, a) => {
  return context?.$toolUtil.isAuth(e, a)
}
//判断是否从个人中心跳转
const centerType = ref(false)
//返回
const backClick = () => {
  router.push(
    `/index/${context?.$toolUtil.storageGet('frontSessionTable')}Center`
  )
}
const init = () => {
  if (route.query.centerType) {
    centerType.value = true
  }
  switch (route.query.type) {
    case '1':
      formName.value = '我的收藏管理'
      break
  }
  getList()
}
//搜索
const searchQuery = ref({})

const searchClick = () => {
  listQuery.value.page = 1
  getList()
}
//分页
const layouts = ref(['prev', 'pager', 'next', 'total', 'jumper'])
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
//列表
const getList = () => {
  listLoading.value = true
  let params = JSON.parse(JSON.stringify(listQuery.value))
  if (searchQuery.value.name && searchQuery.value.name != '') {
    params.name = '%' + searchQuery.value.name + '%'
  }
  if (route.query.type) {
    params.type = route.query.type
  }
  context
    ?.$http({
      url: `${tableName}/list`,
      method: 'get',
      params: params,
    })
    .then((res) => {
      listLoading.value = false
      list.value = res.data.data.list
      total.value = Number(res.data.data.total)
    })
}
const detailClick = (item) => {
  router.push(`${item.tablename}Detail?id=${item.refid}`)
}
init()
</script>
<style lang="scss" scoped>
// 返回盒子
.back_view {
  border-radius: 0px;
  padding: 0 20px;
  margin: 0 auto 20px;
  background: none;
  display: block;
  width: 100%;
  text-align: right;
  // 返回按钮
  .back_btn {
    border: 1px solid #ddd;
    cursor: pointer;
    border-radius: 0px;
    padding: 0 30px;
    outline: none;
    color: #666;
    background: #f9f9f9;
    width: auto;
    font-size: 14px;
    height: 32px;
  }
  // 返回按钮-悬浮
  .back_btn:hover {
  }
}

//搜索
.list_search {
  border: 0px solid #ddd;
  padding: 20px 0 0 20px;
  margin: 0 auto;
  background: none;
  display: flex;
  width: calc(100% - 0px);
  justify-content: center;
  align-items: center;
  .search_view {
    margin: 0 10px 0 0;
    display: flex;
    align-items: center;
    .search_label {
      margin: 0 10px 0 0;
      color: #333;
      font-weight: 500;
      display: inline-block;
      font-size: 14px;
      line-height: 40px;
      height: 40px;
    }
    .search_box {
      display: flex;
      width: calc(100% - 100px);
      // 输入框
      :deep(.search_inp) {
        padding: 0 10px;
        border-color: #eee;
        outline-offset: 0px;
        line-height: 36px;
        box-sizing: border-box;
        border-radius: 4px;
        box-shadow: 0 0 0px rgba(85, 255, 255, 0.5);
        outline: 0px solid #efefef;
        background: #fff;
        width: 100%;
        border-width: 0 0 1px;
        border-style: solid;
        min-width: 200px;
        height: 36px;
        //去掉默认样式
        .el-input__wrapper {
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
  .search_btn_view {
    margin: 20px 0;
    display: flex;
    // 搜索按钮
    .search_btn {
      border: 0;
      cursor: pointer;
      border-radius: 4px;
      padding: 0 20px;
      outline: none;
      margin: 0 10px 0 0;
      color: #fff;
      background: #113961;
      width: auto;
      font-size: 14px;
      transition: all 0.3s;
      height: 36px;
    }
    // 搜索按钮-悬浮
    .search_btn:hover {
      transform: rotate(0deg) scale(0.9) skew(0deg, 0deg)
        translate3d(0px, -10px, 0px);
    }
  }
}
// 数据盒子
.list_bottom {
  border-radius: 0;
  padding: 20px 0 0;
  margin: 20px 0 0;
  background: none;
  display: flex;
  width: calc(100% - 0px);
  align-items: flex-start;
  flex-wrap: wrap;
  //列表
  .data_box {
    border: 0px solid #ddd;
    padding: 0;
    background: none;
    width: 100%;
    .data_view {
      border: 0px solid #eee;
      padding: 0 0 0 20px;
      overflow: hidden;
      background: none;
      display: flex;
      width: 100%;
      flex-wrap: wrap;
      .data_item {
        cursor: pointer;
        border: 0px solid #ddd;
        padding: 10px;
        margin: 0 20px 20px 0;
        background: none;
        width: calc(32% - 20px);
        box-sizing: border-box;
        // 图片盒子
        .data_img_box {
          margin: 0 auto 0px;
          width: 100%;
          position: relative;
          height: 300px;
          // 图片
          .data_img {
            padding: 0px;
            object-fit: cover;
            width: 100%;
            height: 100%;
          }
        }
        // 内容盒子
        .data_content {
          border: 1px solid rgba(55, 55, 55, 0.2);
          padding: 0 10px;
          background: #fff;
          display: flex;
          width: 100%;
          justify-content: space-between;
          flex-wrap: wrap;
          // 标题
          .data_title {
            padding: 0 16px;
            margin: 0 0 0px;
            overflow: hidden;
            color: #000;
            white-space: nowrap;
            background: none;
            width: 100%;
            font-size: 14px;
            line-height: 30px;
            text-overflow: ellipsis;
            text-align: center;
            height: 30px;
          }
        }
      }
    }
  }
}
// animation
.animation_box {
  transform: rotate(0deg) scale(1) skew(0deg, 0deg) translate3d(0px, 0px, 0px);
  z-index: initial;
}
.animation_box:hover {
  transform: rotate(0deg) scale(0.9) skew(0deg, 0deg)
    translate3d(0px, 10px, 0px);
  -webkit-perspective: 1000px;
  perspective: 1000px;
  transition: 0.3s;
}
.animation_box img {
  transform: rotate(0deg) scale(1) skew(0deg, 0deg) translate3d(0px, 0px, 0px);
  z-index: initial;
}
.animation_box img:hover {
  transform: rotate(0deg) scale(1) skew(0deg, 0deg) translate3d(0px, 0px, 0px);
  -webkit-perspective: 1000px;
  perspective: 1000px;
  transition: 0.3s;
}
// animation

// 分页器
.el-pagination {
  // 总页码
  :deep(.el-pagination__total) {
    margin: 0 10px 0;
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
    border: 1px solid #ddd;
    border-radius: 2px;
    padding: 0 4px;
    margin: 0 2px;
    color: #666;
    background: #fff;
    display: inline-block;
    vertical-align: top;
    font-size: 13px;
    line-height: 28px;
    min-width: 28px;
    height: 28px;
  }
  // 下一页
  :deep(.btn-next) {
    border: 1px solid #ddd;
    border-radius: 2px;
    padding: 0 4px;
    margin: 0 2px;
    color: #666;
    background: #fff;
    display: inline-block;
    vertical-align: top;
    font-size: 14px;
    line-height: 28px;
    min-width: 28px;
    height: 28px;
  }
  // 上一页禁用
  :deep(.btn-prev:disabled) {
    border: 1px solid #ddd;
    cursor: not-allowed;
    padding: 0 4px;
    margin: 0 2px;
    color: #c0c4cc;
    display: inline-block;
    vertical-align: top;
    font-size: 14px;
    line-height: 28px;
    border-radius: 2px;
    background: none;
    min-width: 28px;
    height: 28px;
  }
  // 下一页禁用
  :deep(.btn-next:disabled) {
    border: 1px solid #ddd;
    cursor: not-allowed;
    padding: 0 4px;
    margin: 0 2px;
    color: #c0c4cc;
    display: inline-block;
    vertical-align: top;
    font-size: 14px;
    line-height: 28px;
    border-radius: 2px;
    background: none;
    min-width: 28px;
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
      min-width: 28px;
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
      background: #113961;
      text-align: center;
      min-width: 28px;
      height: 28px;
    }
    // 选中
    .number.is-active {
      cursor: default;
      padding: 0 4px;
      margin: 0 5px;
      color: #fff;
      display: inline-block;
      vertical-align: top;
      font-size: 13px;
      line-height: 28px;
      border-radius: 2px;
      background: #113961;
      text-align: center;
      min-width: 28px;
      height: 28px;
    }
  }
  // sizes
  :deep(.el-pagination__sizes) {
    box-shadow: none;
    margin: 0 0 0 5px;
    display: inline-block;
    vertical-align: top;
    font-size: 13px;
    line-height: 28px;
    height: 28px;
    .el-select {
      border: 0px solid #dcdfe6;
      cursor: pointer;
      padding: 0;
      color: #606266;
      display: inline-block;
      font-size: 13px;
      line-height: 28px;
      border-radius: 2px;
      outline: 0;
      background: #f4f4f5;
      width: 100%;
      text-align: center;
      height: 28px;
      //去掉默认样式
      .select-trigger {
        height: 100%;
        .el-input {
          height: 100%;
          .el-input__wrapper {
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
      border: 1px solid #ddd;
      cursor: pointer;
      padding: 0 3px;
      margin: 0 6px;
      color: #606266;
      display: inline-block;
      font-size: 14px;
      line-height: 28px;
      border-radius: 3px;
      outline: 0;
      background: #fff;
      width: 38px;
      text-align: center;
      height: 28px;
      //去掉默认样式
      .el-input__wrapper {
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
