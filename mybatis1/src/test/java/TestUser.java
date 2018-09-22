import cn.itcast.dao.UserDao;
import cn.itcast.pojo.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;


public class TestUser {
    InputStream is;
    private SqlSessionFactory factory;
    SqlSession session;
    private UserDao userDao;
    @Before
    public void init() throws IOException {
        //1.读取配置文件
        is = Resources.getResourceAsStream("SqlMapConfig.xml");
        //2.创建SqlSessionFactory工厂
        SqlSessionFactoryBuilder builder =new SqlSessionFactoryBuilder();
        factory = builder.build(is);
        //3.使用工厂生产SqlSession对象
        session = factory.openSession();
        //4.使用SqlSession创建Dao接口的代理对象
        userDao = session.getMapper(UserDao.class);
    }

    @After
    public void destory() throws IOException{
        session.commit();
        //6.释放资源
        session.close();
        is.close();
    }

    /**
     * 测试查询所有
     */
    @Test
    public void findAll(){
        //5.使用代理对象执行方法
        List<User> users = userDao.findAll();
        for (User user : users) {
            System.out.println(user);
        }
    }

    /**
     * 测试根据姓名模糊查询
     */
    @Test
    public void findByName(){
        //5.使用代理对象执行方法
        List<User> users = userDao.findByName("王");
        for (User user : users) {
            System.out.println(user);
        }
    }

    /**
     * 测试添加
     */
    @Test
    public void Testinsert(){
        //5.使用代理对象执行方法
        User user =new User();
        user.setUsername("fusihui");
        user.setPassword("123456");
        user.setName("付思慧");
        user.setBirthday("1990-01-23");
        user.setSex("女");
        user.setTelephone("13527378761");
       int n= userDao.insert(user);
        System.out.println(n);
        System.out.println(user);
    }

    /**
     * 测试删除
     */
    @Test
    public void TestDelete(){
        userDao.delete(15);
    }

    /**
     * 测试更改
     */
    @Test
    public void TestUpdate(){
        User user = userDao.findOne(4);
        user.setPassword("123456");
        userDao.update(user);
    }


}
