package com.youngforcoding.test;

import com.youngforcoding.pojo.User;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;


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


    @Test
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
