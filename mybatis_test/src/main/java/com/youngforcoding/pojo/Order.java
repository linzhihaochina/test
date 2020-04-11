package com.youngforcoding.pojo;

import java.io.Serializable;

/**
 *    
 *  *  
 *  * @Description:  订单表实体   
 *  * @Author:       linZhiHao   
 *  * @CreateDate:   2020-04-08 12:13 AM   
 *  *    
 *  
 */

public class Order implements Serializable {

    private Long id;

    private Long userID;

    private String productName;

    private String productor;

    /**
     * 存在一对一关系
     */
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserID() {
        return userID;
    }

    public void setUserID(Long userID) {
        this.userID = userID;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductor() {
        return productor;
    }

    public void setProductor(String productor) {
        this.productor = productor;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", userID=" + userID +
                ", productName='" + productName + '\'' +
                ", productor='" + productor + '\'' +
                ", user=" + user +
                '}';
    }

//    @Override
//    public String toString() {
//        return "Order{" +
//                "id=" + id +
//                ", userID=" + userID +
//                ", productName='" + productName + '\'' +
//                ", productor='" + productor + '\'' +
//                '}';
//    }
}
