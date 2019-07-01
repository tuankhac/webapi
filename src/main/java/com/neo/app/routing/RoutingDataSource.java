package com.neo.app.routing;

import javax.servlet.http.HttpServletRequest;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

// Đây là một DataSource.
public class RoutingDataSource extends AbstractRoutingDataSource {

	@Override
	protected Object determineCurrentLookupKey() {

		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
				.getRequest();

		// Xem thêm: DataSourceInterceptor
		String keyDS = (String) request.getAttribute("keyDS");

		System.out.println("KeyDS=" + keyDS);

		if (keyDS == null) {
			keyDS = "PUBLISHER_DS";
		}

		return keyDS;
	}

}