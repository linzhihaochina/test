package com.youngforcoding.session.impl;

import com.youngforcoding.config.Configuration;
import com.youngforcoding.session.SqlSession;
import com.youngforcoding.session.SqlSessionFactory;

/**
 *    
 *  *  
 *  * @Description:  [一句话描述该类的功能]   
 *  * @Author:       linZhiHao   
 *  * @CreateDate:   2020-04-05 12:01 AM   
 *  *    
 *  
 */
public class DefaultSqlSessionFactory implements SqlSessionFactory {

    private Configuration configuration;

    public DefaultSqlSessionFactory(Configuration configuration) {
        this.configuration = configuration;
    }

    public Configuration getConfiguration() {
        return configuration;
    }

    public void setConfiguration(Configuration configuration) {
        this.configuration = configuration;
    }

    @Override
    public SqlSession openSession() {
        return new DefaultSqlSession(configuration);
    }

    @Override
    public SqlSession openSession(boolean autoCommit) {
        return new DefaultSqlSession(autoCommit, configuration);
    }
}
