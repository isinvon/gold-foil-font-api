// src/main.js
import { createApp } from 'vue'
import App from './App.vue'
import ElementPlus from 'element-plus'
import 'element-plus/dist/index.css'
import './styles/index.less'
import {sendHeartbeat} from "@/api/sendHeartbeat.js";

// 在应用挂载前调用 sendHeartbeat 来监听可见性变化
sendHeartbeat();

createApp(App).use(ElementPlus).mount('#app')
