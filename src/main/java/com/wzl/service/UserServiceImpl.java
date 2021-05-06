package com.wzl.service;

import com.wzl.dao.BaseDao;
import com.wzl.dao.user.UserDao;
import com.wzl.dao.user.UserDaoImpl;
import com.wzl.pojo.User;

import java.sql.Connection;
import java.sql.SQLException;

public class UserServiceImpl implements UserService{
    private UserDao userDao;
    public UserServiceImpl(){
        userDao = new UserDaoImpl();
    }
    public User getLoginUser(String username, String password) {
        Connection connection = null;
        User user = null;
        try {
            connection = BaseDao.getConnection();
            user = userDao.getLoginUser(connection, username, password);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            BaseDao.closeResource(connection, null, null);
        }
        return user;
    }

    public User createUser(String username, String password, int id) {
        Connection connection = null;
        User user = null;
        try {
            connection = BaseDao.getConnection();
            user = userDao.createUser(connection, username, password, id);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            BaseDao.closeResource(connection, null, null);
        }
        return user;
    }
}
