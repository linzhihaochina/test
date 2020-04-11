package com.youngforcoding.builder.xml;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.youngforcoding.builder.parse.GenericTokenParser;
import com.youngforcoding.builder.parse.imple.ParameterMappingTokenHandler;
import com.youngforcoding.config.BoundSql;
import com.youngforcoding.constants.SqlType;
import com.youngforcoding.exception.DataSourceNotFindException;
import com.youngforcoding.config.Configuration;
import com.youngforcoding.config.MappedStatement;
import com.youngforcoding.config.Resources;
import com.youngforcoding.exception.NoDeclaredException;
import org.dom4j.*;
import org.dom4j.io.SAXReader;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;
import java.io.InputStream;
import java.util.*;

/**
 *    
 *  *  
 *  * @Description:  自定义持久层框架解析Xml的构造器   
 *  * @Author:       linZhiHao   
 *  * @CreateDate:   2020-04-05 12:05 AM   
 *  *    
 *  
 */
public class XmlConfigBuilder {


    public XmlConfigBuilder() {
    }

    public Configuration parseConfiguration(String path) throws DocumentException, PropertyVetoException, ClassNotFoundException {
        //  读取配置文件获取输入流
        InputStream is = Resources.getResource(path);
        //  使用dom4j解析xml
        SAXReader saxReader = new SAXReader();
        Document document = saxReader.read(is);
        Element rootElement = document.getRootElement();
        Configuration configuration = new Configuration();

        //  获取连接池配置信息
        configuration.setDataSource(getDataSource(rootElement.selectNodes("//datasource")));
        //  获取实体类映射配置信息
        configuration.setMappedStatementMap(getMappedStatementMap(configuration, rootElement.selectSingleNode("//mappers")));

        return configuration;
    }

    @SuppressWarnings("unchecked")
    private Map<String, MappedStatement> getMappedStatementMap(Configuration configuration, Node mappers) throws DocumentException, ClassNotFoundException {
        Element element = (Element) mappers;
        List<Element> mapperElements = element.elements("mapper");
        List<String> mapperResources = new ArrayList<>();
        for (Element mapperElement : mapperElements) {
            mapperResources.add(mapperElement.attributeValue("resource"));
        }

        return buildMappedStatementMap(configuration, mapperResources);
    }

    private Map<String, MappedStatement> buildMappedStatementMap(Configuration configuration, List<String> mapperResources) throws DocumentException, ClassNotFoundException {
        Map<String, MappedStatement> mappedStatementMap = new HashMap<>(16);
        for (String mapperResource : mapperResources) {
            mappedStatementMap.putAll(parseMappedStatement(configuration, mapperResource));
        }
        return mappedStatementMap;
    }

    @SuppressWarnings("unchecked")
    private Map<? extends String, ? extends MappedStatement> parseMappedStatement(Configuration configuration
            , String mapperResource) throws DocumentException, ClassNotFoundException {
        InputStream is = Resources.getResource(mapperResource);
        SAXReader saxReader = new SAXReader();
        Document document = saxReader.read(is);
        Element rootElement = document.getRootElement();
        String nameSpace = rootElement.attributeValue("namespace");
        //  拿到所有的select标签

        Map<String, MappedStatement> insertStatement = parseInsertTag(configuration, nameSpace
                , rootElement.selectNodes("//insert"));
        Map<String, MappedStatement> deleteStatement = parseDeleteTag(configuration, nameSpace
                , rootElement.selectNodes("//delete"));
        Map<String, MappedStatement> updateStatement = parseUpdateTag(configuration, nameSpace
                , rootElement.selectNodes("//update"));
        Map<String, MappedStatement> selectStatement = parseSelectTag(configuration, nameSpace
                , rootElement.selectNodes("//select"));

        Map<String, MappedStatement> mappedStatementMap = new HashMap<>(insertStatement.size() + selectStatement.size());
        mappedStatementMap.putAll(insertStatement);
        mappedStatementMap.putAll(deleteStatement);
        mappedStatementMap.putAll(updateStatement);
        mappedStatementMap.putAll(selectStatement);
        return mappedStatementMap;
    }

    private Map<String, MappedStatement> parseInsertTag(Configuration configuration, String nameSpace
            , List<Element> insertElements) throws ClassNotFoundException {
        Map<String, MappedStatement> mappedStatementMap = new HashMap<>(insertElements.size());
        for (Element insertElement : insertElements) {
            MappedStatement mappedStatement = new MappedStatement();
            mappedStatement.setConfiguration(configuration);
            mappedStatement.setType(SqlType.INSERT);
            mappedStatement.setId(nameSpace + "." + insertElement.attributeValue("id"));
            mappedStatement.setBoundSql(getBoundSql(insertElement.getTextTrim()));
            String parameterType = insertElement.attributeValue("parameterType");
            if (parameterType != null) {
                mappedStatement.setParameterType(Class.forName(parameterType));
            }
            mappedStatementMap.put(mappedStatement.getId(), mappedStatement);
        }
        return mappedStatementMap;
    }

    private Map<String, MappedStatement> parseDeleteTag(Configuration configuration, String nameSpace
            , List<Element> deleteElements) throws ClassNotFoundException {
        Map<String, MappedStatement> mappedStatementMap = new HashMap<>(deleteElements.size());
        for (Element deleteElement : deleteElements) {
            MappedStatement mappedStatement = new MappedStatement();
            mappedStatement.setConfiguration(configuration);
            mappedStatement.setType(SqlType.DELETE);
            mappedStatement.setId(nameSpace + "." + deleteElement.attributeValue("id"));
            mappedStatement.setBoundSql(getBoundSql(deleteElement.getTextTrim()));
            String parameterType = deleteElement.attributeValue("parameterType");
            if (parameterType != null) {
                mappedStatement.setParameterType(Class.forName(parameterType));
            }
            mappedStatementMap.put(mappedStatement.getId(), mappedStatement);
        }

        return mappedStatementMap;
    }

    private Map<String, MappedStatement> parseUpdateTag(Configuration configuration, String nameSpace
            , List<Element> updateElements) throws ClassNotFoundException {
        Map<String, MappedStatement> mappedStatementMap = new HashMap<>(updateElements.size());
        for (Element updateElement : updateElements) {
            MappedStatement mappedStatement = new MappedStatement();
            mappedStatement.setConfiguration(configuration);
            mappedStatement.setType(SqlType.UPDATE);
            mappedStatement.setId(nameSpace + "." + updateElement.attributeValue("id"));
            mappedStatement.setBoundSql(getBoundSql(updateElement.getTextTrim()));
            String parameterType = updateElement.attributeValue("parameterType");
            if (parameterType != null) {
                mappedStatement.setParameterType(Class.forName(parameterType));
            }
            mappedStatementMap.put(mappedStatement.getId(), mappedStatement);
        }
        return mappedStatementMap;
    }


    private Map<String, MappedStatement> parseSelectTag(Configuration configuration, String nameSpace
            , List<Element> selectElements) throws ClassNotFoundException {
        Map<String, MappedStatement> mappedStatementMap = new HashMap<>(selectElements.size());
        for (Element selectElement : selectElements) {
            MappedStatement mappedStatement = new MappedStatement();
            mappedStatement.setConfiguration(configuration);
            mappedStatement.setType(SqlType.SELECT);
            mappedStatement.setId(nameSpace + "." + selectElement.attributeValue("id"));
            mappedStatement.setBoundSql(getBoundSql(selectElement.getTextTrim()));
            String parameterType = selectElement.attributeValue("parameterType");
            if (parameterType != null) {
                mappedStatement.setParameterType(Class.forName(parameterType));
            }
            String returnType = selectElement.attributeValue("resultType");
            if (returnType != null) {
                mappedStatement.setResultType(Class.forName(returnType));
            } else {
                throw new NoDeclaredException("查询语句必须指明返回类型!");
            }
            mappedStatementMap.put(mappedStatement.getId(), mappedStatement);
        }
        return mappedStatementMap;
    }

    private BoundSql getBoundSql(String sql) {
        ParameterMappingTokenHandler parameterMappingTokenHandler = new ParameterMappingTokenHandler();
        GenericTokenParser parser = new GenericTokenParser("#{", "}", parameterMappingTokenHandler);
        BoundSql boundSql = new BoundSql();
        boundSql.setSqlText(parser.parse(sql));
        boundSql.setParameterList(parameterMappingTokenHandler.getParameterList());
        return boundSql;
    }

    private DataSource getDataSource(List<Element> dataSourceElementList) throws PropertyVetoException {
        if (dataSourceElementList == null || dataSourceElementList.isEmpty()) {
            throw new DataSourceNotFindException("数据源配置信息找不到");
        }
        List<DataSource> dataSourceList = new ArrayList<>();
        for (Element dataSourceElement : dataSourceElementList) {
            List<Element> propertyElements = dataSourceElement.elements("property");
            if (propertyElements == null && !propertyElements.isEmpty()) {
                throw new DataSourceNotFindException("数据源配置信息的属性配置找不到");
            }
            dataSourceList.add(buildDataSource(getDataSourceProperties(propertyElements)));
        }

        return dataSourceList.get(0);
    }

    private Properties getDataSourceProperties(List<Element> propertyElements) {
        Properties properties = new Properties();
        for (Element propertyElement : propertyElements) {
            String name = propertyElement.attributeValue("name");
            String value = propertyElement.attributeValue("value");
            properties.put(name, value);
        }

        return properties;
    }

    private ComboPooledDataSource buildDataSource(Properties properties) throws PropertyVetoException {
        String driverClass = properties.getProperty("driverClass");
        String jdbcUrl = properties.getProperty("jdbcUrl");
        String user = properties.getProperty("user");
        String password = properties.getProperty("password");
        if (driverClass == null || jdbcUrl == null || user == null || password == null) {
            throw new DataSourceNotFindException("DataSource配置信息不全!");
        }
        ComboPooledDataSource c3p0DataSource = new ComboPooledDataSource();
        c3p0DataSource.setDriverClass(driverClass);
        c3p0DataSource.setJdbcUrl(jdbcUrl);
        c3p0DataSource.setUser(user);
        c3p0DataSource.setPassword(password);
        return c3p0DataSource;
    }

}
