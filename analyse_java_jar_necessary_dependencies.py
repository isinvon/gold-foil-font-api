import os
import subprocess


def get_jar_files(path):
    """获取指定路径中的所有JAR文件"""
    jar_files = []
    for root, _, files in os.walk(path):
        for file in files:
            if file.endswith(".jar"):
                jar_files.append(os.path.join(root, file))
    return jar_files


def run_jdeps(jar_files, target_files):
    """运行jdeps命令"""
    classpath = ";".join(jar_files)  # 将所有JAR文件路径用分号连接
    command = [
        "jdeps",
        "--multi-release", "21",
        "-verbose",
        "--class-path", classpath,
        *target_files  # 将目标JAR文件传递给jdeps
    ]

    # 执行命令并捕获输出
    result = subprocess.run(command, stdout=subprocess.PIPE, stderr=subprocess.PIPE, text=True)
    if result.returncode == 0:
        print(result.stdout)
    else:
        print(f"Error: {result.stderr}")


if __name__ == "__main__":
    # 获取当前工作目录
    current_dir = os.path.abspath(os.getcwd())
    print(f"Current directory: {current_dir}")  # 打印当前目录，方便调试

    # 设置目标路径
    target_path = os.path.join(current_dir, "backend", "target", "*.jar")  # 目标JAR文件路径
    join = os.path.join(current_dir, "backend", 'target')
    print(f"Looking for JAR files in: {join}")  # 打印要查找的目录

    # 获取所有JAR文件
    jar_files = get_jar_files(os.path.join(current_dir, "backend", "target"))

    # 调试：打印找到的所有 JAR 文件
    if jar_files:
        print("Found JAR files:")
        for jar in jar_files:
            print(jar)
    else:
        print("No JAR files found.")

    # 如果有JAR文件，则运行jdeps命令
    if jar_files:
        run_jdeps(jar_files, [target_path])
    else:
        print("No JAR files found in target directory.")
