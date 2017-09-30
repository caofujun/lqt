package com.nis.cdc.entity;

import com.nis.comm.entity.BaseEntity;
import java.io.Serializable;

public class CtgBk001Tb extends BaseEntity implements Serializable {
	private String masterid;
	private String subid;
	private String registerType;
	private String patientSource;
	private String patientSourceOther;
	private String symptom;
	private String coughTime;
	private String symptomOther;
	private String tbHistory;
	private String drSource;
	private String drDiagnose;
	private String result1;
	private String result2;
	private String result3;
	private String cureDispose;
	private String tbRegister;
	private String referralUnit;

	public String getMasterid() {
		return this.masterid;
	}

	public void setMasterid(String masterid) {
		this.masterid = masterid;
	}

	public String getSubid() {
		return this.subid;
	}

	public void setSubid(String subid) {
		this.subid = subid;
	}

	public String getRegisterType() {
		return this.registerType;
	}

	public void setRegisterType(String registerType) {
		this.registerType = registerType;
	}

	public String getPatientSource() {
		return this.patientSource;
	}

	public void setPatientSource(String patientSource) {
		this.patientSource = patientSource;
	}

	public String getPatientSourceOther() {
		return this.patientSourceOther;
	}

	public void setPatientSourceOther(String patientSourceOther) {
		this.patientSourceOther = patientSourceOther;
	}

	public String getSymptom() {
		return this.symptom;
	}

	public void setSymptom(String symptom) {
		this.symptom = symptom;
	}

	public String getCoughTime() {
		return this.coughTime;
	}

	public void setCoughTime(String coughTime) {
		this.coughTime = coughTime;
	}

	public String getSymptomOther() {
		return this.symptomOther;
	}

	public void setSymptomOther(String symptomOther) {
		this.symptomOther = symptomOther;
	}

	public String getTbHistory() {
		return this.tbHistory;
	}

	public void setTbHistory(String tbHistory) {
		this.tbHistory = tbHistory;
	}

	public String getDrSource() {
		return this.drSource;
	}

	public void setDrSource(String drSource) {
		this.drSource = drSource;
	}

	public String getDrDiagnose() {
		return this.drDiagnose;
	}

	public void setDrDiagnose(String drDiagnose) {
		this.drDiagnose = drDiagnose;
	}

	public String getResult1() {
		return this.result1;
	}

	public void setResult1(String result1) {
		this.result1 = result1;
	}

	public String getResult2() {
		return this.result2;
	}

	public void setResult2(String result2) {
		this.result2 = result2;
	}

	public String getResult3() {
		return this.result3;
	}

	public void setResult3(String result3) {
		this.result3 = result3;
	}

	public String getCureDispose() {
		return this.cureDispose;
	}

	public void setCureDispose(String cureDispose) {
		this.cureDispose = cureDispose;
	}

	public String getTbRegister() {
		return this.tbRegister;
	}

	public void setTbRegister(String tbRegister) {
		this.tbRegister = tbRegister;
	}

	public String getReferralUnit() {
		return this.referralUnit;
	}

	public void setReferralUnit(String referralUnit) {
		this.referralUnit = referralUnit;
	}
}