package com.Jaily.Service;

import com.Jaily.entity.Group;
import com.Jaily.service.GroupService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class GroupServiceTest {
    @Autowired
    private GroupService groupService;

    @Test
    void testAddGroup(){
        Group group = new Group();
        group.setGroupId("1002");
        group.setGroupName("苏维埃联盟");
        group.setAvatar("g03");
        System.out.println(groupService.addGroup(group));
    }
}
