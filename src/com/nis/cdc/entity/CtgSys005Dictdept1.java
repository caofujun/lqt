package com.nis.cdc.entity;

import com.nis.comm.entity.BaseEntity;
import java.io.Serializable;
import org.apache.ibatis.type.Alias;

@Alias("ctgSys005Dictdept1")
public class CtgSys005Dictdept1 extends BaseEntity implements Serializable {
	private String deptid;
	private String deptname;
	private String spm;

	public String getDeptid() {
		return this.deptid;
	}

	public void setDeptid(String deptid) {
		this.deptid = deptid;
	}

	public String getDeptname() {
		return this.deptname;
	}

	public void setDeptname(String deptname) {
		this.deptname = deptname;
	}

	public String getSpm() {
		return this.spm;
	}

	public void setSpm(String spm) {
		this.spm = spm;
	}
}