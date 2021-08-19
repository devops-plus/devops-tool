# nocodb安装
## 一、docker、sqlite运行nocodb
```shell
# nocodb默认使用sqlite存储数据，0.10.6版本后数据目录为：/usr/app/data/
docker run -d --name nocodb -v nocodb-data:/usr/app/data/ -p 8080:8080 nocodb/nocodb
```

## 二、docker-compose、mysql运行nocodb
具体内容参见docker-compose-mysql.yaml
```shell
docker-compose -f .\docker-compose-nocodb.yaml up -d
```

