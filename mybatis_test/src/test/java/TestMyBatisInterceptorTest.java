import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.youngforcoding.pojo.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

/**
 *    
 *  *  
 *  * @Description:  [一句话描述该类的功能]   
 *  * @Author:       linZhiHao   
 *  * @CreateDate:   2020-04-09 12:51 AM   
 *  *    
 *  
 */
public class TestMyBatisInterceptorTest {

    private SqlSession sqlSession;

    @Before
    public void getMapper() throws IOException {
        sqlSession = new SqlSessionFactoryBuilder().build(Resources.getResourceAsStream("sqlMapConfig.xml")).openSession();
    }

    @Test
    public void test01() {
        PageHelper.startPage(1, 2);
        List<User> userList = sqlSession.selectList("com.youngforcoding.dao.UserMapper.selectList");
        userList.forEach(System.out::println);

        sqlSession.clearCache();
    }

    @Test
    public void test02(){
        PageInfo<User> pageInfo = new PageInfo<>(sqlSession.selectList("com.youngforcoding.dao.UserMapper.selectList"), 2);
        pageInfo.getList().forEach(System.out::println);

    }
}
