package com.Jaily.service;

import com.Jaily.entity.FriendMessage;
import com.Jaily.entity.Group;
import com.Jaily.entity.GroupMessage;
import com.Jaily.utility.Code;

import java.util.List;

public interface GroupService {
    /**
     * 处理添加群聊，返回添加群聊的结果
     * @param group
     * @return
     */
    public Code addGroup(Group group);

    /**
     * 删除群聊，返回是否删除成功
     * @param groupId
     * @return
     */
    public Boolean deleteGroup(String groupId);

    /**
     * 根据群聊号获取群聊对象
     * @param groupId
     * @return
     */
    public Group getGroupById(String groupId);

    /**
     * 群聊操作，返回该聊天记录是否存储成功
     * @param groupMessage
     * @return
     */
    public Boolean chat(GroupMessage groupMessage);

    /**
     * 获取群聊天信息，聊天记录，返回该聊天信息
     * @param groupId
     * @return
     */
    public List<GroupMessage> getChattingMessage(String groupId);
}
