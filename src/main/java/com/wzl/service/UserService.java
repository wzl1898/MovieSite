package com.wzl.service;

import com.wzl.pojo.User;

public interface UserService {
    public User getLoginUser(String username, String password);
    public User createUser(String username, String password, int id);
}
