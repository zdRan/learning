package com.zdran.dubbomall.web;

import com.zdran.dubbomall.user.service.UserService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Create by ranzd on 2018/11/12
 *
 * @author cm.zdran@gmail.com
 */
public class ApplicationMain {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
                new String[]{"dubbo/dubbo-server.xml"});
        context.start();
        UserService demoService = (UserService) context.getBean("userService");
        String hello = demoService.sayHello("dubbo");
        System.out.println(hello);

    }
}
