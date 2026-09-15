# ==========================================
# Stage 1: Build React SPA using Node 20
# ==========================================
FROM node:20-alpine AS build
WORKDIR /app

# Copy dependency manifests
COPY package*.json ./
RUN npm install

# Accept build arguments for environment variables
ARG VITE_API_BASE_URL=http://localhost:8080/api/v1
ENV VITE_API_BASE_URL=$VITE_API_BASE_URL

# Copy source code and build
COPY . .
RUN npm run build

# ==========================================
# Stage 2: Serve using Nginx Alpine
# ==========================================
FROM nginx:alpine

# Copy built static files to Nginx public directory
COPY --from=build /app/dist /usr/share/nginx/html

# Copy custom Nginx configuration for React Router SPA
COPY nginx.conf /etc/nginx/conf.d/default.conf

EXPOSE 80

CMD ["nginx", "-g", "daemon off;"]
