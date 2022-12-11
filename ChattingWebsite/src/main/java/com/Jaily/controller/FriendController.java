package com.Jaily.controller;

import com.Jaily.service.FriendService;
import com.Jaily.utility.Code;
import com.Jaily.utility.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/friends")
public class FriendController {
    @Autowired
    private FriendService friendService;

    @GetMapping("/{name1}/{name2}")
    private Result<Boolean> addFriend(@PathVariable("name1") String username, @PathVariable("name2") String friendName){
        Code code = friendService.addFriend(username, friendName);
        if(code == Code.FRIEND_ADD_AGAIN){
            return new Result<Boolean>(code.getCode(), false, "该用户已经是您的好友,请勿重复添加!");
        }
        return new Result<Boolean>(code.getCode(), true, "好友添加成功");
    }

    @DeleteMapping("/{name1}/{name2}")
    private Result<Boolean> deleteFriend(@PathVariable("name1") String username, @PathVariable("name2") String friendName){
        friendService.deleteFriend(username, friendName);
        return new Result<Boolean>(Code.FRIEND_DELETE_SUCCESS.getCode(), true);
    }

}
