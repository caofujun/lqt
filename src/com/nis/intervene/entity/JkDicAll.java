package com.nis.intervene.entity;

import com.nis.comm.entity.BaseEntity;
import java.io.Serializable;
import java.util.Date;

public class JkDicAll extends BaseEntity implements Serializable {
	private String className;
	private String classCode;
	private String orderCode;
	private String orderName;
	private String orderUse;
	private String orderVisible;
	private Long flag;
	private Date updTime;
	private String wbCode;
	private String spCode;
	private String bhCode;
	private String updateUserId;
	private Date updateTime;
	private String pyCode;
	private String source;

	public String getClassName() {
		return this.className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getClassCode() {
		return this.classCode;
	}

	public void setClassCode(String classCode) {
		this.classCode = classCode;
	}

	public String getOrderCode() {
		return this.orderCode;
	}

	public void setOrderCode(String orderCode) {
		this.orderCode = orderCode;
	}

	public String getOrderName() {
		return this.orderName;
	}

	public void setOrderName(String orderName) {
		this.orderName = orderName;
	}

	public String getOrderUse() {
		return this.orderUse;
	}

	public void setOrderUse(String orderUse) {
		this.orderUse = orderUse;
	}

	public String getOrderVisible() {
		return this.orderVisible;
	}

	public void setOrderVisible(String orderVisible) {
		this.orderVisible = orderVisible;
	}

	public Long getFlag() {
		return this.flag;
	}

	public void setFlag(Long flag) {
		this.flag = flag;
	}

	public Date getUpdTime() {
		return this.updTime;
	}

	public void setUpdTime(Date updTime) {
		this.updTime = updTime;
	}

	public String getWbCode() {
		return this.wbCode;
	}

	public void setWbCode(String wbCode) {
		this.wbCode = wbCode;
	}

	public String getSpCode() {
		return this.spCode;
	}

	public void setSpCode(String spCode) {
		this.spCode = spCode;
	}

	public String getBhCode() {
		return this.bhCode;
	}

	public void setBhCode(String bhCode) {
		this.bhCode = bhCode;
	}

	public String getUpdateUserId() {
		return this.updateUserId;
	}

	public void setUpdateUserId(String updateUserId) {
		this.updateUserId = updateUserId;
	}

	public Date getUpdateTime() {
		return this.updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getSource() {
		return this.source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getPyCode() {
		return this.pyCode;
	}

	public void setPyCode(String pyCode) {
		this.pyCode = pyCode;
	}
}