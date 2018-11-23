package com.example.sweater.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

//Этот класс содержит конфигурацию нашего веб слоя
@Configuration
public class MvcConfig implements WebMvcConfigurer {

    public void addViewControllers(ViewControllerRegistry registry) {

        //Убрали 3 строки так как у нас есть такой маппинг, нужен только /login
       // registry.addViewController("/home").setViewName("home");
       // registry.addViewController("/").setViewName("home");
       // registry.addViewController("/hello").setViewName("hello");
        registry.addViewController("/login").setViewName("login");
    }

}