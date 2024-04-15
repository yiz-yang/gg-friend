package com.yang.ggfriends.service;

import com.yang.ggfriends.model.domain.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import javax.annotation.Resource;

/**
 * Redis 测试
 */
@SpringBootTest
public class RedisTest {

    @Resource
    private RedisTemplate redisTemplate;

    @Test
    void test() {
        ValueOperations valueOperations = redisTemplate.opsForValue();
        // 增
        valueOperations.set("yangpiString", "dog");
        valueOperations.set("yangpiInt", 1);
        valueOperations.set("yangpiDouble", 2.0);
        User user = new User();
        user.setId(1L);
        user.setUsername("yangpi");
        valueOperations.set("yangpiUser", user);
        // 查
        Object yangpi = valueOperations.get("yangpiString");
        Assertions.assertTrue("dog".equals((String) yangpi));
        yangpi = valueOperations.get("yangpiInt");
        Assertions.assertTrue(1 == (Integer) yangpi);
        yangpi = valueOperations.get("yangpiDouble");
        Assertions.assertTrue(2.0 == (Double) yangpi);
        System.out.println(valueOperations.get("yangpiUser"));
        valueOperations.set("yangpiString", "dog");
        redisTemplate.delete("yangpiString");
    }
}
