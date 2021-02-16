package com.hhq.common_sdk.swagger2;


import com.hhq.common_sdk.pom_model.PomModelSingleton;
import org.apache.maven.model.Model;
import org.springframework.beans.factory.annotation.Value;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.Objects;

import static com.hhq.common_sdk.swagger2.SwaggerDeafultInfo.*;

/*
* 继承的模块在激动类同级新增配置类并调用createRestApi注册bean
* 配置yml可修改其他信息
*/
public abstract class SwaggerDefaultConfig {


    @Value("${swagger.title:cloud组件接口文档}")
    protected String title;

    @Value("${swagger.desc:cloud组件接口文档}")
    protected String desc;

    @Value("${swagger.cpackage:com.hhq}")
    protected String cpackage;


    protected Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                // 详细信息定制
                .apiInfo(apiInfo())
                .select()
                // 指定当前包路径
                .apis(RequestHandlerSelectors.basePackage(cpackage))
                // 扫描所有 .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build();
    }

    /**
     * 添加摘要信息
     */
    private ApiInfo apiInfo() {
        Model instance = PomModelSingleton.getInstance();
        // 用ApiInfoBuilder进行定制
        return new ApiInfoBuilder()
                .title(title)
                .description(desc)
                .contact(new Contact(CONTACT_USER,CONTACT_URL,CONTACT_EMAIL))
                .version(Objects.nonNull(instance)?instance.getVersion(): "1.0")
                .build();
    }
}
