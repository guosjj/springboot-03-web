package com.guo.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

//如果我们要扩展spring mvc官方建议我们这样
@Configuration
@ComponentScan
public class MyMvcConfig implements WebMvcConfigurer {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("index");
        registry.addViewController("/index.html").setViewName("index");
        registry.addViewController("/main.html").setViewName("dashboard");
    }

/*    @Bean
    public LocaleResolver localeResolver(){
        return new MyLocaleResolver();
    }*/

    //拦截器
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
          registry.addInterceptor(new LoginHandlerInterceptor()).addPathPatterns("/**")
                  .excludePathPatterns("/index.html","/","/user/login","/css/*","/js/**","/img/**");
    }
}
