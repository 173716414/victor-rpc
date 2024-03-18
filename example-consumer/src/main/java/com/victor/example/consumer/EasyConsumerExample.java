package com.victor.example.consumer;

import com.victor.example.common.model.User;
import com.victor.example.common.service.UserService;
import com.victor.vicrpc.proxy.ServiceProxyFactory;

/*
 *Author：Victor_htq
 *Package：com.victor.example.consumer
 *Project：victor-rpc
 *name：EasyConsumerExample
 *Date：2024/3/14  20:46
 *Filename：EasyConsumerExample
 */
public class EasyConsumerExample {
    public static void main(String[] args) {

        UserService userService = ServiceProxyFactory.getProxy(UserService.class);
        User user = new User();
        user.setName("victor");
        // 调用
        User newUser = userService.getUser(user);
        if (newUser != null) {
            System.out.println(newUser.getName());
        } else {
            System.out.println("user == null");
        }
        short number = userService.getNumber();
        System.out.println(number);
    }
}
