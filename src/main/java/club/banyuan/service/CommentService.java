package club.banyuan.service;

import club.banyuan.bean.Comment;
import club.banyuan.bean.User;
import club.banyuan.dao.CommentDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {
    private CommentDao commentDao;

    @Autowired
    public CommentService(CommentDao commentDao) {
        this.commentDao = commentDao;
    }


    public List<Comment> getCommentByBlogId(Integer id) {
        return commentDao.selectCommentByBlogId(id);
    }

    public List<User> getUsernameByBlogId(Integer id){
        return commentDao.selectUserByBlogId(id);
    }

    public void addComments(Comment comment){
        commentDao.insertComments(comment);
    }
}
