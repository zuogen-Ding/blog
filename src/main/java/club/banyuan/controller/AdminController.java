package club.banyuan.controller;

import club.banyuan.bean.Blog;
import club.banyuan.bean.User;
import club.banyuan.service.BlogService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private BlogService blogService;

    @GetMapping
    public String showAdminHtml(HttpSession session,
                                Model model,
                                HttpServletRequest request,
                                @RequestParam(required = false, defaultValue = "1") Integer page,
                                @RequestParam(required = false, defaultValue = "5") Integer size
    ) {

        User user = (User) session.getAttribute("CURRENT_USER");
        if (user != null) {
            String name = user.getName();
            PageInfo blog = blogService.pageUserBlogs(name, page, size);
            model.addAttribute("blog", blog);
            model.addAttribute("user",user);
            return "admin";
        } else {
            return "redirect:/login?next" + request.getRequestURI();
        }
    }
}
