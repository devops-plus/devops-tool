# 集成Knife4j

## pom.xml
```xml
<dependency>
    <groupId>com.github.xiaoymin</groupId>
    <artifactId>knife4j-spring-boot-starter</artifactId>
    <version>2.0.7</version>
</dependency>
```

## knife4j-配置
```java
package devops.xiecf.postman.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebMvc;

@Configuration
@EnableSwagger2WebMvc
public class Knife4jConfiguration {

    @Bean(value = "defaultApi2")
    public Docket defaultApi2() {
        Docket docket = new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(new ApiInfoBuilder()
                        //.title("swagger-bootstrap-ui-demo RESTful APIs")
                        .description("# swagger-bootstrap-ui-demo RESTful APIs")
                        .termsOfServiceUrl("http://www.xx.com/")
                        .contact(new Contact("xiecf", null, "xiecf-wh@qq.com"))
                        .version("1.0")
                        .build())
                //分组名称
                .groupName("2.X版本")
                .select()
                //这里指定Controller扫描包路径
                .apis(RequestHandlerSelectors.basePackage("devops.xiecf.postman.controller"))
                .paths(PathSelectors.any())
                .build();
        return docket;
    }
}
```

## RestController
参见UserController.java

## 启用knife4j

* application.properties
```properties
spring.profiles.active=dev
```

* application-dev.yaml
```yaml
knife4j:
  enable: true
  # 测试环境 开启Swagger的Basic认证功能,默认是false
  basic:
    enable: true
    # Basic认证用户名
    username: xiecf
    # Basic认证密码
    password: 123456
```
* application-prod.yaml
```yaml
knife4j:
  enable: true
  # 开启生产环境屏蔽 无法访问接口 /doc.html
  production: true
```

参考：
* [SpringBoot集成knife4j接口文档](https://blog.csdn.net/qq_41971087/article/details/115824175)