package com.zdran.springboot.task;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * Create by ranzd on 2018/10/16
 *
 * @author cm.zdran@gmail.com
 */
@Component
public class HelloTask {
    private static int count = 1;
    private Logger logger = LoggerFactory.getLogger(HelloTask.class);

    @Scheduled(fixedRate = 5000)
    public void printHello() {
        logger.info("Hello 定时任务开始。");
        logger.info("Hello Task! count = {}", count++);
        logger.info("Hello 定时任务结束。");
    }
}
