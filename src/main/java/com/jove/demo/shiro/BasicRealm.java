package com.jove.demo.shiro;


import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.jove.demo.model.User;
import com.jove.demo.service.UserService;

public class BasicRealm extends AuthorizingRealm {

	private static final Logger logger = LoggerFactory.getLogger(BasicRealm.class);

	
	/**
	 * 授权管理，查看用户的角色是否满足方位的URL要求
	 * 
	 * 注意：每次访问需要判断角色的URL都会被调用
	 * TODO1：使用Cache的方式来避免重复调用
	 * TODO2: 授权不通过的异常处理
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		logger.debug("授权/权限赋予开始...");
		String userName = (String) principals.getPrimaryPrincipal();
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
		// 取得当前用户的角色，判断是否有权限
		User user = userService.getUser(userName);
		Set<String> shiroRoles = new HashSet<>();
		String[] userRoles = user.getRole().split(",");
		for( String userRole : userRoles) {
			shiroRoles.add(userRole);
		}
		info.setRoles(shiroRoles);
		return info;
	}

	@Autowired
	UserService userService;

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		logger.debug("认证/登录认证开始...");
		UsernamePasswordToken userInfo = (UsernamePasswordToken) token;
		User user = userService.getUser(userInfo.getUsername());
		if (Objects.isNull(user)) {
			// 服务端验证，确认用户是否存在。
			return null;
		}

		/**
		 * 第一个参数 getPrincipal()获得，自己按需要传 第二个参数 是用来和authenticationToken里的密码比对
		 */
		return new SimpleAuthenticationInfo(token.getPrincipal(), user.getPassword(), this.getName());

	}

	@Override
	public void setCredentialsMatcher(CredentialsMatcher credentialsMatcher) {
		CustomerCredentialsMatcher customerCredentialsMatcher = new CustomerCredentialsMatcher();
		super.setCredentialsMatcher(customerCredentialsMatcher);
	}

}
