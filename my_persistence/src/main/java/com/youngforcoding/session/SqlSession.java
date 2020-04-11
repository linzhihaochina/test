package com.youngforcoding.session;

import java.beans.IntrospectionException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.List;

/**
 *    
 *  *  
 *  * @Description:  [一句话描述该类的功能]   
 *  * @Author:       linZhiHao   
 *  * @CreateDate:   2020-04-04 11:54 PM   
 *  *    
 *  
 */
public interface SqlSession {

    //这里我们不知道执行的sql到底要返回什么结果集，如果固定了返回值类型，那么下次添加新的实体操作数据库的方法时又要重新增加新的方法。
    //所以我们使用泛型，再通过反射的形式来获取需要的返回结果类型
    //泛型会在编译的时候做类型擦除，其中的E如果指定了对应的类型，当我们编译完代码之后会生成对应的字节码文件，E会被替换成对应的类型。

    <E> List<E> selectList(String statementID, Object... params) throws SQLException, IllegalAccessException, InstantiationException, IntrospectionException, InvocationTargetException, Exception;

    <E> E select(String statementID, Object... params) throws IllegalAccessException, SQLException, InstantiationException, IntrospectionException, InvocationTargetException, Exception;

    <T> T getMapper(Class<T> clazz);

}
