package com.nis.icu.entity;

import com.nis.comm.entity.BaseEntity;
import java.io.Serializable;
import java.util.Date;

public class Gm003Ybsj extends BaseEntity implements Serializable {
	private String inDeptCount;
	private Date creationdate;
	private Long dtYear;
	private Long dtMonth;
	private Long dtDay;
	private String typeId;
	private String deptId;
	private Long surveyCount;
	private Long ywryksCount;
	private Long ywryfxCount;
	private Long ywryfrCount;
	private Long bhksCount;
	private Long bhfxCount;
	private String deptName;
	private String typeName;

	public String getDeptName() {
		return this.deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public String getTypeName() {
		return this.typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public Long getYwryksCount() {
		return this.ywryksCount;
	}

	public void setYwryksCount(Long ywryksCount) {
		this.ywryksCount = ywryksCount;
	}

	public Long getYwryfxCount() {
		return this.ywryfxCount;
	}

	public void setYwryfxCount(Long ywryfxCount) {
		this.ywryfxCount = ywryfxCount;
	}

	public Long getBhksCount() {
		return this.bhksCount;
	}

	public void setBhksCount(Long bhksCount) {
		this.bhksCount = bhksCount;
	}

	public Long getBhfxCount() {
		return this.bhfxCount;
	}

	public void setBhfxCount(Long bhfxCount) {
		this.bhfxCount = bhfxCount;
	}

	public String getInDeptCount() {
		return this.inDeptCount;
	}

	public void setInDeptCount(String inDeptCount) {
		this.inDeptCount = inDeptCount;
	}

	public Date getCreationdate() {
		return this.creationdate;
	}

	public void setCreationdate(Date creationdate) {
		this.creationdate = creationdate;
	}

	public Long getDtYear() {
		return this.dtYear;
	}

	public void setDtYear(Long dtYear) {
		this.dtYear = dtYear;
	}

	public Long getDtMonth() {
		return this.dtMonth;
	}

	public void setDtMonth(Long dtMonth) {
		this.dtMonth = dtMonth;
	}

	public Long getDtDay() {
		return this.dtDay;
	}

	public void setDtDay(Long dtDay) {
		this.dtDay = dtDay;
	}

	public String getTypeId() {
		return this.typeId;
	}

	public void setTypeId(String typeId) {
		this.typeId = typeId;
	}

	public String getDeptId() {
		return this.deptId;
	}

	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}

	public Long getSurveyCount() {
		return this.surveyCount;
	}

	public void setSurveyCount(Long surveyCount) {
		this.surveyCount = surveyCount;
	}

	public Long getYwryfrCount() {
		return this.ywryfrCount;
	}

	public void setYwryfrCount(Long ywryfrCount) {
		this.ywryfrCount = ywryfrCount;
	}
}