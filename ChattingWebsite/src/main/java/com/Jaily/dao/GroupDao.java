package com.Jaily.dao;

import com.Jaily.entity.Group;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface GroupDao {
    @Insert("insert into rt_group (groupid,groupname,avatar) values(#{groupId},#{groupName},#{avatar})")
    public int add(Group group);

    @Delete("delete from rt_group where groupid = #{groupId}")
    public int delete(String groupId);

    @Select("select * from rt_group where groupid = #{groupId}")
    public Group getGroupById(String groupId);

    @Select("select count(*) from rt_group where groupid = #{groupId}")
    public Boolean countGroupById(String groupId);
}
