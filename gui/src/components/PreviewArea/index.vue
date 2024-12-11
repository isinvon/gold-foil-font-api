<template>
  <div class="preview-area">
    <!-- 显示生成的图片 -->
    <div v-show="showImage && imageBlobUrl" class="preview-card">
      <img :src="imageBlobUrl" alt="Generated Image" class="preview-image"/>
      <div class="download-buttons">
        <el-button type="primary" @click="downloadImage" class="download-btn">下载图片</el-button>
      </div>
    </div>

    <!-- 显示生成的 SVG -->
    <div v-show="showSvg && svgContent" class="preview-card">
      <!--直接将SVG挂载到页面上(弃用此方法,布局不可控,当文字过长的时候导致布局过长,并且无法使用CSS进行控制)-->
      <!--<div v-html="svgContent" class="svg-preview"></div>-->
      <!--将SVG 转换为 Data URL 并显示通过图片方式显示(十分推荐,css可控,并且不会影响对于SVG下载的格式,即下载的时候依然是SVG)-->
      <img :src="svgImageUrl" alt="Generated SVG" class="svg-image"/>
      <div class="download-buttons">
        <el-button type="primary" @click="downloadSVG" class="download-btn">下载 SVG</el-button>
      </div>
    </div>

    <!-- 提示没有生成内容 -->
    <div v-if="!imageBlobUrl && !svgContent" class="no-content">
      <p>尚未生成图片或 SVG。</p>
    </div>
  </div>
</template>

<script setup>
import {defineProps} from 'vue';
import {useImagePreview} from './imagePreview.js'; // 导入新的 JS 文件

// 接收父组件传递的数据
const props = defineProps({
  imageUrl: {
    type: Blob,
    default: null,
  },
  svgContent: {
    type: String,
    default: '',
  },
});

// 使用自定义 hook 来管理逻辑
const {
  imageBlobUrl,
  showImage,
  showSvg,
  svgContent,
  svgImageUrl,
  downloadImage,
  downloadSVG
} = useImagePreview(props);
</script>

<style lang="less" scoped>
@import "./index";
</style>
