package com.example.hong.config;

import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
public class ShiroConfig{

    @Bean
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(@Qualifier("securityManager") DefaultWebSecurityManager securityManager)
    {
        ShiroFilterFactoryBean bean = new ShiroFilterFactoryBean();

        bean.setSecurityManager(securityManager);

        Map<String, String> filterMap = new LinkedHashMap<>();

        filterMap.put("/user/*", "authc");
        bean.setFilterChainDefinitionMap(filterMap);

        return bean;
    }

    @Bean(name="securityManager")
    public DefaultWebSecurityManager getDefaultWebSecurityManager(@Qualifier("userRealm") UserRealm userRealm)
    {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        //关闭Realm
        securityManager.setRealm(userRealm);
        return securityManager;
    }
    @Bean
    public UserRealm userRealm(){
        return new UserRealm();
    }
}
