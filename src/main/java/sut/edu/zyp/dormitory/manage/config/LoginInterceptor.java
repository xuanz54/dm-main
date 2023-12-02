package sut.edu.zyp.dormitory.manage.config;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/***
 * 这是一个Spring MVC的配置类。实现了WebMvcConfigurer接口。 其中配置了LoginInterceptor拦截器的路径映射,添加了该拦截器到拦截器注册表,并配置了拦截器的路径映射规则。 该类的作用是进行拦截器的相关配置,将登录拦截器LoginInterceptor加入到Spring MVC的拦截器链中。
 */

public class LoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        try {
            //通过session获取用户的已登录信息
            Object user = request.getSession().getAttribute("user");
            Object type = request.getSession().getAttribute("userType");
            //已登录信息不为空，说明已登录，不需要拦截
            if (user != null && type != null) {
                return true;
            }
            //需要拦截，并配置跳转登录
            response.sendRedirect(request.getContextPath() + "/login.html");
        } catch (IOException e) {
        }
        return false;
    }
}
