# see https://docs.oracle.com/en/java/javase/21/docs/specs/man/jpackage.html
import os
import subprocess
import sys

# 设置变量
PROJECT_PATH = os.getcwd()
OUTPUT_DIR = os.path.join(PROJECT_PATH, 'output')
APP_NAME = 'gold-foil-font-api'
APP_DECRIPTION = 'gold-foil-font-api is a program to convert fonts into images and SVG'
APP_VERSION = '0.0.1'
LAUNCHER_CLASS = 'com.sinvon.goldfoilfontapi.GoldFoilFontApiApplication'
HELP_URL = 'https://github.com/isinvon/gold-foil-font'
UUID = '41e8b4b3-cee4-4c66-9a22-2b1d320c3be9'

# 检查 output 文件夹是否存在，若不存在则创建
if not os.path.exists(OUTPUT_DIR):
    os.makedirs(OUTPUT_DIR)


# 检查 jpackage 是否可用


def check_jpackage():
    try:
        subprocess.run(['jpackage', '--version'], check=True,
                       stdout=subprocess.PIPE, stderr=subprocess.PIPE)
    except subprocess.CalledProcessError:
        print(">>> jpackage is not available. Please ensure JDK 14 or higher is installed.")
        sys.exit(1)


check_jpackage()


# 执行命令


def run_command(command):
    print(f">>>Running command: {command}")
    result = subprocess.run(command, shell=True, cwd=PROJECT_PATH)
    if result.returncode != 0:
        print(f">>>Command failed: {command}")
        sys.exit(1)


# cd 到 gui下运行 pnpm build --emptyOutDir, 然后在cd回去
run_command('cd gui && pnpm build --emptyOutDir && cd ..')

# 清理项目
run_command('mvn clean')

# 清理项目
run_command('mvn clean')

# 打包项目
run_command('mvn package -DskipTests')

# 自动检索 JAR 文件
jar_file = None
target_dir = os.path.join(PROJECT_PATH, 'target')

for file in os.listdir(target_dir):
    if file.endswith('.jar'):
        print(f">>>Found JAR file: {file}")
        jar_file = os.path.join(target_dir, file)
        break

if not jar_file:
    print(">>>No JAR file found in target directory!")
    sys.exit(1)

# 构造 jpackage 命令
jpackage_command = (
    f'jpackage '
    f'--type exe '
    f'--input "{target_dir}" '
    f'--main-jar "{jar_file}" '
    # f'--main-class "{LAUNCHER_CLASS}" '  # 指定主类 (springboot默认已经设置了main-class,无需自己去设置)
    f'--name "{APP_NAME}" '
    f'--app-version "{APP_VERSION}" '
    f'--description "{APP_DECRIPTION}" '
    f'--dest "{OUTPUT_DIR}" '
    f'--win-dir-chooser '  # 允许用户选择安装目录
    f'--win-help-url "{HELP_URL}" '  # 帮助链接
    # --win-menu 和 --win-menu-group <menu-group-name> 使用说明看底部注释
    # f'--win-menu'  # 开启菜单
    # f'--win-menu-group "GoldFoilFont" '  # 菜单组名称
    f'--win-shortcut-prompt '  # 添加一个对话框，使用户能够选择是否将快捷方式 由 installer 创建
    f'--win-per-user-install '  # 安装为每个用户
    f'--win-menu '  # 在菜单中创建快捷方式
    f'--win-shortcut '  # 在桌面创建快捷方式
    # f'--win-console ' # 启用控制台
    f'--icon gui/public/favicon.ico '  # 图标
    # f'--verbose ' # 显示详细日志
    f'--win-upgrade-uuid "{UUID}" '  # 与此程序包的升级关联的 UUID, uuid 要带有破折号
)

run_command(jpackage_command)

print(f">>>Packaging succeeded! The executable is located in: {OUTPUT_DIR}")

"""
jpackage 的 --win-menu-group 参数用于在创建 Windows 安装程序时指定快捷方式所属的 "开始菜单" 分组。通过指定一个菜单组名称，可以将应用程序的快捷方式归类到特定的文件夹中，例如 "公司名称" 或 "应用程序类别"。

jpackage --type app-image \
         --input <path-to-input> \
         --main-jar <main-jar-file> \
         --name <application-name> \
         --win-menu \
         --win-menu-group <menu-group-name>

参数说明
--win-menu-group: 指定快捷方式所属的 "开始菜单" 文件夹。
menu-group-name: 这是一个字符串，表示菜单组名称，例如 "MyCompany\MyApplications"。如果不提供此参数，默认会将快捷方式放在顶层菜单中。

示例
场景：公司名为 "TechCorp"，应用名为 "MyApp"。
以下命令会将快捷方式放置在 "开始菜单" 的 TechCorp 文件夹中。
    
    jpackage --type exe \
             --input ./input \
             --main-jar myapp.jar \
             --name MyApp \
             --win-menu \
             --win-menu-group TechCorp
             
运行后：

安装完成后，用户可以在 "开始菜单 -> TechCorp" 文件夹中找到 MyApp 的快捷方式。
嵌套分组
如果需要更精确的分组，可以使用嵌套路径。例如：

--win-menu-group "TechCorp\\Utilities"
这会将快捷方式放置在 TechCorp 文件夹下的 Utilities 子文件夹中。

--win-menu-group 需要搭配 --win-menu 一起使用：只有启用了 --win-menu，--win-menu-group 参数才会生效。
分组名称的格式：确保分组名称是一个有效的路径字符串，不包含非法字符（如 \ / : * ? " < > | 等）。
默认行为：如果未指定 --win-menu-group，快捷方式会直接出现在 "开始菜单" 的根目录下。
"""
