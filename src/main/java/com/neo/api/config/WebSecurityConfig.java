package com.neo.api.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.core.env.Environment;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configurers.provisioning.InMemoryUserDetailsManagerConfigurer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import com.neo.api.utils.TrippleDes;

@Configuration
@EnableWebSecurity
// @RefreshScope

public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	// @Autowired
	// private AuthenticationEntryPoint authEntryPoint;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable();

		// Tất cả các request gửi tới Web Server yêu cầu phải được xác thực
		// (authenticated).
		// http.authorizeRequests().anyRequest().authenticated();

		// Sử dụng AuthenticationEntryPoint để xác thực user/password
		// http.httpBasic().authenticationEntryPoint(authEntryPoint);

	}

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
		return bCryptPasswordEncoder;
	}

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {

		String password = "123";

		String encrytedPassword = this.passwordEncoder().encode(password);
		System.out.println("Encoded password of 123=" + encrytedPassword);

		InMemoryUserDetailsManagerConfigurer<AuthenticationManagerBuilder> //
		mngConfig = auth.inMemoryAuthentication();

		// Định nghĩa 2 người dùng, lưu trữ trong bộ nhớ.
		// ** Spring BOOT >= 2.x (Spring Security 5.x)
		// Spring auto add ROLE_
		UserDetails u1 = User.withUsername("tom").password(encrytedPassword).roles("USER").build();
		UserDetails u2 = User.withUsername("jerry").password(encrytedPassword).roles("USER").build();

		mngConfig.withUser(u1);
		mngConfig.withUser(u2);

		// If Spring BOOT < 2.x (Spring Security 4.x)):
		// Spring auto add ROLE_
		// mngConfig.withUser("tom").password("123").roles("USER");
		// mngConfig.withUser("jerry").password("123").roles("USER");
	}

}
