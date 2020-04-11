package com.youngforcoding.executor;

import com.youngforcoding.config.MappedStatement;

import java.util.List;

/**
 *    
 *  *  
 *  * @Description:  sql执行器接口   
 *  * @Author:       linZhiHao   
 *  * @CreateDate:   2020-04-10 7:34 AM   
 *  *    
 *  
 */
public interface Executor {

    Integer doUpdate(MappedStatement mappedStatement, Object... parameters) throws Exception;

    <E> List<E> doQuery(MappedStatement mappedStatement, Object... parameters) throws Exception;

    void commit();

    void rollback();

    void close();

}
