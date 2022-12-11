package com.Jaily.controller;

import com.Jaily.entity.Group;
import com.Jaily.service.GroupService;
import com.Jaily.utility.Code;
import com.Jaily.utility.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/groups")
public class GroupController {
    @Autowired
    private GroupService groupService;

    @PostMapping
    private Result<Boolean> addGroup(@RequestBody Group group){
        Code code = groupService.addGroup(group);
        if(code == Code.GROUP_ADD_AGAIN){
            return new Result<Boolean>(code.getCode(), false, "该群号已存在,请重新创建");
        }
        if(code == Code.GROUP_ADD_FAILED){
            return new Result<Boolean>(code.getCode(), false, "创建失败");
        }
        return new Result<Boolean>(code.getCode(), true, "创建成功");
    }

    @GetMapping("/{groupid}")
    private Result<?> searchGroup(@PathVariable("groupid") String groupId){
        Group group = groupService.getGroupById(groupId);
        if(group == null){
            return new Result<Boolean>(Code.SEARCH_GROUP_FAILED.getCode(), false);
        }
        return new Result<Group>(Code.SEARCH_GROUP_SUCCESS.getCode(), group);
    }
}
