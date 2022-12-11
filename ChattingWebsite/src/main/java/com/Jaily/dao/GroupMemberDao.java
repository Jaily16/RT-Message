package com.Jaily.dao;

import com.Jaily.entity.Group;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface GroupMemberDao {
    @Insert("insert into rt_group_member (groupid, membername) values(#{groupId},#{username})")
    public int add(String groupId, String username);

    @Select("select count(*) from rt_group_member where groupid = #{groupId}")
    public Boolean countGroupById(String groupId);

    @Select("Select rt_group.* from rt_group,rt_group_member where rt_group_member.membername = #{username} and rt_group_member.groupid = rt_group.groupid")
    public List<Group> getGroupsByUsername(String username);

    @Select("select count(*) from rt_group_member where groupid = #{groupId} and membername = #{username}")
    public Boolean countGroup(String groupId, String username);

    @Delete("delete from rt_group_member where groupid = #{groupId} and membername = #{username}")
    public int delete(String groupId, String username);
}
