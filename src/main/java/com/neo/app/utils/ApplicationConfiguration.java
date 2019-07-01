package com.neo.app.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * Handles properties of application.properties file
 *
 * Created by dinukshakandasamanage on 9/5/18.
 */
@Component
@PropertySource("classpath:application.properties")
public class ApplicationConfiguration {

	@Value("${app.username}")
	private String username;

	@Value("${app.password}")
	private String password;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
