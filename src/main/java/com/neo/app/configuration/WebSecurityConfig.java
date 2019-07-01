package com.neo.app.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.header.writers.StaticHeadersWriter;

import com.neo.app.utils.ConstantParams;

@Configuration
@EnableWebSecurity

public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable();

		// // Các trang không yêu cầu login
		// http.authorizeRequests().antMatchers("/", "/login", "/logout").permitAll();
		//
		// // Trang /userInfo yêu cầu phải login với vai trò ROLE_USER hoặc ROLE_ADMIN.
		// // Nếu chưa login, nó sẽ redirect tới trang /login.
		// http.authorizeRequests().antMatchers("/userInfo").access("hasAnyRole('ROLE_USER',
		// 'ROLE_ADMIN')");
		//
		// // Trang chỉ dành cho ADMIN
		// http.authorizeRequests().antMatchers("/admin").access("hasRole('ROLE_ADMIN')");
		//
		// // Khi người dùng đã login, với vai trò XX.
		// // Nhưng truy cập vào trang yêu cầu vai trò YY,
		// // Ngoại lệ AccessDeniedException sẽ ném ra.
		// http.authorizeRequests().and().exceptionHandling().accessDeniedPage("/403");
		//
		// // Cấu hình cho Login Form.
		// http.authorizeRequests().and().formLogin()//
		// // Submit URL của trang login
		// .loginProcessingUrl("/j_spring_security_check") // Submit URL
		// .loginPage("/login")//
		// .defaultSuccessUrl("/userAccountInfo")//
		// .failureUrl("/login?error=true")//
		// .usernameParameter("username")//
		// .passwordParameter("password")
		// // Cấu hình cho Logout Page.
		// .and().logout().logoutUrl("/logout").logoutSuccessUrl("/logoutSuccessful");

		// Cấu hình Remember Me.// 24h
		http.rememberMe().key("uniqueAndSecret").tokenValiditySeconds(1 * 24 * 60 * 60);

		// Chỉ cho phép user đã đăng nhập mới được truy cập đường dẫn /admin/**
		http.authorizeRequests().antMatchers(ConstantParams.ADMIN_PATH + "/**").authenticated();

		// Sử dụng AuthenticationEntryPoint để xác thực user/password
		// http.httpBasic().authenticationEntryPoint(authEntryPoint);
		http.headers().frameOptions().sameOrigin()
				.addHeaderWriter(
						new StaticHeadersWriter("Content-Security-Policy", "script-src 'self' 'unsafe-inline'"))
				.addHeaderWriter(new StaticHeadersWriter("X-Permitted-Cross-Domain-Policies", "none"))
				.addHeaderWriter(new StaticHeadersWriter("Feature-Policy", "geolocation 'none'"))
				.addHeaderWriter(new StaticHeadersWriter("Referrer-Policy", "no-referrer"));
	}

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
		return bCryptPasswordEncoder;
	}

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {

		// Sét đặt dịch vụ để tìm kiếm User trong Database.
		// Và sét đặt PasswordEncoder.
		// auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
	}

}
