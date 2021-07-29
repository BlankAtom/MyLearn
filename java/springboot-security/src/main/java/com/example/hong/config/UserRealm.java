package com.example.hong.config;

import com.example.hong.pojo.User;
import com.example.hong.service.UserService;
import com.example.hong.service.UserServiceImpl;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.List;

public class UserRealm extends AuthorizingRealm {
    @Autowired
    private UserServiceImpl userService;
    //如果呈现找不到UserService的bean，可能是因为Impl实现类没有增加@Service注解

    //授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("执行 ==> 授权");
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.addStringPermission("user");

        Subject subject= SecurityUtils.getSubject();
        User currentUser = (User) subject.getPrincipal();

        info.addStringPermission(currentUser.getAccess());
        return info;
    }
    //认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        System.out.println("执行 ==> 认证");

        UsernamePasswordToken userToken = (UsernamePasswordToken) authenticationToken;

        User user = userService.queryUserByName(userToken.getUsername());

        if(user == null){
            return null;
        }

        return new SimpleAuthenticationInfo(user, user.getPassword(), "");


    }
}
