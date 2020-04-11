package com.youngforcoding.pojo;

import java.io.Serializable;

/**
 *    
 *  *  
 *  * @Description:  [一句话描述该类的功能]   
 *  * @Author:       linZhiHao   
 *  * @CreateDate:   2020-04-08 8:14 AM   
 *  *    
 *  
 */
public class Role implements Serializable {

    private Long id;

    private Long userID;

    private String roleName;

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

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    @Override
    public String toString() {
        return "Role{" +
                "id=" + id +
                ", userID=" + userID +
                ", roleName='" + roleName + '\'' +
                '}';
    }
}
