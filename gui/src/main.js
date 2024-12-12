// src/main.js
import { createApp } from 'vue'
import App from './App.vue'
import ElementPlus from 'element-plus'
import 'element-plus/dist/index.css'
import './styles/index.less'
import {shutdownRequest} from "@/api/shutdownAPI.js";

// 在应用挂载前调用 shutdownRequest 来监听可见性变化
shutdownRequest();

createApp(App).use(ElementPlus).mount('#app')
