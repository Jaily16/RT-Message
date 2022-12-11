package com.Jaily.controller;

import com.Jaily.entity.Group;
import com.Jaily.service.GroupMemberService;
import com.Jaily.utility.Code;
import com.Jaily.utility.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/groupMember")
public class GroupMemberController {
    @Autowired
    private GroupMemberService groupMemberService;

    @GetMapping("/{groupid}/{username}")
    private Result<Boolean> joinInGroup(@PathVariable("groupid") String groupId, @PathVariable("username") String username){
        Code code = groupMemberService.joinInGroup(groupId, username);
        if(code == Code.GROUP_JOIN_AGAIN){
            return new Result<Boolean>(code.getCode(), false, "您不能重复加入该群聊!");
        }
        if(code == Code.GROUP_JOIN_FAILED){
            return new Result<Boolean>(code.getCode(), false, "加入群聊失败");
        }
        return new Result<Boolean>(code.getCode(), true, "加入群聊成功");
    }

    @DeleteMapping ("/{groupid}/{username}")
    private Result<Boolean> exitGroup(@PathVariable("groupid") String groupId, @PathVariable("username") String username){
        Code code = groupMemberService.exitGroup(groupId, username);
        if(code == Code.GROUP_EXIT_FAILED){
            return new Result<Boolean>(code.getCode(), false, "退出群聊失败");
        }
        if(code == Code.GROUP_EXIT_SUCCESS_AND_DELETE_GROUP){
            return new Result<Boolean>(code.getCode(), true, "成功退出该群聊,由于您最后一个退群,该群聊自动删除");
        }
        return new Result<Boolean>(code.getCode(), true, "成功退出该群聊");
    }

}
