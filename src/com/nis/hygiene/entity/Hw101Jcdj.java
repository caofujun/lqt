package com.nis.hygiene.entity;

import com.nis.comm.entity.BaseEntity;
import java.io.Serializable;
import java.util.Date;
import org.apache.ibatis.type.Alias;

@Alias("hw101Jcdj")
public class Hw101Jcdj extends BaseEntity implements Serializable {
	private String djId;
	private String deptId;
	private String deptName;
	private String takeBy;
	private Date takeAt;
	private String createBy;
	private Date createAt;
	private String reportBy;
	private Date reportAt;
	private String memo;
	private Integer type;
	private Integer isprint;

	public Integer getIsprint() {
		return this.isprint;
	}

	public void setIsprint(Integer isprint) {
		this.isprint = isprint;
	}

	public String getDjId() {
		return this.djId;
	}

	public void setDjId(String djId) {
		this.djId = djId;
	}

	public String getDeptId() {
		return this.deptId;
	}

	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}

	public String getDeptName() {
		return this.deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public String getTakeBy() {
		return this.takeBy;
	}

	public void setTakeBy(String takeBy) {
		this.takeBy = takeBy;
	}

	public Date getTakeAt() {
		return this.takeAt;
	}

	public void setTakeAt(Date takeAt) {
		this.takeAt = takeAt;
	}

	public String getCreateBy() {
		return this.createBy;
	}

	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}

	public Date getCreateAt() {
		return this.createAt;
	}

	public void setCreateAt(Date createAt) {
		this.createAt = createAt;
	}

	public String getReportBy() {
		return this.reportBy;
	}

	public void setReportBy(String reportBy) {
		this.reportBy = reportBy;
	}

	public Date getReportAt() {
		return this.reportAt;
	}

	public void setReportAt(Date reportAt) {
		this.reportAt = reportAt;
	}

	public String getMemo() {
		return this.memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public Integer getType() {
		return this.type;
	}

	public void setType(Integer type) {
		this.type = type;
	}
}