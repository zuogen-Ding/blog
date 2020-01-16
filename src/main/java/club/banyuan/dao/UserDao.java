package club.banyuan.dao;

import club.banyuan.bean.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao {
    //通过username查询
    User selectUserByName(String name);
    User selectUserByBlogId(Integer id);


}
