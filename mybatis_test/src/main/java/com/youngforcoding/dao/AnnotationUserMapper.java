package com.youngforcoding.dao;

import com.youngforcoding.pojo.User;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;

/**
 *    
 *  *  
 *  * @Description:  用户Mapper   
 *  * @Author:       linZhiHao   
 *  * @CreateDate:   2020-04-08 8:26 AM   
 *  *    
 *  
 */

@Mapper
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
    })
    User selectByID(Long id);

    @Select("select * from user_info")
    @Results(value = {
            @Result(column = "id", property = "id"),
            @Result(column = "name", property = "name"),
            @Result(column = "age", property = "age"),
            @Result(column = "id", property = "roleList"
                    , many = @Many(select = "com.youngforcoding.dao.AnnotationRoleMapper.getRoleListByUserID")),
    })
    List<User> selectUserListAndRoleListByID();

    @Select("select * from user_info")
    @Results(value = {
            @Result(column = "id", property = "id"),
            @Result(column = "name", property = "name"),
            @Result(column = "age", property = "age"),
            @Result(column = "id", property = "orderList", javaType = List.class
                    , many = @Many(select = "com.youngforcoding.dao.AnnotationOrderMapper.selectByUserID"
                    , fetchType = FetchType.LAZY)),
    })
    List<User> selectUserListAndOrderList();


}
