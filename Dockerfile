# Stage 1: Build the backend (Spring Boot)
FROM openjdk:21-jdk-slim as backend-builder

# Set working directory for backend build
WORKDIR /app

# Copy the backend source code to /temp in Docker
COPY ./backend /temp/backend

# Debug: List the contents of /temp to ensure backend code was copied
RUN ls -la /temp/backend

# Run Maven to build the backend (skip tests)
RUN cd /temp/backend && ./mvnw clean package -DskipTests

# Stage 2: Build the frontend (Vue)
FROM node:20 as frontend-builder

# Set working directory for frontend build
WORKDIR /app

# 安装 pnpm
RUN npm install -g pnpm

# Copy the frontend source code to /temp
COPY ./frontend /temp/frontend

# Install frontend dependencies and build the frontend
RUN cd /temp/frontend && pnpm install && pnpm build

# Stage 3: Create the final image
FROM openjdk:21-jdk-slim

# Set working directory for the final image
WORKDIR /app

# Copy the built backend .jar file from the backend-builder stage
COPY --from=backend-builder /temp/backend/target/backend.jar /app/backend/backend.jar

# Copy the built frontend dist folder to Nginx's default directory
COPY --from=frontend-builder /temp/frontend/dist /usr/share/nginx/html

# Install Nginx
RUN apt-get update && apt-get install -y nginx

# Copy the Nginx configuration file
COPY nginx.conf /etc/nginx/nginx.conf

# Remove unnecessary files to reduce image size
RUN rm -rf /temp

# Expose backend and frontend ports
# EXPOSE 8080 3000
EXPOSE 8080 80

# Use supervisord to start Nginx and the backend service
CMD ["/usr/bin/supervisord"]
