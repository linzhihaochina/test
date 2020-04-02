package com.youngforconding.test;

import com.youngforcoding.pojo.User;

import java.io.*;

/**
 *    
 *  *  
 *  * @Description:  测试   
 *  * @Author:       linZhiHao   
 *  * @CreateDate:   2020-04-01 5:20 PM   
 *  *    
 *  
 */
public class FrameTest {


    public void testSerializable() throws Exception {
        User user = new User();
        user.setId(1L);
        user.setUsername("lisi");

        ObjectOutputStream obs = new ObjectOutputStream(new FileOutputStream("test.txt"));
        obs.writeObject(user);
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream("test.txt"));
        Object object = ois.readObject();
    }
}
