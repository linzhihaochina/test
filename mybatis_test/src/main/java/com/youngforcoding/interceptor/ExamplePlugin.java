package com.youngforcoding.interceptor;

import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;

import java.util.Properties;

/**
 *    
 *  *  
 *  * @Description:  [一句话描述该类的功能]   
 *  * @Author:       linZhiHao   
 *  * @CreateDate:   2020-04-09 12:42 AM   
 *  *    
 *  
 */
@Intercepts(value = {
        @Signature(type = Executor.class, method = "query", args = {MappedStatement.class, Object.class
                , RowBounds.class, ResultHandler.class})
})
public class ExamplePlugin implements Interceptor {

    public ExamplePlugin() {
    }

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        System.out.println("增强了，牛不牛？");
        return invocation.proceed();
    }

    @Override
    public Object plugin(Object target) {
        //  将目标对象target（四大核心组件对象ParameterHandler、ResultSetHandler、StatementHandler、Executor）增强了
        return Plugin.wrap(target, this);
    }

    @Override
    public void setProperties(Properties properties) {

    }
}
