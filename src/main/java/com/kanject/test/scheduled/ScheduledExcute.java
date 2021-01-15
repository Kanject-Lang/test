package com.kanject.test.scheduled;

import com.kanject.test.domain.pojo.Counter;
import com.kanject.test.websocket.UserServerEndpoint;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

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

    /**
     * cron表达式格式：
     * {秒数} {分钟} {小时} {日期} {月份} {星期} {年份(可为空)}
     * 参考: https://www.cnblogs.com/softidea/p/5833248.html
     */

    @Scheduled(cron = "0 0 0 * * ?")
    public void checkUpdate() {
        log.info("The latest version is {}", version++);
        log.info("The latest version is {}", Counter.add(1));
    }

    /**
     * 聊天室-整点报时
     */
    @Scheduled(cron = "0 0 0 * * ?")
    public void hourlyChime() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        UserServerEndpoint.messagePush(sdf.format(new Date()));
    }
}
