package com.Jaily.service;

import com.Jaily.entity.Group;
import com.Jaily.utility.Code;

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
}
