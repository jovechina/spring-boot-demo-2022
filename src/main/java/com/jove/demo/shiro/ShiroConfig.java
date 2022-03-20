package com.jove.demo.shiro;


import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.cache.MemoryConstrainedCacheManager;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ShiroConfig {
	
	/**
	 * 创建Realm
	 * @return Realm instance
	 */
    @Bean
    public BasicRealm basicRealm() {
        return new BasicRealm();
    }
    

    /**
     * 创建ShiroFilterFactoryBean
     * @param securityManager
     * @return shiroFilterFactoryBean
     */
    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(@Qualifier("securityManager")DefaultWebSecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        //设置安全管理器
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        /*
         * 使用SHIRO内置过滤器实现页面拦截：拦截URL链接请求
         * 
         * SHIRO内置过滤器，可以实现权限相关的拦截器
         *      常用的过滤器：
         *          anon:无需认证（登录）可以访问
         *          authc:必须认证才可以访问
         *          role:该资源必须得到角色权限才可以访问 
         * 配置的过滤器从上向下顺序执行，命中前面的后面的不会执行，所以一般将 /**放在最为下边      
         * 
         */
         
        //拦截器集合
        Map<String,String> filterChainDefinitionMap = new LinkedHashMap<>();   

        //登出/退出
        filterChainDefinitionMap.put("/logout", "logout");
        //无需权限
        filterChainDefinitionMap.put("/static/**", "anon");
        filterChainDefinitionMap.put("/", "anon");
        filterChainDefinitionMap.put("/login", "anon");
        filterChainDefinitionMap.put("/test-db/**", "anon");   
        //开发环境 - 查看接口信息 无需权限
        filterChainDefinitionMap.put("/swagger-ui/**", "anon"); 
        filterChainDefinitionMap.put("/v3/**", "anon"); 
        
        //单个URL拦截，   
        filterChainDefinitionMap.put("/estimation-2", "authc");        
        //批量URL拦截
        filterChainDefinitionMap.put("/admin/**", "authc, roles[admin]");
        filterChainDefinitionMap.put("/**", "authc");

        //可以自定义修改调整的登录页面
        shiroFilterFactoryBean.setLoginUrl("/");        
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        return shiroFilterFactoryBean;

    }
    
    
    /**
     * 创建DefaultWebSecurityManager
     * @param basicRealm
     * @return securityManager
     */
    @Bean(name="securityManager")
    public DefaultWebSecurityManager getDefaultWebSecurityManager(@Qualifier("basicRealm")BasicRealm basicRealm) {
        
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        //配置realm
        securityManager.setRealm(basicRealm);
        //配置cache
        //直接常用的ehCache管理器
//        securityManager.setCacheManager(ehCacheManager());
		//直接使用Shiro的cache管理器        
        securityManager.setCacheManager(cacheManager());        
        return securityManager;
    }
    
    /**
     * 权限管理 
     * @param securityManager
     * @return authorizationAttributeSourceAdvisor
     */
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(@Qualifier("securityManager") DefaultWebSecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor sourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        sourceAdvisor.setSecurityManager(securityManager);
        return sourceAdvisor;
    }
    

//   /**
//    * @return 缓存管理器
//    * ehCache
//    */
//    @Bean
//    public EhCacheManager ehCacheManager() {
//        EhCacheManager cacheManager = new EhCacheManager();
//        cacheManager.setCacheManagerConfigFile("classpath:config/ehcache.xml");
//        return cacheManager;
//    }
    

    /**
     * Shiro MemoryConstrainedCacheManager
     * @return 缓存管理器
     */
     @Bean
     public CacheManager cacheManager(){
         return new MemoryConstrainedCacheManager();
     }

       
}
