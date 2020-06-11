package com.songyang.api.swaggerconfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SwaggerConfig {

    @Bean
    public Docket buildDocket() {
        return  new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())//调用下面apiInfo()方法
                .select()
                //Controller所在路径
                .apis(RequestHandlerSelectors.basePackage("com.songyang.api.controller"))  // controller 所在包名
                .paths(PathSelectors.any())
                .build();
    }

    public ApiInfo apiInfo() {
        return  new ApiInfoBuilder()
                .title("springboot结合swagger2构建Restful API")
                .description("这是一个swagger2小型demo")
                .termsOfServiceUrl("localhost:8080")
                .version("0.0.1")
                .build();

    }

}