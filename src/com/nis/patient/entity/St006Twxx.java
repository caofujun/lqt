package com.nis.patient.entity;

import com.nis.comm.entity.BaseEntity;
import com.nis.comm.utils.f;
import java.io.Serializable;
import java.util.Date;
import org.apache.ibatis.type.Alias;

@Alias("st006Twxx")
public class St006Twxx extends BaseEntity implements Serializable {
	private String id;
	private String zyid;
	private String patientId;
	private Date recordingAt;
	private Double twValues;
	private Double jz_line;
	private Double fr_line;
	private Double bz_line;
	private String tempratureType;
	private String tempratureTypeName;
	private Date updDate;
	private String twValuesKtl;
	private Long twAnalFlag;
	private Date twAnalDt;
	private Date firstDate;
	private Date endDate;
	private String showRecordingAt;

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

	public Date getRecordingAt() {
		return this.recordingAt;
	}

	public void setRecordingAt(Date recordingAt) {
		this.recordingAt = recordingAt;
		this.setShowRecordingAt(f.c(recordingAt, "MM-dd HH:mm"));
	}

	public Double getTwValues() {
		return this.twValues;
	}

	public void setTwValues(Double twValues) {
		this.twValues = twValues;
	}

	public String getTempratureType() {
		return this.tempratureType;
	}

	public void setTempratureType(String tempratureType) {
		this.tempratureType = tempratureType;
	}

	public String getTempratureTypeName() {
		return this.tempratureTypeName;
	}

	public void setTempratureTypeName(String tempratureTypeName) {
		this.tempratureTypeName = tempratureTypeName;
	}

	public Date getUpdDate() {
		return this.updDate;
	}

	public void setUpdDate(Date updDate) {
		this.updDate = updDate;
	}

	public String getTwValuesKtl() {
		return this.twValuesKtl;
	}

	public void setTwValuesKtl(String twValuesKtl) {
		this.twValuesKtl = twValuesKtl;
	}

	public Long getTwAnalFlag() {
		return this.twAnalFlag;
	}

	public void setTwAnalFlag(Long twAnalFlag) {
		this.twAnalFlag = twAnalFlag;
	}

	public Date getTwAnalDt() {
		return this.twAnalDt;
	}

	public void setTwAnalDt(Date twAnalDt) {
		this.twAnalDt = twAnalDt;
	}

	public Double getJz_line() {
		return this.jz_line;
	}

	public void setJz_line(Double jz_line) {
		this.jz_line = jz_line;
	}

	public Double getFr_line() {
		return this.fr_line;
	}

	public void setFr_line(Double fr_line) {
		this.fr_line = fr_line;
	}

	public Double getBz_line() {
		return this.bz_line;
	}

	public void setBz_line(Double bz_line) {
		this.bz_line = bz_line;
	}

	public Date getFirstDate() {
		return this.firstDate;
	}

	public void setFirstDate(Date firstDate) {
		this.firstDate = firstDate;
	}

	public Date getEndDate() {
		return this.endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getShowRecordingAt() {
		return this.showRecordingAt;
	}

	public void setShowRecordingAt(String showRecordingAt) {
		this.showRecordingAt = showRecordingAt;
	}

	public String toString() {
		return "St006Twxx [id=" + this.id + ", zyid=" + this.zyid + ", patientId=" + this.patientId + ", recordingAt="
				+ this.recordingAt + ", twValues=" + this.twValues + ", jz_line=" + this.jz_line + ", fr_line="
				+ this.fr_line + ", bz_line=" + this.bz_line + ", tempratureType=" + this.tempratureType
				+ ", tempratureTypeName=" + this.tempratureTypeName + ", updDate=" + this.updDate + ", twValuesKtl="
				+ this.twValuesKtl + ", twAnalFlag=" + this.twAnalFlag + ", twAnalDt=" + this.twAnalDt + ", firstDate="
				+ this.firstDate + ", endDate=" + this.endDate + "]";
	}
}