<template>
  <div class="app-container">
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
</style>
