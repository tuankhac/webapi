package com.neo.app.model;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
public class UserInfo implements Serializable {

	private static final long serialVersionUID = 1L;

	@JsonProperty("user_id")
	@Id
	@Size(min=4,message="required")
	@NotNull
	private String username;

	@JsonProperty("password")
	@Size(min=8,message="required")
	@NotNull
	private String password;

	@JsonProperty("first_name")
	private String first_name;

	@JsonProperty("last_name")
	private String last_name;

	@JsonProperty("mobile")
	private String mobile;

	@JsonProperty("deparment")
	private String deparment;

	@JsonProperty("email")
	@Pattern(regexp="^[a-z][a-z0-9_\\.]{5,52}@[a-z0-9]{2,}(\\.[a-z0-9]{2,4}){1,2}$")
	private String email;

	@JsonProperty("gender")
	private boolean gender;

	@JsonProperty("status")
	private int status;

	@JsonProperty("create_date")
	private Timestamp create_date;

	@JsonProperty("modified_date")
	private Timestamp modified_date;

	@JsonProperty("role_level")
	private int role_level;

	@JsonProperty("salt_pass")
	private String salt_pass;

	@JsonProperty("token")
	private String token;

	private String rememberme;

	private String reasonFail;

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

	public String getFirst_name() {
		return first_name;
	}

	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}

	public String getLast_name() {
		return last_name;
	}

	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getDeparment() {
		return deparment;
	}

	public void setDeparment(String deparment) {
		this.deparment = deparment;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public boolean isGender() {
		return gender;
	}

	public void setGender(boolean gender) {
		this.gender = gender;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public Timestamp getCreate_date() {
		return create_date;
	}

	public void setCreate_date(Timestamp create_date) {
		this.create_date = create_date;
	}

	public Timestamp getModified_date() {
		return modified_date;
	}

	public void setModified_date(Timestamp modified_date) {
		this.modified_date = modified_date;
	}

	public int getRole_level() {
		return role_level;
	}

	public void setRole_level(int role_level) {
		this.role_level = role_level;
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

	public String getRememberme() {
		return rememberme;
	}

	public void setRememberme(String rememberme) {
		this.rememberme = rememberme;
	}

	public String getReasonFail() {
		return reasonFail;
	}

	public void setReasonFail(String reasonFail) {
		this.reasonFail = reasonFail;
	}

}
