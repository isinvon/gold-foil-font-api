#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""
Author: sinvon
Date: 2024-12-20

此脚本用于通过容器 ID 停止、删除 Docker 容器并删除容器所使用的镜像。
使用方法：
1. 执行脚本时，传入容器的名称或 ID 作为参数：
   python stop_remove_container_image.py <container_id_or_name>

功能说明：
- 停止容器：执行 docker container stop 命令。
- 删除容器：执行 docker container rm 命令。
- 删除镜像：根据容器的镜像 ID 执行 docker image rm 命令。

请注意：此脚本会删除容器及其镜像，执行时请谨慎操作。
"""

import json
import subprocess
import sys


def run_command(command):
    """执行 Docker 命令并返回结果"""
    try:
        result = subprocess.run(
            command, shell=True, check=True, stdout=subprocess.PIPE, stderr=subprocess.PIPE)
        return result.stdout.decode()
    except subprocess.CalledProcessError as e:
        print(f"错误: {e.stderr.decode()}")
        sys.exit(1)


def get_image_id(container_id):
    """通过容器 ID 获取镜像 ID"""
    command = f"docker inspect --format '{{{{.Config.Image}}}}' {container_id}"
    image_id = run_command(command).strip()
    return image_id


def main():
    """脚本主逻辑"""
    if len(sys.argv) != 2:
        print("请提供容器的名称或ID作为参数.")
        sys.exit(1)

    container_name_or_id = sys.argv[1]

    # 停止容器
    stop_command = f"docker container stop {container_name_or_id}"
    print(f"执行命令: {stop_command}")
    run_command(stop_command)

    # 删除容器
    remove_container_command = f"docker container rm {container_name_or_id}"
    print(f"执行命令: {remove_container_command}")
    run_command(remove_container_command)

    # 获取镜像 ID
    image_id = get_image_id(container_name_or_id)
    print(f"容器 {container_name_or_id} 使用的镜像 ID: {image_id}")

    # 删除镜像
    remove_image_command = f"docker image rm {image_id}"
    print(f"执行命令: {remove_image_command}")
    run_command(remove_image_command)


if __name__ == "__main__":
    main()
