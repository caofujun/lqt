package com.nis.analysis.entity;

import com.nis.comm.entity.BaseEntity;
import java.io.Serializable;

public class Zg007DictEn extends BaseEntity implements Serializable {
	private String elementId;
	private String itemName;
	private String itemClass;
	private String elementType;
	private String qyElement;
	private String appElement;
	private String bhElement;
	private String keyid;
	private String dictType;
	private String flag;
	private String isMain;

	public String getElementId() {
		return this.elementId;
	}

	public void setElementId(String elementId) {
		this.elementId = elementId;
	}

	public String getItemName() {
		return this.itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getItemClass() {
		return this.itemClass;
	}

	public void setItemClass(String itemClass) {
		this.itemClass = itemClass;
	}

	public String getElementType() {
		return this.elementType;
	}

	public void setElementType(String elementType) {
		this.elementType = elementType;
	}

	public String getQyElement() {
		return this.qyElement;
	}

	public void setQyElement(String qyElement) {
		this.qyElement = qyElement;
	}

	public String getAppElement() {
		return this.appElement;
	}

	public void setAppElement(String appElement) {
		this.appElement = appElement;
	}

	public String getBhElement() {
		return this.bhElement;
	}

	public void setBhElement(String bhElement) {
		this.bhElement = bhElement;
	}

	public String getKeyid() {
		return this.keyid;
	}

	public void setKeyid(String keyid) {
		this.keyid = keyid;
	}

	public String getDictType() {
		return this.dictType;
	}

	public void setDictType(String dictType) {
		this.dictType = dictType;
	}

	public String getFlag() {
		return this.flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String getIsMain() {
		return this.isMain;
	}

	public void setIsMain(String isMain) {
		this.isMain = isMain;
	}
}