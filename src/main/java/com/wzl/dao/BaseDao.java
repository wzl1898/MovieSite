package com.wzl.dao;

import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

public class BaseDao {
    private static String driver;
    private static String url;
    private static String username;
    private static String password;

    static {
        Properties properties = new Properties();

        InputStream is = BaseDao.class.getClassLoader().getResourceAsStream("db.properties");

        try{
            properties.load(is);
        }catch (Exception e){
            e.printStackTrace();
        }

        driver = properties.getProperty("driver");
        url = properties.getProperty("url");
        username = properties.getProperty("username");
        password = properties.getProperty("password");
    }
    //获取数据库连接
    public static Connection getConnection(){
        Connection connection = null;
        try{
            Class.forName(driver);
            connection = DriverManager.getConnection(url, username, password);
        }catch (Exception e){
            e.printStackTrace();
        }
        return connection;
    }
    //查询公共方法
    public static ResultSet execute(Connection connection, String sql, Object[] param ,ResultSet resultSet,PreparedStatement preparedStatement) throws SQLException {
        preparedStatement = connection.prepareStatement(sql);
        for (int i = 0; i < param.length; i++) {
            preparedStatement.setObject(i+1, param[i]);
        }
        resultSet = preparedStatement.executeQuery();
        return resultSet;
    }
    //增删改公共方法
    public static int execute(Connection connection, String sql, Object[] param, PreparedStatement preparedStatement) throws SQLException {
        preparedStatement = connection.prepareStatement(sql);
        for (int i = 0; i < param.length; i++) {
            preparedStatement.setObject(i+1, param[i]);
        }
        int updatedRows = preparedStatement.executeUpdate();
        return updatedRows;
    }
    //关闭连接
    public static boolean closeResource(Connection connection, PreparedStatement preparedStatement, ResultSet resultSet){
        boolean flag = true;
        if (resultSet != null){
            try {
                resultSet.close();
                resultSet = null;
            } catch (SQLException e1) {
                flag = false;
                e1.printStackTrace();
            }
        }
        if (preparedStatement != null){
            try {
                preparedStatement.close();
                preparedStatement = null;
            } catch (SQLException e1) {
                flag = false;
                e1.printStackTrace();
            }
        }
        if (connection != null){
            try {
                connection.close();
                connection = null;
            } catch (SQLException e1) {
                flag = false;
                e1.printStackTrace();
            }
        }
        return flag;
    }
}
