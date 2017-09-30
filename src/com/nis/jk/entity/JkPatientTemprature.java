package com.nis.jk.entity;

import com.nis.comm.entity.BaseEntity;
import java.io.Serializable;
import java.util.Date;
import org.apache.ibatis.type.Alias;

@Alias("jkPatientTemprature")
public class JkPatientTemprature extends BaseEntity implements Serializable {
	private String id;
	private String zyid;
	private String patientId;
	private Long visitId;
	private Date recordingAt;
	private Double twValues;
	private String tempratureTypeName;
	private Date updTime;
	private Long updFlag;

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

	public Long getVisitId() {
		return this.visitId;
	}

	public void setVisitId(Long visitId) {
		this.visitId = visitId;
	}

	public Date getRecordingAt() {
		return this.recordingAt;
	}

	public void setRecordingAt(Date recordingAt) {
		this.recordingAt = recordingAt;
	}

	public Double getTwValues() {
		return this.twValues;
	}

	public void setTwValues(Double twValues) {
		this.twValues = twValues;
	}

	public String getTempratureTypeName() {
		return this.tempratureTypeName;
	}

	public void setTempratureTypeName(String tempratureTypeName) {
		this.tempratureTypeName = tempratureTypeName;
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