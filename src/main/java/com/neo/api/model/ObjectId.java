package com.neo.api.model;

import java.io.Serializable;
import java.sql.Timestamp;

public class ObjectId implements Serializable {

	private static final long serialVersionUID = 1L;
	private String user_id;

	private String password;

	private String first_name;

	private String last_name;

	private String mobile;

	private String deparment;

	private String email;

	private boolean gender;

	private int status;

	private Timestamp create_date;

	private Timestamp modified_date;

	private int role_level;

	private String salt_pass;

	private String token;

	public ObjectId(String user_id, String password) {
		super();
		this.password = password;
		this.user_id = user_id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Timestamp getCreate_date() {
		return create_date;
	}

	public void setCreate_date(Timestamp create_date) {
		this.create_date = create_date;
	}

	public int getRole_level() {
		return role_level;
	}

	public void setRole_level(int role_level) {
		this.role_level = role_level;
	}

	public boolean isGender() {
		return gender;
	}

	public void setGender(boolean gender) {
		this.gender = gender;
	}

	public String getDeparment() {
		return deparment;
	}

	public void setDeparment(String deparment) {
		this.deparment = deparment;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getLast_name() {
		return last_name;
	}

	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFirst_name() {
		return first_name;
	}

	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}

	public Timestamp getModified_date() {
		return modified_date;
	}

	public void setModified_date(Timestamp modified_date) {
		this.modified_date = modified_date;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getSalt_pass() {
		return salt_pass;
	}

	public void setSalt_pass(String salt_pass) {
		this.salt_pass = salt_pass;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

}
