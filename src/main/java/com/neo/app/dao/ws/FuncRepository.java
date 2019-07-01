package com.neo.app.dao.ws;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.IOUtils;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.config.RequestConfig.Builder;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Repository;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.neo.app.utils.ConstantParams;

@Repository
// Load to Environment.
public class FuncRepository {

	// @PropertySource.
	private Environment env;

	private ObjectMapper mapper;// = new ObjectMapper();

	private final String USER_AGENT = "Mozilla/5.0";
	private final String INSTANCEOF_STRING = "java.lang.String";
	private final String KEY_VALUE = "=";
	private final String CONTINOUS = "&";
	private final String SPLIT = "?";

	public FuncRepository() {
		super();
	}

	@Autowired
	public FuncRepository(Environment env, ObjectMapper mapper) {
		super();
		this.env = env;
		this.mapper = mapper; 
	}

	public Environment getEnv() {
		return env;
	}

	public void setEnv(Environment env) {
		this.env = env;
	}

	public ObjectMapper getMapper() {
		return mapper;
	}

	public void setMapper(ObjectMapper mapper) {
		this.mapper = mapper;
	}

	// # get data json
	@SuppressWarnings("unchecked")
	private <T> T getDataJsonServiceConnectTimeout(final TypeReference<T> type, String method,
			Map<Object, Object> params, String token) {
		String url = env.getProperty("service_url").trim() + method + SPLIT;
		// String url = "http://localhost:8090/neo/gw/" + method + SPLIT;
		CloseableHttpClient httpClient = null;
		CloseableHttpResponse response = null;
		try {
			StringBuilder queryStringBuilder = new StringBuilder();

			for (Map.Entry<Object, Object> entry : params.entrySet()) {
				try {
					queryStringBuilder.append(entry.getKey() + KEY_VALUE
							+ URLEncoder.encode(entry.getValue().toString(), ConstantParams.ENCODE_UTF8) + CONTINOUS);
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}
			}

			url += queryStringBuilder.toString();

			HttpGet httpGet = new HttpGet(url);
			// add request header
			httpGet.addHeader("User-Agent", USER_AGENT);
			httpGet.addHeader("Authorization", token);

			// set the connection timeout value to 30 seconds (30000 milliseconds)
			Builder builder = RequestConfig.custom();
			builder.setConnectTimeout(Integer.parseInt(env.getProperty("service_timeout").trim()) * 1000);
			// builder.setConnectTimeout(30 * 1000);
			builder.setAuthenticationEnabled(true);
			RequestConfig requestConfig = builder.build();
			httpClient = HttpClientBuilder.create().setDefaultRequestConfig(requestConfig).build();

			response = httpClient.execute(httpGet);
			String json = IOUtils.toString(response.getEntity().getContent(), "UTF-8");
			System.out.println("result==>" + json);

			T t = null;
			if (!(type.getType().getTypeName().equals(INSTANCEOF_STRING))) {
				t = mapper.readValue(json, type);
			} else {
				t = (T) json;
			}
			return t;
		} catch (UnsupportedOperationException | IOException e) {
			e.printStackTrace();
		} finally {
			try {
				httpClient.close();
				response.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	// # end get data json

	// # post data json
	private Object postDataParam(String method, Map<Object, Object> params, String token) {
		String url = env.getProperty("service_url").trim() + method + SPLIT;
		// String url = "http://localhost:8090/neo/gw/" + method + SPLIT;
		CloseableHttpClient httpClient = null;
		CloseableHttpResponse response = null;
		try {
			HttpPost httpPost = new HttpPost(url);

			// add request header
			httpPost.addHeader("User-Agent", USER_AGENT);
			httpPost.addHeader("Authorization", token);
			httpPost.setHeader("Content-Type", "application/x-www-form-urlencoded");

			List<NameValuePair> listParams = new ArrayList<NameValuePair>();
			for (Map.Entry<Object, Object> entry : params.entrySet()) {
				listParams.add(new BasicNameValuePair(entry.getKey().toString(), entry.getValue().toString()));
			}
			UrlEncodedFormEntity form = new UrlEncodedFormEntity(listParams);
			form.setContentEncoding(ConstantParams.ENCODE_UTF8);
			httpPost.setEntity(form);

			// set the connection timeout value to 30 seconds (30000 milliseconds)
			Builder builder = RequestConfig.custom();
			builder.setConnectTimeout(Integer.parseInt(env.getProperty("service_timeout").trim()) * 1000);
			// builder.setConnectTimeout(30 * 1000);
			builder.setAuthenticationEnabled(true);
			RequestConfig requestConfig = builder.build();
			httpClient = HttpClientBuilder.create().setDefaultRequestConfig(requestConfig).build();

			response = httpClient.execute(httpPost);
			String json = IOUtils.toString(response.getEntity().getContent(), "UTF-8");
			System.out.println("result==>" + json);

			Object t = null;
			t = (Object) json;
			return t;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				httpClient.close();
				response.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	private Object postDataBody(String method, Map<Object, Object> params, String token) {
		String url = env.getProperty("service_url").trim() + method + SPLIT;
		// String url = "http://localhost:8090/neo/gw/" + method + SPLIT;
		CloseableHttpClient httpClient = null;
		CloseableHttpResponse response = null;
		try {
			HttpPost httpPost = new HttpPost(url);

			// add request header
			httpPost.addHeader("User-Agent", USER_AGENT);
			httpPost.addHeader("Authorization", token);

			List<NameValuePair> listParams = new ArrayList<NameValuePair>();
			for (Map.Entry<Object, Object> entry : params.entrySet()) {
				listParams.add(new BasicNameValuePair(entry.getKey().toString(), entry.getValue().toString()));
			}
			UrlEncodedFormEntity form = new UrlEncodedFormEntity(listParams);
			form.setContentEncoding(ConstantParams.ENCODE_UTF8);
			httpPost.setEntity(form);

			// set the connection timeout value to 30 seconds (30000 milliseconds)
			Builder builder = RequestConfig.custom();
			builder.setConnectTimeout(Integer.parseInt(env.getProperty("service_timeout").trim()) * 1000);
			// builder.setConnectTimeout(30 * 1000);
			builder.setAuthenticationEnabled(true);
			RequestConfig requestConfig = builder.build();
			httpClient = HttpClientBuilder.create().setDefaultRequestConfig(requestConfig).build();

			response = httpClient.execute(httpPost);
			String json = IOUtils.toString(response.getEntity().getContent(), "UTF-8");
			System.out.println("result==>" + json);

			Object t = null;
			t = (Object) json;
			return t;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				httpClient.close();
				response.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	// # end post data json

	// # login
	@SuppressWarnings("unchecked")
	public <Type> Type login(final TypeReference<Type> type,String username, String password) {
		String url = env.getProperty("service_url").trim() + "login";
		// String url = "http://localhost:8090/neo/gw/" + "/login";
		CloseableHttpClient httpClient = null;
		CloseableHttpResponse response = null;
		try {
			HttpPost httpPost = new HttpPost(url);

			// add request header
			httpPost.addHeader("User-Agent", USER_AGENT);
			httpPost.setHeader("Content-Type", "application/x-www-form-urlencoded");

			List<NameValuePair> listParams = new ArrayList<NameValuePair>();
			listParams.add(new BasicNameValuePair("username", username));
			listParams.add(new BasicNameValuePair("password", password));

			UrlEncodedFormEntity form = new UrlEncodedFormEntity(listParams);
			form.setContentEncoding(ConstantParams.ENCODE_UTF8);
			httpPost.setEntity(form);

			// set the connection timeout value to 30 seconds (30000 milliseconds)
			Builder builder = RequestConfig.custom();
			builder.setConnectTimeout(Integer.parseInt(env.getProperty("service_timeout").trim()) * 1000);
			// builder.setConnectTimeout(30 * 1000);
			builder.setAuthenticationEnabled(true);
			RequestConfig requestConfig = builder.build();
			httpClient = HttpClientBuilder.create().setDefaultRequestConfig(requestConfig).build();

			response = httpClient.execute(httpPost);
			String json = IOUtils.toString(response.getEntity().getContent(), "UTF-8");
			System.out.println("result==>" + json);

			Type t = null;
			if(!json.equals("")) {
				t = (Type) mapper.readValue(json, type);
			}
			return t;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				httpClient.close();
				response.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public <Type> Type login(String username) {
		String url = env.getProperty("service_url").trim() + "/login";
		// String url = "http://localhost:8090/neo/gw/" + "/login";
		CloseableHttpClient httpClient = null;
		CloseableHttpResponse response = null;
		try {
			HttpPost httpPost = new HttpPost(url);

			// add request header
			httpPost.addHeader("User-Agent", USER_AGENT);
			httpPost.setHeader("Content-Type", "application/x-www-form-urlencoded");

			List<NameValuePair> listParams = new ArrayList<NameValuePair>();
			listParams.add(new BasicNameValuePair("username", username));

			UrlEncodedFormEntity form = new UrlEncodedFormEntity(listParams);
			form.setContentEncoding(ConstantParams.ENCODE_UTF8);
			httpPost.setEntity(form);

			// set the connection timeout value to 30 seconds (30000 milliseconds)
			Builder builder = RequestConfig.custom();
			builder.setConnectTimeout(Integer.parseInt(env.getProperty("service_timeout").trim()) * 1000);
			// builder.setConnectTimeout(30 * 1000);
			builder.setAuthenticationEnabled(true);
			RequestConfig requestConfig = builder.build();
			httpClient = HttpClientBuilder.create().setDefaultRequestConfig(requestConfig).build();

			response = httpClient.execute(httpPost);
			String json = IOUtils.toString(response.getEntity().getContent(), "UTF-8");
			System.out.println("result==>" + json);

			Type t = null;
			t = (Type) json;
			return t;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				httpClient.close();
				response.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	// #end login
	// #qry fold
	public <T> T qryListModel(final TypeReference<T> type, Map<Object, Object> params, String token) {
		return getDataJsonServiceConnectTimeout(type, "qry", params, token);
	}

	// # end qry fold

	// #ref fold
	public <T> T refListModel(final TypeReference<T> type, Map<Object, Object> params, String token) {
		return getDataJsonServiceConnectTimeout(type, "ref", params, token);
	}
	// # end ref fold

	// # val fold
	public Object valPostParam(Map<Object, Object> params, String token) {
		return postDataParam("val1", params, token);
	}

	public Object valPostBody(Map<Object, Object> params, String token) {
		return postDataBody("val2", params, token);
	}
	// #end val fold

	// # val fold
	public Object updateParam(Map<Object, Object> params, String token) {
		return postDataParam("update", params, token);
	}

	public Object updateBody(Map<Object, Object> params, String token) {
		return postDataBody("updates", params, token);
	}
	// #end val fold

}
