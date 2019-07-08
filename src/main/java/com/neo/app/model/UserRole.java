package com.neo.app.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
public class UserRole implements Serializable {

	private static final long serialVersionUID = 1L;

	@JsonProperty("ID")
	@Id
	private Long id;

	@JsonProperty("ROLE_ID")
	private String role_id;

	@JsonProperty("USER_ID")
	private String user_id;

	@JsonProperty("MENU_ID")
	private Long menu_id;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getRole_id() {
		return role_id;
	}

	public void setRole_id(String role_id) {
		this.role_id = role_id;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public Long getMenu_id() {
		return menu_id;
	}

	public void setMenu_id(Long menu_id) {
		this.menu_id = menu_id;
	}

}
