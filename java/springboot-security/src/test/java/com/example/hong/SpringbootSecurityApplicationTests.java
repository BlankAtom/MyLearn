package com.example.hong;

import com.example.hong.service.UserService;
import com.example.hong.service.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SpringbootSecurityApplicationTests {
    @Autowired
    private UserServiceImpl userService;
    @Test
    void contextLoads() {
        System.out.println(userService.queryUserByName("admin"));
    }

}
