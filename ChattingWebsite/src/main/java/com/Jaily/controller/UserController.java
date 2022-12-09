package com.Jaily.controller;

import com.Jaily.entity.User;
import com.Jaily.service.UserService;
import com.Jaily.utility.Code;
import com.Jaily.utility.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;


    @PostMapping("/login")
    private Result<Boolean> login(@RequestBody User user){
        Code code = userService.login(user.getUsername(), user.getPassword());
        if(code == Code.LOGIN_USERNAME_NOT_FOUND){
            return new Result<Boolean>(code.getCode(),false, "用户名不存在");
        } else if (code == Code.LOGIN_PASSWORD_INCORRECT) {
            return new Result<Boolean>(code.getCode(), false, "密码错误");
        }
        return new Result<Boolean>(code.getCode(), true);
    }

    @GetMapping("/{username}")
    private Result<Boolean> checkUsername(@PathVariable String username){
        if(userService.ifUsernameExist(username)){
            return new Result<Boolean>(Code.REGISTER_NAME_HAS_BEEN_USED.getCode(), false, "用户名已被使用");
        }
        return new Result<Boolean>(Code.REGISTER_NAME_PASS.getCode(), true);
    }

    @PostMapping("/register")
    private Result<Boolean> register(@RequestBody User user){
        if(userService.register(user)){
            return new Result<Boolean>(Code.REGISTER_SUCCESS.getCode(), true, "注册成功");
        }
        return new Result<Boolean>(Code.REGISTER_FAILED.getCode(), false, "注册失败");
    }


}
