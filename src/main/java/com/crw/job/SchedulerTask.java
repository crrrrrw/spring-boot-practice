package com.crw.job;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class SchedulerTask {

    private int count = 0;


    /**
     * 每6秒执行一次
     */
    @Scheduled(cron = "*/6 * * * * ?")
    private void job1() {
        log.info("job1 run count: {}", (count++));
    }


    /**
     * 上一次执行完毕时间点之后6秒再执行
     */
    @Scheduled(fixedDelay = 6000)
    private void job2() {
        log.info("job2 run time:{}", System.currentTimeMillis());
    }
}
