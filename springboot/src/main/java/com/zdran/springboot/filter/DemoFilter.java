package com.zdran.springboot.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import java.io.IOException;

/**
 * Create by ranzd on 2018/8/9
 *
 * @author cm.zdran@gmail.com
 */
public class DemoFilter implements Filter{
    private Logger logger = LoggerFactory.getLogger(DemoFilter.class);
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        logger.info("初始化 DemoFilter ");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        logger.info("拦截到参数：{}", servletRequest.getParameterMap());
        if (true) {
            filterChain.doFilter(servletRequest,servletResponse);
        }
        logger.info("拦截到参数。请求被拦截");
    }

    @Override
    public void destroy() {
        logger.info("销毁 DemoFilter ");
    }
}
