package com.youngforcoding.test;

import com.youngforcoding.config.Resources;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

/**
 *    
 *  *  
 *  * @Description:  [一句话描述该类的功能]   
 *  * @Author:       linZhiHao   
 *  * @CreateDate:   2020-04-03 12:57 AM   
 *  *    
 *  
 */
public class ResourcesTest {

    public static void main(String[] args) throws FileNotFoundException {
        //InputStream is = Resources.getResource("jdbc.properties");
        InputStream is = Resources.getResource("text");
        InputStream is2 = new FileInputStream("/Users/linzhihao/test/Test.java");
        InputStream is3 = new FileInputStream("text");
        System.out.println(is);
        System.out.println(is2);
    }
}
