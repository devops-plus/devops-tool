# 使用flyway执行sql脚本变更

## 一、Docker
```
# 启动mysql容器，使用docker inspect docker-mysql获取ip（172.17.0.3)，创建数据库（test，可选）
docker run -itd --name docker-mysql --hostname docker-mysql -p 3308:3306 -e MYSQL_ROOT_PASSWORD=123456 mysql:5.7

# 关于挂载：/flyway/sql和/flyway/conf 必须用绝对路径（注意对比下文的docker-compose，使用相对路径就好了）

# -url如果没有指定数据库（这里是test),将会根据schemas名自动创建
docker run --rm -v /absolute-path/sql:/flyway/sql  -e FLYWAY_EDITION=community flyway/flyway  -url=jdbc:mysql://172.17.0.3:3306/test -schemas=test -user=root -password=123456 migrate -X

# 或在flyway.conf中指定目标数据库
docker run --rm -v /absolute-path/sql:/flyway/sql -v /absolute-path/conf:/flyway/conf -e FLYWAY_EDITION=community flyway/flyway migrate -X
```

### flyway.conf

flyway.conf：加载配置文件遵循下列顺序，并且后面加载的配置会覆盖前面的配置。

上面用到的flyway.conf内容如下

```shell
flyway.url=jdbc:mysql://172.17.0.3:3306/test
flyway.user=root
flyway.password=123456
# flyway.schemas=test
```

> <install-dir>/conf/flyway.conf
>
> <user-home>/flyway.conf
>
> <current-dir>/flyway.conf
>
文件中除了配置数据库信息，还能对比如prefix、separator、suffix做定义，默认prefix是V（执行一遍，并且版本号唯一，如果有重复就会报错）或R（重复执行，不需要版本号），separator是双下划线__，suffix是.sql。

更多配置属性参考：[flyway从入门到精通（四）：flyway配置属性详解](https://www.jianshu.com/p/2c5679f268f9)

## 二、Docker Compose
docker-compose.yaml

```shell
version: '3'
services:
  flyway:
    image: flyway/flyway
    command: -url=jdbc:mysql://db -schemas=test -user=root -password=123456 -connectRetries=60 migrate
    volumes:
      - ./sql:/flyway/sql
    depends_on:
      - db
  db:
    image: mysql
    command: --default-authentication-plugin=mysql_native_password --character-set-server=utf8mb4 --collation-server=utf8mb4_unicode_ci
    ports:
      - 3308:3306
    environment:
      - MYSQL_ROOT_PASSWORD=123456
      - MYSQL_ROOT_HOST=%
      - MYSQL_DATABASE=test
    security_opt:
      - seccomp=unconfined
    healthcheck:
      test: ["CMD-SHELL", "nc -z 127.0.0.1 3306 || exit 1"]
      interval: 1m30s
      timeout: 60s
      retries: 6
```

关键点说明：
* `flyway/flyway`，使用的是flyway team version，好像是收费的，默认30天免费试用。
* db服务会自动创建test数据库
* `docker-compose up` 即可将sql目录中的脚本执行完毕。
* `/flyway/sql`为flyway的sql脚本目录，可以通过volume挂载方式传递，sql文件命名规则如下：
  - 仅需要被执行一次的SQL命名以大写的"V"开头，后面跟上"0~9"数字的组合,数字之间可以用“.”或者下划线"_"分割开，然后再以两个下划线分割，其后跟文件名称，最后以.sql结尾。比如，V2.1.5__create_user_ddl.sql、V4.1_2__add_user_dml.sql。
  - V后面的数字不能重复，最好加上日期。
  - 可重复运行的SQL，则以大写的“R”开头，后面再以两个下划线分割，其后跟文件名称，最后以.sql结尾。。比如，R__update_user_dml.sql。
  - **综上，V开头的脚本不可重复编辑后执行，R开头的脚本可以重复编辑执行。**

参考：
* [flyway-docker](https://github.com/flyway/flyway-docker)
* [mysql - 将Flyway添加到MySQL Docker容器](https://www.coder.work/article/2485225)
* [flywaydb.org](https://flywaydb.org/documentation/usage/commandline/#download-and-installation)
* [SpringBoot使用flayway自动执行数据库升级脚本](https://cloud.tencent.com/developer/article/1399841)
* [Flyway快速上手教程](https://www.jianshu.com/p/567a8a161641)