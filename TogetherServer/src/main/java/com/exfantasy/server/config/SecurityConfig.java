package com.exfantasy.server.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	/**
	 * Reference site:	
	 * 		http://ithelp.ithome.com.tw/question/10160537
	 */
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
			.anyRequest()		// 對象為所有網址  
			.authenticated()	// 存取必須通過驗證  
			.and()
			.formLogin()		// 若未不符合authorize條件，則產生預設login表單  
			.and()
			.httpBasic();		// 產生基本表單  
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication() // 自訂Runtime時的使用者帳號  
            .withUser("admin") 		  // 新增user  
            .password("ilovebmw")     // 指定密碼  
            .roles("ADMIN", "USER")   // 指派權限群組  
            .and() 					  // 再新增使用者  
            .withUser("user")  
            .password("user12345")  
            .roles("USER");  
	}
}
