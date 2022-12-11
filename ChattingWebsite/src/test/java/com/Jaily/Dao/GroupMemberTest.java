package com.Jaily.Dao;

import com.Jaily.dao.GroupMemberDao;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class GroupMemberTest {
    @Autowired
    private GroupMemberDao groupMemberDao;

    @Test
    void testAdd(){
        System.out.println(groupMemberDao.add("1003", "Yellowbest2"));
    }

    @Test
    void testCount(){
        System.out.println(groupMemberDao.countGroupById("1005"));
    }

    @Test
    void testGet(){
        System.out.println(groupMemberDao.getGroupsByUsername("芜湖大司马"));
    }

    @Test
    void testDelete(){
        System.out.println(groupMemberDao.delete("1001", "一只小团团"));
    }
}
