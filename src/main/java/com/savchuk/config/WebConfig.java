package com.savchuk.config;

import com.savchuk.services.IUserService;
import org.apache.catalina.filters.CorsFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.config.annotation.*;

import javax.servlet.Filter;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by home on 07.03.17.
 */
@Configuration
@EnableWebMvc
@ComponentScan
public class WebConfig extends WebMvcConfigurerAdapter {


    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**").addResourceLocations("/");
    }

//    @Bean
//    public FilterRegistrationBean authFilterRegistration() {
//
//        List<String> urls = new ArrayList<>();
//        urls.add("/home/*");
//        urls.add("/identity/login");
//
//        FilterRegistrationBean registration = new FilterRegistrationBean();
//        registration.setFilter(authFilter());
//        registration.addUrlPatterns("/identity/*");
//        //registration.setUrlPatterns(urls);
//        registration.addInitParameter("paramName", "paramValue");
//        registration.setName("AuthFilter");
//        registration.setOrder(1);
//        return registration;
//    }

    @Bean
    public FilterRegistrationBean headerFilterRegistration() {
        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
        characterEncodingFilter.setForceEncoding(true);
        characterEncodingFilter.setEncoding("UTF-8");
        registrationBean.setFilter(characterEncodingFilter);
        return registrationBean;
    }
//
//    @Bean(name = "headerFilter")
//    public Filter headerFilter(){
//        return new CorsFilter();
//    }

//    @Bean(name = "authFilter")
//    public Filter authFilter() {
//        return new AuthFilter();
//    }
}
