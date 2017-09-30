package com.nis.patient.entity;

import com.nis.comm.entity.BaseEntity;
import java.io.Serializable;
import java.util.Date;
import org.apache.ibatis.type.Alias;

@Alias("st012Kjyw")
public class St012Kjyw extends BaseEntity implements Serializable {
	private String syDrugId;
	private String id;
	private String drugId;
	private String drugName;
	private String classId;
	private String className;
	private String pycode;
	private String wbcode;
	private String bhcode;
	private Date lastAt;
	private Date updDate;
	private Long drugLine;
	private String isSys;

	public String getSyDrugId() {
		return this.syDrugId;
	}

	public void setSyDrugId(String syDrugId) {
		this.syDrugId = syDrugId;
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDrugId() {
		return this.drugId;
	}

	public void setDrugId(String drugId) {
		this.drugId = drugId;
	}

	public String getDrugName() {
		return this.drugName;
	}

	public void setDrugName(String drugName) {
		this.drugName = drugName;
	}

	public String getClassId() {
		return this.classId;
	}

	public void setClassId(String classId) {
		this.classId = classId;
	}

	public String getClassName() {
		return this.className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getPycode() {
		return this.pycode;
	}

	public void setPycode(String pycode) {
		this.pycode = pycode;
	}

	public String getWbcode() {
		return this.wbcode;
	}

	public void setWbcode(String wbcode) {
		this.wbcode = wbcode;
	}

	public String getBhcode() {
		return this.bhcode;
	}

	public void setBhcode(String bhcode) {
		this.bhcode = bhcode;
	}

	public Date getLastAt() {
		return this.lastAt;
	}

	public void setLastAt(Date lastAt) {
		this.lastAt = lastAt;
	}

	public Date getUpdDate() {
		return this.updDate;
	}

	public void setUpdDate(Date updDate) {
		this.updDate = updDate;
	}

	public Long getDrugLine() {
		return this.drugLine;
	}

	public void setDrugLine(Long drugLine) {
		this.drugLine = drugLine;
	}

	public String getIsSys() {
		return this.isSys;
	}

	public void setIsSys(String isSys) {
		this.isSys = isSys;
	}
}