import com.dfbz.entity.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.*;
import java.util.List;

/**
 * @author Zhao
 * @description
 * @date 2019/12/11
 */
public class Test1 {
    private EntityManagerFactory factory;
    private EntityManager entityManager;
    private EntityTransaction transaction;

    @Before
    public void before() {
        //指定实体化单元名称获取实体管理对象工厂
        factory = Persistence.createEntityManagerFactory("mysql");
        //获取实体管理对象
        entityManager = factory.createEntityManager();
        //获取事务操作对象
        transaction = entityManager.getTransaction();
        //开启事务
        transaction.begin();
    }

    @After
    public void after() {
        //提交事务
        transaction.commit();
        //释放资源
        entityManager.close();
        factory.close();
    }

    @Test
    public void test1() {
        User user = new User();
        user.setUsername("小东");
        user.setBirthday("2010-08-08");
        user.setSex("男");
        user.setAddress("广州");

        //保存用户
        entityManager.merge(user);
    }

    @Test
    public void test2() {
        User user = entityManager.find(User.class, 1);
        user.setAddress("深圳");
        entityManager.merge(user);
    }

    @Test
    public void test3() {
        entityManager.remove(entityManager.find(User.class, 1));
    }

    @Test
    public void test4() {
        //User:实体类名称,而不是表名称
        //String jpql = "select u from User u";  标准写法
        String jpql = "from User";
        Query query = entityManager.createQuery(jpql);
        List list = query.getResultList();
        for (Object o : list) {
            System.out.println(o);
        }
    }

    @Test
    public void test5() {
        //User:实体类名称,而不是表名称
        //String jpql = "select u from User u";  标准写法
        String jpql = "from User";
        Query query = entityManager.createQuery(jpql);
        query.setFirstResult(0);
        query.setMaxResults(2);
        List list = query.getResultList();
        for (Object o : list) {
            System.out.println(o);
        }
    }

    @Test
    public void test6() {
        String jpql = "from User where username like ?";
        Query query = entityManager.createQuery(jpql);
        query.setParameter(1, "%东%");
        List list = query.getResultList();
        for (Object o : list) {
            System.out.println(o);
        }
    }

    @Test
    public void test7() {
        String jpql = "from User order by birthday";
        Query query = entityManager.createQuery(jpql);
        List list = query.getResultList();
        for (Object o : list) {
            System.out.println(o);
        }
    }

    @Test
    public void test8() {
        String jpql = "select count(*) from User";
        Query query = entityManager.createQuery(jpql);
        Object result = query.getSingleResult();
        System.out.println(result);
    }
}
