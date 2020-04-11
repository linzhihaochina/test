package com.youngforcoding.executor.impl;

import com.youngforcoding.config.BoundSql;
import com.youngforcoding.config.MappedStatement;
import com.youngforcoding.executor.Executor;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *    
 *  *  
 *  * @Description:  默认的sql执行器,默认每次都关闭连接   
 *  * @Author:       linZhiHao   
 *  * @CreateDate:   2020-04-10 7:34 AM   
 *  *    
 *  
 */
public class SimpleExecutor implements Executor {

    private boolean autoCommit;

    public SimpleExecutor(boolean autoCommit) {
        this.autoCommit = autoCommit;
    }

    @Override
    public Integer doUpdate(MappedStatement mappedStatement, Object... parameters) throws Exception {
        Connection connection = mappedStatement.getConfiguration().getDataSource().getConnection();
        connection.setAutoCommit(false);
        BoundSql boundSql = mappedStatement.getBoundSql();
        PreparedStatement statement = connection.prepareStatement(boundSql.getSqlText());
        setParams(mappedStatement, statement, parameters);
        int affectCount = statement.executeUpdate();
        if (autoCommit) {
            try {
                connection.commit();
            } catch (SQLException e) {
                connection.rollback();
            }
        }
        statement.close();
        connection.close();
        return affectCount;
    }


    @Override
    @SuppressWarnings("unchecked")
    public <E> List<E> doQuery(MappedStatement mappedStatement, Object... parameters) throws Exception {
        Connection connection = mappedStatement.getConfiguration().getDataSource().getConnection();
        BoundSql boundSql = mappedStatement.getBoundSql();
        PreparedStatement statement = connection.prepareStatement(boundSql.getSqlText());
        setParams(mappedStatement, statement, parameters);
        ResultSet resultSet = statement.executeQuery();
        Class<?> resultType = mappedStatement.getResultType();
        List<E> result = new ArrayList<>();
        while (resultSet.next()) {
            Constructor<?> constructor = resultType.getConstructor((Class<?>) null);
            E obj = (E) constructor.newInstance((Object) null);
            for (Field field : resultType.getDeclaredFields()) {
                field.setAccessible(true);
                String fieldName = field.getName();
                Object value = resultSet.getObject(fieldName);
                if (value != null) {
                    field.set(obj, value);
                }
            }
            result.add(obj);
        }
        resultSet.close();
        statement.close();
        connection.close();
        return result;
    }

    private void setParams(MappedStatement mappedStatement, PreparedStatement statement
            , Object[] parameters) throws Exception {
        if (parameters != null) {
            //  参数对象
            dealParameterObj(mappedStatement, statement, parameters);
        }
    }

    private void dealParameterObj(MappedStatement mappedStatement, PreparedStatement statement
            , Object[] parameters) throws Exception {
        Object parameterObj = parameters[0];
        List<String> parameterList = mappedStatement.getBoundSql().getParameterList();
        //  基本类型
        if (isPrimitive(parameterObj)) {
            statement.setObject(1, parameterObj);
        } else {
            //  预处理的参数超过一个，则认为是一个正常JavaBean
            for (int i = 0; i < parameterList.size(); i++) {
                Field field = parameterObj.getClass().getDeclaredField(parameterList.get(i));
                field.setAccessible(true);
                statement.setObject(i + 1, field.get(parameterObj));
            }
        }
    }

    private boolean isPrimitive(Object obj) {
        try {
            return ((Class<?>) obj.getClass().getField("TYPE").get(null)).isPrimitive();
        } catch (Exception e) {
            return false;
        }
    }


    @Override
    public void commit() {

    }

    @Override
    public void rollback() {

    }

    @Override
    public void close() {

    }
}
