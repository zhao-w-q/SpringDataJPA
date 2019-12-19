import com.dfbz.dao.UserDao;
import com.dfbz.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Zhao
 * @description
 * @date 2019/12/11
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring.xml")
public class TestJPA {
    @Autowired
    private UserDao userDao;

    @Test
    public void test1() {
        User user = new User();
        user.setUsername("root");
        userDao.save(user);
    }

    @Test
    public void test2() {
        User user = new User();
        user.setId(2);
        user.setUsername("admin");
        userDao.save(user);
    }

    @Test
    public void test3() {
        userDao.delete(2);
    }

    @Test
    public void test4() {
        User user = userDao.findOne(4);
        System.out.println(user);
    }

    @Test
    public void test5() {
        List<User> list = userDao.findAll();
        for (User user : list) {
            System.out.println(user);
        }
    }

    @Test
    public void test6() {
        PageRequest page = new PageRequest(1, 2);
        Page<User> userPage = userDao.findAll(page);
        System.out.println("总页数：" + userPage.getTotalPages());
        System.out.println("总记录数：" + userPage.getTotalElements());
        for (User user : userPage) {
            System.out.println(user);
        }
    }

    @Test
    public void test7() {
        Sort sort = new Sort(Sort.Direction.DESC, "birthday");
        List<User> list = userDao.findAll(sort);
        for (User user : list) {
            System.out.println(user);
        }
    }

    @Test
    public void test8() {
        List<User> list = userDao.findAll();
        for (User user : list) {
            System.out.println(user);
        }
    }

    @Test
    public void test9() {
        User user = userDao.findById(4);
        System.out.println(user);
    }

    @Test
    @Transactional
    @Rollback(false)
    public void test10() {
        userDao.update("username", 4);
    }

    @Test
    public void test11() {
        User root = userDao.findByUsername("root");
        System.out.println(root);
    }

    @Test
    public void test12() {
        List<User> list = userDao.findByUsernameLike("%小%");
        for (User user : list) {
            System.out.println(user);
        }
    }

    @Test
    public void test13() {
        List<User> list = userDao.findByUsernameLikeOrderByBirthdayDesc("%小%");
        for (User user : list) {
            System.out.println(user);
        }
    }
}
