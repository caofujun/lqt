package com.nis.jk.entity;

import com.nis.comm.entity.BaseEntity;
import java.io.Serializable;
import java.util.Date;
import org.apache.ibatis.type.Alias;

@Alias("jkDicDoctor")
public class JkDicDoctor extends BaseEntity implements Serializable {
	private String id;
	private String drNo;
	private String drName;
	private Long drJob;
	private String deptId;
	private String deptName;
	private Long docLine;
	private String drType;
	private String drSpell;
	private String drStatus;
	private String drTitle;
	private Date updTime;
	private Long updFlag;

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDrNo() {
		return this.drNo;
	}

	public void setDrNo(String drNo) {
		this.drNo = drNo;
	}

	public String getDrName() {
		return this.drName;
	}

	public void setDrName(String drName) {
		this.drName = drName;
	}

	public Long getDrJob() {
		return this.drJob;
	}

	public void setDrJob(Long drJob) {
		this.drJob = drJob;
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

	public Long getDocLine() {
		return this.docLine;
	}

	public void setDocLine(Long docLine) {
		this.docLine = docLine;
	}

	public String getDrType() {
		return this.drType;
	}

	public void setDrType(String drType) {
		this.drType = drType;
	}

	public String getDrSpell() {
		return this.drSpell;
	}

	public void setDrSpell(String drSpell) {
		this.drSpell = drSpell;
	}

	public String getDrStatus() {
		return this.drStatus;
	}

	public void setDrStatus(String drStatus) {
		this.drStatus = drStatus;
	}

	public String getDrTitle() {
		return this.drTitle;
	}

	public void setDrTitle(String drTitle) {
		this.drTitle = drTitle;
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