import { defineConfig } from "vite";
import vue from "@vitejs/plugin-vue";
import path from "path";

export default defineConfig({
  base: "./",
  plugins: [vue()],
  resolve: {
    alias: {
      "@": path.resolve(__dirname, "src"), // 定义 @ 为 src 目录
    },
  },
  server: {
    host: "0.0.0.0", // 开发服务器可通过 IP 访问
    port: 3000, // 默认端口
    changeOrigin: true,
    proxy: {
      "/api": {
        target: "http://localhost:8080", // 代理目标地址
        changeOrigin: true, // 支持跨域
        rewrite: (path) => path.replace(/^\/api/, ""), // 重写路径
      },
    },
  },
  build: {
    sourcemap: false, // 生产环境关闭 Source Map
    outDir: "dist", // 输出目录
    rollupOptions: {
      output: {
        manualChunks: {
          vue: ["vue"], // 分包策略
        },
      },
    },
  },
  envPrefix: "VITE_", // 支持 VITE_ 开头的环境变量
});
