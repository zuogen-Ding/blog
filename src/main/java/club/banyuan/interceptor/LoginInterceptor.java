package club.banyuan.interceptor;

import club.banyuan.bean.User;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginInterceptor extends HandlerInterceptorAdapter {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        User user = (User) request.getSession().getAttribute("CURRENT_USER");
        if (user != null) {
            return true;
        } else {
            String redirectUri = "/login?next=" + request.getRequestURI();
            response.sendRedirect(redirectUri);
            return false;
        }
    }
}
