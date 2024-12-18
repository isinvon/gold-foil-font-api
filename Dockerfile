# 使用 Node.js 20 作为基础镜像
FROM node:20

# 安装 JDK 21
RUN apt-get update && apt-get install -y openjdk-21-jdk

# 设置工作目录
WORKDIR /app

# 复制前后端源代码
COPY . /app

# 安装 Spring Boot 和前端依赖
# 构建 Spring Boot 应用
RUN ./mvnw clean package -DskipTests

# 安装前端依赖
WORKDIR /app/gui
RUN npm install

# 构建前端应用
RUN npm run build

# 安装 serve 和 supervisord
RUN npm install -g serve && apt-get install -y supervisor

# 将 supervisord 配置文件复制到容器中
COPY ./supervisord.conf /etc/supervisor/conf.d/supervisord.conf

# 暴露前端和后端端口
EXPOSE 8080 3000

# 使用 supervisord 启动所有服务
CMD ["/usr/bin/supervisord"]
