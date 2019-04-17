package com.zdran.springboot.aop;

import com.zdran.springboot.dao.AccountInfo;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * Create by ranzd on 2019/4/17
 *
 * @author cm.zdran@gmail.com
 */
@Aspect
@Component
public class AopAspect {
    private static Logger logger = LoggerFactory.getLogger(AopAspect.class);

    /**
     * 类 AopServiceImpl 下的 aopHello 方法为切入点
     */
    @Pointcut("execution(public * com.zdran.springboot.service.impl.AopServiceImpl.aopHello(..))")
    public void pointCut() {
    }

    @Before(value = "pointCut()")
    public void doBefore(JoinPoint joinPoint) {
        logger.info("before run");
        AccountInfo accountInfo = (AccountInfo) joinPoint.getArgs()[0];
        logger.info("AOP:{}", accountInfo.toString());
        accountInfo.setName("aop");

    }

    @After(value = "pointCut()")
    public void doAfter(JoinPoint joinPoint) {
        logger.info("after run");
        logger.info(joinPoint.getTarget().toString());

        System.out.println(joinPoint.getTarget());
    }
}
