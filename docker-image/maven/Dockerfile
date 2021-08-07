FROM maven:3.8.1-openjdk-8-slim
MAINTAINER xiecf <xiecf_wh@qq.com>

# 更改源为国内阿里镜像源
COPY sources.list /etc/apt/sources.list

# 更改maven中心仓库
COPY settings.xml /usr/share/maven/conf/
