# Step 1: Backend Build (Build the backend using Maven)
FROM eclipse-temurin:21-jdk-alpine AS backend-builder

WORKDIR /app
# Copy backend source code into the temporary directory
COPY ./backend /app/temp/backend

# Build the backend (skip tests) and output logs
RUN cd /app/temp/backend && ./mvnw clean package -DskipTests -X

# Debug: List files in the target directory to verify the JAR file exists
RUN ls -l /app/temp/backend/target/

# Debug: Check for specific .jar file
RUN find /app/temp/backend/target -name "*.jar"

# Ensure that the JAR file was actually built and exists
RUN if [ ! -f /app/temp/backend/target/*.jar ]; then echo "No JAR file found"; exit 1; fi

# Create the backend directory and move the built JAR file from temp directory to /app/backend
RUN mkdir -p /app/backend && mv /app/temp/backend/target/*.jar /app/backend/backend.jar

# Clean up the temporary directory
RUN rm -rf /app/temp

# Step 2: Frontend Build (Build the frontend using pnpm)
FROM node:20-alpine AS frontend-builder

WORKDIR /app
# Copy frontend code (make sure the package.json exists in this directory)
COPY ./frontend /app/temp/frontend

# Ensure the frontend directory contains the necessary files
WORKDIR /app/temp/frontend
# Install dependencies and build the frontend
RUN apk update && apk add --no-cache nginx npm && \
    npm install -g pnpm && \
    pnpm install && \
    pnpm build

# Copy the dist files to both the frontend folder and Nginx's HTML directory
RUN cp -r /app/temp/frontend/dist /app/frontend && \
    cp -r /app/temp/frontend/dist /usr/share/nginx/html

# Clean up the temporary directory
RUN rm -rf /app/temp

# Step 3: Final image setup
FROM alpine:latest

# Install Nginx and OpenJDK runtime environment
RUN apk update && apk add --no-cache nginx openjdk21

# Install fontconfig and ttf fonts
RUN apk add --no-cache \
    fontconfig \
    ttf-dejavu \
    ttf-droid \
    ttf-freefont \
    font-noto \
    libx11 \
    libxrender \
    cairo \
    harfbuzz \
    pango \
    libjpeg \
    libpng \
    xvfb \
    ttf-linux-libertine

# Debug: List installed fonts to ensure they are available
RUN fc-list

# Debug: Rebuild font cache
RUN fc-cache -fv

# Set JAVA_HOME and PATH environment variables
ENV JAVA_HOME=/usr/lib/jvm/java-21-openjdk
ENV PATH=$JAVA_HOME/bin:$PATH

# Copy the backend JAR and the Nginx configuration file
COPY --from=backend-builder /app/backend/backend.jar /app/backend/
COPY --from=frontend-builder /usr/share/nginx/html /usr/share/nginx/html
COPY ./nginx.conf /etc/nginx/nginx.conf

# Expose ports for backend (8080) and frontend (3000)
EXPOSE 8080 3000

# Command to run both backend and frontend
CMD ["sh", "-c", "java -jar /app/backend/backend.jar & nginx -g 'daemon off;'"]
