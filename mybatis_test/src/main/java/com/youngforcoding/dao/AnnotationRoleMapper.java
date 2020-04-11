package com.youngforcoding.dao;

import com.youngforcoding.pojo.Role;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 *    
 *  *  
 *  * @Description:  [一句话描述该类的功能]   
 *  * @Author:       linZhiHao   
 *  * @CreateDate:   2020-04-08 1:57 PM   
 *  *    
 *  
 */
public interface AnnotationRoleMapper {

    @Select(value = "select * from user_role as ur inner join role as r on r.id = ur.role_id and ur.user_id = #{userID}")
    @Results(value = {
            @Result(column = "id",property = "id"),
            @Result(column = "user_id",property = "userID"),
            @Result(column = "role_name",property = "roleName"),
    })
    List<Role> getRoleListByUserID(Long userID);

}
