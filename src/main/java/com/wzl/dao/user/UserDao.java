package com.wzl.dao.user;

import com.wzl.pojo.User;

import java.sql.Connection;
import java.sql.SQLException;

public interface UserDao {
    public User getLoginUser(Connection connection, String username, String password) throws SQLException;
    public User createUser(Connection connection, String username, String password, int id) throws SQLException;
}
