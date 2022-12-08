package com.Jaily.service.Impl;

import com.Jaily.dao.UserDao;
import com.Jaily.entity.User;
import com.Jaily.service.UserService;
import com.Jaily.utility.Code;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public Boolean register(User user) {
        return userDao.add(user) > 0;
    }

    @Override
    public Code login(String username, String password) {
        User user = userDao.getByName(username);
        if(user == null){
            return Code.LOGIN_USERNAME_NOT_FOUND;
        }
        if(!password.equals(user.getPassword())){
            return Code.LOGIN_PASSWORD_INCORRECT;
        }
        return Code.LOGIN_SUCCESS;
    }

    @Override
    public Boolean logout(String username) {
        return userDao.delete(username) > 0;
    }

    @Override
    public Boolean updateUserInfo(User user) {
        return userDao.update(user) > 0;
    }

    @Override
    public Boolean ifUsernameExist(String username) {
        return userDao.getByName(username) != null;
    }

    @Override
    public User getUserByName(String username) {
        return userDao.getByName(username);
    }
}
