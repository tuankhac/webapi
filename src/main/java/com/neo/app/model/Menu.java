package com.neo.app.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
public class Menu implements Serializable {

	private static final long serialVersionUID = 1L;
	@JsonProperty("ID")
	@Id
	private Long id;

	@JsonProperty("NAME")
	private String name;

	@JsonProperty("NAME_EN")
	private String name_en;

	@JsonProperty("DISPLAY_ORDER")
	private Long display_order;

	@JsonProperty("PICTURE_FILE")
	private String picture_file;

	@JsonProperty("DETAIL_FILE")
	private String detail_file;

	@JsonProperty("MENU_LEVEL")
	private Long menu_level;

	@JsonProperty("PARENT_ID")
	private Long parent_id;

	@JsonProperty("PUBLISH")
	private Long publish;

	private String constr;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName_en() {
		return name_en;
	}

	public void setName_en(String name_en) {
		this.name_en = name_en;
	}

	public Long getDisplay_order() {
		return display_order;
	}

	public void setDisplay_order(Long display_order) {
		this.display_order = display_order;
	}

	public String getPicture_file() {
		return picture_file;
	}

	public void setPicture_file(String picture_file) {
		this.picture_file = picture_file;
	}

	public String getDetail_file() {
		return detail_file;
	}

	public void setDetail_file(String detail_file) {
		this.detail_file = detail_file;
	}

	public Long getMenu_level() {
		return menu_level;
	}

	public void setMenu_level(Long menu_level) {
		this.menu_level = menu_level;
	}

	public Long getParent_id() {
		return parent_id;
	}

	public void setParent_id(Long parent_id) {
		this.parent_id = parent_id;
	}

	public Long getPublish() {
		return publish;
	}

	public void setPublish(Long publish) {
		this.publish = publish;
	}

	public String getConstr() {
		return constr;
	}

	public void setConstr(String constr) {
		this.constr = constr;
	}

}
