package com.youngforcoding.session;

/**
 *    
 *  *  
 *  * @Description:     
 *  * @Author:       linZhiHao   
 *  * @CreateDate:   2020-04-04 11:56 PM   
 *  *    
 *  
 */
public interface SqlSessionFactory {

    SqlSession openSession();
    SqlSession openSession(boolean autoCommit);

}
