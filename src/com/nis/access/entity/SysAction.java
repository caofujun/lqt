package com.nis.access.entity;

import com.nis.comm.entity.BaseEntity;
import java.io.Serializable;
import java.util.Date;
import org.apache.ibatis.type.Alias;

@Alias("sysAction")
public class SysAction extends BaseEntity implements Serializable {
	private String id;
	private String sourceHospital;
	private String sourceDepno;
	private String sourceUser;
	private String funcType;
	private String funcNo;
	private String content;
	private Date addTime;
	private Date updateTime;
	private String remark;
	private String ext1;

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getSourceHospital() {
		return this.sourceHospital;
	}

	public void setSourceHospital(String sourceHospital) {
		this.sourceHospital = sourceHospital;
	}

	public String getSourceDepno() {
		return this.sourceDepno;
	}

	public void setSourceDepno(String sourceDepno) {
		this.sourceDepno = sourceDepno;
	}

	public String getSourceUser() {
		return this.sourceUser;
	}

	public void setSourceUser(String sourceUser) {
		this.sourceUser = sourceUser;
	}

	public String getFuncType() {
		return this.funcType;
	}

	public void setFuncType(String funcType) {
		this.funcType = funcType;
	}

	public String getFuncNo() {
		return this.funcNo;
	}

	public void setFuncNo(String funcNo) {
		this.funcNo = funcNo;
	}

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getAddTime() {
		return this.addTime;
	}

	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}

	public Date getUpdateTime() {
		return this.updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getExt1() {
		return this.ext1;
	}

	public void setExt1(String ext1) {
		this.ext1 = ext1;
	}
}