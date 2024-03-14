package com.victor.example.provider;

import com.victor.example.common.model.User;
import com.victor.example.common.service.UserService;

/*
 *Author：Victor_htq
 *Package：com.victor.example.provider
 *Project：victor-rpc
 *name：UserServiceImpl
 *Date：2024/3/14  20:40
 *Filename：UserServiceImpl
 */
public class UserServiceImpl implements UserService {
    @Override
    public User getUser(User user) {
        System.out.println("用户名" + user.getName());
        return user;
    }
}
