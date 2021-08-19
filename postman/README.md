# 使用newman+postman在docker中运行自动化接口测试
## 一、准备工作
### 1 - api接口服务

本目录下提供了基于Spring Boot编写的最基础的Restful风格api服务。

### 2 - postman脚本

使用postman的collection功能，记录接口脚本，导出后得到如下文件：
    
    - xxx.postman_collection.json
    - xxx.postman_environment.json

### 3 - 公开postman collection（可选）
公开地址：https://www.getpostman.com/collections/06021083e32874319f67

这里，需要启动接口应用程序（我的应用未发布到外网，所以使用了内网穿透：http://devops-tool.vaiwan.com）
    
## 二、在Docker下执行接口测试
 
以下三种命令都可以正常运行

**注意，需要挂载/etc/newman的地方**
* 1. 指定本地collection，如xxx.postman_collection.json
    > user-manager.postman_collection.json

* 2. 指定本地environment，如xxx.postman_environment.json
    > --environment=vaiwan.postman_environment.json
* 3. 使用--reporters导出测试报告
    > --reporters junit --reporter-junit-export="newman-report.xml"
    >
    > --reporters json --reporter-json-export newman-report.json
* 4. 指定本地data file，详情参考：[using data file](/external/README.md)
    > --iteration-data holiday.csv 
### 1 - 执行公开的collection
```shell
docker run --rm -t postman/newman run "https://www.getpostman.com/collections/06021083e32874319f67"
```
### 2 - 执行公开的collection，传递environment(无法通过url传递)
```shell
docker run --rm -v D:\workspace\devops-tool\postman:/etc/newman -t postman/newman run "https://www.getpostman.com/collections/06021083e32874319f67" --insecure --environment=vaiwan.postman_environment.json --reporters junit --reporter-junit-export="newman-report.xml"
```

### 3 - 执行本地xxx.postman_collection.json
```shell
docker run --rm -v D:\workspace\devops-tool\postman:/etc/newman -t postman/newman run user-manager.postman_collection.json --insecure --environment=vaiwan.postman_environment.json   --reporters junit --reporter-junit-export="newman-report.xml"
```

### 4 - 使用--reporters导出测试报告
格式：xml/json
```
 --reporters junit --reporter-junit-export="newman-report.xml"

 --reporters json --reporter-json-export newman-report.json
```

上面用到了newman的run命令，更多命令选项可以参考：[command-line-integration-with-newman](https://learning.postman.com/docs/running-collections/using-newman-cli/command-line-integration-with-newman/)

参考：
* [Postman/Newman+Docker+Jenkins/Pipeline 做接口自动化测试](https://blog.csdn.net/galen2016/article/details/106839394)
* [docker-hub postman/newman](https://hub.docker.com/r/postman/newman)
* [Newman with Docker](https://learning.postman.com/docs/running-collections/using-newman-cli/newman-with-docker/)

## 三、延伸阅读
postman在接口测试这块还是挺强大的，以前用过全局/局部变量，environments，Pre-request Script,Tests
[Postman高级教程][Postman-Tutorial]让我对它有了更深的理解：

- [拦截重发HTTP请求](https://blog.csdn.net/Al_assad/article/details/81370171)
    > 类似工具有fildder

- [导入csv/json外部数据](https://blog.csdn.net/Al_assad/article/details/81370183)

    > **csv/json外部数据可以实现接口多场景参数的传递（多测试用例）**

- [使用xmysql链接mysql数据库](https://blog.csdn.net/Al_assad/article/details/81370196)
   
    > **postman发送请求后，断言过程中，将接口返回数据与数据库直接对比，xmysql可以直接以restApi方式读取数据库表数据**
    > 
    > 也可查看：[postman 使用 xmysql 连接 mysql 的基本操作](https://blog.csdn.net/adorable_/article/details/111703638)

    ps：
        xmysql也可以用nocodb来实现，关于nocodb信息，参考下面的链接。

[Postman-Tutorial]: https://blog.csdn.net/al_assad/category_7902146.html

参考：
* [github-nocodb](https://github.com/nocodb/nocodb)
* [github-nocodb-install-with-docker-cmopse](https://github.com/nocodb/nocodb/blob/master/docker-compose/mysql/docker-compose.yml)