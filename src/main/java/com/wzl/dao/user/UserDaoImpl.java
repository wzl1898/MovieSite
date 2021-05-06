package com.wzl.dao.user;

import com.wzl.dao.BaseDao;
import com.wzl.pojo.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDaoImpl implements UserDao{
    public User getLoginUser(Connection connection, String username, String password) throws SQLException {
        ResultSet resultSet = null;
        PreparedStatement preparedStatement = null;
        String sql = "select * from users where username=? and password=?";
        Object[] param = {username, password};
        User user = null;
        if(connection != null){
            resultSet = BaseDao.execute(connection, sql, param, resultSet, preparedStatement);
            if (resultSet.next()){
                user = new User();
                user.setId(resultSet.getInt("id"));
                user.setUsername(resultSet.getString("username"));
                user.setPassword(resultSet.getString("password"));
                user.setIsAdmin(resultSet.getInt("isAdmin"));
            }
            BaseDao.closeResource(null, preparedStatement, resultSet);
        }
        return user;

    }

    public User createUser(Connection connection, String username, String password, int id) throws SQLException {
        PreparedStatement preparedStatement = null;
        String sql = "insert into users(id, `username`, `password`)" +
                "values(?, ?, ?);";
        Object[] param = {id, username, password};
        User user = new User();
        user.setIsAdmin(0);
        user.setUsername(username);
        user.setId(id);
        user.setPassword(password);
        int updatedRow = 0;
        if(connection != null){
            updatedRow = BaseDao.execute(connection, sql, param, preparedStatement);
            BaseDao.closeResource(null, preparedStatement, null);
        }
        if (updatedRow == 1){
            return user;
        }else{
            return null;
        }
    }
}
