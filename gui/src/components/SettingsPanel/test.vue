<template>
  <el-form :model="settings" label-width="120px" class="settings-form">
    <el-form-item v-for="(options, key) in formItems" :key="key" :label="options.label">
      <component
          :is="options.component"
          v-model="settings[key]"
          v-bind="options.props"
      >
        <el-option
            v-if="options.component === 'el-select' && options.options"
            v-for="opt in options.options"
            :key="opt.value"
            :label="opt.label"
            :value="opt.value"
        />
      </component>
    </el-form-item>
    <el-form-item>
      <!-- 通过传递参数区分生成类型 -->
      <el-button type="primary" @click="generate('image')" class="apply-btn">生成图片</el-button>
      <el-button type="primary" @click="generate('svg')" class="apply-btn">生成SVG</el-button>
    </el-form-item>
  </el-form>
</template>

<script setup>
const props = defineProps({
  settings: {
    type: Object,
    required: true,
  },
});

const emit = defineEmits(['update:settings', 'generate']); // 定义额外的 generate 事件

const formItems = {
  text: {
    label: "文本内容",
    component: "el-input",
    props: {
      type: "textarea",
      rows: 1,
      placeholder: "请输入文本内容"
    }
  },
  fontColorType: {
    label: "字体颜色",
    component: "el-select",
    props: {
      placeholder: "选择颜色"
    },
    options: [
      {label: "金色", value: "gold"},
      {label: "银色", value: "silver"},
      {label: "黑色", value: "black"},
      {label: "黑色渐变", value: "blackGradient"}
    ]
  },
  isBackground: {
    label: "背景",
    component: "el-switch",
    props: {
      activeText: "有",
      inactiveText: "无"
    }
  },
  gradientPos: {
    label: "渐变方向",
    component: "el-select",
    props: {
      placeholder: "选择方向"
    },
    options: [
      {label: "从左到右", value: "leftToRight"},
      {label: "从上到下", value: "topToBottom"},
      {label: "从左上到右下", value: "leftTopToRightBottom"},
      {label: "从左下到右上", value: "leftBottomToRightTop"},
      {label: "从右到左", value: "rightToLeft"},
      {label: "从下到上", value: "bottomToTop"},
      {label: "从右上到左下", value: "rightTopToLeftBottom"},
      {label: "从右下到左上", value: "rightBottomToLeftTop"},
      {label: "圆形", value: "circular"},
      {label: "圆形随机", value: "circularRandom"},
      {label: "随机", value: "random"}
    ]
  }
};

// 触发不同的生成事件
const generate = (type) => {
  emit('generate', type); // 将生成类型发送到父组件
};
</script>

<style lang="less">
.settings-form {
  max-width: 800px;
  margin: 0 auto;
  background: linear-gradient(135deg, rgba(201, 142, 176, 0.34), rgba(221, 180, 15, 0.3), rgba(217, 172, 224, 0.34)); /* 银色渐变 */
  border-radius: 12px;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
  padding: 20px;
}

.apply-btn {
  color: #f0cb46;
  background-color: #4d4d4d;
  font-size: 16px;
  border-radius: 8px;
  padding: 10px 20px;
  transition: background-color 0.3s;
}

.apply-btn:hover {
  background-color: #999999;
}

</style>
