# 自定义Maven镜像
## 缘由
官方maven镜像通常由于maven中心库被墙和无法配置私有maven仓库的问题带来各种不便，因此自定义maven在所难免。

## 思路
1. 继承官方maven仓库
2. 外挂setting.xml等配置文件（使用国内maven镜像仓库）
3. 打包，上传到制品库
4. 使用自定义maven镜像


参考：
* [docker-hub-maven](https://hub.docker.com/_/maven/?tab=tags&page=1&ordering=last_updated)
* [创建自定义maven的docker镜像](https://blog.csdn.net/rentian1/article/details/109702886)