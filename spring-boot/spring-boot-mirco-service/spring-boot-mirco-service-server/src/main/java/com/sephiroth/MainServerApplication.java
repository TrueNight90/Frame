package com.sephiroth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class MainServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(MainServerApplication.class);
    }
}
