package com.Jaily.Dao;

import com.Jaily.dao.GroupDao;
import com.Jaily.entity.Group;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class GroupDaoTest {
    @Autowired
    private GroupDao groupDao;

    @Test
    void testAddGroup(){
        Group group = new Group();
        group.setGroupId("1001");
        group.setGroupName("联合国");
        group.setAvatar("g01");
        System.out.println(groupDao.add(group));
    }

    @Test
    void testDeleteGroup(){
        System.out.println(groupDao.delete("1001"));
    }

    @Test
    void testGetGroup(){
        System.out.println(groupDao.getGroupById("1001"));
    }

    @Test
    void testCountGroup(){
        System.out.println(groupDao.countGroupById("1002"));
    }
}
