package com.dxj.config;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 读取项目相关配置
 */
@Data
@Component
@ConfigurationProperties(prefix = "keep-accounts")
public class KeepAccountConfig {
    private String title;
    private String description;
    private String name;
    private String version;
    private Boolean enabledSwagger;
    private String pathMapping;
}
