package com.nis.jk.entity;

import com.nis.comm.entity.BaseEntity;
import java.io.Serializable;
import java.util.Date;
import org.apache.ibatis.type.Alias;

@Alias("jkPatientZkmx")
public class JkPatientZkmx extends BaseEntity implements Serializable {
	private String id;
	private String zyid;
	private Date inDeptAt;
	private String inDeptId;
	private Date outDepAt;
	private String outDeptId;
	private String bedNo;
	private Integer inDeptDays;
	private Date updTime;
	private Long updFlag;

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getZyid() {
		return this.zyid;
	}

	public void setZyid(String zyid) {
		this.zyid = zyid;
	}

	public Date getInDeptAt() {
		return this.inDeptAt;
	}

	public void setInDeptAt(Date inDeptAt) {
		this.inDeptAt = inDeptAt;
	}

	public String getInDeptId() {
		return this.inDeptId;
	}

	public void setInDeptId(String inDeptId) {
		this.inDeptId = inDeptId;
	}

	public Date getOutDepAt() {
		return this.outDepAt;
	}

	public void setOutDepAt(Date outDepAt) {
		this.outDepAt = outDepAt;
	}

	public String getOutDeptId() {
		return this.outDeptId;
	}

	public void setOutDeptId(String outDeptId) {
		this.outDeptId = outDeptId;
	}

	public String getBedNo() {
		return this.bedNo;
	}

	public void setBedNo(String bedNo) {
		this.bedNo = bedNo;
	}

	public Integer getInDeptDays() {
		return this.inDeptDays;
	}

	public void setInDeptDays(Integer inDeptDays) {
		this.inDeptDays = inDeptDays;
	}

	public Date getUpdTime() {
		return this.updTime;
	}

	public void setUpdTime(Date updTime) {
		this.updTime = updTime;
	}

	public Long getUpdFlag() {
		return this.updFlag;
	}

	public void setUpdFlag(Long updFlag) {
		this.updFlag = updFlag;
	}
}