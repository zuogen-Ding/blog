package club.banyuan.interceptor;

import club.banyuan.bean.User;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URLDecoder;
import java.net.URLEncoder;

public class AdminInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        User user=(User) request.getSession().getAttribute("CURRENT_USER");
        String uri=request.getRequestURI();
        String uriName=uri.split("/")[2];
        String username= URLDecoder.decode(uriName,"utf-8");
        if(user.getName().equals(username)){
            return true;
        }else {
            response.sendRedirect("/admin/"+ URLEncoder.encode(username,"utf-8"));
            return false;
        }

    }
}
