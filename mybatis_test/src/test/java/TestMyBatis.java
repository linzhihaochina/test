import com.youngforcoding.dao.AnnotationOrderMapper;
import com.youngforcoding.dao.AnnotationUserMapper;
import com.youngforcoding.dao.OrderMapper;
import com.youngforcoding.dao.UserMapper;
import com.youngforcoding.pojo.Order;
import com.youngforcoding.pojo.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;

/**
 *    
 *  *  
 *  * @Description:  [一句话描述该类的功能]   
 *  * @Author:       linZhiHao   
 *  * @CreateDate:   2020-04-07 1:23 PM   
 *  *    
 *  
 */
public class TestMyBatis {

    UserMapper userMapper;

    OrderMapper orderMapper;

    AnnotationUserMapper annotationUserMapper;

    AnnotationOrderMapper annotationOrderMapper;

    @Before
    public void getMapper() throws IOException {
        InputStream is = Resources.getResourceAsStream("sqlMapConfig.xml");
        //SqlSession sqlSession = new SqlSessionFactoryBuilder().build(is).openSession();
        SqlSession sqlSession = new SqlSessionFactoryBuilder().build(is).openSession(true);
        //  前提是xml中的mapper标签的namespace写上接口的全限定名
        this.userMapper = sqlSession.getMapper(UserMapper.class);
        this.orderMapper = sqlSession.getMapper(OrderMapper.class);
        this.annotationUserMapper = sqlSession.getMapper(AnnotationUserMapper.class);
        this.annotationOrderMapper = sqlSession.getMapper(AnnotationOrderMapper.class);
    }

//    @Test
//    public void test01() throws Exception {
//        InputStream is = Resources.getResourceAsStream("sqlMapConfig.xml");
//        SqlSession sqlSession = new SqlSessionFactoryBuilder().build(is).openSession();
//        User user = new User();
//        user.setId(12L);
//        user.setName("lisi");
//        user.setAge(11);
//        int count = sqlSession.insert("userMapper.insert",user);
//        //
//        System.out.println(count);
//    }

    @Test
    public void test02() throws Exception {
        InputStream is = Resources.getResourceAsStream("sqlMapConfig.xml");
        SqlSession sqlSession = new SqlSessionFactoryBuilder().build(is).openSession();
        //  前提是xml中的mapper标签的namespace写上接口的全限定名
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        User user = new User();
        user.setId(12L);
        user.setName("lisi");
        user.setAge(11);
        //int count = sqlSession.insert("userMapper.insert",user);
        int count = userMapper.insert(user);
        sqlSession.commit();
        System.out.println(count);

    }

    @Test
    public void test03() {
        for (User user : userMapper.selectList()) {
            System.out.println(user);
        }
    }

    @Test
    public void test04() {
        User user = new User();
        user.setId(2L);
        user.setName("a");
        User user2 = userMapper.findByCondition(user);
        System.out.println(user2);
        //user2 = userMapper.findByCondition(user);
        User user3 = userMapper.findByCondition2(2L, "a");
        System.out.println(user3);
    }

    @Test
    public void test05() {
        //List<User> userList = userMapper.selectByIDList(Arrays.asList(1L, 2L, 3L, 4L, 5L));
        List<User> userList = userMapper.selectByIDList(Arrays.asList());
        userList.forEach(System.out::println);
    }

    /**
     * 一对一查询
     */
    @Test
    public void test06() {
        List<Order> orders = orderMapper.selectOrderList();
        orders.forEach(System.out::println);
    }

    /**
     * 一对多查询
     */
    @Test
    public void test07() {
        User user = userMapper.selectUserAndOrderList(2L);
        System.out.println(user);
    }

    @Test
    public void test08() {
        List<User> userList = userMapper.selectUserAndRoles();
        userList.forEach(System.out::println);
    }


    //  测试注解新增

    @Test
    public void test09() {
        User user = new User();
        user.setId(13L);
        user.setName("zhangsan");
        user.setAge(15);
        Integer count = annotationUserMapper.insert(user);

        System.out.println(count);
    }

    @Test
    public void test10() {
        User user = new User();
        user.setId(13L);
        user.setName("zhangsan02");
        user.setAge(16);
        //Integer count = annotationUserMapper.update(user);
        //System.out.println(count);
        //Integer count = annotationUserMapper.update02(user, 12L);
        //System.out.println(count);
    }

    @Test
    public void test11() {
        Integer count = annotationUserMapper.delete(13L);
        System.out.println(count);
    }

    @Test
    public void test12() {
        User user = annotationUserMapper.selectByID(3L);
        System.out.println(user);
    }


    //  one To one
    @Test
    public void test13() {
        List<Order> orders = annotationOrderMapper.selectOrderListContainUser();
        orders.forEach(System.out::println);
    }

    //  one To many
    @Test
    public void test14() {
        List<User> userList = annotationUserMapper.selectUserListAndOrderList();
        for (User user : userList) {
            System.out.println(user);
        }
    }

    // many to many
    @Test
    public void test15() {
        List<User> userList = annotationUserMapper.selectUserListAndRoleListByID();
        userList = annotationUserMapper.selectUserListAndRoleListByID();
        userList.forEach(System.out::println);
    }

    @Test
    public void lazyLoad() {
        User user = userMapper.selectUserAndOrderList2(2L);
        System.out.println(user);
        List<Order> orderList = user.getOrderList();
        System.out.println(user);
    }

}
