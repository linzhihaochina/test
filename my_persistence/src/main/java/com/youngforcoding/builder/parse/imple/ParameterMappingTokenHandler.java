package com.youngforcoding.builder.parse.imple;


import com.youngforcoding.builder.parse.TokenHandler;

import java.util.ArrayList;
import java.util.List;

/**
 *    
 *  *  
 *  * @Description:  sql处理器处理#{}占位符   
 *  * @Author:       linZhiHao   
 *  * @CreateDate:   2020-04-10 1:54 PM   
 *  *    
 *  
 */
public class ParameterMappingTokenHandler implements TokenHandler {

    private List<String> parameterList = new ArrayList<>();

    @Override
    public String handleToken(String content) {
        parameterList.add(content);
        return "?";
    }

    public List<String> getParameterList() {
        return parameterList;
    }

}
