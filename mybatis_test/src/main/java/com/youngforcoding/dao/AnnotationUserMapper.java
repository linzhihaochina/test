package com.youngforcoding.dao;

import com.youngforcoding.pojo.Order;
import com.youngforcoding.pojo.Role;
import com.youngforcoding.pojo.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 *    
 *  *  
 *  * @Description:  [一句话描述该类的功能]   
 *  * @Author:       linZhiHao   
 *  * @CreateDate:   2020-04-08 8:26 AM   
 *  *    
 *  
 */

public interface AnnotationUserMapper {

    @Insert(value = "insert into user_info values (#{id},#{name},#{age})")
    Integer insert(User user);

    @Update(value = "update user_info set name = #{name},age = #{age} where id = #{id}")
    Integer update(User user);

    //  对象和@Param注解不能同时使用
//    @Update(value = "update user_info set name = #{name},age = #{age} where id = #{id}")
//    Integer update02(User user, @Param("id") Long id);

    @Delete(value = "delete from user_info where id = #{id}")
    Integer delete(@Param("id") Long id);


    @Select("select * from user_info where id = #{id}")
    @Results(value = {
            @Result(column = "id", property = "id"),
            @Result(column = "name", property = "name"),
            @Result(column = "age", property = "age"),
//            @Result(column = "id", property = "roleList", javaType = Role.class
//                    , many = @Many(select = "com.youngforcoding.dao.AnnotationUserMapper.selectUserAndRoleListByID")),
    })
    User selectByID(Long id);


    //@Select("select * from user_info as u left join user_role as ur on u.id = ur.user_id inner join role as r on r.id = #{id}")
    @Select("select * from user_info")
    @Results(value = {
            @Result(column = "id",property = "id"),
            @Result(column = "name",property = "name"),
            @Result(column = "age",property = "age"),
            @Result(column = "id",property = "roleList",many = @Many(select = "com.youngforcoding.dao.AnnotationRoleMapper.getRoleListByUserID")),
    })
    List<User> selectUserListAndRoleListByID();

    @Select("select * from user_info")
    @Results(value = {
            @Result(column = "id", property = "id"),
            @Result(column = "name", property = "name"),
            @Result(column = "age", property = "age"),
            @Result(column = "id", property = "orderList", javaType = List.class
                    , many = @Many(select = "com.youngforcoding.dao.AnnotationOrderMapper.selectByUserID")),
    })
    List<User> selectUserListAndOrderList();


}
