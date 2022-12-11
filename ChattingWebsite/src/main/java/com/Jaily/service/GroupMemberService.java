package com.Jaily.service;

import com.Jaily.entity.Group;
import com.Jaily.utility.Code;

import java.util.List;

public interface GroupMemberService {
    /**
     * 处理加入群聊的操作，返回加入群聊的状态
     * @param groupId
     * @param username
     * @return
     */
    public Code joinInGroup(String groupId, String username);

    /**
     * 处理获取群聊列表的操作，返回群聊列表
     * @param username
     * @return
     */
    public List<Group> getGroupList(String username);

    /**
     * 处理退出群聊的操作，返回退出状态
     * @param groupId
     * @param username
     * @return
     */
    public Code exitGroup(String groupId, String username);
}
