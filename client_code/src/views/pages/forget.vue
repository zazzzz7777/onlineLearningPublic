<template>
  <div>
    <div class="forget_view">
      <div class="outTitle_view">
        <div class="outTilte">{{ projectName }}注册</div>
      </div>
      <el-form :model="forgetForm" class="forget_box">
        <div class="tab_line">
          <div class="line"></div>
          <div class="num_line">
            <div
              class="line_number"
              :class="
                (pageType == 1 ? 'line_number1' : '',
                pageType > 1 ? 'line_number2' : '')
              "
            >
              <div class="number" v-if="pageType < 2">1</div>
              <el-icon v-else><Check /></el-icon>
            </div>
            <div
              class="line_number"
              :class="
                (pageType == 2 ? 'line_number1' : '',
                pageType > 2 ? 'line_number2' : '')
              "
            >
              <div class="number" v-if="pageType < 3">2</div>
              <el-icon v-else><Check /></el-icon>
            </div>
            <div
              class="line_number"
              :class="pageType == 3 ? 'line_number1' : ''"
            >
              <div class="number">3</div>
            </div>
          </div>
        </div>
      </el-form>
    </div>
  </div>
</template>
<script setup>
import { ref, getCurrentInstance, nextTick } from 'vue'
const context = getCurrentInstance()?.appContext.config.globalProperties
const pageType = ref(1)
const forgetForm = ref({})
const userForm = ref({})
//返回登录
const close = () => {
  context?.$router.push({
    path: '/login',
  })
}
</script>

<style lang="scss" scoped>
.forget_view {
  background-repeat: no-repeat;
  flex-direction: column;
  background-size: cover;
  background: url(http://localhost:8080/onlineLearning/file/a17d6c296f6d40029b0130c036ef24a0.jpg);
  display: flex;
  min-height: 100vh;
  justify-content: center;
  align-items: center;
  position: relative;
  background-position: center center;
  // 标题盒子
  .outTitle_view {
    padding: 0 0 10px;
    display: flex;
    align-items: center;
    .outTilte {
      border: 0px solid #fff;
      border-radius: 0px;
      padding: 10px 20px;
      color: #fff;
      font-size: 28px;
    }
  }
  // 表单盒子
  .forget_box {
    border-radius: 8px;
    padding: 40px 90px 40px 40px;
    box-shadow: 0px 26px 26px -30px #4986ff;
    margin: 20px 0;
    background: #fff;
    display: block;
    width: 550px;
    min-height: 500px;
    justify-content: center;
    align-items: flex-start;
    flex-wrap: wrap;
    // tab
    // 盒子
    .tab_line {
      padding: 30px 0 30px 120px;
      margin: 0;
      flex-direction: column;
      display: flex;
      width: 100%;
      align-items: center;
      // 中间线
      .line {
        background: #ccc;
        width: 100%;
        height: 1px;
      }
      // item盒子
      .num_line {
        margin: -20px 0 0;
        display: flex;
        width: 80%;
        justify-content: space-between;
        align-items: center;
        height: 40px;
        // 默认样式
        .line_number {
          border: 2px solid #ddd;
          border-radius: 50%;
          color: #aaa;
          background: #eee;
          display: flex;
          width: 40px;
          font-size: 18px;
          justify-content: center;
          align-items: center;
          height: 40px;
        }
        // 选中样式
        .line_number1 {
          color: rgba(64, 158, 255, 1);
          font-size: 22px;
          border-color: rgba(64, 158, 255, 1);
        }
        // 完成样式
        .line_number2 {
          color: rgba(0, 170, 0, 1);
          border-color: rgba(0, 170, 0, 1);
        }
      }
    }
    // item
    .list_item {
      margin: 10px auto 30px;
      display: flex;
      width: 100%;
      justify-content: flex-start;
      align-items: center;
      // label
      .item_label {
        color: #333;
        width: 120px;
        font-size: 14px;
        box-sizing: border-box;
        text-align: right;
      }
      // 输入框
      :deep(.list_inp) {
        border: 1px solid #4986ff;
        border-radius: 0px;
        padding: 0 10px;
        color: #666;
        background: none;
        width: calc(100% - 120px);
        border-width: 0 0 1px;
        line-height: 40px;
        box-sizing: border-box;
        height: 40px;
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
      //下拉框样式
      :deep(.list_sel) {
        border: 1px solid #4986ff;
        border-radius: 0px;
        padding: 0 10px;
        color: #666;
        background: none;
        width: calc(100% - 120px);
        border-width: 0 0 1px;
        line-height: 40px;
        box-sizing: border-box;
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
    // 按钮盒子
    .list_btn {
      margin: 30px 0 0;
      display: flex;
      width: 100%;
      justify-content: center;
      align-items: center;
      flex-wrap: wrap;
      // 获取密保
      :deep(.el-button--success) {
        border: 0;
        cursor: pointer;
        border-radius: 0px;
        padding: 0 30px;
        margin: 0 0px 0 120px;
        outline: none;
        color: #fff;
        background: linear-gradient(
            180deg,
            rgba(66, 137, 208, 1) 0%,
            rgba(17, 57, 97, 1) 100%
          ),
          #113961;
        width: calc(100% - 120px);
        font-size: 14px;
        height: 40px;
      }
      // 获取密保悬浮
      :deep(.el-button--success:hover) {
      }
      // 确认密保
      :deep(.el-button--primary) {
        border: 0;
        cursor: pointer;
        border-radius: 0px;
        padding: 0 30px;
        margin: 0 0px 0 120px;
        outline: none;
        color: #fff;
        background: linear-gradient(
            180deg,
            rgba(66, 137, 208, 1) 0%,
            rgba(17, 57, 97, 1) 100%
          ),
          #113961;
        width: calc(100% - 120px);
        font-size: 14px;
        height: 40px;
      }
      // 确认密保悬浮
      :deep(.el-button--primary:hover) {
      }
      // 重置密码
      :deep(.el-button--warning) {
        border: 0;
        cursor: pointer;
        border-radius: 0px;
        padding: 0 30px;
        margin: 0 0px 0 120px;
        outline: none;
        color: #fff;
        background: linear-gradient(
            180deg,
            rgba(66, 137, 208, 1) 0%,
            rgba(17, 57, 97, 1) 100%
          ),
          #113961;
        width: calc(100% - 120px);
        font-size: 14px;
        height: 40px;
      }
      // 重置密码悬浮
      :deep(.el-button--warning:hover) {
      }
      .r-login {
        cursor: pointer;
        padding: 0;
        margin: 20px 0 0 120px;
        color: #333;
        width: calc(100% - 120px);
        font-size: 13px;
        text-align: left;
      }
    }
  }
}
</style>
