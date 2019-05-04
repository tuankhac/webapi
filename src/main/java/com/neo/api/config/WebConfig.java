package com.neo.api.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.handler.SimpleUrlHandlerMapping;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;
import org.springframework.web.servlet.resource.ResourceHttpRequestHandler;

import com.neo.api.interceptor.RequestInterceptor;
import com.neo.api.utils.TrippleDes;

import java.util.Arrays;
import java.util.Collections;
import java.util.Locale;

import javax.sql.DataSource;

@Configuration
@EnableWebMvc
// @ComponentScan({"com.neo.api.controllers", "com.neo.api.utils"})
// @EnableAutoConfiguration
@PropertySources({ @PropertySource("classpath:application.properties") })
public class WebConfig implements WebMvcConfigurer {

	@Autowired
	RequestInterceptor requestInterceptor;

	@Autowired
	private Environment env;

	@Bean
	public DataSource dataSource() {
		String username = env.getProperty("spring.datasource.username");
		String pass = env.getProperty("spring.datasource.password");
		String decryptU = TrippleDes.decrypt(username);
		String decryptP = TrippleDes.decrypt(pass);
		return DataSourceBuilder.create().username(decryptU).password(decryptP)
				.url(env.getProperty("spring.datasource.url"))
				.driverClassName(env.getProperty("spring.datasource.driver-class-name")).build();
	}

	@Bean
	public PersistentTokenRepository persistentTokenRepository() {
		JdbcTokenRepositoryImpl db = new JdbcTokenRepositoryImpl();
		db.setDataSource(dataSource());
		return db;
	}

	@Bean
	public LocaleChangeInterceptor localeChangeInterceptor() {
		LocaleChangeInterceptor localeChangeInterceptor = new LocaleChangeInterceptor();
		localeChangeInterceptor.setParamName("lang");
		return localeChangeInterceptor;
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(requestInterceptor);
		registry.addInterceptor(localeChangeInterceptor());
	}

	// handle resource not found
	@Bean
	public DispatcherServlet dispatcherServlet() {
		DispatcherServlet ds = new DispatcherServlet();
		ds.setThrowExceptionIfNoHandlerFound(true);
		return ds;
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/**").addResourceLocations("classpath:/static/");
	}

	@Bean
	public SimpleUrlHandlerMapping faviconHandlerMapping() {
		SimpleUrlHandlerMapping mapping = new SimpleUrlHandlerMapping();
		mapping.setOrder(Integer.MIN_VALUE);
		mapping.setUrlMap(Collections.singletonMap("img/favicon.ico", faviconRequestHandler()));
		return mapping;
	}

	@Bean
	protected ResourceHttpRequestHandler faviconRequestHandler() {
		ResourceHttpRequestHandler requestHandler = new ResourceHttpRequestHandler();
		requestHandler.setLocations(Arrays.<Resource>asList(new ClassPathResource("/")));
		return requestHandler;
	}

	@Bean
	public LocaleResolver localeResolver() {
		SessionLocaleResolver localeResolver = new SessionLocaleResolver();
		localeResolver.setDefaultLocale(Locale.US);
		return localeResolver;
	}
}