package club.banyuan.controller;

import club.banyuan.bean.Blog;
import club.banyuan.bean.Comment;
import club.banyuan.bean.User;
import club.banyuan.service.BlogService;
import club.banyuan.service.CommentService;
import club.banyuan.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class ItemController {
    @Autowired
    private BlogService blogService;
    @Autowired
    private UserService userService;
    @Autowired
    private CommentService commentService;

    @GetMapping("/blogs/{id}")
    String getItem(@PathVariable Integer id, Model model){
        User user=userService.getUserByBlogId(id);
       List<User> users=commentService.getUsernameByBlogId(id);
        Blog blog=blogService.getBlogById(id);
        List<Comment> comments=commentService.getCommentByBlogId(id);
        model.addAttribute("blog",blog);
        model.addAttribute("user",user);
        model.addAttribute("users",users);
        model.addAttribute("comments",comments);
        return "item";
    }


    @PostMapping("/blogs/{blogId}/comments")
    String  createComment(@PathVariable Integer blogId,
            Comment comment
    ){comment.setBlogId(blogId);
    Integer userId =2;
    comment.setUserId(userId);
    commentService.addComments(comment);
    return "redirect:/blogs/"+blogId;

    }

}
