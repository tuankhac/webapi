package com.neo.app.utils;

public interface ConstantParams {
	public static final String SPLIT_CHARACTER = ",";
	public static final String SPLIT_FILE_CHARACTER = "\\.";

	public static final String ENCODE_UTF8 = "UTF-8";
	public static final String ERROR_VIEW_NAME = "error";
	public static final int DEFAULT_COOKIE_EXPIRE_DATE = 1 * 24 * 60 * 60;
	public static final int INITIAL_PAGE = 0;
	public static final int PAGE_SIZE = 10;
	public static final int BUTTONS_TO_SHOW = 5;
	public static final int[] PAGE_SIZES = { 5, 10, 20, 30, 50 };

	public static final String ADMIN_PATH = "neo/";
	public static final String USER_PATH = "admin/";
	public static final String SLASH = "/";

	public static final String SECRET_KEY_PATH = "ZDf+fWqwFNUPelJpn87uZQ==";

	public static final String sqlFile = ConstantParams.class.getClassLoader().getResource("static/sql.properties")
			.toString();
	// public static String sqlFile = "sql.properties";
	// public static final int REFRESH_DELAY = 2 * 60 * 1000;
	public static final int REFRESH_DELAY = 1000;

	public enum PATH {
		PATH_ONE("ONE"), PATH_TWO("TWO");

		private final String text;

		PATH(final String text) {
			this.text = text;
		}

		@Override
		public String toString() {
			return text;
		}
	}
}
