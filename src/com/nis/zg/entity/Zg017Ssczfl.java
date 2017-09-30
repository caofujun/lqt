package com.nis.zg.entity;

import com.nis.comm.entity.BaseEntity;
import java.io.Serializable;
import java.util.Date;

public class Zg017Ssczfl extends BaseEntity implements Serializable {
	private String opepartkindid;
	private String partkindname;
	private String partkindenname;
	private Double reportorder;
	private String bhCode;
	private String spCode;
	private String wbCode;
	private String remark;
	private Double flag;
	private Date lastAt;

	public String getOpepartkindid() {
		return this.opepartkindid;
	}

	public void setOpepartkindid(String opepartkindid) {
		this.opepartkindid = opepartkindid;
	}

	public String getPartkindname() {
		return this.partkindname;
	}

	public void setPartkindname(String partkindname) {
		this.partkindname = partkindname;
	}

	public String getPartkindenname() {
		return this.partkindenname;
	}

	public void setPartkindenname(String partkindenname) {
		this.partkindenname = partkindenname;
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

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Double getFlag() {
		return this.flag;
	}

	public void setFlag(Double flag) {
		this.flag = flag;
	}

	public Date getLastAt() {
		return this.lastAt;
	}

	public void setLastAt(Date lastAt) {
		this.lastAt = lastAt;
	}
}