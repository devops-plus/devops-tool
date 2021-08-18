## 一、postman设置

* api-url:
  
  get  http://timor.tech/api/holiday/info/{{date}}


* Pre-request Script

    ![打印参数](images/postman-01.png)

* Tests

    ![设置断言](images/postman-02.png)

    ```js
    var jsonData = JSON.parse(pm.response.text())
    // console.log(jsonData)

    pm.test("Body matches holiday type:"+data.type, function(){
        pm.expect(jsonData.type.type).to.eq(data.type)
    });

    pm.test("Body matches holiday name："+data.name, function(){
        pm.expect(jsonData.type.name).eq(data.name)
    });

    pm.test("Body matches holiday week："+data.week, function(){
        pm.expect(jsonData.type.week).eq(data.week)
    });
    ```

## 二、准备数据
holiday.csv
```csv
date,type,name,week
2021-8-18,0,周三,3
2021-8-19,0,周四,4
2021-8-20,0,周五,5
2021-8-21,1,周六,6
2021-8-22,1,周日,7
2021-8-23,0,周一,1
```

## 三、使用runner 执行collection
选择数据文件、迭代次数、迭代间隔时间
 ![选择数据文件](images/postman-03.png)

查看断言结果
 ![查看断言结果](images/postman-04.png)

参考：
* [postman 详细使用 (4)：导入 csv、json 外部数据](https://blog.csdn.net/Al_assad/article/details/81370183)
* [Using CSV and JSON Data Files in the Postman Collection Runner](https://blog.postman.com/using-csv-and-json-files-in-the-postman-collection-runner/)
* [Looping through a Data File in the Postman Collection Runner](https://blog.postman.com/looping-through-a-data-file-in-the-postman-collection-runner/)