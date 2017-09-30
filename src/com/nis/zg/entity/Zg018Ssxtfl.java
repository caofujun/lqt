package com.nis.zg.entity;

import com.nis.comm.entity.BaseEntity;
import java.io.Serializable;

public class Zg018Ssxtfl extends BaseEntity implements Serializable {
	private String opesysid;
	private String opesystem;
	private String icdbound;
	private Double reportorder;
	private String bhCode;
	private String spCode;
	private String wbCode;

	public String getOpesysid() {
		return this.opesysid;
	}

	public void setOpesysid(String opesysid) {
		this.opesysid = opesysid;
	}

	public String getOpesystem() {
		return this.opesystem;
	}

	public void setOpesystem(String opesystem) {
		this.opesystem = opesystem;
	}

	public String getIcdbound() {
		return this.icdbound;
	}

	public void setIcdbound(String icdbound) {
		this.icdbound = icdbound;
	}

	public Double getReportorder() {
		return this.reportorder;
	}

	public void setReportorder(Double reportorder) {
		this.reportorder = reportorder;
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
}