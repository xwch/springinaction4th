package com.xwch.web;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.spring4.SpringTemplateEngine;
import org.thymeleaf.spring4.view.ThymeleafViewResolver;
import org.thymeleaf.templateresolver.ServletContextTemplateResolver;
import org.thymeleaf.templateresolver.TemplateResolver;
import org.thymeleaf.util.TemplateModeUtils;

/**
 * web上下文配置，包含视图解析，控制器，静态资源处理等
 * Created by xwch on 2017/10/18.
 */
@Configuration
@EnableWebMvc  //用于启用SpringMVC组件，等价于<mvc:annotation-driven>
@ComponentScan("com.xwch.web")
public class WebConfig extends WebMvcConfigurerAdapter {

    @Bean
    public ViewResolver viewResolver(SpringTemplateEngine templateEngine) {
        ThymeleafViewResolver viewResolver = new ThymeleafViewResolver();
        viewResolver.setTemplateEngine(templateEngine);
        viewResolver.setCharacterEncoding("utf-8");
        return viewResolver;
    }

    @Bean
    public SpringTemplateEngine templateEngine(TemplateResolver templateResolver){
        SpringTemplateEngine templateEngine = new SpringTemplateEngine();
        templateEngine.setTemplateResolver(templateResolver);
        return templateEngine;
    }

    @Bean
    public TemplateResolver templateResolver(){
        TemplateResolver templateResolver =
                new ServletContextTemplateResolver();
        templateResolver.setPrefix("/WEB-INF/templates/");
        templateResolver.setSuffix(".html");
        //渲染为HTML5
        templateResolver.setTemplateMode("HTML5");
        return templateResolver;
    }



    /**
     * 静态资源处理
     * 要求DispatcherServlet将对静态资源的请求转发到Servlet容器中默认的Servlet上，
     * 而不是使用DispatcherServlet本身来处理 此类请求。
     * @param configurer
     */
    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }

    /**
     * 这个是干啥用的？
     * @param registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        super.addResourceHandlers(registry);
    }

    /**
     * 配置spring message属性文件
     * 这里的关键区别在于basename属性设置为在应用的外部查找（而不是像 ResourceBundleMessageSource那样在类路径下查找）。
     * basename属性可以设置为在
     *      1.类路径下（以“classpath:”作为前缀）、
     *      2.文件系统中（以“file:”作为前缀）
     *      3.Web应用 的根路径下（没有前缀）查找属性。
     * 在这里，我将其配置为在类路径的“properties/welcome”目录下的属性文件中查找信息，并且基础的文件名为“message”
     * @return
     */
    @Bean
    public MessageSource messageSource(){
        ReloadableResourceBundleMessageSource messageSource
                = new ReloadableResourceBundleMessageSource();
        messageSource.setBasename("classpath:/properties/welcome/message");
        messageSource.setCacheSeconds(10);
        return messageSource;
    }

}
