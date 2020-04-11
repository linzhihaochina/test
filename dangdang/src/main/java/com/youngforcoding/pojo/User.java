package com.youngforcoding.pojo;

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
public class User implements Serializable {

    private Long id;

    private String name;

    private Integer age;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
