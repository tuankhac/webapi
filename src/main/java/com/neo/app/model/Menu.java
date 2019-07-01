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
	private String pictureFile;

	@JsonProperty("DETAIL_FILE")
	private String detailFile;

	@JsonProperty("MENU_LEVEL")
	private Long menuLevel; 

	@JsonProperty("PARENT_ID")
	private Long parentId;

	@JsonProperty("PUBLISH")
	private Long publish;

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

	public String getPictureFile() {
		return pictureFile;
	}

	public void setPictureFile(String pictureFile) {
		this.pictureFile = pictureFile;
	}

	public String getDetailFile() {
		return detailFile;
	}

	public void setDetailFile(String detailFile) {
		this.detailFile = detailFile;
	}

	public Long getMenuLevel() {
		return menuLevel;
	}

	public void setMenuLevel(Long menuLevel) {
		this.menuLevel = menuLevel;
	}

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	public Long getPublish() {
		return publish;
	}

	public void setPublish(Long publish) {
		this.publish = publish;
	}

}
