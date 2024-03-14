package com.victor.example.common.model;

import java.io.Serializable;

/*
 *Author：Victor_htq
 *Package：com.victor.example.common.model
 *Project：victor-rpc
 *name：User
 *Date：2024/3/14  20:29
 *Filename：User
 */
public class User implements Serializable {

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
