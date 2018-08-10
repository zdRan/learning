package com.zdran.springboot.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.util.Map;

/**
 * Create by ranzd on 2018/8/9
 *
 * @author cm.zdran@gmail.com
 */
public class DemoHttpServletRequestWrapper extends HttpServletRequestWrapper {

    private Logger logger = LoggerFactory.getLogger(DemoHttpServletRequestWrapper.class);

    public DemoHttpServletRequestWrapper(HttpServletRequest request) {
        super(request);
    }

    @Override
    public String getHeader(String name) {
        String value = super.getHeader(name);
        logger.info("获取 Header 中的参数：{}", value);
        return value;
    }

    @Override
    public String getParameter(String name) {
        String value = super.getParameter(name);
        logger.info("获取 getParameter 中的参数：{}", value);
        return value;
    }

    @Override
    public Map<String, String[]> getParameterMap() {
        Map<String, String[]> map = super.getParameterMap();
        logger.info("获取 getParameterMap 中的参数：{}", map);
        return map;
    }

    @Override
    public HttpSession getSession() {
        HttpSession session = super.getSession();
        logger.info("获取 getCookies 中的cookie：{}", session);
        return session;
    }

    @Override
    public Object getAttribute(String name) {
        Object obj = super.getAttribute(name);
        logger.info("获取 getCookies 中的cookie：{}", obj);

        return obj;
    }

    @Override
    public String[] getParameterValues(String name) {
        String[] value = super.getParameterValues(name);
        logger.info("获取 getParameter 中的参数：{}", value);
        return value;
    }

    @Override
    public ServletInputStream getInputStream() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(super.getInputStream()));
        StringBuilder resultBuilder = new StringBuilder();
        String line;
        while ((line = br.readLine()) != null) {
            resultBuilder.append(line);
        }
        String result = resultBuilder.toString();
        logger.info("获取 getInputStream 中的参数：{}", result);
        return new WrappedServletInputStream(new ByteArrayInputStream(result.getBytes()));
    }

    /**
     * 读取 RequestBody 中的参数时需要重新再次写入到流中，否则在 Controller 中会读取不到参数。
     */
    private class WrappedServletInputStream extends ServletInputStream {
        public void setStream(InputStream stream) {
            this.stream = stream;
        }

        private InputStream stream;

        public WrappedServletInputStream(InputStream stream) {
            this.stream = stream;
        }

        @Override
        public int read() throws IOException {
            return stream.read();
        }

        @Override
        public boolean isFinished() {
            return true;
        }

        @Override
        public boolean isReady() {
            return true;
        }

        @Override
        public void setReadListener(ReadListener readListener) {

        }
    }
}
