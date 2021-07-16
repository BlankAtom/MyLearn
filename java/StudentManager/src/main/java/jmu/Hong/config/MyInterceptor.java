package jmu.Hong.config;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class MyInterceptor implements HandlerInterceptor
{
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session =request.getSession();
        // 设置允许多个域名请求
        String[] allowDomains = {"http://localhost:8080","http://localhost:8334"};
        response.setHeader("Access-Control-Allow-Origin", "http://localhost:8334");
        // 设置服务器允许浏览器发送请求都携带cookie
        response.setHeader("Access-Control-Allow-Credentials","true");
        // 允许的访问方法
        response.setHeader("Access-Control-Allow-Methods","POST, GET, PUT, OPTIONS, DELETE, PATCH");
        // Access-Control-Max-Age 用于 CORS 相关配置的缓存
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Headers","token,Origin, X-Requested-With, Content-Type, Accept,mid,X-Token");
        response.setCharacterEncoding("UTF-8");

        //登录页面放行
        if(request.getRequestURI().contains("user"))
            return true;
        if(request.getRequestURI().contains("look"))
            return true;
        if(request.getRequestURI().contains("login"))
            return true;
        if(request.getRequestURI().contains("check"))
            return true;
        //已登录放行
        if( session.getAttribute("username")!=null){
            return true;
        }
        if(request.getRequestURI().contains("/api"))
            return true;

        request.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(request, response);
        return false;
    }
}
