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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
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
    String getItem(@PathVariable Integer id, Model model) {
        User user = userService.getUserByBlogId(id);
        Blog blog = blogService.getBlogById(id);
        List<Comment> comments = commentService.getCommentByBlogId(id);
        model.addAttribute("blog", blog);
        model.addAttribute("user", user);
        model.addAttribute("comments", comments);
        return "item";
    }

    @GetMapping("/blogs/{id}/comments")
    String showComments(@PathVariable Integer id,
                        Model model,
                        HttpSession session
    ) {
        String content = (String) session.getAttribute("CONTENT_VALUE");
        session.removeAttribute("CONTENT_VALUE");
        User user = (User) session.getAttribute("CURRENT_USER");
        Comment comment = new Comment();
        comment.setContent(content);
        comment.setUserId(user.getId());
        comment.setBlogId(id);
        commentService.addComments(comment);
        session.removeAttribute("CONTENT_VALUE");

        return "redirect:/blogs/" + id;
    }

    @PostMapping("/blogs/{blogId}/comments")
    String createComment(@PathVariable Integer blogId,
                         Comment comment,
                         HttpSession session,
                         HttpServletRequest request
    ) {
        comment.setBlogId(blogId);
        User user = (User) session.getAttribute("CURRENT_USER");
        if (!comment.getContent().equals("")) {
            if (user != null) {
                comment.setUserId(user.getId());
                commentService.addComments(comment);
            } else {
                String content = comment.getContent();
                session.setAttribute("CONTENT_VALUE", content);
                return "redirect:/login?next=" + request.getRequestURI();
            }
        }
        return "redirect:/blogs/" + blogId;

    }

}
