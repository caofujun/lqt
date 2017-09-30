package com.nis.dict.entity;

import com.nis.comm.entity.BaseEntity;
import java.io.Serializable;
import org.apache.ibatis.type.Alias;

@Alias("zg033Jcxxpp")
public class Zg033Jcxxpp extends BaseEntity implements Serializable {
	private String id;
	private String sjId;
	private String itemField;
	private String itemMatch;
	private String itemMatchValue;
	private String valueField;
	private String valueMatch;
	private String valueMatchValue;

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getSjId() {
		return this.sjId;
	}

	public void setSjId(String sjId) {
		this.sjId = sjId;
	}

	public String getItemField() {
		return this.itemField;
	}

	public void setItemField(String itemField) {
		this.itemField = itemField;
	}

	public String getItemMatch() {
		return this.itemMatch;
	}

	public void setItemMatch(String itemMatch) {
		this.itemMatch = itemMatch;
	}

	public String getItemMatchValue() {
		return this.itemMatchValue;
	}

	public void setItemMatchValue(String itemMatchValue) {
		this.itemMatchValue = itemMatchValue;
	}

	public String getValueField() {
		return this.valueField;
	}

	public void setValueField(String valueField) {
		this.valueField = valueField;
	}

	public String getValueMatch() {
		return this.valueMatch;
	}

	public void setValueMatch(String valueMatch) {
		this.valueMatch = valueMatch;
	}

	public String getValueMatchValue() {
		return this.valueMatchValue;
	}

	public void setValueMatchValue(String valueMatchValue) {
		this.valueMatchValue = valueMatchValue;
	}
}