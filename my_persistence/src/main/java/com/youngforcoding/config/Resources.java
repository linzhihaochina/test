package com.youngforcoding.config;

import java.io.InputStream;

/**
 *    
 *  *  
 *  * @Description:  将资源文件转化为输入流
 *  * @Author:       linZhiHao   
 *  * @CreateDate:   2020-04-03 12:54 AM   
 *  *    
 *  
 */
public class Resources {

    /**
     * @param path  资源文件路径
     * @return  获取资源文件输入流
     */
    public static InputStream getResource(String path){
        //  通过类加载器获取输入流的方式能够基于当前类加载器的加载路径获取指定文件的输入流
        return Resources.class.getClassLoader().getResourceAsStream(path);
    }
}
