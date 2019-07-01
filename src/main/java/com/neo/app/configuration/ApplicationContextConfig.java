package com.neo.app.configuration;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.neo.app.dao.ws.FuncRepository;
import com.neo.app.resolver.UrlLocaleResolver;
import com.neo.app.routing.RoutingDataSource;
import com.neo.app.service.FuncServiceWS;

@Configuration
@ComponentScan("com.neo.*")

@EnableTransactionManagement
// Load to Environment.
@PropertySource({ "classpath:application.properties", "classpath:database/datasource-cfg.properties","classpath:path/mapping_url_folder.properties" })
public class ApplicationContextConfig {
	// Lay cac thuoc tinh load boi @PropertySource.
	@Autowired
	private Environment env;

	@Autowired
	private ObjectMapper mapper;

	@Bean(name = "viewResolver")
	public InternalResourceViewResolver getViewResolver() {
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setPrefix("/WEB-INF/");
		viewResolver.setSuffix(".jsp");
		return viewResolver;
	}

	@Bean(name = "messageSource")
	public MessageSource getMessageResource() {
		ReloadableResourceBundleMessageSource messageResource = new ReloadableResourceBundleMessageSource();

		// nguon vao file i18n/messages_xxx.properties
		// Vi du i18n/message_en.properties
		messageResource.setBasename("classpath:i18n/messages");
		messageResource.setDefaultEncoding("UTF-8");
		return messageResource;
	}

	// @Bean(name = "localeResolver")
	// public LocaleResolver getLocaleResolver() {
	// CookieLocaleResolver resolver = new CookieLocaleResolver();
	// resolver.setCookieDomain("myAppLocaleCookie");
	//
	// // 60 phut
	// resolver.setCookieMaxAge(60 * 60);
	// return resolver;
	// }

	// To solver URL like:
	// /SpringMVCInternationalization/en/login2
	// /SpringMVCInternationalization/vi/login2
	// /SpringMVCInternationalization/fr/login2
	@Bean(name = "localeResolver")
	public LocaleResolver getLocaleResolver() {
		LocaleResolver resolver = new UrlLocaleResolver();
		return resolver;
	}

	// Returns Routing DataSource (MyRoutingDataSource)
	@Autowired
	@Bean(name = "dataSource")
	public DataSource getDataSource(DataSource dataSource1, DataSource dataSource2) {

		System.out.println("## Create DataSource from dataSource1 & dataSource2");

		RoutingDataSource dataSource = new RoutingDataSource();

		Map<Object, Object> dsMap = new HashMap<Object, Object>();
		dsMap.put("PUBLISHER_DS", dataSource1);
		dsMap.put("ADVERTISER_DS", dataSource2);

		dataSource.setTargetDataSources(dsMap);

		return dataSource;
	}

	@Bean(name = "dataSource1")
	public DataSource getDataSource1() throws SQLException {
		BasicDataSource dataSource = new BasicDataSource();

		// See: datasouce-cfg.properties
		dataSource.setDriverClassName(env.getProperty("ds.database-driver1"));
		dataSource.setUrl(env.getProperty("ds.url1"));
		dataSource.setUsername(env.getProperty("ds.username1"));
		dataSource.setPassword(env.getProperty("ds.password1"));

		System.out.println("## getDataSource1: " + dataSource);
		return dataSource;
	}

	@Bean(name = "dataSource2")
	public DataSource getDataSource2() throws SQLException {
		BasicDataSource dataSource = new BasicDataSource();

		// See: datasouce-cfg.properties
		dataSource.setDriverClassName(env.getProperty("ds.database-driver2"));
		dataSource.setUrl(env.getProperty("ds.url2"));
		dataSource.setUsername(env.getProperty("ds.username2"));
		dataSource.setPassword(env.getProperty("ds.password2"));

		System.out.println("## getDataSource2: " + dataSource);

		return dataSource;
	}

	@Autowired
	@Bean(name = "transactionManager")
	public DataSourceTransactionManager getTransactionManager(DataSource dataSource) {
		DataSourceTransactionManager txManager = new DataSourceTransactionManager();

		txManager.setDataSource(dataSource);

		return txManager;
	}

	@Bean
	public FuncServiceWS getFuncServiceWS() {
		return new FuncServiceWS(env, mapper);
	}

	@Bean
	public FuncRepository getFuncRepository() {
		return new FuncRepository(env, mapper);
	}

	@Bean
	public ObjectMapper getObjectMapper() {
		return new ObjectMapper();
	}
}
