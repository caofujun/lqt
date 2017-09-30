package com.nis.comm.entity;

import java.io.Serializable;

public class KvEntity implements Serializable {
	private static final long serialVersionUID = -4495987129630008126L;
	private String key;
	private String value;
	private boolean selected;
	private String exp;

	public KvEntity() {
	}

	public KvEntity(String key, String value) {
		this.key = key;
		this.value = value;
	}

	public KvEntity(String key, String value, String exp) {
		this.key = key;
		this.value = value;
		this.exp = exp;
	}

	public String getKey() {
		return this.key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getValue() {
		return this.value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getExp() {
		return this.exp;
	}

	public void setExp(String exp) {
		this.exp = exp;
	}

	public boolean isSelected() {
		return this.selected;
	}

	public void setSelected(boolean selected) {
		this.selected = selected;
	}
}