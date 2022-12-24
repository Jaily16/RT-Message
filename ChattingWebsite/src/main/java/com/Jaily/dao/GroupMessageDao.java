package com.Jaily.dao;

import com.Jaily.entity.GroupMessage;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface GroupMessageDao {
    @Insert("insert into rt_group_message (groupid,sender,type,time,content) values(#{groupId},#{sender},#{type},#{time},#{content})")
    public int add(GroupMessage groupMessage);

    @Select("select * from rt_group_message where groupid = #{groupId} order by time")
    public List<GroupMessage> get(String groupId);
}
