package com.nis.jk.entity;

import com.nis.comm.entity.BaseEntity;
import java.io.Serializable;
import java.util.Date;
import org.apache.ibatis.type.Alias;

@Alias("jkPatientRoutine")
public class JkPatientRoutine extends BaseEntity implements Serializable {
	private String id;
	private String zyid;
	private String patientId;
	private Long visitId;
	private Date stoolDate;
	private Date recordingAt;
	private String routineValues;
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

	public Date getStoolDate() {
		return this.stoolDate;
	}

	public void setStoolDate(Date stoolDate) {
		this.stoolDate = stoolDate;
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

	public Long getUpdFlag() {
		return this.updFlag;
	}

	public void setUpdFlag(Long updFlag) {
		this.updFlag = updFlag;
	}
}