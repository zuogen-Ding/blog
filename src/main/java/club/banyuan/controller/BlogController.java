package club.banyuan.controller;


import club.banyuan.bean.Blog;
import club.banyuan.bean.User;
import club.banyuan.service.BlogService;
import club.banyuan.service.UserService;
import com.github.pagehelper.PageInfo;
import com.google.gson.internal.$Gson$Preconditions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


@Controller
public class BlogController {
    @Autowired
    private UserService userService;
    @Autowired
    private BlogService blogService;

    @GetMapping("/user/{username}")
    String getUserBlog(@PathVariable String username,
                       @RequestParam(required = false, defaultValue = "1") Integer page,
                       @RequestParam(required = false, defaultValue = "2") Integer size,
                       Model model
    ) {
        User user = userService.getUserByName(username);
        PageInfo blog = blogService.pageUserBlogs(username, page, size);
        model.addAttribute("user", user);
        model.addAttribute("blog", blog);

        return "list";

    }

    @GetMapping("/blog/create")
    String createBlogPage(HttpSession session,
                          Model model
    ) {
        User user = (User) session.getAttribute("CURRENT_USER");
        model.addAttribute("user",user);
        return "create";
    }

    @PostMapping("/blogs")
    String createBlog(@RequestParam String title,
                      @RequestParam String content,
                      HttpSession session) {
        Blog blog = new Blog();
        User user = (User) session.getAttribute("CURRENT_USER");
        blog.setUserId(user.getId());
        blog.setTitle(title);
        blog.setContent(content);
        blogService.addBlog(blog);
        return "redirect:/blogs/" + blog.getId();

    }


}
