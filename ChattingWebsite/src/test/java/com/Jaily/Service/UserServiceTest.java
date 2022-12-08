package com.Jaily.Service;

import com.Jaily.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @Test
    public void loginTest(){
//        System.out.println(userService.login("芜湖大司马","1992"));
        System.out.println(userService.login("Yellowbest2","123456"));
    }
}
