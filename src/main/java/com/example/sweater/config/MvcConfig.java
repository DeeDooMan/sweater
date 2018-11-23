package com.example.sweater.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

//Этот класс содержит конфигурацию нашего веб слоя
@Configuration
public class MvcConfig implements WebMvcConfigurer {
    @Value("${upload.path}")
    private String uploadPath;

    public void addViewControllers(ViewControllerRegistry registry) {

        //Убрали 3 строки так как у нас есть такой маппинг, нужен только /login
       // registry.addViewController("/home").setViewName("home");
       // registry.addViewController("/").setViewName("home");
       // registry.addViewController("/hello").setViewName("hello");
        registry.addViewController("/login").setViewName("login");
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/img/**")
                .addResourceLocations("file:/"+uploadPath+"/");
        registry.addResourceHandler("/static/**")
                .addResourceLocations("classpath:/static/");
    }
}