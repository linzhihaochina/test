package com.youngforcoding.exception;

/**
 *    
 *  *  
 *  * @Description:  数据源配置信息没有找到   
 *  * @Author:       linZhiHao   
 *  * @CreateDate:   2020-04-05 11:09 AM   
 *  *    
 *  
 */
public class DataSourceNotFindException extends RuntimeException {


    public DataSourceNotFindException() {
    }

    public DataSourceNotFindException(String message) {
        super(message);
    }
}
