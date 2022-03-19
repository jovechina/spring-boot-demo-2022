package com.jove.demo.shiro;


import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ShiroConfig {
	
    /*
     * 创建Realm——自定义Realm类
     */
    @Bean //添加到spring容器中
    public BasicRealm basicRealm() {
        return new BasicRealm();
    }
    
    /*
     * 创建ShiroFilterFactoryBean
     */
    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(@Qualifier("securityManager")DefaultWebSecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        //设置安全管理器
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        /*
         * 使用SHIRO内置过滤器实现页面拦截：拦截url链接请求
         * 
         * SHIRO内置过滤器，可以实现权限相关的拦截器
         *      常用的过滤器：
         *          anon:无需认证（登录）可以访问
         *          authc:必须认证才可以访问
         *          user:如果使用rememberMe的功能可以直接访问  （记住用户和密码）
         *          perms:该资源必须得到资源权限才可以访问      （密码验证）
         *          role:该资源必须得到角色权限才可以访问       （VIP会员）
         * 
         */
         
        //创建集合——充作拦截器集合
        Map<String,String> filterChainDefinitionMap = new LinkedHashMap<>();   

        //URL放行
        filterChainDefinitionMap.put("/test-db/**", "anon");   
        filterChainDefinitionMap.put("/", "anon");
        filterChainDefinitionMap.put("/login", "anon");
        filterChainDefinitionMap.put("/swagger-ui/**", "anon"); 
        filterChainDefinitionMap.put("/v3/**", "anon"); 
        
        //单个URL拦截，   
        filterChainDefinitionMap.put("/estimation-2", "authc");        
        //批量URL拦截
        filterChainDefinitionMap.put("/admin/**", "authc, roles[admin]");
        filterChainDefinitionMap.put("/**", "authc");
        
        

        /*
        * SHIRO拦截器拦截成功后，会返回一个默认的地址login.jsp
        * 可以自定义修改调整的登录页面
        */
        shiroFilterFactoryBean.setLoginUrl("/");        
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        return shiroFilterFactoryBean;

    }
    
    
    /*
     * 创建DefaultWebSecurityManager——关联realm
     */
    @Bean(name="securityManager")
    public DefaultWebSecurityManager getDefaultWebSecurityManager(@Qualifier("basicRealm")BasicRealm basicRealm) {
        
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        //关联realm
        securityManager.setRealm(basicRealm);
        return securityManager;
    }
    
    //注入权限管理
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(@Qualifier("securityManager") DefaultWebSecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor sourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        sourceAdvisor.setSecurityManager(securityManager);
        return sourceAdvisor;
    }
    
}
