package com.youngforcoding.config;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 *    
 *  *  
 *  * @Description:  [一句话描述该类的功能]   
 *  * @Author:       linZhiHao   
 *  * @CreateDate:   2020-04-04 3:11 PM   
 *  *    
 *  
 */
public class Configuration {

    private DataSource dataSource;

    private Map<String, MappedStatement> mappedStatementMap = new HashMap<>();

    public Configuration() {
    }

    public DataSource getDataSource() {
        return dataSource;
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public Map<String, MappedStatement> getMappedStatementMap() {
        return mappedStatementMap;
    }

    public void setMappedStatementMap(Map<String, MappedStatement> mappedStatementMap) {
        this.mappedStatementMap = mappedStatementMap;
    }
}
