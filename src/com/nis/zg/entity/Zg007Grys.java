package com.nis.zg.entity;

import com.nis.comm.entity.BaseEntity;
import java.io.Serializable;
import java.util.Date;

public class Zg007Grys extends BaseEntity implements Serializable {
	private Date lastAt;
	private String elementId;
	private String elementName;
	private String dataFrom;
	private String lisItem;
	private String sampleName;
	private String itemName;
	private String valueType;
	private String compData;
	private Long ifFollow;
	private String relatSymbols;
	private String bhCode;
	private String spCode;
	private String wbCode;
	private Date enterAt;
	private Long flag;

	public Date getLastAt() {
		return this.lastAt;
	}

	public void setLastAt(Date lastAt) {
		this.lastAt = lastAt;
	}

	public String getElementId() {
		return this.elementId;
	}

	public void setElementId(String elementId) {
		this.elementId = elementId;
	}

	public String getElementName() {
		return this.elementName;
	}

	public void setElementName(String elementName) {
		this.elementName = elementName;
	}

	public String getDataFrom() {
		return this.dataFrom;
	}

	public void setDataFrom(String dataFrom) {
		this.dataFrom = dataFrom;
	}

	public String getLisItem() {
		return this.lisItem;
	}

	public void setLisItem(String lisItem) {
		this.lisItem = lisItem;
	}

	public String getSampleName() {
		return this.sampleName;
	}

	public void setSampleName(String sampleName) {
		this.sampleName = sampleName;
	}

	public String getItemName() {
		return this.itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getValueType() {
		return this.valueType;
	}

	public void setValueType(String valueType) {
		this.valueType = valueType;
	}

	public String getCompData() {
		return this.compData;
	}

	public void setCompData(String compData) {
		this.compData = compData;
	}

	public Long getIfFollow() {
		return this.ifFollow;
	}

	public void setIfFollow(Long ifFollow) {
		this.ifFollow = ifFollow;
	}

	public String getRelatSymbols() {
		return this.relatSymbols;
	}

	public void setRelatSymbols(String relatSymbols) {
		this.relatSymbols = relatSymbols;
	}

	public String getBhCode() {
		return this.bhCode;
	}

	public void setBhCode(String bhCode) {
		this.bhCode = bhCode;
	}

	public String getSpCode() {
		return this.spCode;
	}

	public void setSpCode(String spCode) {
		this.spCode = spCode;
	}

	public String getWbCode() {
		return this.wbCode;
	}

	public void setWbCode(String wbCode) {
		this.wbCode = wbCode;
	}

	public Date getEnterAt() {
		return this.enterAt;
	}

	public void setEnterAt(Date enterAt) {
		this.enterAt = enterAt;
	}

	public Long getFlag() {
		return this.flag;
	}

	public void setFlag(Long flag) {
		this.flag = flag;
	}
}