package com.neo.api.model;

public class ObjectId {

	private String userId;
	private String password;

	private String token;

	public ObjectId() {
	}

	public ObjectId(String userId) {
		this.userId = userId;
	}

	public ObjectId(String userId, String password) {
		this.userId = userId;
		this.password = password;
	}

	public String getUserId() {
		return this.userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getToken() {
		return this.token;
	}

	public void setToken(String token) {
		this.token = token;
	}

}
