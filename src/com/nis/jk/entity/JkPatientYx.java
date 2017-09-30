package com.nis.jk.entity;

import com.nis.comm.entity.BaseEntity;
import java.io.Serializable;
import java.util.Date;
import org.apache.ibatis.type.Alias;

@Alias("jkPatientYx")
public class JkPatientYx extends BaseEntity implements Serializable {
	private String id;
	private String mzid;
	private String zyid;
	private String patientId;
	private Long visitId;
	private String patientName;
	private String sex;
	private String checkNo;
	private String examClass;
	private String checkPart;
	private String description;
	private String impression;
	private String clinDiagnose;
	private String deptId;
	private String deptName;
	private Date checkDate;
	private Date reportDate;
	private String reportDoctor;
	private String checkDoctor;
	private Date updTime;
	private Long updFlag;

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getMzid() {
		return this.mzid;
	}

	public void setMzid(String mzid) {
		this.mzid = mzid;
	}

	public String getZyid() {
		return this.zyid;
	}

	public void setZyid(String zyid) {
		this.zyid = zyid;
	}

	public String getPatientId() {
		return this.patientId;
	}

	public void setPatientId(String patientId) {
		this.patientId = patientId;
	}

	public Long getVisitId() {
		return this.visitId;
	}

	public void setVisitId(Long visitId) {
		this.visitId = visitId;
	}

	public String getPatientName() {
		return this.patientName;
	}

	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}

	public String getSex() {
		return this.sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getCheckNo() {
		return this.checkNo;
	}

	public void setCheckNo(String checkNo) {
		this.checkNo = checkNo;
	}

	public String getExamClass() {
		return this.examClass;
	}

	public void setExamClass(String examClass) {
		this.examClass = examClass;
	}

	public String getCheckPart() {
		return this.checkPart;
	}

	public void setCheckPart(String checkPart) {
		this.checkPart = checkPart;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getImpression() {
		return this.impression;
	}

	public void setImpression(String impression) {
		this.impression = impression;
	}

	public String getClinDiagnose() {
		return this.clinDiagnose;
	}

	public void setClinDiagnose(String clinDiagnose) {
		this.clinDiagnose = clinDiagnose;
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

	public Date getCheckDate() {
		return this.checkDate;
	}

	public void setCheckDate(Date checkDate) {
		this.checkDate = checkDate;
	}

	public Date getReportDate() {
		return this.reportDate;
	}

	public void setReportDate(Date reportDate) {
		this.reportDate = reportDate;
	}

	public String getReportDoctor() {
		return this.reportDoctor;
	}

	public void setReportDoctor(String reportDoctor) {
		this.reportDoctor = reportDoctor;
	}

	public String getCheckDoctor() {
		return this.checkDoctor;
	}

	public void setCheckDoctor(String checkDoctor) {
		this.checkDoctor = checkDoctor;
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