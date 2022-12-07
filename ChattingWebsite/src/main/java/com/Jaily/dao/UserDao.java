package com.Jaily.dao;

import com.Jaily.entity.User;
import org.apache.ibatis.annotations.*;

@Mapper
public interface UserDao {
    @Insert("insert into user (username,lastname,firstname,password,birthday,sex,avatar) values(#{username},#{lastname},#{firstname},#{password},#{birthday},#{sex},#{avatar})")
    public int add(User user);

    @Update("update user set lastname = #{lastname}, firstname = #{firstname}, lastname = #{lastname}, password = #{password}, birthday = #{birthday}, sex = #{sex}, avatar = #{avatar} where username = #{username}")
    public int update(User user);

    @Delete("delete from user where username = #{username}")
    public int delete(String username);

    @Select("select * from user where username = #{username}")
    public User getByName(String username);
}
