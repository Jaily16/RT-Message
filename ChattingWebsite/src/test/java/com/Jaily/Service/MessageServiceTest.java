package com.Jaily.Service;

import com.Jaily.entity.FriendMessage;
import com.Jaily.service.FriendService;
import com.Jaily.utility.MessageType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class MessageServiceTest {
    @Autowired
    private FriendService friendService;

    @Test
    void chatWithFriend(){
        FriendMessage friendMessage = new FriendMessage();
        friendMessage.setSender("刁得二");
        friendMessage.setRecipient("芜湖大司马");
        friendMessage.setType(MessageType.TEXT.getType());
        friendMessage.setContent("行");
        System.out.println(friendService.chat(friendMessage));
    }

    @Test
    void getMessage(){
        System.out.println(friendService.getChattingMessage("刁得二", "芜湖大司马"));
    }
}
