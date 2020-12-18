package com.kanject.test.domain.pojo;

/**
 * TODO
 *
 * @author guangjie.liang
 * @date 2020/12/14 20:20:41
 */
public class Counter {

    private static int count = 0;

    public static int add(int num) {
        count += num;
        return count;
    }
}
