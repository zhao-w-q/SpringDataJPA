import com.dfbz.dao.UserDao;
import com.dfbz.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.persistence.criteria.*;
import java.util.List;

/**
 * @author Zhao
 * @description
 * @date 2019/12/11
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring.xml")
public class TestJPASpecification {
    @Autowired
    private UserDao userDao;

    @Test
    public void test1() {
        // 创建Specification子类对象
        Specification<User> spec = new Specification<User>() {

            /**
             *
             * @param root:用于获取元素属性
             * @param criteriaQuery:自定义查询对象
             * @param criteriaBuilder:构建查询对象
             * @return
            方法名称 Sql对应关系
            equle filed = value
            gt（greaterThan ） filed > value
            lt（lessThan ） filed < value
            ge（greaterThanOrEqualTo ） filed >= value
            le（ lessThanOrEqualTo） filed <= value
            notEqule filed != value
            like filed like value
            notLike filed not like value
            bulider构建对象对应的方法解释：
             */
            @Override
            public Predicate toPredicate(Root<User> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                //获取username属性
                Path<Object> username = root.get("username");
                Path<Object> birthday = root.get("birthday");
                //构建查询对象最终封装成一个Predicate对象返回
                Predicate predicate = criteriaBuilder.like(username.as(String.class), "%小%");
                Predicate predicate1 = criteriaBuilder.greaterThan(birthday.as(String.class), "2010-08-08");
                return criteriaBuilder.and(predicate,predicate1);
            }
        };

        //根据条件查询
        List<User> userList = userDao.findAll(spec);
        for (User user : userList) {
            System.out.println(user);
        }

    }
}
