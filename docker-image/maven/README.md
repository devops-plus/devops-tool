# 自定义Maven镜像
## 缘由
官方maven镜像通常由于maven中心库被墙和无法配置私有maven仓库的问题带来各种不便，因此自定义maven在所难免。

## 思路
1. 继承官方maven仓库

2. 更改系统更新源`sources.list`、使用自定义`setting.xml`等配置文件（使用国内maven镜像仓库）

3. 打包，上传到制品库

    ```shell
    # 打包
    docker build -t  devops-tool/maven:3.5.2-jdk-8-alpine .

    # 上传
    docker tag devops-tool/maven:3.5.2-jdk-8-alpine registry.cn-shenzhen.aliyuncs.com/devops-tool/maven:3.5.2-jdk-8-alpine

    docker login --username=xxx@qq.com registry.cn-shenzhen.aliyuncs.com
    
    docker push registry.cn-shenzhen.aliyuncs.com/devops-tool/maven:3.5.2-jdk-8-alpine 
    ```

    一步到位：
    ```shell
    docker build -t  registry.cn-shenzhen.aliyuncs.com/devops-tool/maven:3.5.2-jdk-8-alpine .

    docker login --username=xxx@qq.com registry.cn-shenzhen.aliyuncs.com

    docker push registry.cn-shenzhen.aliyuncs.com/devops-tool/maven:3.5.2-jdk-8-alpine 
    ```
4. 使用自定义maven镜像

    ```shell
    docker pull registry.cn-shenzhen.aliyuncs.com/devops-tool/maven:3.5.2-jdk-8-alpine 
    ```

参考：
* [docker-hub-maven](https://hub.docker.com/_/maven/?tab=tags&page=1&ordering=last_updated)
* [AliyunContainerService-maven-image](https://github.com/AliyunContainerService/maven-image)
* [创建自定义maven的docker镜像](https://blog.csdn.net/rentian1/article/details/109702886)