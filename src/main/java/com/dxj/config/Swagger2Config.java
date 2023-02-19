package com.dxj.config;

import com.dxj.security.common.constants.SecurityConstants;
import com.github.xiaoymin.knife4j.spring.annotations.EnableKnife4j;
import io.swagger.annotations.ApiOperation;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import springfox.bean.validators.configuration.BeanValidatorPluginsConfiguration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;

/**
 * Knife4j增强版本的swagger的前端ui
 * 访问url http://localhost:8089/ka/doc.html
 */
@Configuration
@EnableSwagger2
@EnableKnife4j
@Import(BeanValidatorPluginsConfiguration.class)
public class Swagger2Config {
    @Resource
    private KeepAccountConfig keepAccountConfig;

    /**
     * 构建Docket对象
     *
     * @return
     */
    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                //是否启用swagger
                .enable(keepAccountConfig.getEnabledSwagger())
                //接口文档中显示的api基本信息
                .apiInfo(apiInfo())
                //设置哪些接口暴露给Swagger展示
                .select()
                //扫描所有注解的api
                .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
                .paths(PathSelectors.any())
                .build()
                .securityContexts(securityContext())
                .securitySchemes(securitySchemes());
        //.pathMapping(keepAccountConfig.getPathMapping());
    }

    private List<SecurityScheme> securitySchemes() {
        return Collections.singletonList(new ApiKey("JWT", SecurityConstants.TOKEN_HEADER, "header"));
    }

    private List<SecurityContext> securityContext() {
        SecurityContext securityContext = SecurityContext.builder()
                .securityReferences(defaultAuth())
                .build();
        return Collections.singletonList(securityContext);
    }

    List<SecurityReference> defaultAuth() {
        AuthorizationScope authorizationScope
                = new AuthorizationScope("global", "accessEverything");
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
        authorizationScopes[0] = authorizationScope;
        return Collections.singletonList(new SecurityReference("JWT", authorizationScopes));
    }

    /**
     * 创建api文档信息
     *
     * @return
     */
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                //标题
                .title(keepAccountConfig.getTitle())
                //描述
                .description(keepAccountConfig.getDescription())
                //作者信息
                .contact(new Contact(keepAccountConfig.getName(), null, null))
                .version("版本号：" + keepAccountConfig.getVersion())
                .build();
    }
}
