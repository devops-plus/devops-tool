# nocodb使用说明
## 一、nocodb安装
### 1 - docker、sqlite运行nocodb
```shell
# nocodb默认使用sqlite存储数据，0.10.6版本后数据目录为：/usr/app/data/
docker run -d --name nocodb -v nocodb-data:/usr/app/data/ -p 8080:8080 nocodb/nocodb

#或使用docker-compose
docker-compose up -d
```

### 2 - docker-compose、mysql运行nocodb
具体内容参见docker-compose-mysql.yaml

```shell
docker-compose -f .\docker-compose-mysql.yaml up -d
```

### 3 - docker-compose、postgres运行nocodb
具体内容参见docker-compose-postgres.yaml

```shell
docker-compose -f .\docker-compose-postgres.yaml up -d 
```

## 二、配置nocodb

## 三、通过nocodb直接访问mysql
参考：
* [nocodb APIs Access](https://docs.nocodb.com/setup-and-usages/apis-access)
* [nocodb REST APIs](https://docs.nocodb.com/developer-resources/rest-apis)
