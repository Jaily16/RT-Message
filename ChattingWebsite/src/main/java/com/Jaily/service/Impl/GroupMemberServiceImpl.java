package com.Jaily.service.Impl;

import com.Jaily.dao.GroupDao;
import com.Jaily.dao.GroupMemberDao;
import com.Jaily.entity.Group;
import com.Jaily.service.GroupMemberService;
import com.Jaily.utility.Code;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GroupMemberServiceImpl implements GroupMemberService {
    @Autowired
    private GroupMemberDao groupMemberDao;
    @Autowired
    private GroupDao groupDao;
    @Override
    public Code joinInGroup(String groupId, String username) {
        if(groupMemberDao.countGroup(groupId, username)){
            return Code.GROUP_JOIN_AGAIN;
        }
        return (groupMemberDao.add(groupId, username) > 0) ? Code.GROUP_JOIN_SUCCESS : Code.GROUP_JOIN_FAILED;
    }

    @Override
    public List<Group> getGroupList(String username) {
        return groupMemberDao.getGroupsByUsername(username);
    }

    @Override
    public Code exitGroup(String groupId, String username) {
        if(groupMemberDao.delete(groupId, username) <= 0){
            return Code.GROUP_EXIT_FAILED;
        }
        if(groupMemberDao.countGroupById(groupId)){
            return Code.GROUP_EXIT_SUCCESS;
        }
        groupDao.delete(groupId);
        return Code.GROUP_EXIT_SUCCESS_AND_DELETE_GROUP;
    }
}
