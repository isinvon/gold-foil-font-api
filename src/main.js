const {app, BrowserWindow} = require('electron');
const path = require('path');
const {spawn} = require('child_process');
const fs = require('fs');
const kill = require('tree-kill');

let backendProcess; // 用于存储后端子进程
const isDevelopment = process.env.NODE_ENV === 'development';

// 日志路径配置
const userDataPath = app.getPath('userData');
const logFilePath = path.join(userDataPath, 'backend.log');
const logStream = fs.createWriteStream(logFilePath, {encoding: 'utf8', flags: 'a'});

function logToFile(message) {
    logStream.write(`[${new Date().toISOString()}] ${message}\n`);
}

// 创建 Electron 窗口
function createWindow() {
    const win = new BrowserWindow({
        width: 1000,
        height: 700,
        autoHideMenuBar: true, // 自动隐藏菜单栏
        icon: path.join(app.getAppPath(), 'gui/dist/favicon.ico'), // 设置图标(这里和应用程序图标不一样,这是标题的图标,因为package.json中用了files捆绑了dist,所以这里用app.getAppPath获取)
        webPreferences: {
            nodeIntegration: false,
            contextIsolation: true,
            preload: path.join(__dirname, 'preload.js'),
        },
    });

    if (isDevelopment) {
        win.loadURL('http://localhost:5174');
    } else {
        win.loadFile(path.join(app.getAppPath(), 'gui/dist/index.html'));
    }
}

// 启动后端服务
function startBackend() {
    return new Promise((resolve, reject) => {

        let jrePath = null;
        let jarPath = null;

        if (isDevelopment) { // 开发环境
            jrePath = path.join(app.getAppPath(), 'resources/java/jre/bin/java');
            jarPath = path.join(app.getAppPath(), 'backend/target/backend.jar');
        } else { // 生产环境
            const resourcesPath = process.resourcesPath;
            jrePath = path.join(resourcesPath, 'resources/java/jre/bin/java');
            jarPath = path.join(resourcesPath, 'backend/target/backend.jar');
        }
        const preferredPort = 8080;

        logToFile(`Attempting to start backend on port ${preferredPort}`);

        backendProcess = spawn(jrePath, ['-jar', jarPath, `--server.port=${preferredPort}`]);

        backendProcess.stdout.on('data', (data) => {
            const message = data.toString();
            logToFile(`Backend stdout: ${message}`);
            if (message.includes('Started')) {
                resolve(preferredPort);
            }
        });

        backendProcess.stderr.on('data', (data) => {
            const error = data.toString();
            logToFile(`Backend stderr: ${error}`);
        });

        backendProcess.on('error', (err) => {
            logToFile(`Failed to start backend: ${err.message}`);
            reject(err);
        });

        backendProcess.on('close', (code) => {
            logToFile(`Backend process exited with code ${code}`);
        });
    });
}

// 停止后端服务
function stopBackend() {
    return new Promise((resolve) => {
        if (backendProcess) {
            kill(backendProcess.pid, 'SIGKILL', () => {
                logToFile('Backend process and child processes terminated.');
                resolve();
            });
        } else {
            resolve();
        }
    });
}
// 主进程启动逻辑
app.whenReady().then(() => {
    startBackend()
        .then((port) => {
            logToFile(`Backend started successfully on port ${port}`);
            createWindow();
        })
        .catch((error) => {
            logToFile(`Failed to start backend: ${error.message}`);
            app.quit();
        });

    app.on('activate', () => {
        if (BrowserWindow.getAllWindows().length === 0) {
            createWindow();
        }
    });
});

// 应用关闭时清理资源
app.on('window-all-closed', () => {
    if (process.platform !== 'darwin') {
        stopBackend().then(() => {
            app.quit();
        });
    }
});

// 优雅退出
app.on('will-quit', (event) => {
    event.preventDefault();
    stopBackend().then(() => {
        app.quit();
    });
});
