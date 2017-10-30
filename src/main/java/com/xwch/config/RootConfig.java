package com.xwch.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.Import;
import org.springframework.core.type.filter.RegexPatternTypeFilter;

import java.util.regex.Pattern;

/**
 * 通用上下文，配置入口,扫描com.xwch包，过滤com.xwch.web
 * Created by xwch on 2017/10/18.
 */
@Configuration
@Import(DataConfig.class)
@ComponentScan(basePackages = {"com.xwch"},
        excludeFilters = {
            @Filter(type = FilterType.CUSTOM,value = RootConfig.WebPakage.class)
        })
public class RootConfig {
    public static class WebPakage extends RegexPatternTypeFilter {
        public WebPakage(){
            super(Pattern.compile("com\\.xwch\\.web"));
        }
    }
}
