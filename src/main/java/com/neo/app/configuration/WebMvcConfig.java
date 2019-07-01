package com.neo.app.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.neo.app.interceptor.AdminInterceptor;
import com.neo.app.interceptor.DataSourceIntercetor;
import com.neo.app.interceptor.LogInterceptor;
import com.neo.app.interceptor.UrlLocaleInterceptor;
import com.neo.app.utils.ConstantParams;

@Configuration
@EnableWebMvc
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebMvcConfig extends WebMvcConfigurerAdapter {

	// Cấu hình để sử dụng các file nguồn tĩnh (html, image, ..)
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/assets/files/assets/css/**").addResourceLocations("/assets/files/assets/css/")
				.setCachePeriod(31556926);
		registry.addResourceHandler("/assets/files/fonts.lug.ustc.edu.cn/**")
				.addResourceLocations("/assets/files/fonts.lug.ustc.edu.cn/").setCachePeriod(31556926);
		registry.addResourceHandler("/assets/files/assets/images/**")
				.addResourceLocations("/assets/files/assets/images/").setCachePeriod(31556926);
		registry.addResourceHandler("/assets/files/assets/icon/**").addResourceLocations("/assets/files/assets/icon/")
				.setCachePeriod(31556926);
		registry.addResourceHandler("/assets/files/assets/fonts/**").addResourceLocations("/assets/files/assets/fonts/")
				.setCachePeriod(31556926);
		registry.addResourceHandler("/assets/files/assets/js/**").addResourceLocations("/assets/files/assets/js/")
				.setCachePeriod(31556926);
		registry.addResourceHandler("/assets/files/assets/pages/**").addResourceLocations("/assets/files/assets/pages/")
				.setCachePeriod(31556926);

		registry.addResourceHandler("/assets/files/bower_components/**")
				.addResourceLocations("/assets/files/bower_components/").setCachePeriod(31556926);
		registry.addResourceHandler("/assets/files/gtag/**").addResourceLocations("/assets/files/gtag/")
				.setCachePeriod(31556926);
		registry.addResourceHandler("/assets/files/pages/**")
		.addResourceLocations("/assets/files/pages/").setCachePeriod(31556926);
		// resource test
//		registry.addResourceHandler("/dt_json_data/arrays.txt").addResourceLocations("/dt_json_data/arrays.txt")
//		.setCachePeriod(31556926);
//		registry.addResourceHandler("/dt_json_data/arrays.txt").addResourceLocations("/WEB-INF/dt_json_data/arrays.txt")
//		.setCachePeriod(31556926);
//		registry.addResourceHandler("/dt_json_data/arrays.txt").addResourceLocations("classpath:dt_json_data/arrays.txt")
//		.setCachePeriod(31556926);
		// có thể cấu hình tới tận addResourceLocations("/WEB-INF/resources/css/")
	}

	@Override
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
		configurer.enable();
	}

	/*
	 * Huong dan cho spring container nhan interceptor nay la bean de autowired dk 1
	 * bean khac
	 */
	@Bean
	public AdminInterceptor adminInterceptor() {
		return new AdminInterceptor();
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
//		UrlLocaleInterceptor localeInterceptor = new UrlLocaleInterceptor();
//		registry.addInterceptor(localeInterceptor).addPathPatterns("/vi/**", "/en/**", "/fr/**");

		// sau khi login xong thi se kich hoat locate
		// LocaleChangeInterceptor localeInterceptor = new LocaleChangeInterceptor();
		// localeInterceptor.setParamName("lang");
		// registry.addInterceptor(localeInterceptor).addPathPatterns("/*");

		// LogInterceptor áp dụng cho mọi URL.
		// registry.addInterceptor(new LogInterceptor());


		// Interceptor này áp dụng cho các URL có dạng /admin/*
		// Loại đi trường hợp /admin/oldLogin
		registry.addInterceptor(adminInterceptor())
				.addPathPatterns("/{locale:en|fr|vi}/" + ConstantParams.ADMIN_PATH + "**")
				.addPathPatterns(ConstantParams.SLASH + ConstantParams.ADMIN_PATH + "**")
				.excludePathPatterns("/{locale:en|fr|vi}/" + ConstantParams.ADMIN_PATH + "login.html")
				.excludePathPatterns(ConstantParams.SLASH + ConstantParams.ADMIN_PATH + "login.html");

		// database interceptor
		// registry.addInterceptor(new
		// DataSourceIntercetor()).addPathPatterns("/publisher/*", "/advertiser/*");

	}

}