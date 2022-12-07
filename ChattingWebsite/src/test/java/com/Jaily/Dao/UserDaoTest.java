package com.Jaily.Dao;

import com.Jaily.dao.UserDao;
import com.Jaily.entity.User;
import com.Jaily.utility.Code;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserDaoTest {

    @Autowired
    private UserDao userDao;


    @Test
    public void testAdd(){
        User user = new User();
        user.setUsername("芜湖大司马");
        user.setLastname("韩");
        user.setFirstname("金龙");
        user.setPassword("1997");
        user.setBirthday("1980-10-10");
        user.setSex(true);
        user.setAvatar("dsm1");

        User user1 = new User();
        user1.setUsername("Yellowbest");
        user1.setLastname("黄");
        user1.setFirstname("昊");
        user1.setPassword("123456");
        user1.setBirthday("2002-03-01");
        user1.setSex(true);
        user1.setAvatar("u01");

        System.out.println(userDao.add(user1));
    }

    @Test
    public void testUpdate(){
        User user = new User();
        user.setUsername("芜湖大司马");
        user.setLastname("韩");
        user.setFirstname("金龙");
        user.setPassword("1997");
        user.setBirthday("1983-10-10");
        user.setSex(true);
        user.setAvatar("dsm2");

        System.out.println(userDao.update(user));
    }

    @Test
    public void testDelete(){
        System.out.println(userDao.delete("Yellowbest"));
    }

    @Test
    public void testGetByName(){
        System.out.println(userDao.getByName("芜湖大司马"));
    }

}
