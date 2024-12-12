import os
import subprocess
import sys

# 设置变量
PROJECT_PATH = os.getcwd()
OUTPUT_DIR = os.path.join(PROJECT_PATH, 'output')
APP_NAME = 'gold-foil-font-api'
APP_VERSION = '0.0.1'
LAUNCHER_CLASS = 'com.sinvon.goldfoilfontapi.GoldFoilFontApiApplication'

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


# cd 到 gui下运行 pnpm build,然后在cd回去
run_command('cd gui && pnpm build && cd ..')

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
    f'--dest "{OUTPUT_DIR}" '
    f'--win-dir-chooser '  # 允许用户选择安装目录
)

run_command(jpackage_command)

print(f">>>Packaging succeeded! The executable is located in: {OUTPUT_DIR}")
