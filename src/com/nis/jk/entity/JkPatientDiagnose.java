package com.nis.jk.entity;

import com.nis.comm.entity.BaseEntity;
import java.io.Serializable;
import java.util.Date;
import org.apache.ibatis.type.Alias;

@Alias("jkPatientDiagnose")
public class JkPatientDiagnose extends BaseEntity implements Serializable {
	private String id;
	private String mzid;
	private String zyid;
	private String patientId;
	private Integer visitId;
	private Integer diagnosisTypeMain;
	private String diagnosisType;
	private String diagnosisTypeName;
	private String diagnosisNo;
	private String diagnosisName;
	private Date diagnosisDate;
	private String docId;
	private String docName;
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

	public Integer getVisitId() {
		return this.visitId;
	}

	public void setVisitId(Integer visitId) {
		this.visitId = visitId;
	}

	public Integer getDiagnosisTypeMain() {
		return this.diagnosisTypeMain;
	}

	public void setDiagnosisTypeMain(Integer diagnosisTypeMain) {
		this.diagnosisTypeMain = diagnosisTypeMain;
	}

	public String getDiagnosisType() {
		return this.diagnosisType;
	}

	public void setDiagnosisType(String diagnosisType) {
		this.diagnosisType = diagnosisType;
	}

	public String getDiagnosisTypeName() {
		return this.diagnosisTypeName;
	}

	public void setDiagnosisTypeName(String diagnosisTypeName) {
		this.diagnosisTypeName = diagnosisTypeName;
	}

	public String getDiagnosisNo() {
		return this.diagnosisNo;
	}

	public void setDiagnosisNo(String diagnosisNo) {
		this.diagnosisNo = diagnosisNo;
	}

	public String getDiagnosisName() {
		return this.diagnosisName;
	}

	public void setDiagnosisName(String diagnosisName) {
		this.diagnosisName = diagnosisName;
	}

	public Date getDiagnosisDate() {
		return this.diagnosisDate;
	}

	public void setDiagnosisDate(Date diagnosisDate) {
		this.diagnosisDate = diagnosisDate;
	}

	public String getDocId() {
		return this.docId;
	}

	public void setDocId(String docId) {
		this.docId = docId;
	}

	public String getDocName() {
		return this.docName;
	}

	public void setDocName(String docName) {
		this.docName = docName;
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