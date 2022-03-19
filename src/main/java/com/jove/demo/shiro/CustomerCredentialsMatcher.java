package com.jove.demo.shiro;

import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class CustomerCredentialsMatcher implements CredentialsMatcher {

	PasswordEncoder encoder = new BCryptPasswordEncoder();
	
	@Override
	public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
		//前端传来的密码
        String currentPass = String.copyValueOf((char[]) token.getCredentials());
        //数据库密码
        String dbPass = (String) info.getCredentials();
        return encoder.matches(currentPass,dbPass);
	}

}
