package com.Jaily.service.Impl;

import com.Jaily.dao.FriendDao;
import com.Jaily.dao.FriendMessageDao;
import com.Jaily.entity.FriendMessage;
import com.Jaily.entity.User;
import com.Jaily.service.FriendService;
import com.Jaily.utility.Code;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class FriendServiceImpl implements FriendService {
    @Autowired
    private FriendDao friendDao;
    @Autowired
    private FriendMessageDao friendMessageDao;

    @Override
    public Code addFriend(String username, String friendName) {
        if(friendDao.countFriendByName(username, friendName)){
            return Code.FRIEND_ADD_AGAIN;
        }
        // 添加相应的两条信息
        friendDao.add(username, friendName);
        friendDao.add(friendName, username);
        return Code.FRIEND_ADD_SUCCESS;
    }

    @Override
    public List<User> getFriendList(String username) {
        return friendDao.getFriendsByName(username);
    }

    @Override
    public Boolean deleteFriend(String username, String friendName) {
        friendDao.delete(username, friendName);
        friendDao.delete(friendName, username);
        return true;
    }

    @Override
    public Boolean chat(FriendMessage friendMessage) {
        friendMessage.setTime(new Date());
        return friendMessageDao.add(friendMessage) > 0;
    }

    @Override
    public List<FriendMessage> getChattingMessage(String user, String friend) {
        return friendMessageDao.get(user, friend);
    }
}
