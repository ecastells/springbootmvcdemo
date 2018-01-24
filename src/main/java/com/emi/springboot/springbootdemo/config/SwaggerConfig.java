package com.emi.springboot.springbootdemo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Created by Emi on 22/01/2018.
 * http://localhost:8080/swagger-ui.html#/
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket productApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.emi.springboot.springbootdemo.controller"))
                .paths(PathSelectors.regex("/api.*"))
                .build()
                .apiInfo( metaData());

    }

    private ApiInfo metaData() {
        return new ApiInfo(
                "SpringBootDemo - Backend Service Rest Api",
                " REST API for SpringBootDemo Project",
                "1.0", "Terms of service",
                new Contact("Emiliano Castells", "","emi.castells@gmail.com"),
                "",
                "");
    }
}