<template>
  <div
    class="app-contain"
    :style="{
      padding: '0px 0',
      margin: '20px auto 40px',
      alignItems: 'flex-start',
      borderRadius: '0px',
      flexWrap: 'wrap',
      background: 'none',
      display: 'flex',
      width: '86%',
      position: 'relative',
      justifyContent: 'space-between',
    }"
  >
    <div class="bread_view">
      <el-breadcrumb separator=">" class="breadcrumb">
        <el-breadcrumb-item class="first_breadcrumb" :to="{ path: '/' }"
          >首页</el-breadcrumb-item
        >
        <el-breadcrumb-item
          class="second_breadcrumb"
          v-for="(item, index) in breadList"
          :key="index"
          >{{ item.name }}</el-breadcrumb-item
        >
      </el-breadcrumb>
    </div>
    <div class="back_view">
      <el-button class="back_btn" @click="backClick" type="primary"
        >返回</el-button
      >
    </div>
    <div class="detail_view">
      <div class="swiper_view">
        <mySwiper
          :data="bannerList"
          :type="3"
          :loop="false"
          :navigation="false"
          :pagination="true"
          :paginationType="1"
          :scrollbar="false"
          :slidesPerView="1"
          :spaceBetween="20"
          :autoHeight="false"
          :centeredSlides="false"
          :freeMode="false"
          :effectType="5"
          :direction="horizontal"
          :autoplay="true"
          :slidesPerColumn="1"
        >
          <template #default="scope">
            <img
              :style="{
                border: '1px solid #ddd',
                width: '100%',
                objectFit: 'contain',
                height: '480px',
              }"
              :src="scope.row ? $config.url + scope.row : ''"
            />
          </template>
        </mySwiper>
      </div>

      <div class="info_view">
        <div class="title_view">
          <div class="detail_title">
            {{ detail.zuoyemingcheng }}
          </div>
        </div>
        <div class="info_item">
          <div class="info_label">提交时间</div>
          <div class="info_text">{{ detail.tijiaoshijian }}</div>
        </div>
        <div class="info_item">
          <div class="info_label">教师工号</div>
          <div class="info_text">{{ detail.jiaoshigonghao }}</div>
        </div>
        <div class="info_item">
          <div class="info_label">教师姓名</div>
          <div class="info_text">{{ detail.jiaoshixingming }}</div>
        </div>
        <div class="info_item">
          <div class="info_label">学号</div>
          <div class="info_text">{{ detail.xuehao }}</div>
        </div>
        <div class="info_item">
          <div class="info_label">学生姓名</div>
          <div class="info_text">{{ detail.xueshengxingming }}</div>
        </div>
        <div class="info_item">
          <div class="info_label">班级</div>
          <div class="info_text">{{ detail.banji }}</div>
        </div>
        <div class="info_item">
          <div class="info_label">提交作业</div>
          <div class="info_text">
            <el-button
              class="info_down"
              v-if="detail.tijiaozuoye"
              @click="downClick(detail.tijiaozuoye)"
              >点击下载</el-button
            >
            <el-button class="info_undown" v-else>暂无</el-button>
          </div>
        </div>
        <div class="btn_view">
          <el-button
            class="edit_btn"
            v-if="centerType && btnAuth('zuoyetijiao', '修改')"
            type="primary"
            @click="editClick"
            >修改</el-button
          >
          <el-button
            class="del_btn"
            v-if="centerType && btnAuth('zuoyetijiao', '删除')"
            type="danger"
            @click="delClick"
            >删除</el-button
          >
        </div>
      </div>
    </div>
    <el-tabs type="border-card" v-model="activeName" class="tabs_view">
    </el-tabs>
  </div>
</template>
<script setup>
import axios from 'axios'
import {
  ref,
  getCurrentInstance,
  watch,
  onUnmounted,
  onMounted,
  nextTick,
  computed,
} from 'vue'
import { ElMessageBox } from 'element-plus'
import { useRoute, useRouter } from 'vue-router'
const context = getCurrentInstance()?.appContext.config.globalProperties
const route = useRoute()
const router = useRouter()
//基础信息
const tableName = 'zuoyetijiao'
const formName = '作业提交'
//基础信息
const breadList = ref([
  {
    name: formName,
  },
])
//权限验证
const btnAuth = (e, a) => {
  if (centerType.value) {
    return context?.$toolUtil.isBackAuth(e, a)
  } else {
    return context?.$toolUtil.isAuth(e, a)
  }
}
//查看权限验证
const btnFrontAuth = (e, a) => {
  if (centerType.value) {
    return context?.$toolUtil.isBackAuth(e, a)
  } else {
    return context?.$toolUtil.isFrontAuth(e, a)
  }
}
// 返回
const backClick = () => {
  history.back()
}
// 轮播图
const bannerList = ref([])
// 详情
const title = ref('')
const detail = ref({})
const activeName = ref('first')
const getDetail = () => {
  context
    ?.$http({
      url: `${tableName}/detail/${route.query.id}`,
      method: 'get',
    })
    .then((res) => {
      title.value = res.data.data.zuoyemingcheng
      detail.value = res.data.data
    })
}
// 下载文件
const downClick = (file) => {
  if (!file) {
    context?.$toolUtil.message('文件不存在', 'error')
  }
  let arr = file.replace(new RegExp('file/', 'g'), '')
  axios
    .get(
      (location.href.split(context?.$config.name).length > 1
        ? location.href.split(context?.$config.name)[0]
        : '') +
        context?.$config.name +
        '/file/download?fileName=' +
        arr,
      {
        headers: {
          token: context?.$toolUtil.storageGet('frontToken'),
        },
        responseType: 'blob',
      }
    )
    .then(({ data }) => {
      const binaryData = []
      binaryData.push(data)
      const objectUrl = window.URL.createObjectURL(
        new Blob(binaryData, {
          type: 'application/pdf;chartset=UTF-8',
        })
      )
      const a = document.createElement('a')
      a.href = objectUrl
      a.download = arr
      // a.click()
      // 下面这个写法兼容火狐
      a.dispatchEvent(
        new MouseEvent('click', {
          bubbles: true,
          cancelable: true,
          view: window,
        })
      )
      window.URL.revokeObjectURL(data)
    })
}
// 判断是否从个人中心跳转
const centerType = ref(false)
const init = () => {
  if (route.query.centerType) {
    centerType.value = true
  }
  getDetail()
}
//修改
const editClick = () => {
  router.push(`/index/${tableName}Add?id=${detail.value.id}&&type=edit`)
}
//删除
const delClick = () => {
  ElMessageBox.confirm(`是否删除此${formName}？`, '提示', {
    confirmButtonText: '是',
    cancelButtonText: '否',
    type: 'warning',
  }).then(() => {
    context
      ?.$http({
        url: `${tableName}/delete`,
        method: 'post',
        data: [detail.value.id],
      })
      .then((res) => {
        context?.$toolUtil.message('删除成功', 'success', () => {
          history.back()
        })
      })
  })
}
onMounted(() => {
  init()
})
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
// 面包屑盒子
.bread_view {
  border-radius: 0px;
  padding: 12px 20px;
  margin: 0px auto;
  background: none;
  width: 100%;
  border-color: #eee;
  border-width: 0 0 0px;
  position: relative;
  border-style: solid;
  :deep(.breadcrumb) {
    font-size: 14px;
    line-height: 1;
    .el-breadcrumb__separator {
      margin: 0 9px;
      color: #999;
      font-weight: 500;
    }
    .first_breadcrumb {
      .el-breadcrumb__inner {
        color: #333;
        display: inline-block;
      }
    }
    .second_breadcrumb {
      .el-breadcrumb__inner {
        color: #999;
        display: inline-block;
      }
    }
  }
}

.detail_view {
  border-radius: 0;
  padding: 20px 0;
  background: none;
  display: flex;
  width: 100%;
  border-color: #ddd;
  border-width: 0px;
  justify-content: space-between;
  position: relative;
  border-style: solid;
  flex-wrap: wrap;
  // 轮播图
  .swiper_view {
    border: 0px solid #ddd;
    padding: 0px;
    margin: 0 auto;
    background: none;
    width: 480px;
    float: left;
    height: auto;
    order: 0;
  }

  // 文字区
  .info_view {
    border: 1px solid #ddd;
    padding: 30px 4% 10px;
    margin: 0 0 0px;
    background: url(http://localhost:8080/onlineLearning/file/974a5cd5eb9840c9b1a4576231567b5c.png);
    width: calc(100% - 520px);
    box-sizing: border-box;
    float: right;
    order: 3;

    .title_view {
      border: 0px solid #ddd;
      padding: 0 20px;
      margin: 0 0 20px;
      background: #fff;
      display: flex;
      width: 100%;
      border-width: 1px 1px 4px;
      line-height: 40px;
      justify-content: space-between;
      align-items: center;

      .detail_title {
        color: #333;
        font-weight: 600;
        font-size: 16px;
      }
      .follow {
        border: 0px solid #ffffff50;
        cursor: pointer;
        border-radius: 4px;
        padding: 4px 10px;
        color: #fff;
        background: none;
        display: flex;
        width: auto;
        line-height: 1;
        justify-content: center;
        align-items: center;
        .iconfont {
          margin: 0 4px 0 0;
          color: #f60;
          font-size: 18px;
        }
        .iconfontActive {
          margin: 0 4px 0 0;
          color: #11396160;
          font-size: 18px;
        }
        span {
          color: #f60;
          font-size: 14px;
        }
        .textActive {
          color: #11396190;
          font-size: 14px;
        }
      }
      .follow:hover {
      }
      .follow:active {
        transform: scale(0.9);
      }
    }

    .info_item {
      border-radius: 0px;
      padding: 0 0 10px;
      margin: 0 0 10px;
      background: none;
      display: flex;
      border-color: #ddd;
      border-width: 0 0 1px;
      align-items: center;
      border-style: dashed;

      .info_label {
        margin: 0 12px 0 0;
        color: #666;
        font-weight: 500;
        width: auto;
      }
      .info_text {
      }
      .info_down {
        border: 1px solid #ddd;
        border-radius: 20px;
        padding: 0 20px;
        color: #666;
        background: #fff;
        line-height: 30px;
      }
      .info_down:hover {
        color: #666;
        background: none;
      }
      .info_undown {
        border: 0px solid #ddd;
        border-radius: 20px;
        padding: 0 20px;
        color: #888;
        background: none;
        line-height: 30px;
      }
    }
    .btn_view {
      padding: 0;
      margin: 20px 0 20px;
      display: block;
      width: 100%;
      flex-wrap: wrap;
      // 修改-按钮
      .edit_btn {
        border: 1px solid #2da065;
        padding: 0 10px;
        color: #2da065;
        background: #fff;
        line-height: 32px;
        transition: all 0.3s;
        height: 32px;
      }
      // 悬浮
      .edit_btn:hover {
        transform: scale(0.9) translate3d(0px, -3px, 0px);
      }
      // 删除-按钮
      .del_btn {
        border: 1px solid #f68b8b;
        padding: 0 10px;
        color: #f68b8b;
        background: #fff;
        line-height: 32px;
        transition: all 0.3s;
        height: 32px;
      }
      // 悬浮
      .del_btn:hover {
        transform: scale(0.9) translate3d(0px, -3px, 0px);
      }
    }
  }
}

//底部盒子
.tabs_view {
  border: 1px solid #ddd;
  border-radius: 0px;
  padding: 20px 0;
  box-shadow: none;
  margin: 0px auto;
  background: url(http://localhost:8080/onlineLearning/file/974a5cd5eb9840c9b1a4576231567b5c.png);
  width: 100%;
  :deep(.el-tabs__header) {
    background: transparent;
    border: none;
  }
  // 头部
  :deep(.el-tabs__nav-scroll) {
    border-radius: 0;
    padding: 2px 20px 0;
    margin: 0;
    background: none;
    display: flex;
    border-color: #ddd;
    border-width: 0px 0px 0px;
    border-style: solid;
    flex-wrap: wrap;
    height: 50px;
    .el-tabs__nav {
      .el-tabs__item {
        border: 1px solid #ddd;
        padding: 0 20px;
        margin: 0 10px 0 0;
        color: #333;
        font-weight: 500;
        display: inline-block;
        font-size: 14px;
        line-height: 48px;
        transition: all 0.3s;
        border-radius: 0px;
        background: #fff;
        border-width: 1px 1px 3px;
        position: relative;
        list-style: none;
        text-align: center;
        min-width: 140px;
        height: 48px;
      }
      .el-tabs__item:hover {
        border: 1px solid #113961;
        color: #113961;
        background: #fff;
        border-width: 1px 1px 3px;
      }
      .is-active {
        border: 1px solid #113961;
        border-radius: 0px;
        padding: 0 20px;
        margin: 0 10px 0 0;
        color: #113961;
        background: #fff;
        border-width: 1px 1px 3px;
        line-height: 48px;
        text-align: center;
        min-width: 140px;
        height: 48px;
      }
    }
  }
  // 内容区
  :deep(.el-tabs__content) {
    border-radius: 4px;
    padding: 20px;
    margin: 20px;
    color: #666;
    background: none;
    font-size: 14px;
    border-color: #eee;
    border-width: 0px;
    border-style: solid;
  }
}
</style>
