package com.kanject.test.runner;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * TODO
 *
 * @author guangjie.liang
 * @date 2021/01/25 16:15:18
 */
@Slf4j
@Component
@Order(2)
public class TestApplicationRunner implements ApplicationRunner {
    @Override
    public void run(ApplicationArguments args) throws Exception {
        log.info("TestApplicationRunner is running...");
//        testArgs(args);
    }

    /** 参数基本用不上，几乎没影响，可以忽略 */
    private void testArgs(ApplicationArguments args){
        log.info("args.containsOption =====> {}", args.containsOption("1"));
        log.info("args.getNonOptionArgs =====> {}", args.getNonOptionArgs());
        log.info("args.getOptionNames =====> {}", args.getOptionNames());
        log.info("args.getOptionValues =====> {}", args.getOptionValues("1"));
        log.info("args.getSourceArgs =====> {}", args.getSourceArgs());
    }
}
