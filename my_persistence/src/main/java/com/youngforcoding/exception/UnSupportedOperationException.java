package com.youngforcoding.exception;

/**
 *    
 *  *  
 *  * @Description:  [一句话描述该类的功能]   
 *  * @Author:       linZhiHao   
 *  * @CreateDate:   2020-04-10 11:51 PM   
 *  *    
 *  
 */
public class UnSupportedOperationException extends RuntimeException{

    public UnSupportedOperationException() {
    }

    public UnSupportedOperationException(String message) {
        super(message);
    }

}
