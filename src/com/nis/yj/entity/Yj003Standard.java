package com.nis.yj.entity;

import com.nis.comm.entity.BaseEntity;
import java.io.Serializable;

public class Yj003Standard extends BaseEntity implements Serializable {
	private String id;
	private String typeName;
	private String name;

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTypeName() {
		return this.typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}
}