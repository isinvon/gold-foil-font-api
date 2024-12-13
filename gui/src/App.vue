<template>
  <div class="app-container">
    <a href="https://github.com/isinvon/Pvideo-demo" target="_blank" class="github-link">
      <button class="quarter-circle-btn">
        <span class="icon-container">
            <svg class="icon" xmlns="http://www.w3.org/2000/svg" width="20" height="18" viewBox="0 0 24 24"><path
                fill="currentColor"
                fill-rule="evenodd"
                d="M11.999 1C5.926 1 1 5.925 1 12c0 4.86 3.152 8.983 7.523 10.437c.55.102.75-.238.75-.53c0-.26-.009-.952-.014-1.87c-3.06.664-3.706-1.475-3.706-1.475c-.5-1.27-1.221-1.61-1.221-1.61c-.999-.681.075-.668.075-.668c1.105.078 1.685 1.134 1.685 1.134c.981 1.68 2.575 1.195 3.202.914c.1-.71.384-1.195.698-1.47c-2.442-.278-5.01-1.222-5.01-5.437c0-1.2.428-2.183 1.132-2.952c-.114-.278-.491-1.397.108-2.91c0 0 .923-.297 3.025 1.127A10.5 10.5 0 0 1 12 6.32a10.5 10.5 0 0 1 2.754.37c2.1-1.424 3.022-1.128 3.022-1.128c.6 1.514.223 2.633.11 2.911c.705.769 1.13 1.751 1.13 2.952c0 4.226-2.572 5.156-5.022 5.428c.395.34.747 1.01.747 2.037c0 1.47-.014 2.657-.014 3.017c0 .295.199.637.756.53C19.851 20.979 23 16.859 23 12c0-6.075-4.926-11-11.001-11"/></svg>
        </span>
      </button>
    </a>

    <el-row :gutter="20" class="mt-20">
      <el-col :span="24">
        <setting-form
            v-model:settings="settings"
            @update:settings="generateContent"
            @generate="handleGenerate"
        />
      </el-col>
    </el-row>

    <PreviewArea :settings="settings" :imageUrl="imageUrl" :svgContent="svgContent"/>
  </div>
</template>

<script setup>
import {ref} from 'vue';
import {generateGoldFoilImage, generateGoldFoilSVG} from './api/goldFoilApi';
import SettingForm from './components/SettingsPanel/index.vue';
import PreviewArea from "./components/PreviewArea/index.vue";
import {ElLoading, ElMessage} from 'element-plus';
import {defaultValue} from "@/data/defaultValue.js";

const settings = ref({
  text: defaultValue.text,
  fontColorType: defaultValue.fontColorType,
  fontCustomColor: defaultValue.fontCustomColor,
  isBackground: defaultValue.isBackground,
  isRandomBackground: defaultValue.isRandomBackground,
  backgroundColor: defaultValue.backgroundColor,
  gradientPos: defaultValue.gradientPos,
});

const imageUrl = ref('');
const svgContent = ref('');


const generateContent = async (type) => {
  // 校验用户输入
  if (!settings.value.text) {
    ElMessage.warning('请输入要生成的文本！');
    return;
  }
  if (settings.value.fontColorType === 'custom' && settings.value.fontCustomColor === '') {
    ElMessage.warning('请选择字体颜色！');
    return;
  }
  if (settings.value.fontColorType === 'customGradient' && settings.value.fontCustomColor === '') {
    ElMessage.warning('请选择字体颜色！');
    return;
  }
  if (settings.value.isBackground && !settings.value.isRandomBackground && settings.value.backgroundColor === '') {
    ElMessage.warning('请选择背景颜色！');
    return;
  }

  // 显示加载动画（放在验证完成之后!避免不必要的加载!）
  const loading = ElLoading.service({
    lock: true,
    text: '生成中...',
    background: 'rgba(0, 0, 0, 0.7)',
  });

  try {
    const params = {
      text: settings.value.text,
      gradientPos: settings.value.gradientPos,
      fontColorType: settings.value.fontColorType,
      fontCustomColor: settings.value.fontCustomColor,
      isBackground: settings.value.isBackground.toString(),
      isRandomBackground: settings.value.isRandomBackground.toString(),
      backgroundColor: settings.value.backgroundColor,
    };

    if (type === 'image') {
      imageUrl.value = await generateGoldFoilImage(params);
    } else if (type === 'svg') {
      svgContent.value = await generateGoldFoilSVG(params);
    }
  } catch (error) {
    ElMessage.error('生成内容失败，请检查输入或稍后重试！');
    console.error(error);
  } finally {
    loading.close();
  }
};

// 处理 generate 事件，根据类型调用不同的生成函数(svg或者image)
const handleGenerate = (type) => {
  generateContent(type);
};
</script>

<style lang="less" scoped>
@import "@/styles/globalVariable";

.app-container {
  // 银色渐变上中下
  background: linear-gradient(111deg, #f5f5f5, #d2d2d2, #f5f5f5);
  padding: 35px;
  border-radius: @Global-radius;
  margin-top: 20px;
  margin-left: 20px;
  margin-right: 20px;
  box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.1);
}


/* 左上角1/4圆形按钮样式 */
.quarter-circle-btn {
  position: absolute;
  top: -40px;
  left: -40px;
  width: 80px;
  height: 80px;
  background-color: rgba(211, 211, 211, 0.38);
  border: none;
  border-radius: 50%;
  clip-path: polygon(100% 50%, 50% 50%, 50% 100%, 100% 100%);
  cursor: pointer;
  transition: transform 0.3s ease, background-color 0.3s ease;
}

/* 按钮悬浮效果 */
.quarter-circle-btn:hover {
  transform: scale(1.5);
  background-color: #d5d5d5;
}

/* 图标容器样式 */
.icon-container {
  position: absolute;
  top: 38%;
  left: 35%;
  transform: translate(16.8px, 16.8px);
}

.icon {
  color: #ffa1a1;
}
</style>
