package com.yang.ggfriends;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.DigestUtils;

import java.security.NoSuchAlgorithmException;

/**
 * 测试类
 */
@SpringBootTest
class MyApplicationTest {

    @Test
    void testDigest() throws NoSuchAlgorithmException {
        String newPassword = DigestUtils.md5DigestAsHex(("yangpi" + "12345678").getBytes());
        System.out.println(newPassword);
    }

    @Test
    void contextLoads() {

    }

}
