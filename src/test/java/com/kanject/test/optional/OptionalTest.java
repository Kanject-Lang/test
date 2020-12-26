package com.kanject.test.optional;

import com.kanject.test.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.Optional;

/**
 * @description: TODO
 * @author: Kanject
 */
@Slf4j
public class OptionalTest {

    @Test
    public void test() {
        User user = new User();
        user.setUsername("zhangsan");
        user.setAge(16);
//        user = null;
        Optional optional1 = Optional.of(user);
        Optional optional2 = Optional.ofNullable(user);
        log.info("optional1 =====> isPresent: {}, get: {}", optional1.isPresent(), optional1.get());
        log.info("optional2 =====> isPresent: {}, get: {}", optional2.isPresent(), optional2.get());

        optional2.ifPresent(o -> System.out.println(o.toString()));

        String str1 = "optional";
        String str2 = null;

        log.info("orElse =====> {}", Optional.ofNullable(str1).orElse("default"));
        log.info("orElse =====> {}", Optional.ofNullable(str2).orElse("default"));

        log.info("orElseGet =====> {}", Optional.ofNullable(str1).orElseGet(() -> new String("new String")));
        log.info("orElseGet =====> {}", Optional.ofNullable(str2).orElseGet(() -> new String("new String")));

        log.info("orElseThrow =====> {}", Optional.ofNullable(str1).orElseThrow(() -> new RuntimeException("not exist")));
//        log.info("orElseThrow =====> {}", Optional.ofNullable(str2).orElseThrow(() -> new RuntimeException("not exist")));//java.lang.RuntimeException: not exist


        User lisi = new User();
        lisi.setUsername("LI_SI");
        Optional<String> username_map = Optional
                .ofNullable(lisi)
                .map(u -> lisi.getUsername())
                .map(name -> name.toLowerCase())
                .map(name -> name.replace('_', ' '));
        log.info("map =====> username: {}", username_map.orElse("Unknown"));


        User wangwu = new User();
        wangwu.setUsername("wangwu");
        wangwu.setAge(19);
        Optional<String> username_filter = Optional.ofNullable(wangwu).filter(u -> u.getAge() > 18).map(u -> u.getUsername());
        log.info("filter =====> age pass username: {}", username_filter.orElse("Unknown"));

    }

}
