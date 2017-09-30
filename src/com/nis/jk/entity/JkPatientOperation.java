package com.nis.jk.entity;

import com.nis.comm.entity.BaseEntity;
import java.io.Serializable;
import java.util.Date;
import org.apache.ibatis.type.Alias;

@Alias("jkPatientOperation")
public class JkPatientOperation extends BaseEntity implements Serializable {
	private String id;
	private String zyid;
	private String patientId;
	private Integer visitId;
	private String patientName;
	private String operOrderNo;
	private String operRoom;
	private String operName;
	private String operId;
	private Date operAt;
	private String opedocId;
	private String opedocName;
	private String anesDrName;
	private String operDiagnose;
	private Integer incisionGrade;
	private Integer heal;
	private Integer asa;
	private Integer urgentOpe;
	private String narcKind;
	private Date operStartTime;
	private Date operEndTime;
	private String operLengTime;
	private Integer replant;
	private Integer glassOpe;
	private Integer wsqyy;
	private String deptId;
	private String deptName;
	private String opedocName2;
	private String opedocName3;
	private Date createAt;
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

	public Integer getVisitId() {
		return this.visitId;
	}

	public void setVisitId(Integer visitId) {
		this.visitId = visitId;
	}

	public String getPatientName() {
		return this.patientName;
	}

	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}

	public String getOperOrderNo() {
		return this.operOrderNo;
	}

	public void setOperOrderNo(String operOrderNo) {
		this.operOrderNo = operOrderNo;
	}

	public String getOperRoom() {
		return this.operRoom;
	}

	public void setOperRoom(String operRoom) {
		this.operRoom = operRoom;
	}

	public String getOperName() {
		return this.operName;
	}

	public void setOperName(String operName) {
		this.operName = operName;
	}

	public String getOperId() {
		return this.operId;
	}

	public void setOperId(String operId) {
		this.operId = operId;
	}

	public Date getOperAt() {
		return this.operAt;
	}

	public void setOperAt(Date operAt) {
		this.operAt = operAt;
	}

	public String getOpedocId() {
		return this.opedocId;
	}

	public void setOpedocId(String opedocId) {
		this.opedocId = opedocId;
	}

	public String getOpedocName() {
		return this.opedocName;
	}

	public void setOpedocName(String opedocName) {
		this.opedocName = opedocName;
	}

	public String getAnesDrName() {
		return this.anesDrName;
	}

	public void setAnesDrName(String anesDrName) {
		this.anesDrName = anesDrName;
	}

	public String getOperDiagnose() {
		return this.operDiagnose;
	}

	public void setOperDiagnose(String operDiagnose) {
		this.operDiagnose = operDiagnose;
	}

	public Integer getIncisionGrade() {
		return this.incisionGrade;
	}

	public void setIncisionGrade(Integer incisionGrade) {
		this.incisionGrade = incisionGrade;
	}

	public Integer getHeal() {
		return this.heal;
	}

	public void setHeal(Integer heal) {
		this.heal = heal;
	}

	public Integer getAsa() {
		return this.asa;
	}

	public void setAsa(Integer asa) {
		this.asa = asa;
	}

	public Integer getUrgentOpe() {
		return this.urgentOpe;
	}

	public void setUrgentOpe(Integer urgentOpe) {
		this.urgentOpe = urgentOpe;
	}

	public String getNarcKind() {
		return this.narcKind;
	}

	public void setNarcKind(String narcKind) {
		this.narcKind = narcKind;
	}

	public Date getOperStartTime() {
		return this.operStartTime;
	}

	public void setOperStartTime(Date operStartTime) {
		this.operStartTime = operStartTime;
	}

	public Date getOperEndTime() {
		return this.operEndTime;
	}

	public void setOperEndTime(Date operEndTime) {
		this.operEndTime = operEndTime;
	}

	public String getOperLengTime() {
		return this.operLengTime;
	}

	public void setOperLengTime(String operLengTime) {
		this.operLengTime = operLengTime;
	}

	public Integer getReplant() {
		return this.replant;
	}

	public void setReplant(Integer replant) {
		this.replant = replant;
	}

	public Integer getGlassOpe() {
		return this.glassOpe;
	}

	public void setGlassOpe(Integer glassOpe) {
		this.glassOpe = glassOpe;
	}

	public Integer getWsqyy() {
		return this.wsqyy;
	}

	public void setWsqyy(Integer wsqyy) {
		this.wsqyy = wsqyy;
	}

	public String getDeptId() {
		return this.deptId;
	}

	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}

	public String getDeptName() {
		return this.deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public String getOpedocName2() {
		return this.opedocName2;
	}

	public void setOpedocName2(String opedocName2) {
		this.opedocName2 = opedocName2;
	}

	public String getOpedocName3() {
		return this.opedocName3;
	}

	public void setOpedocName3(String opedocName3) {
		this.opedocName3 = opedocName3;
	}

	public Date getCreateAt() {
		return this.createAt;
	}

	public void setCreateAt(Date createAt) {
		this.createAt = createAt;
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