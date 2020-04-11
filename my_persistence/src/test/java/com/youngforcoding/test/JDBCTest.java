package com.youngforcoding.test;


import java.sql.*;
import java.util.Properties;

/**
 *    
 *  *  
 *  * @Description:  JDBC测试类  
 *  * @Author:       linZhiHao   
 *  * @CreateDate:   2020-04-02 11:53 PM   
 *  *    
 *  
 */
public class JDBCTest {

    /**
     * JDBC的使用
     * 问题:
     * 1、配置硬编码                                      =》配置文件
     * 2、每一次访问数据库都获取一个连接，然后使用完关闭       =》连接池
     * 3、sql的执行，不灵活(有查询条件的sql)                =》配置文件
     * 4、查询的结果每次都需要封装成对象                     =》反射、内省
     *
     *
     * 数据库的连接信息是不经常改变的配置，存放到properties文件中
     *
     */
    public static void main(String[] args) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            //  1.加载驱动
            Properties properties = new Properties();
            properties.put("user", "root");
            properties.put("password", "123456");
            //  2.获取连接
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/test_db?characterEncoding=utf-8"
                    , properties);
            //  3.获取用于执行sql的对象
            //Statement statement = connection.createStatement();
            /**
             * 预处理对象，能够先将sql发送mysql进行预处理(将?进行转义，替换)
             */
            statement = connection.prepareStatement("select * from user_info where id = ?");
            statement.setLong(1, 1L);
            //  4.获取结果集
            //ResultSet resultSet = statement.executeQuery("select * from user_info");
            resultSet = statement.executeQuery();
            //  5.遍历结果集
            while (resultSet.next()) {
                Long id = resultSet.getLong("id");
                String name = resultSet.getString("name");
                Integer age = resultSet.getInt("age");
                System.out.println(id + "---" + name + "---" + age);
            }
        } catch (Exception e) {
            System.out.println("查询失败!");
        } finally {
            //  6.关闭连接
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

        }
    }

}
