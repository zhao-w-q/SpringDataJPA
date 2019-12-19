package com.dfbz.dao;

import com.dfbz.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * JpaRepository<实体类,id的类型>:普通增删改查
 * JpaSpecificationExecutor<实体类>:用于复杂多条件查询
 */

public interface UserDao extends JpaRepository<User,Integer>, JpaSpecificationExecutor<User> {

    @Query("from User")
    List<User> findAll();

    @Query("from User where id=?1")
    User findById(Integer id);

    @Query("update User set username=?1 where id=?2")
    @Modifying
    void update(String username,Integer id);

    /**
     * nativeQuery:是否使用本地sql
     * true:开启本地sql查询
     * false:不开启(默认值)
     * @param id id
     * @return user
     */
    @Query(value = "select * from user where id=?1",nativeQuery = true)
    User findById_SQL(Integer id);

    @Query(value = "update user set username=?1 where id=?2",nativeQuery = true)
    @Modifying
    void update_SQL(String username,int id);

    @Query(value = "delete from user where id=?1",nativeQuery = true)
    @Modifying
    void delete_SQL(Integer id);

    //通过名字精确查询
    User findByUsername(String username);

    //通过名字模糊查询
    List<User> findByUsernameLike(String username);

    //通过名字模糊查询最后按生日降序
    List<User> findByUsernameLikeOrderByBirthdayDesc(String username);
}
