package com.victor.example.common.service;

import com.victor.example.common.model.User;

/*
 *Author：Victor_htq
 *Package：com.victor.example.common.service
 *Project：victor-rpc
 *name：UserService
 *Date：2024/3/14  20:33
 *Filename：UserService
 */
public interface UserService {

    /**
     * 获取用户
     * @param user 用户
     * @return user
     */
    User getUser(User user);

    /**
     * 新方法 - 获取数字
     * @return 1
     */
    default short getNumber() {
        return 1;
    }
}
