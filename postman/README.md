# 使用newman+postman在docker中运行自动化接口测试
## 一、准备工作
本目录下提供了基于Spring Boot编写的最基础的Restful风格api服务。

## 二、在Docker下执行接口测试
```shell
# postman上公开的接口集合，需要启动接口应用程序（内网穿透：http://devops-tool.vaiwan.com）
# https://www.getpostman.com/collections/06021083e32874319f67

 
# 以下三种命令都可以正常运行
# 注意，需要挂载/etc/newman的地方：
# 1. 传递environment或xxx.postman_collection.json
# 2. 需要导出测试报告

# 一、执行公开的collection
docker run --rm -t postman/newman run "https://www.getpostman.com/collections/06021083e32874319f67"

# 二、执行公开的collection，传递environment(无法通过url传递)
docker run --rm -v D:\workspace\devops-tool\postman:/etc/newman -t postman/newman run "https://www.getpostman.com/collections/06021083e32874319f67" --insecure --environment=vaiwan.postman_environment.json --reporters junit --reporter-junit-export="newman-report.xml"

# 三、执行本地xxx.postman_collection.json
docker run --rm -v D:\workspace\devops-tool\postman:/etc/newman -t postman/newman run user-manager.postman_collection.json --insecure --environment=vaiwan.postman_environment.json   --reporters junit --reporter-junit-export="newman-report.xml"

# 四、使用--reporters导出测试报告（格式：xml/json）
 --reporters junit --reporter-junit-export="newman-report.xml"
 --reporters json --reporter-json-export newman-report.json
```

上面用到了newman的run命令，更多命令选项可以参考：[command-line-integration-with-newman](https://learning.postman.com/docs/running-collections/using-newman-cli/command-line-integration-with-newman/)

参考：
* [Postman/Newman+Docker+Jenkins/Pipeline 做接口自动化测试](https://blog.csdn.net/galen2016/article/details/106839394)
* [docker-hub postman/newman](https://hub.docker.com/r/postman/newman)
* [Newman with Docker](https://learning.postman.com/docs/running-collections/using-newman-cli/newman-with-docker/)