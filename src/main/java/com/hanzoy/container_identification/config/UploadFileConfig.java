package com.hanzoy.container_identification.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

@Configuration
public class UploadFileConfig extends WebMvcConfigurationSupport {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry){
        registry.addResourceHandler("/images/**").addResourceLocations("file:/A:/temp/jzx/");
        super.addResourceHandlers(registry);
        registry.addResourceHandler("/huiImages/**").addResourceLocations("file:/A:/code/Python/集装箱3/集装箱/集装箱/model/EAST/out/");
        super.addResourceHandlers(registry);
    }

}