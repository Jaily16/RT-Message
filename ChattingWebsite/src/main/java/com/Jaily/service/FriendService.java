package com.Jaily.service;

import com.Jaily.entity.User;
import com.Jaily.utility.Code;

import java.util.List;

public interface FriendService {

    /**
     * 处理添加好友的操作，返回添加好友是否成功的状态
     * @param username
     * @param friendName
     * @return
     */
    public Code addFriend(String username, String friendName);

    /**
     * 获取某用户的好友列表
     * @param username
     * @return
     */
    public List<User> getFriendList(String username);

    /**
     * 删除好友操作，返回删除好友是否成功
     * @param username
     * @param friendName
     * @return
     */
    public Boolean deleteFriend(String username, String friendName);
}
