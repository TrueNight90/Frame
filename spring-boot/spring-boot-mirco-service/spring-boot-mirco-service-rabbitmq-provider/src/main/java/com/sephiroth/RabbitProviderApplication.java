package com.sephiroth;

import org.apache.dubbo.config.spring.context.annotation.DubboComponentScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@DubboComponentScan(basePackages = "com.sephiroth.service")
public class RabbitProviderApplication {
    public static void main(String[] args) {
        SpringApplication.run(RabbitProviderApplication.class);
    }
}
