package com.neo.api.utils;

public class ConstantParams {
	public static final String SPLIT_CHARACTER = ",";
	public static final String SPLIT_FILE_CHARACTER = "\\.";

	public static final String ENCODE_UTF8 = "UTF-8";
	public static final String ERROR_VIEW_NAME = "error";
	public static final int DEFAULT_COOKIE_EXPIRE_DATE = 1 * 24 * 60 * 60;
	public static final int INITIAL_PAGE = 0;
	public static int PAGE_SIZE = 10;
	public static int BUTTONS_TO_SHOW = 5;
	public static int[] PAGE_SIZES = { 5, 10, 20 };

	public static String SECRET_KEY_PATH = "ZDf+fWqwFNUPelJpn87uZQ==";

	// public static String sqlFile =
	// ConstantParams.class.getClassLoader().getResource("static/sql.properties").toString();
	public static String sqlFile = "static/sql.properties";
	//public static String LOG_CONFIG_FILE = ConstantParams.class.getClassLoader().getResource("log4j.properties").getPath();
	public static String LOG_CONFIG_FILE = "static/log4j.properties";
	//ConstantParams.class.getClassLoader().getResource("log4j.properties").getPath();
	// public static String sqlFile = "sql.properties";
	public static final int REFRESH_DELAY = 2 * 1000;
	//	public static final int REFRESH_DELAY = 1000;
}