package com.Jaily.Service;

import com.Jaily.service.FriendService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class FriendServiceTest {
    @Autowired
    private FriendService friendService;

    @Test
    void testAddFriend(){
        System.out.println(friendService.addFriend("刁得二", "芜湖大司马"));
    }

    @Test
    void testDeleteFriend(){
        System.out.println(friendService.deleteFriend("刁得二", "芜湖大司马"));
    }
}
