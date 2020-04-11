import com.youngforcoding.dao.UserMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;


/**
 *    
 *  *  
 *  * @Description:  [一句话描述该类的功能]   
 *  * @Author:       linZhiHao   
 *  * @CreateDate:   2020-04-08 10:23 PM   
 *  *    
 *  
 */
public class TestMyBatisCache {


    @Test
    public void test01() throws Exception {
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(Resources.getResourceAsStream("sqlMapConfig.xml"));
        SqlSession sqlSession01 = sqlSessionFactory.openSession();
        SqlSession sqlSession02 = sqlSessionFactory.openSession();
        SqlSession sqlSession03 = sqlSessionFactory.openSession();

        UserMapper userMapper01 = sqlSession01.getMapper(UserMapper.class);
        UserMapper userMapper02 = sqlSession02.getMapper(UserMapper.class);
        UserMapper userMapper03 = sqlSession03.getMapper(UserMapper.class);

        userMapper01.selectUserAndOrderList(1L);
        //userMapper02.selectUserAndOrderList(1L);
        //userMapper03.selectUserAndOrderList(1L);

        sqlSession01.close();
//        sqlSession01.commit();
        //sqlSession01.close();
        userMapper02.selectUserAndOrderList(1L);
//        userMapper02.selectUserAndOrderList(1L);
        //userMapper03.selectUserAndOrderList(1L);


        SqlSessionFactory sqlSessionFactory2 = new SqlSessionFactoryBuilder().build(Resources.getResourceAsStream("sqlMapConfig.xml"));
        SqlSession sqlSession04 = sqlSessionFactory2.openSession();
        UserMapper userMapper04 = sqlSession04.getMapper(UserMapper.class);
        userMapper04.selectUserAndOrderList(1L);
    }


    @Test
    public void test03() {

    }
}
