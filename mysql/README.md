# docker 运行mysql
## 一、docker run运行mysql
```shell
docker run --name mysql_db -e MYSQL_ROOT_PASSWORD=123456 -p 3306:3306 -d mysql:5.7
```

## 二、docker-compose运行mysql

```shell
# 单mysql
docker-compose up -d

# 带有phpadmin，可以使用web管理mysql
docker-compose -f .\docker-compose-phpadmin.yaml up -d
```