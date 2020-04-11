package com.youngforcoding.test;

import com.youngforcoding.builder.parse.GenericTokenParser;
import com.youngforcoding.builder.parse.TokenHandler;
import com.youngforcoding.builder.parse.imple.ParameterMappingTokenHandler;
import org.junit.Test;

/**
 *    
 *  *  
 *  * @Description:  [一句话描述该类的功能]   
 *  * @Author:       linZhiHao   
 *  * @CreateDate:   2020-04-10 2:02 PM   
 *  *    
 *  
 */
public class ParserTest {

    @Test
    public void test01() {
        GenericTokenParser parser = new GenericTokenParser("#{", "}", new ParameterMappingTokenHandler());

        System.out.println(parser.parse("select * from user_info where id = #{id} and name = #{name}"));
    }
}
