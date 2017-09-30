package com.nis.intervene.entity;

import com.nis.comm.entity.BaseEntity;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class FxPatientZb extends BaseEntity implements Serializable {
	private Date updateTime;
	private String pzId;
	private String patientId;
	private String zyId;
	private String zbId;
	private Integer zbScore;
	private Date startDate;
	private Date endDate;
	private String zbName;
	private String pzStatus;
	private String caseId;
	private String charDate;
	private String zbNames;
	private String zbType;
	private String qid;
	private String pdcaStatus;
	private String qsStatus;
	private String fxColor;
	private String pdcaNames;
	private List<FxPatientZb> pzList;

	public Date getUpdateTime() {
		return this.updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getPzId() {
		return this.pzId;
	}

	public void setPzId(String pzId) {
		this.pzId = pzId;
	}

	public String getPatientId() {
		return this.patientId;
	}

	public void setPatientId(String patientId) {
		this.patientId = patientId;
	}

	public String getZyId() {
		return this.zyId;
	}

	public void setZyId(String zyId) {
		this.zyId = zyId;
	}

	public String getZbId() {
		return this.zbId;
	}

	public void setZbId(String zbId) {
		this.zbId = zbId;
	}

	public Integer getZbScore() {
		return this.zbScore;
	}

	public void setZbScore(Integer zbScore) {
		this.zbScore = zbScore;
	}

	public Date getStartDate() {
		return this.startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return this.endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getZbName() {
		return this.zbName;
	}

	public void setZbName(String zbName) {
		this.zbName = zbName;
	}

	public String getCharDate() {
		return this.charDate;
	}

	public void setCharDate(String charDate) {
		this.charDate = charDate;
	}

	public String getZbNames() {
		return this.zbNames;
	}

	public void setZbNames(String zbNames) {
		this.zbNames = zbNames;
	}

	public List<FxPatientZb> getPzList() {
		return this.pzList;
	}

	public void setPzList(List<FxPatientZb> pzList) {
		this.pzList = pzList;
	}

	public String getPzStatus() {
		return this.pzStatus;
	}

	public void setPzStatus(String pzStatus) {
		this.pzStatus = pzStatus;
	}

	public String getCaseId() {
		return this.caseId;
	}

	public void setCaseId(String caseId) {
		this.caseId = caseId;
	}

	public String getZbType() {
		return this.zbType;
	}

	public void setZbType(String zbType) {
		this.zbType = zbType;
	}

	public String getPdcaStatus() {
		return this.pdcaStatus;
	}

	public void setPdcaStatus(String pdcaStatus) {
		this.pdcaStatus = pdcaStatus;
	}

	public String getPdcaNames() {
		return this.pdcaNames;
	}

	public void setPdcaNames(String pdcaNames) {
		this.pdcaNames = pdcaNames;
	}

	public String getQid() {
		return this.qid;
	}

	public void setQid(String qid) {
		this.qid = qid;
	}

	public String getQsStatus() {
		return this.qsStatus;
	}

	public void setQsStatus(String qsStatus) {
		this.qsStatus = qsStatus;
	}

	public String getFxColor() {
		return this.fxColor;
	}

	public void setFxColor(String fxColor) {
		this.fxColor = fxColor;
	}
}