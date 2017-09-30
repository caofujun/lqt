package com.nis.hygiene.entity;

import com.nis.comm.entity.BaseEntity;
import java.io.Serializable;
import java.util.Date;
import org.apache.ibatis.type.Alias;

@Alias("hw005Cyff")
public class Hw005Cyff extends BaseEntity implements Serializable {
	private String takeModeId;
	private String takeModeName;
	private String classId;
	private String bhCode;
	private String spCode;
	private String wbCode;
	private Date lastAt;
	private Integer flag;
	private Integer rownum;
	private String className;

	public String getTakeModeId() {
		return this.takeModeId;
	}

	public void setTakeModeId(String takeModeId) {
		this.takeModeId = takeModeId;
	}

	public String getTakeModeName() {
		return this.takeModeName;
	}

	public void setTakeModeName(String takeModeName) {
		this.takeModeName = takeModeName;
	}

	public String getClassId() {
		return this.classId;
	}

	public void setClassId(String classId) {
		this.classId = classId;
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

	public Date getLastAt() {
		return this.lastAt;
	}

	public void setLastAt(Date lastAt) {
		this.lastAt = lastAt;
	}

	public Integer getFlag() {
		return this.flag;
	}

	public void setFlag(Integer flag) {
		this.flag = flag;
	}

	public Integer getRownum() {
		return this.rownum;
	}

	public void setRownum(Integer rownum) {
		this.rownum = rownum;
	}

	public String getClassName() {
		return this.className;
	}

	public void setClassName(String className) {
		this.className = className;
	}
}