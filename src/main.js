const { app, BrowserWindow } = require('electron');
const path = require('path');
const {exec} = require("child_process");

// 判断当前的环境
const isDevelopment = process.env.NODE_ENV === 'development';

const createWindow = () => {
    const win = new BrowserWindow({
        width: 1000,
        height: 700,
        webPreferences: {
            nodeIntegration: false, // 推荐禁用 nodeIntegration
            contextIsolation: true, // 启用上下文隔离
            preload: path.join(__dirname, 'preload.js') // 可选：如果你需要主进程与渲染进程通信
        }
    });

    if (isDevelopment) {
        // 开发环境：加载 Vue 的开发服务器
        win.loadURL('http://localhost:5174');
    } else {
        // 生产环境：加载打包后的静态文件
        win.loadFile(path.join(app.getAppPath(), 'gui/dist/index.html'));
    }
};

app.whenReady().then(() => {
    // ==============jar启动========start========
    // 获取捆绑的jre的绝对路径
    const jrePath = path.join(app.getAppPath(), 'resources/java/jre/bin/java');
    // 获取当前程序jar的绝对路径
    const jarPath = path.join(app.getAppPath(), 'backend/target/backend.jar');
    // 使用捆绑的 JRE 启动后端
    const backend = exec(`"${jrePath}" -jar "${jarPath}"`);
    // ==============jar启动=========end========
    createWindow();

    app.on('activate', () => {
        if (BrowserWindow.getAllWindows().length === 0) {
            createWindow();
        }
    });
});

app.on('window-all-closed', () => {
    if (process.platform !== 'darwin') {
        app.quit();
    }
});
