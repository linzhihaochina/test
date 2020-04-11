package com.youngforcoding.dao;

import com.youngforcoding.pojo.Order;
import com.youngforcoding.pojo.User;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 *    
 *  *  
 *  * @Description:  [一句话描述该类的功能]   
 *  * @Author:       linZhiHao   
 *  * @CreateDate:   2020-04-08 8:59 AM   
 *  *    
 *  
 */
public interface AnnotationOrderMapper {

    @Select(value = "select * from order_info")
    @Results(value = {
            @Result(column = "id", property = "id"),
            @Result(column = "user_id", property = "userID"),
            @Result(column = "product_name", property = "productName"),
            @Result(column = "productor", property = "productor"),
            @Result(column = "user_id", property = "user", javaType = User.class
                    , one = @One(select = "com.youngforcoding.dao.AnnotationUserMapper.selectByID")),
    })
    List<Order> selectOrderListContainUser();


    @Select(value = "select * from order_info where user_id = #{userID}")
    List<Order> selectByUserID(Long userID);

}
