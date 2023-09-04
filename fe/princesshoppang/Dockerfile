# FROM node:16-alpine

# WORKDIR /react-vite-app

# EXPOSE 3000

# COPY package.json package-lock.json ./

# RUN npm install --silent

# COPY . ./

# CMD ["npm", "run", "dev"]

FROM nginx

# RUN mkdir /react-vite-app

WORKDIR /react-vite-app

RUN mkdir ./build

# spellit에 있는 build 배포파일을 생성한 image의 build 디렉터리로 복사
COPY ./dist ./build

# nginx 의 default.conf 를 삭제
RUN rm /etc/nginx/conf.d/default.conf

# git nginx.conf 를 아래 경로에 복사
COPY ./nginx/ph.conf /etc/nginx/conf.d

EXPOSE 3000

CMD ["nginx", "-g", "daemon off;"]