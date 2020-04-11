package com.youngforcoding.dao;

import com.youngforcoding.pojo.Order;

import java.util.List;

/**
 *    
 *  *  
 *  * @Description:  Order表的mapper接口   
 *  * @Author:       linZhiHao   
 *  * @CreateDate:   2020-04-08 12:17 AM   
 *  *    
 *  
 */
public interface OrderMapper {
    List<Order> selectOrderList();


}
