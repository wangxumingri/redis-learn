package com.wxss.springbootrediscluster;

import com.wxss.springbootrediscluster.service.RedisService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SpringbootRedisClusterApplicationTests {

    @Autowired
    private RedisService redisService;

    @Test
    void testString() {
        redisService.set("hello","你好");
        System.out.println(redisService.get("hello"));

        redisService.set("world","世界",20L);
        System.out.println(redisService.getExpire("world"));
    }

    @Test
    void test1() {
//        redisService.set("hello","你好");
        System.out.println(redisService.get("hello"));

//        redisService.set("world","世界",20L);
        System.out.println(redisService.getExpire("world"));
        System.out.println(redisService.get("world"));
    }

}
