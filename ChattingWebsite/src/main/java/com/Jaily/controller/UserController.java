package com.Jaily.controller;

import com.Jaily.entity.User;
import com.Jaily.service.FriendService;
import com.Jaily.service.UserService;
import com.Jaily.utility.Code;
import com.Jaily.utility.Result;
import org.apache.ibatis.jdbc.Null;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private FriendService friendService;


    @PostMapping("/login")
    private Result<Boolean> login(@RequestBody User user, HttpServletResponse resp) throws Exception {
        Code code = userService.login(user.getUsername(), user.getPassword());
        if(code == Code.LOGIN_USERNAME_NOT_FOUND){
            return new Result<Boolean>(code.getCode(),false, "用户名不存在");
        } else if (code == Code.LOGIN_PASSWORD_INCORRECT) {
            return new Result<Boolean>(code.getCode(), false, "密码错误");
        }
        //使用Cookie存储用户登录信息
        //使用URL编码存储用户数据
        String username = URLEncoder.encode(user.getUsername(), "UTF-8");
        Cookie cookie = new Cookie("username", username);
//        cookie.setMaxAge(60 * 30);
        resp.addCookie(cookie);
        return new Result<Boolean>(code.getCode(), true);
    }

    @GetMapping("/signOut")
    private Result<Null> signOut(HttpServletResponse resp){
        Cookie cookie = new Cookie("username", "");
        resp.addCookie(cookie);
        return new Result<Null>(Code.LOGOUT.getCode(), null);
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

    @GetMapping("/verify")
    private Result<Boolean> verifyUser(@CookieValue(value = "username", defaultValue = "1234567890123") String name){
        if("1234567890123".equals(name)){
            return new Result<Boolean>(Code.VERIFY_FAILED.getCode(), false, "请先登录");
        }
        String username = URLDecoder.decode(name);
        return new Result<Boolean>(Code.VERIFY_SUCCESS.getCode(), true);
    }

    @GetMapping("/info")
    private Result<User> getUserInfo(@CookieValue(value = "username") String name){
        String username = URLDecoder.decode(name);
        User user = userService.getUserByName(username);
        return new Result<User>(Code.VERIFY_SUCCESS.getCode(), user);
    }

    @PutMapping("/info")
    private Result<Boolean> updateUserInfo(@RequestBody User user){
        return userService.updateUserInfo(user) ? new Result<Boolean>(Code.USER_INFO_CHANGED_SUCCESS.getCode(), true, "用户信息修改成功") : new Result<Boolean>(Code.USER_INFO_CHANGED_FAILED.getCode(), false, "用户信息修改失败");
    }

    @GetMapping("/search/{username}")
    private Result<?> searchUserIndo(@PathVariable String username){
        User user = userService.getUserByName(username);
        if(user == null){
            return new Result<Boolean>(Code.SEARCH_USER_FAILED.getCode(), false,"您查找的用户不存在");
        }
        return new Result<User>(Code.SEARCH_USER_SUCCESS.getCode(), user);
    }

    @GetMapping("/friendList")
    private Result<List<User>> getFriendList(@CookieValue(value = "username") String name){
        String username = URLDecoder.decode(name);
        List<User> friendList = friendService.getFriendList(username);
        return new Result<List<User>>(Code.FRIEND_LIST_LOAD.getCode(), friendList);
    }
}
