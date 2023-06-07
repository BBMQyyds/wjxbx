package com.jdsbbmq.wjxbx.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
@Configuration //配置类
public class SwaggerConfig {
    @Bean
    public OpenAPI springOpenAPI() {
        return new OpenAPI().info(new Info() //
                .title("SpringDoc API Test") //
                .description("SpringDoc Simple Application Test") //
                .version("0.0.1"));
    }
}
