import subprocess
import os

def run_command(command):
    """运行 Shell 命令"""
    try:
        result = subprocess.run(command, shell=True, check=True, text=True, capture_output=True)
        return result.stdout.strip()  # 返回命令的输出结果
    except subprocess.CalledProcessError as e:
        print(f"命令执行失败: {e}")
        exit(1)

def create_daemon_json():
    """创建并更新 /etc/docker/daemon.json 文件"""
    daemon_json_path = "/etc/docker/daemon.json"
    
    # 创建目录
    print("正在创建 /etc/docker 目录...")
    run_command("sudo mkdir -p /etc/docker")
    
    # 写入配置到 daemon.json
    print("正在写入 Docker 配置...")
    config_content = '''{
  "registry-mirrors": [
    "https://dockerpull.org",
    "https://docker.1panel.dev",
    "https://docker.foreverlink.love",
    "https://docker.fxxk.dedyn.io",
    "https://docker.xn--6oq72ry9d5zx.cn",
    "https://docker.zhai.cm",
    "https://docker.5z5f.com",
    "https://a.ussh.net",
    "https://docker.cloudlayer.icu",
    "https://hub.littlediary.cn",
    "https://hub.crdz.gq",
    "https://docker.unsee.tech",
    "https://docker.kejilion.pro",
    "https://registry.dockermirror.com",
    "https://hub.rat.dev",
    "https://dhub.kubesre.xyz",
    "https://docker.nastool.de",
    "https://docker.udayun.com",
    "https://docker.rainbond.cc",
    "https://hub.geekery.cn",
    "https://docker.1panelproxy.com",
    "https://atomhub.openatom.cn",
    "https://docker.m.daocloud.io",
    "https://docker.1ms.run",
    "https://docker.linkedbus.com"
  ]
}'''
    
    # 使用 Python 写入内容到 daemon.json
    with open(daemon_json_path, 'w') as f:
        f.write(config_content)
    print(f"{daemon_json_path} 文件已成功创建并写入配置")
    
def cat_daemon_content():
    """打印 /etc/docker/daemon.json 文件的内容"""
    print("正在打印 daemon.json 文件内容...")
    content = run_command("sudo cat /etc/docker/daemon.json")
    print(content)

def reload_and_restart_docker():
    """重新加载 Docker 配置并重启 Docker 服务"""
    print("正在重新加载 Docker 配置并重启 Docker 服务...")
    run_command("sudo systemctl daemon-reload")
    run_command("sudo systemctl restart docker")
    print("Docker 服务已重启，配置已生效")

if __name__ == "__main__":
    # 创建并更新 daemon.json 配置
    create_daemon_json()
    
    # 重新加载配置并重启 Docker 服务
    reload_and_restart_docker()
    
    # 打印 daemon.json 文件内容
    cat_daemon_content()
    
    print("操作完成！")
