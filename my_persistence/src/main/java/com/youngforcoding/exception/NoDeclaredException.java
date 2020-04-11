package com.youngforcoding.exception;

/**
 *    
 *  *  
 *  * @Description:  Xml没有声明必须标签时抛出该异常   
 *  * @Author:       linZhiHao   
 *  * @CreateDate:   2020-04-11 9:25 AM   
 *  *    
 *  
 */
public class NoDeclaredException extends RuntimeException{

    public NoDeclaredException(String message){
        super(message);
    }
}
