package club.banyuan.controller;

import club.banyuan.bean.User;
import club.banyuan.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

@Controller
@RequestMapping("/login")
public class LoginController {
    @Autowired
    private UserService userService;

    @GetMapping
    String showLoginHtml(HttpSession session,
                         @RequestParam(required = false) String next) {

        if (next != null) session.setAttribute("NEXT_URI", next);
        return "login";
    }

    @PostMapping
    String login(@RequestParam String username,
                 @RequestParam String password,
                 HttpSession session) throws UnsupportedEncodingException {
        User user = null;
        String regex = "^\\s*\\w+(?:\\.{0,1}[\\w-]+)*@[a-zA-Z0-9]+(?:[-.][a-zA-Z0-9]+)*\\.[a-zA-Z]+\\s*$";
        if (username.matches(regex)) {
            user = userService.getuserByEmail(username);
        } else {
            user = userService.getUserByName(username);
        }
        if (user != null && password.equals(user.getPassword())) {
            session.setAttribute("CURRENT_USER", user);
            String name = user.getName();
            String uri = (String) session.getAttribute("NEXT_URI");
            if (uri != null) {
                session.removeAttribute("NEXT_URI");
                return "redirect:" + uri;
            }

            return "redirect:/admin" ;

        }
        return "redirect:/login";
    }
}
