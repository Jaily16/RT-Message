package com.Jaily.Service;

import com.Jaily.service.Impl.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserServiceImplTest {

    @Autowired
    private UserServiceImpl userService;

    @Test
    public void loginTest(){
        System.out.println(userService.login("芜湖大司马","1997"));
    }
}
