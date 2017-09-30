package com.nis.intervene.entity;

import com.nis.comm.entity.BaseEntity;
import java.io.Serializable;
import java.util.Date;

public class FxPatient extends BaseEntity implements Serializable {
	private String fxId;
	private String patientId;
	private String zyId;
	private Integer score;
	private Date updateTime;
	private String patientName;
	private String sex;
	private String age;
	private String birthDate;
	private String startDate;
	private String endDate;
	private String deptCode;
	private String dgsType;
	private String gyStatus;
	private String pdcaStatus;
	private String gyStatusName;
	private String pdcaStatusName;
	private String[] gyStatusList;
	private String yjgrCount;
	private String qrgrCount;
	private String pgStatus;
	private String cxStatus;

	public String getFxId() {
		return this.fxId;
	}

	public void setFxId(String fxId) {
		this.fxId = fxId;
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

	public Integer getScore() {
		return this.score;
	}

	public void setScore(Integer score) {
		this.score = score;
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

	public String getAge() {
		return this.age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public String getBirthDate() {
		return this.birthDate;
	}

	public void setBirthDate(String birthDate) {
		this.birthDate = birthDate;
	}

	public String getStartDate() {
		return this.startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return this.endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getDeptCode() {
		return this.deptCode;
	}

	public void setDeptCode(String deptCode) {
		this.deptCode = deptCode;
	}

	public Date getUpdateTime() {
		return this.updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getDgsType() {
		return this.dgsType;
	}

	public void setDgsType(String dgsType) {
		this.dgsType = dgsType;
	}

	public String getGyStatus() {
		return this.gyStatus;
	}

	public void setGyStatus(String gyStatus) {
		this.gyStatus = gyStatus;
	}

	public String getPdcaStatus() {
		return this.pdcaStatus;
	}

	public void setPdcaStatus(String pdcaStatus) {
		this.pdcaStatus = pdcaStatus;
	}

	public String getGyStatusName() {
		return this.gyStatusName;
	}

	public void setGyStatusName(String gyStatusName) {
		this.gyStatusName = gyStatusName;
	}

	public String getPdcaStatusName() {
		return this.pdcaStatusName;
	}

	public void setPdcaStatusName(String pdcaStatusName) {
		this.pdcaStatusName = pdcaStatusName;
	}

	public String[] getGyStatusList() {
		return this.gyStatusList;
	}

	public void setGyStatusList(String[] gyStatusList) {
		this.gyStatusList = gyStatusList;
	}

	public String getYjgrCount() {
		return this.yjgrCount;
	}

	public void setYjgrCount(String yjgrCount) {
		this.yjgrCount = yjgrCount;
	}

	public String getQrgrCount() {
		return this.qrgrCount;
	}

	public void setQrgrCount(String qrgrCount) {
		this.qrgrCount = qrgrCount;
	}

	public String getPgStatus() {
		return this.pgStatus;
	}

	public void setPgStatus(String pgStatus) {
		this.pgStatus = pgStatus;
	}

	public String getCxStatus() {
		return this.cxStatus;
	}

	public void setCxStatus(String cxStatus) {
		this.cxStatus = cxStatus;
	}
}