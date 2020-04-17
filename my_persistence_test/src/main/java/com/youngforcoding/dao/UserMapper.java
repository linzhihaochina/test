package com.youngforcoding.dao;

import com.youngforcoding.pojo.User;

import java.util.List;

/**
 *    
 *  *  
 *  * @Description:  [一句话描述该类的功能]   
 *  * @Author:       linZhiHao   
 *  * @CreateDate:   2020-04-06 11:07 PM   
 *  *    
 *  
 */
public interface UserMapper {

    List<User> selectList();

    User selectUser(Long id);

    Integer insertUser(User user);

    Integer updateUser(User user);

    Integer deleteUser(Long id);


}
