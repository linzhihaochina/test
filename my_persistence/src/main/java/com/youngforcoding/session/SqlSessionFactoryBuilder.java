package com.youngforcoding.session;

import com.youngforcoding.builder.xml.XmlConfigBuilder;
import com.youngforcoding.config.Configuration;
import com.youngforcoding.session.impl.DefaultSqlSessionFactory;
import org.dom4j.DocumentException;

import java.beans.PropertyVetoException;

/**
 *    
 *  *  
 *  * @Description:  [一句话描述该类的功能]   
 *  * @Author:       linZhiHao   
 *  * @CreateDate:   2020-04-04 11:54 PM   
 *  *    
 *  
 */
public class SqlSessionFactoryBuilder {

    public SqlSessionFactory build(String path) throws DocumentException, PropertyVetoException, ClassNotFoundException {
        XmlConfigBuilder parser = new XmlConfigBuilder();
        return build(parser.parseConfiguration(path));
    }

    public SqlSessionFactory build(Configuration configuration) {
        return new DefaultSqlSessionFactory(configuration);
    }
}
