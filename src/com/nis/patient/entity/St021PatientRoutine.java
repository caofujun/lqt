package com.nis.patient.entity;

import com.nis.comm.entity.BaseEntity;
import java.io.Serializable;
import java.util.Date;
import org.apache.ibatis.type.Alias;

@Alias("st021PatientRoutine")
public class St021PatientRoutine extends BaseEntity implements Serializable {
	private String id;
	private String zyid;
	private String patientId;
	private Integer visitId;
	private Date recordingAt;
	private String routineValues;
	private Date updTime;
	private Integer updFlag;

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

	public Date getRecordingAt() {
		return this.recordingAt;
	}

	public void setRecordingAt(Date recordingAt) {
		this.recordingAt = recordingAt;
	}

	public String getRoutineValues() {
		return this.routineValues;
	}

	public void setRoutineValues(String routineValues) {
		this.routineValues = routineValues;
	}

	public Date getUpdTime() {
		return this.updTime;
	}

	public void setUpdTime(Date updTime) {
		this.updTime = updTime;
	}

	public Integer getUpdFlag() {
		return this.updFlag;
	}

	public void setUpdFlag(Integer updFlag) {
		this.updFlag = updFlag;
	}

	public String toString() {
		return "St021PatientRoutine [id=" + this.id + ", zyid=" + this.zyid + ", patientId=" + this.patientId
				+ ", visitId=" + this.visitId + ", recordingAt=" + this.recordingAt + ", routineValues="
				+ this.routineValues + ", updTime=" + this.updTime + ", updFlag=" + this.updFlag + "]";
	}
}