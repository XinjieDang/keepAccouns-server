package com.dxj.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author dxj
 * @Description 资源文件映射配置
 * @date 2023/2/26 20:38
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Value("${file.location}")
    private String uploadPath;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        /**
         * 资源映射路径
         * addResourceHandler：访问映射路径
         * addResourceLocations：资源绝对路径
         */registry.addResourceHandler("/upload/**").addResourceLocations("file:/" + this.uploadPath);
    }
}
