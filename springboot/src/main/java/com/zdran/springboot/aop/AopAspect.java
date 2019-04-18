package com.zdran.springboot.aop;

import com.zdran.springboot.dao.AccountInfo;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
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

    /**
     * 在方法执行之前执行
     *
     * @param joinPoint
     */
    @Before(value = "pointCut()")
    public void doBefore(JoinPoint joinPoint) {
        logger.info("doBefore run");
        AccountInfo accountInfo = (AccountInfo) joinPoint.getArgs()[0];
        logger.info("AOP:{}", accountInfo.toString());
        accountInfo.setName("aop");
    }

    /**
     * 在方法执行之后执行
     *
     * @param joinPoint
     */
    @After(value = "pointCut()")
    public void doAfter(JoinPoint joinPoint) {
        logger.info("doAfter run");
    }

    /**
     * 在 after 注解方法之后执行，可以对方法返回值进行修改
     *
     * @param point
     * @param returnValue
     */
    @AfterReturning(value = "pointCut()", returning = "returnValue")
    public void doAfterReturning(JoinPoint point, AccountInfo returnValue) {
        logger.info("doAfterReturning:{}", returnValue);
        returnValue.setName("doAfterReturning");
    }

    /**
     * 在方法抛出异常时执行,执行顺序在 After 之后
     *
     * @param ex
     */
    @AfterThrowing(value = "pointCut()", throwing = "ex")
    public void doAfterThrowing(Throwable ex) {
        logger.info("doAfterThrowing run");
        logger.error("doAfterThrowing:", ex);
    }

    /**
     * 环绕通知
     *
     * @param joinPoint
     */
    @Around(value = "pointCut()")
    public AccountInfo doAround(ProceedingJoinPoint joinPoint) {
        logger.info("doAround run");
        AccountInfo accountInfo = (AccountInfo) joinPoint.getArgs()[0];
        //在方法被执行前，修改参数
        accountInfo.setBalance(123);
        try {
            //执行的实际方法
            joinPoint.proceed();
        } catch (Throwable throwable) {
            return null;
        }
        //在方法执行后修改返回值
        accountInfo.setName("around");
        return accountInfo;
    }

    public void test() {
        //@Around
        try {
            try {
                //@Before
                //method.invoke(..);
            } finally {
                //@After
            }
            //@AfterReturning
        } catch (Exception e) {
            //@AfterThrowing
        }
    }
}