package com.Jaily.Dao;

import com.Jaily.dao.FriendDao;
import com.Jaily.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class FriendDaoTest {
    @Autowired
    private FriendDao friendDao;

    @Test
    void testAdd(){
        System.out.println(friendDao.add("刁得二","芜湖大司马"));
        System.out.println(friendDao.add("刁得二", "Yellowbest2"));
    }

    @Test
    void testCountByName(){
        System.out.println(friendDao.countFriendByName("刁得yi", "Yellowbest2"));
    }

    @Test
    void testGetFriends(){
        List<User> friends = friendDao.getFriendsByName("刁得二");
        System.out.println(friends);
    }

    @Test
    void testDelete(){
        System.out.println(friendDao.delete("刁得二","芜湖大司马"));
    }
}
