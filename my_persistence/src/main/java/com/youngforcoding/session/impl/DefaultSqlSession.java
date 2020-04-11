package com.youngforcoding.session.impl;

import com.youngforcoding.config.Configuration;
import com.youngforcoding.config.MappedStatement;
import com.youngforcoding.exception.NoSuchStatementException;
import com.youngforcoding.exception.UnSupportedOperationException;
import com.youngforcoding.executor.Executor;
import com.youngforcoding.executor.impl.SimpleExecutor;
import com.youngforcoding.session.SqlSession;

import java.lang.reflect.*;
import java.util.List;

/**
 *    
 *  *  
 *  * @Description:  默认SqlSession实现类   
 *  * @Author:       linZhiHao   
 *  * @CreateDate:   2020-04-05 3:35 PM   
 *  *    
 *  
 */
public class DefaultSqlSession implements SqlSession {

    private Configuration configuration;

    private Executor executor;

    private boolean autoCommit;

    public DefaultSqlSession(boolean autoCommit, Configuration configuration) {
        this.executor = new SimpleExecutor(autoCommit);
        this.configuration = configuration;
    }

    public DefaultSqlSession(Configuration configuration) {
        this.configuration = configuration;
        this.executor = new SimpleExecutor(autoCommit);
    }

    public Configuration getConfiguration() {
        return configuration;
    }

    public void setConfiguration(Configuration configuration) {
        this.configuration = configuration;
    }


    @Override
    @SuppressWarnings("unchecked")
    public <E> List<E> selectList(String statementID, Object... params) throws Exception {
        return executor.doQuery(configuration.getMappedStatementMap().get(statementID), params);
    }

    public int insert(String statementID, Object... params) throws Exception {
        return executor.doUpdate(configuration.getMappedStatementMap().get(statementID), params);
    }

//    @Override
//    public <E> List<E> selectList(String statementID, Object... params) throws SQLException, IllegalAccessException
//            , InstantiationException, IntrospectionException, InvocationTargetException {
//        MappedStatement mappedStatement = configuration.getMappedStatementMap().get(statementID);
//        Connection connection = configuration.getDataSource().getConnection();
//        PreparedStatement statement = connection.prepareStatement(mappedStatement.getSql());
//        for (int i = 0; i < params.length; i++) {
//            statement.setObject(i + 1, params[i]);
//        }
//        ResultSet resultSet = statement.executeQuery();
//        Class<?> resultType = mappedStatement.getResultType();
//        List<E> result = new ArrayList<>();
//        ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
//        while (resultSet.next()) {
//            E obj = (E) resultType.newInstance();
//            for (int i = 1; i < resultSetMetaData.getColumnCount(); i++) {
//                String propertyName = resultSetMetaData.getColumnName(i);
//                Object object = resultSet.getObject(i);
//
//                PropertyDescriptor propertyDescriptor = new PropertyDescriptor(propertyName, resultType);
//                propertyDescriptor.getWriteMethod().invoke(obj, object);
//            }
//
//            result.add(obj);
//        }
//
//        return result;
//    }

    @Override
    public <E> E select(String statementID, Object... params) throws Exception {
        List<E> result = selectList(statementID, params);
        return result.get(0);
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T> T getMapper(Class<T> clazz) {
        T result = (T) Proxy.newProxyInstance(clazz.getClassLoader(), new Class[]{clazz}, (proxy, method, args) -> {
            String nameSpace = clazz.getName();
            String statementID = nameSpace + "." + method.getName();
            MappedStatement statement = configuration.getMappedStatementMap().get(statementID);
            if (statement == null) {
                throw new NoSuchStatementException("找不到需要执行的sql语句");
            }
            switch (statement.getType()) {
                case INSERT:
                case DELETE:
                case UPDATE:
                    Integer affectCount = executor.doUpdate(statement, args);
                    if (void.class.equals(method.getReturnType())) {
                        return null;
                    } else {
                        return affectCount;
                    }
                case SELECT:
                    List queryResult = executor.doQuery(statement, args);
                    if (method.getGenericReturnType() instanceof ParameterizedType) {
                        return queryResult;
                    } else {
                        return queryResult.get(0);
                    }
                default:
                    throw new UnSupportedOperationException("不支持该操作！");
            }
        });
        return result;
    }
}
