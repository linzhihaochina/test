package com.youngforcoding.pojo;

import lombok.Data;

import java.io.Serializable;

/**
 *    
 *  *  
 *  * @Description:  使用lombok编写JavaBean   
 *  * @Author:       linZhiHao   
 *  * @CreateDate:   2020-04-02 12:02 PM   
 *  *    
 *  
 */
@Data
public class User implements Serializable {

    private Long id;

    private String username;

}
