package com.youngforcoding.dao;

import com.youngforcoding.pojo.User;
import org.apache.ibatis.annotations.CacheNamespace;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.io.Serializable;
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
public interface UserMapper extends Serializable {

    List<User> selectList();

    Integer insert(User user);

    User findByCondition(User user);

    User findByCondition2(@Param("id") Long id,@Param("name") String name);

    List<User> selectByIDList(List<Long> idList);

    User selectUserAndOrderList(Long id);

    List<User> selectUserAndRoles();


}
