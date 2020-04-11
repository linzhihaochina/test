package com.youngforcoding.test.annotation;

import java.lang.annotation.*;

import static java.lang.annotation.ElementType.*;

/**
 *    
 *  *  
 *  * @Description:  [一句话描述该类的功能]   
 *  * @Author:       linZhiHao  
 *  * @CreateDate:   2020-04-05 9:16 PM   
 *  *    
 *  
 */
@Target(value = {TYPE_PARAMETER})
public @interface Custom {
}
