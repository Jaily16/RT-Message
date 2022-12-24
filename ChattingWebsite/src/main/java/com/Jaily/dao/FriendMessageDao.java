package com.Jaily.dao;

import com.Jaily.entity.FriendMessage;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface FriendMessageDao {
    @Insert("insert into rt_friend_message (sender,recipient,type,time,content) values(#{sender},#{recipient},#{type},#{time},#{content})")
    public int add(FriendMessage friendMessage);

    @Select("select * from rt_friend_message where (sender = #{user} and recipient = #{friend}) " +
            "or (sender = #{friend} and  recipient = #{user}) order by time")
    public List<FriendMessage> get(String user, String friend);
}
