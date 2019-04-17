package com.zdran.springboot.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * Create by ranzd on 2019/4/17
 *
 * @author cm.zdran@gmail.com
 */
@Aspect
@Component
public class AopAspect {
    /**
     * 类 AopServiceImpl 下的 aopHello 方法为切入点
     */
    @Pointcut("execution(public * com.zdran.springboot.service.impl.AopServiceImpl.aopHello())")
    public void pointCut() {
    }

    @Before(value = "pointCut()")
    public void doBefore(JoinPoint joinPoint) {
        System.out.println("before run");

        System.out.println(joinPoint.toLongString());
    }

    @After(value = "pointCut()")
    public void doAfter(JoinPoint joinPoint){
        System.out.println("after run");
        System.out.println(joinPoint.toShortString());
    }
}
