package com.kanject.test.runner;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * TODO
 *
 * @author guangjie.liang
 * @date 2021/01/25 15:34:30
 */
@Slf4j
@Component
@Order(1)
public class TestCommandLineRunner implements CommandLineRunner {
    @Override
    public void run(String... args) throws Exception {
        log.info("TestCommandLineRunner is running...");
//        testArgs(args);
    }

    /** 参数基本用不上，几乎没影响，可以忽略 */
    private void testArgs(String... args) {
        log.info("TestCommandLineRunner is running...");
        log.info("args.length =====> {}", args.length);
        for (String arg : args) {
            log.info("TestCommandLineRunner's arg =====> {}", arg);
        }
    }
}
