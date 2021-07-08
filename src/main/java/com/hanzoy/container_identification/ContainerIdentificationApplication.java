package com.hanzoy.container_identification;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.hanzoy.container_identification.mapper")
public class ContainerIdentificationApplication {

    public static void main(String[] args) {
        SpringApplication.run(ContainerIdentificationApplication.class, args);
    }

}
