package com.Jaily.service;

import com.Jaily.entity.User;
import com.Jaily.utility.Code;

public interface UserService {

    /**
     * 处理用户注册，返回是否注册成功
     * @param user
     * @return
     *
     */
    public Boolean register(User user);

    /**
     * 处理用户登录，返回登录状态
     * @param username
     * @param password
     * @return
     */
    public Code login(String username, String password);

    /**
     * 处理用户注销账号，返回是否注销成功
     * @param username
     * @return
     */
    public Boolean logout(String username);

    /**
     * 修改用户信息
     * @param user
     * @return
     */
    public Boolean updateUserInfo(User user);

    /**
     * 查询某个用户名是否已经被使用
     * @param username
     * @return
     */
    public Boolean ifUsernameExist(String username);

    /**
     * 根据用户名查找用户对象
     * @param username
     * @return
     */
    public User getUserByName(String username);


}
