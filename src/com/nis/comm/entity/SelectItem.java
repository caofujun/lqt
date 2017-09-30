package com.nis.comm.entity;

import com.nis.comm.entity.BaseEntity;

public class SelectItem extends BaseEntity {
	private static final long serialVersionUID = 1L;
	private String name;
	private String value;
	private String title;

	public String getValue() {
		return this.value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
}