package com.nis.cdc.entity;

import com.nis.comm.entity.BaseEntity;
import java.io.Serializable;
import java.util.Date;
import org.apache.ibatis.type.Alias;

@Alias("ctgBk001Zzd")
public class CtgBk001Zzd extends BaseEntity implements Serializable {
	private String masterid;
	private String subid;
	private String referralReason;
	private Date outAt;
	private String referencesUnit;
	private String referencesPerson;
	private Date referencesDt;
	private String unitTel;

	public String getSubid() {
		return this.subid;
	}

	public void setSubid(String subid) {
		this.subid = subid;
	}

	public String getMasterid() {
		return this.masterid;
	}

	public void setMasterid(String masterid) {
		this.masterid = masterid;
	}

	public String getReferralReason() {
		return this.referralReason;
	}

	public void setReferralReason(String referralReason) {
		this.referralReason = referralReason;
	}

	public Date getOutAt() {
		return this.outAt;
	}

	public void setOutAt(Date outAt) {
		this.outAt = outAt;
	}

	public String getReferencesUnit() {
		return this.referencesUnit;
	}

	public void setReferencesUnit(String referencesUnit) {
		this.referencesUnit = referencesUnit;
	}

	public String getReferencesPerson() {
		return this.referencesPerson;
	}

	public void setReferencesPerson(String referencesPerson) {
		this.referencesPerson = referencesPerson;
	}

	public Date getReferencesDt() {
		return this.referencesDt;
	}

	public void setReferencesDt(Date referencesDt) {
		this.referencesDt = referencesDt;
	}

	public String getUnitTel() {
		return this.unitTel;
	}

	public void setUnitTel(String unitTel) {
		this.unitTel = unitTel;
	}
}