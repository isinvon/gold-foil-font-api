# 使用基础镜像
FROM openjdk:21-jdk-slim

# 设置工作目录
WORKDIR /app

# 将项目的 JAR 文件复制到镜像中
COPY target/gold-foil-font-api.jar app.jar

# 暴露端口（与应用程序中配置的端口一致）
EXPOSE 8080

# 设置运行命令
ENTRYPOINT ["java", "-jar", "app.jar"]
