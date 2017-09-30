package com.nis.dict.entity;

import com.nis.comm.entity.BaseEntity;
import java.io.Serializable;
import java.util.Date;
import org.apache.ibatis.type.Alias;

@Alias("monitorOrder")
public class MonitorOrder extends BaseEntity implements Serializable {
	private String classCode;
	private String className;
	private String orderCode;
	private String orderName;
	private String orderUse;
	private String orderVisible;
	private String wbCode;
	private String spCode;
	private String hbCode;
	private Long flag;
	private Date updTime;

	public String getClassCode() {
		return this.classCode;
	}

	public void setClassCode(String classCode) {
		this.classCode = classCode;
	}

	public String getClassName() {
		return this.className;
	}

	public void setClassName(String className) {
		this.className = className;
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

	public String getHbCode() {
		return this.hbCode;
	}

	public void setHbCode(String hbCode) {
		this.hbCode = hbCode;
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

	public String toString() {
		return "MonitorOrder [classCode=" + this.classCode + ", className=" + this.className + ", orderCode="
				+ this.orderCode + ", orderName=" + this.orderName + ", orderUse=" + this.orderUse + ", orderVisible="
				+ this.orderVisible + ", wbCode=" + this.wbCode + ", spCode=" + this.spCode + ", hbCode=" + this.hbCode
				+ ", flag=" + this.flag + ", updTime=" + this.updTime + "]";
	}
}