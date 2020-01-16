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
        PageInfo blog=blogService.pageUserBlogs(username,page,size);
        model.addAttribute("user", user);
        model.addAttribute("blog",blog);

        return "list";

    }
    @GetMapping("/blog/create")
    String createBlogPage(){
        return "create";
    }
    @PostMapping("/blogs")
    String createBlog(@RequestParam String title,@RequestParam String content){
        Blog blog=new Blog();
        blog.setUserId(1);
        blog.setTitle(title);
        blog.setContent(content);
        blogService.addBlog(blog);
        return "redirect:/blogs/"+blog.getId();

    }


}
