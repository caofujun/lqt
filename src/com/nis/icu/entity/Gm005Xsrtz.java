package com.nis.icu.entity;

import com.nis.comm.entity.BaseEntity;
import java.io.Serializable;
import java.util.Date;

public class Gm005Xsrtz extends BaseEntity implements Serializable {
	private String zyid;
	private String deptid;
	private Double weight;
	private String operatoinBy;
	private Date operatorAt;
	private Integer isnewborn;
	private String neonatebw;
	private Integer isadd;

	public String getZyid() {
		return this.zyid;
	}

	public void setZyid(String zyid) {
		this.zyid = zyid;
	}

	public String getDeptid() {
		return this.deptid;
	}

	public void setDeptid(String deptid) {
		this.deptid = deptid;
	}

	public Double getWeight() {
		return this.weight;
	}

	public void setWeight(Double weight) {
		this.weight = weight;
	}

	public String getOperatoinBy() {
		return this.operatoinBy;
	}

	public void setOperatoinBy(String operatoinBy) {
		this.operatoinBy = operatoinBy;
	}

	public Date getOperatorAt() {
		return this.operatorAt;
	}

	public void setOperatorAt(Date operatorAt) {
		this.operatorAt = operatorAt;
	}

	public Integer getIsnewborn() {
		return this.isnewborn;
	}

	public void setIsnewborn(Integer isnewborn) {
		this.isnewborn = isnewborn;
	}

	public String getNeonatebw() {
		return this.neonatebw;
	}

	public void setNeonatebw(String neonatebw) {
		this.neonatebw = neonatebw;
	}

	public Integer getIsadd() {
		return this.isadd;
	}

	public void setIsadd(Integer isadd) {
		this.isadd = isadd;
	}
}