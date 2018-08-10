package com.zdran.springboot.config;

import com.zdran.springboot.filter.DemoFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.DispatcherType;
import javax.servlet.Filter;

/**
 * Create by ranzd on 2018/8/9
 *
 * @author cm.zdran@gmail.com
 */
@Configuration
public class FilterConfig {
    private Logger logger = LoggerFactory.getLogger(FilterConfig.class);

    @Bean
    public FilterRegistrationBean xssFilterRegistrationBean() {
        logger.info("初始化 DemoFilter 过滤器 Bean");
        FilterRegistrationBean<Filter> initXssFilterBean = new FilterRegistrationBean<>();
        initXssFilterBean.setFilter(new DemoFilter());
        initXssFilterBean.setOrder(1);
        initXssFilterBean.setEnabled(true);
        initXssFilterBean.addUrlPatterns("/*");
        initXssFilterBean.setDispatcherTypes(DispatcherType.REQUEST);
        return initXssFilterBean;
    }
}
