package com.example.spring_cook;

import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;

@Configuration
public class MvcConfig implements WebMvcConfigurer {
    public void addViewControllers(ViewControllerRegistry registry) {
        //addViewController - если мы переходим по параметру
        //setViewName - то попадаем на
        registry.addViewController("/").setViewName("index");
        registry.addViewController("/login").setViewName("login");

    }
}
