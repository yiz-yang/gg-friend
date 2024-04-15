package com.yang.ggfriends.service;

import com.yang.ggfriends.model.domain.User;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.DigestUtils;
import org.springframework.util.StopWatch;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * 导入用户测试
 */
@SpringBootTest
public class InsertUsersTest {
    @Resource
    private UserService userService;

    private ExecutorService executorService = new ThreadPoolExecutor(40, 1000, 10000, TimeUnit.MINUTES, new ArrayBlockingQueue<>(10000));

    @Test
    public void doInsertUser(){
        final int INSERT_NUM = 3;
        List<User> userList = new ArrayList<>();
        for (int i=0;i<INSERT_NUM;i++){
            User user = new User();
            user.setUsername("yoxi" + i+1);
            user.setUserAccount("yoxi" + i);
            user.setAvatarUrl("https://one-yang.oss-cn-guangzhou.aliyuncs.com/smartCloudPartner/2024-03-23-11-20-38two.jpg");
            user.setGender(0);
            user.setUserPassword("7ba80eabeb86a683f244e38fa8025dc5");
            user.setPhone("14353563");
            user.setEmail("142413@qq.com");
            user.setUserStatus(0);
            user.setUserRole(0);
            user.setPlanetCode("121" + i);
            user.setTags("[\"被卖的\",\"宠物\",\"母\",\"绿色\"]");
            userList.add(user);
        }
        // 20s可以10w条
        userService.saveBatch(userList,3);
    }

    /**
     * 批量插入用户
     */
    @Test
    public void doInsertUsers(){
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        final int INSERT_NUM = 100000;
        List<User> userList = new ArrayList<>();
        for (int i=0;i<INSERT_NUM;i++){
            User user = new User();
            user.setUsername("p976" + i+ "D" + i+34);
            user.setUserAccount("p96" + i+ "D" + i+34);
            user.setAvatarUrl("https://one-yang.oss-cn-guangzhou.aliyuncs.com/smartCloudPartner/2024-03-23-11-20-38two.jpg");
            user.setGender(0);
            user.setUserPassword("7ba80eabeb86a683f244e38fa8025dc5");
            user.setPhone("685221" + i+ 357);
            user.setEmail("1214251"+i+53+"@qq.com");
            user.setUserStatus(0);
            user.setUserRole(0);
            user.setPlanetCode("14621"+i+530);
            user.setTags("[\"女\",\"宅\",\"daa\"]");
            userList.add(user);
        }
        // 30s可以20w条
        userService.saveBatch(userList,100);
        stopWatch.stop();
        System.out.println(stopWatch.getTotalTimeMillis());
    }

    /**
     * 并发批量插入用户
     */
    @Test
    public void doConcurrencyInsertUsers() {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        //分2十组  3000个*20组
        int batchSize = 3000;
        int j = 0;
        List<CompletableFuture<Void>> futureList = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            List<User> userList = new ArrayList<>();
            while (true) {
                j++;
                User user = new User();
                user.setUsername("pkq" + j +7254+"pkq" + j +i);
                user.setUserAccount("pkq" + j +7254+"pkq" + j +i);
                user.setAvatarUrl("https://one-yang.oss-cn-guangzhou.aliyuncs.com/smartCloudPartner/2024-03-23-01-43-13foru.png");
                user.setGender(1);
                user.setUserPassword("7ba80eabeb86a683f244e38fa8025dc5");
                user.setPhone("527142");
                user.setEmail("7552f333@qq.com");
                user.setUserStatus(0);
                user.setUserRole(0);
                user.setPlanetCode("57442");
                user.setTags("[\"女\",\"皮卡丘\",\"十万伏特\"]");
                userList.add(user);
                if (j % batchSize == 0) {
                    break;
                }
            }
            // 异步执行
            CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
                System.out.println("threadName:" + Thread.currentThread().getName());
                userService.saveBatch(userList, batchSize);
            },executorService);
            futureList.add(future);
        }
        CompletableFuture.allOf(futureList.toArray(new CompletableFuture[]{})).join();
        stopWatch.stop();
        System.out.println(stopWatch.getTotalTimeMillis());
    }


    @Test
    public void doUpdateUsers(){
        String SALT = "yangpi";
        String userPassword = "12345678";
        String encryptPassword = DigestUtils.md5DigestAsHex((SALT + userPassword).getBytes());
        User user = userService.getById(8);
        user.setUsername("aammee");
        user.setUserPassword(encryptPassword);
        userService.saveOrUpdate(user);
    }
}
