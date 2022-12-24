package com.Jaily.service.Impl;

import com.Jaily.dao.GroupDao;
import com.Jaily.dao.GroupMessageDao;
import com.Jaily.entity.Group;
import com.Jaily.entity.GroupMessage;
import com.Jaily.service.GroupService;
import com.Jaily.utility.Code;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class GroupServiceImpl implements GroupService {
    @Autowired
    private GroupDao groupDao;
    @Autowired
    private GroupMessageDao groupMessageDao;
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

    @Override
    public Boolean chat(GroupMessage groupMessage) {
        groupMessage.setTime(new Date());
        return groupMessageDao.add(groupMessage) > 0;
    }

    @Override
    public List<GroupMessage> getChattingMessage(String groupId) {
        return groupMessageDao.get(groupId);
    }
}
