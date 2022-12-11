package com.Jaily.service.Impl;

import com.Jaily.dao.GroupDao;
import com.Jaily.entity.Group;
import com.Jaily.service.GroupService;
import com.Jaily.utility.Code;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GroupServiceImpl implements GroupService {
    @Autowired
    private GroupDao groupDao;
    @Override
    public Code addGroup(Group group) {
        if(groupDao.countGroupById(group.getGroupId())){
            return Code.GROUP_ADD_AGAIN;
        }
        return (groupDao.add(group) > 0) ? Code.GROUP_ADD_SUCCESS : Code.GROUP_ADD_FAILED;
    }

    @Override
    public Boolean deleteGroup(String groupId) {
        return groupDao.delete(groupId) > 0;
    }

    @Override
    public Group getGroupById(String groupId) {
        return groupDao.getGroupById(groupId);
    }
}
