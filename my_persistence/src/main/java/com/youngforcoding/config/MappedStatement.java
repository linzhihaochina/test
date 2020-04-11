package com.youngforcoding.config;

import com.youngforcoding.constants.SqlType;

/**
 *    
 *  *  
 *  * @Description:  每一个sql语句的封装
 *  * @Author:       linZhiHao   
 *  * @CreateDate:   2020-04-05 1:01 AM   
 *  *    
 *  
 */
public class MappedStatement {

    private Configuration configuration;

    private BoundSql boundSql;

    /**
     * namespace . statementID
     */
    private String id;

    private SqlType type;

    private Class<?> parameterType;

    private Class<?> resultType;

    public MappedStatement() {
    }


    public BoundSql getBoundSql() {
        return boundSql;
    }

    public void setBoundSql(BoundSql boundSql) {
        this.boundSql = boundSql;
    }

    public Configuration getConfiguration() {
        return configuration;
    }

    public void setConfiguration(Configuration configuration) {
        this.configuration = configuration;
    }

    public SqlType getType() {
        return type;
    }

    public void setType(SqlType type) {
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    public Class<?> getParameterType() {
        return parameterType;
    }

    public void setParameterType(Class<?> parameterType) {
        this.parameterType = parameterType;
    }

    public Class<?> getResultType() {
        return resultType;
    }

    public void setResultType(Class<?> resultType) {
        this.resultType = resultType;
    }
}
