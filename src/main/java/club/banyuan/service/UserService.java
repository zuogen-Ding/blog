package club.banyuan.service;

import club.banyuan.bean.User;
import club.banyuan.dao.UserDao;
import org.jetbrains.annotations.Contract;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private UserDao userDao;
    @Autowired
    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }

    public User getUserByName(String name){
        return userDao.selectUserByName(name);
    }

    public User getUserByBlogId(Integer id){
        return userDao.selectUserByBlogId(id);
    }

    public User getuserByEmail(String email){return userDao.selectUserByEmail(email);}
}
