package com.nis.organization.entity;

import com.nis.comm.entity.BaseEntity;
import java.io.Serializable;
import java.util.Date;
import org.apache.ibatis.type.Alias;

@Alias("deptType")
public class DeptType extends BaseEntity implements Serializable {
	private String officekindid;
	private String officekind;
	private String remark;
	private String bhCode;
	private String spCode;
	private String wbCode;
	private Long flag;
	private Date lastAt;

	public String getOfficekindid() {
		return this.officekindid;
	}

	public void setOfficekindid(String officekindid) {
		this.officekindid = officekindid;
	}

	public String getOfficekind() {
		return this.officekind;
	}

	public void setOfficekind(String officekind) {
		this.officekind = officekind;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
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

	public Long getFlag() {
		return this.flag;
	}

	public void setFlag(Long flag) {
		this.flag = flag;
	}

	public Date getLastAt() {
		return this.lastAt;
	}

	public void setLastAt(Date lastAt) {
		this.lastAt = lastAt;
	}
}