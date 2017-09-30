package com.nis.patient.entity;

import com.nis.comm.entity.BaseEntity;
import com.nis.comm.utils.f;
import java.io.Serializable;
import java.util.Date;
import org.apache.ibatis.type.Alias;

@Alias("st002Zdxxb")
public class St002Zdxxb extends BaseEntity implements Serializable {
	private String treatresult;
	private String diagnosisClass;
	private String id;
	private String zyid;
	private String patientId;
	private Integer visitId;
	private String docId;
	private String docName;
	private Date diagnosisDate;
	private String diagnosisType;
	private String diagnosisNo;
	private String diagnosisName;
	private Date updDate;
	private String showDiagnosisType;
	private String showDiagnosisDate;
	private String outcome;
	private String diagnosisTypeMain;
	private String mzid;
	private Integer cdcanalflag;
	private Date cdcanaldt;

	public String getMzid() {
		return this.mzid;
	}

	public void setMzid(String mzid) {
		this.mzid = mzid;
	}

	public Integer getCdcanalflag() {
		return this.cdcanalflag;
	}

	public void setCdcanalflag(Integer cdcanalflag) {
		this.cdcanalflag = cdcanalflag;
	}

	public Date getCdcanaldt() {
		return this.cdcanaldt;
	}

	public void setCdcanaldt(Date cdcanaldt) {
		this.cdcanaldt = cdcanaldt;
	}

	public String getShowDiagnosisType() {
		return this.showDiagnosisType;
	}

	public void setShowDiagnosisType(String showDiagnosisType) {
		this.showDiagnosisType = showDiagnosisType;
	}

	public String getShowDiagnosisDate() {
		return this.showDiagnosisDate;
	}

	public void setShowDiagnosisDate(String showDiagnosisDate) {
		this.showDiagnosisDate = showDiagnosisDate;
	}

	public String getTreatresult() {
		return this.treatresult;
	}

	public void setTreatresult(String treatresult) {
		this.treatresult = treatresult;
	}

	public String getDiagnosisClass() {
		return this.diagnosisClass;
	}

	public void setDiagnosisClass(String diagnosisClass) {
		this.diagnosisClass = diagnosisClass;
	}

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

	public Date getDiagnosisDate() {
		return this.diagnosisDate;
	}

	public void setDiagnosisDate(Date diagnosisDate) {
		this.diagnosisDate = diagnosisDate;
		this.setShowDiagnosisDate(f.formatDate(diagnosisDate));
	}

	public String getDiagnosisType() {
		return this.diagnosisType;
	}

	public void setDiagnosisType(String diagnosisType) {
		this.diagnosisType = diagnosisType;
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

	public Date getUpdDate() {
		return this.updDate;
	}

	public void setUpdDate(Date updDate) {
		this.updDate = updDate;
	}

	public String getOutcome() {
		return this.outcome;
	}

	public void setOutcome(String outcome) {
		this.outcome = outcome;
	}

	public String getDiagnosisTypeMain() {
		return this.diagnosisTypeMain;
	}

	public void setDiagnosisTypeMain(String diagnosisTypeMain) {
		this.diagnosisTypeMain = diagnosisTypeMain;
	}
}