package com.jdsbbmq.wjxbx.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.PathItem;
import io.swagger.v3.oas.models.Paths;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
//@EnableWebMvc // 启用 SpringMVC
public class SwaggerConfig {

    @Bean
    public OpenAPI springOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("SpringDoc API Test")
                        .description("SpringDoc Simple Application Test")
                        .version("0.0.1"))
                .paths(new Paths().addPathItem("/com/jdsbbmq/wjxbx/controller", new PathItem())); // 添加指定路径配置
    }

}
