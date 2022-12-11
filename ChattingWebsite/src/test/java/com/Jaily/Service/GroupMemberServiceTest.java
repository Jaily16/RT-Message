package com.Jaily.Service;

import com.Jaily.service.GroupMemberService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class GroupMemberServiceTest {
    @Autowired
    private GroupMemberService groupMemberService;

    @Test
    void testJoin(){
        System.out.println(groupMemberService.joinInGroup("1001","Yellowbest2"));
    }

    @Test
    void testExit(){
        System.out.println(groupMemberService.exitGroup("1001","Yellowbest2"));
        System.out.println(groupMemberService.exitGroup("1001","刁得二"));
        System.out.println(groupMemberService.exitGroup("1001","芜湖大司马"));
    }

}
