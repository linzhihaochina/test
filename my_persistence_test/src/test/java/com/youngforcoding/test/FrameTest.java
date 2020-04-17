package com.youngforcoding.test;

import com.youngforcoding.dao.UserMapper;
import com.youngforcoding.pojo.User;
import com.youngforcoding.session.SqlSession;
import com.youngforcoding.session.SqlSessionFactoryBuilder;
import org.dom4j.DocumentException;
import org.junit.Before;
import org.junit.Test;

import java.beans.IntrospectionException;
import java.beans.PropertyVetoException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.List;


/**
 *    
 *  *  
 *  * @Description:  测试   
 *  * @Author:       linZhiHao   
 *  * @CreateDate:   2020-04-01 5:20 PM   
 *  *    
 *  
 */
public class FrameTest {


    @Test
    public void testSerializable() throws Exception {
        User user = new User();
        user.setId(1L);
        user.setName("lisi");

        ObjectOutputStream obs = new ObjectOutputStream(new FileOutputStream("test.txt"));
        obs.writeObject(user);
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream("test.txt"));
        Object object = ois.readObject();
    }

    @Test
    public void testBuildConfiguration() {
        try {
            SqlSession sqlSession = new SqlSessionFactoryBuilder().build("sqlConfigMapping.xml").openSession();
            List<User> userList = sqlSession.selectList("user.selectList");
            userList.forEach(System.out::println);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private UserMapper userMapper;

    @Before
    public void getMapper() throws DocumentException, PropertyVetoException, ClassNotFoundException {
        SqlSession sqlSession = new SqlSessionFactoryBuilder().build("sqlConfigMapping.xml").openSession(true);
        userMapper = sqlSession.getMapper(UserMapper.class);

    }

    @Test
    public void mapperTestInsert() throws Exception {
        User user = new User();
        user.setId(15L);
        user.setName("linzh3");
        user.setAge(20);
        userMapper.insertUser(user);
    }


    @Test
    public void mapperTestDelete(){
        userMapper.deleteUser(15L);
    }

    @Test
    public void mapperTestUpdate(){
        User user = new User();
        user.setId(14L);
        user.setName("wangwu");
        user.setAge(18);
        userMapper.updateUser(user);
    }
}
