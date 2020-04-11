package com.youngforcoding.config;

import java.util.List;

/**
 *    
 *  *  
 *  * @Description:  SQl语句的封装   
 *  * @Author:       linZhiHao   
 *  * @CreateDate:   2020-04-10 1:44 PM   
 *  *    
 *  
 */

public class BoundSql {
    private String sqlText;

    private List<String> parameterList;

    public String getSqlText() {
        return sqlText;
    }

    public void setSqlText(String sqlText) {
        this.sqlText = sqlText;
    }

    public List<String> getParameterList() {
        return parameterList;
    }

    public void setParameterList(List<String> parameterList) {
        this.parameterList = parameterList;
    }
}
