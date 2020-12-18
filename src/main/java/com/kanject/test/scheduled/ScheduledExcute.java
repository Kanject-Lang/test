package com.kanject.test.scheduled;

import com.kanject.test.domain.pojo.Counter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * TODO
 *
 * @author guangjie.liang
 * @date 2020/12/14 16:17:10
 */
@Slf4j
@Component
public class ScheduledExcute {

    private static Integer version = new Integer(1);

    private static Integer fibonacci = new Integer(0);

//    cron表达式格式：
//    {秒数} {分钟} {小时} {日期} {月份} {星期} {年份(可为空)}

//    @Scheduled(cron = "0,10,20,30,40,50 * * * * ?")
//    public void checkUpdate() {
//        log.info("The latest version is {}", version++);
////        log.info("The latest version is {}", Counter.add(1));
//    }
}
