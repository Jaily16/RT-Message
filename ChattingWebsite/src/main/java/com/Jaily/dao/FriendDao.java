package com.Jaily.dao;

import com.Jaily.entity.User;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface FriendDao {
    @Insert("insert into rt_friend_relationship (username,friendname) values(#{username},#{friendName})")
    public int add(String username, String friendName);

    @Select("select count(*) from rt_friend_relationship where username = #{username} and friendname = #{friendName}")
    public Boolean countFriendByName(String username, String friendName);

    @Select("Select rt_user.* from rt_user,rt_friend_relationship where rt_friend_relationship.username = #{username} and rt_friend_relationship.friendname = rt_user.username")
    public List<User> getFriendsByName(String username);

    @Delete("delete from rt_friend_relationship where username = #{username} and friendname = #{friendName}")
    public int delete(String username, String friendName);
}
