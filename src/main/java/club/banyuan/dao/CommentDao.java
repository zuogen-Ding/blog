package club.banyuan.dao;

import club.banyuan.bean.Comment;
import club.banyuan.bean.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentDao {
    List<Comment> selectCommentByBlogId(Integer id);

    List<User> selectUserByBlogId(Integer id);

    void insertComments(Comment comment);
}
