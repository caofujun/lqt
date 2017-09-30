package com.nis.questionnaire.entity;

import com.nis.comm.entity.BaseEntity;
import com.nis.comm.serializer.d;
import java.io.Serializable;
import java.util.Date;
import org.apache.ibatis.type.Alias;
import org.codehaus.jackson.map.annotate.JsonSerialize;

@Alias("qsSurveyRecord")
public class QsSurveyRecord extends BaseEntity implements Serializable {
	private String rid;
	private String qid;
	private String unitId;
	private String patientId;
	private String patientName;
	private String patientPhone;
	private String visitId;
	private String username;
	private Date surveyTime;
	private String surveyType;
	private String depType;
	private String depNo;
	private String isFinish;
	private Integer dtNum;
	private Float dtScore;
	private String status;
	private String ext1;
	private String ext2;
	private String zyid;
	private String fxColor;
	private String title;
	private String depNoName;
	private Integer tmCount;
	private boolean feedbackValue;
	private Float dcValue;
	private Long userId;
	private String fbStatus;
	private String zbId;
	private String zbName;
	private String[] resultTid;

	public String getUnitId() {
		return this.unitId;
	}

	public void setUnitId(String unitId) {
		this.unitId = unitId;
	}

	public String getRid() {
		return this.rid;
	}

	public void setRid(String rid) {
		this.rid = rid;
	}

	public String getQid() {
		return this.qid;
	}

	public void setQid(String qid) {
		this.qid = qid;
	}

	public String getPatientId() {
		return this.patientId;
	}

	public void setPatientId(String patientId) {
		this.patientId = patientId;
	}

	public String getPatientName() {
		return this.patientName;
	}

	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}

	public String getPatientPhone() {
		return this.patientPhone;
	}

	public void setPatientPhone(String patientPhone) {
		this.patientPhone = patientPhone;
	}

	public String getVisitId() {
		return this.visitId;
	}

	public void setVisitId(String visitId) {
		this.visitId = visitId;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@JsonSerialize(using = d.class)
	public Date getSurveyTime() {
		return this.surveyTime;
	}

	public void setSurveyTime(Date surveyTime) {
		this.surveyTime = surveyTime;
	}

	public String getSurveyType() {
		return this.surveyType;
	}

	public void setSurveyType(String surveyType) {
		this.surveyType = surveyType;
	}

	public String getDepType() {
		return this.depType;
	}

	public void setDepType(String depType) {
		this.depType = depType;
	}

	public String getDepNo() {
		return this.depNo;
	}

	public void setDepNo(String depNo) {
		this.depNo = depNo;
	}

	public String getIsFinish() {
		return this.isFinish;
	}

	public void setIsFinish(String isFinish) {
		this.isFinish = isFinish;
	}

	public Integer getDtNum() {
		return this.dtNum;
	}

	public void setDtNum(Integer dtNum) {
		this.dtNum = dtNum;
	}

	public Float getDtScore() {
		return this.dtScore;
	}

	public void setDtScore(Float dtScore) {
		this.dtScore = dtScore;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getExt1() {
		return this.ext1;
	}

	public void setExt1(String ext1) {
		this.ext1 = ext1;
	}

	public String getExt2() {
		return this.ext2;
	}

	public void setExt2(String ext2) {
		this.ext2 = ext2;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDepNoName() {
		return this.depNoName;
	}

	public void setDepNoName(String depNoName) {
		this.depNoName = depNoName;
	}

	public Integer getTmCount() {
		return this.tmCount;
	}

	public void setTmCount(Integer tmCount) {
		this.tmCount = tmCount;
	}

	public boolean getFeedbackValue() {
		return this.feedbackValue;
	}

	public void setFeedbackValue(boolean feedbackValue) {
		this.feedbackValue = feedbackValue;
	}

	public String getFbStatus() {
		return this.fbStatus;
	}

	public void setFbStatus(String fbStatus) {
		this.fbStatus = fbStatus;
	}

	public Float getDcValue() {
		return this.dcValue;
	}

	public void setDcValue(Float dcValue) {
		this.dcValue = dcValue;
	}

	public Long getUserId() {
		return this.userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getZyid() {
		return this.zyid;
	}

	public void setZyid(String zyid) {
		this.zyid = zyid;
	}

	public String getZbId() {
		return this.zbId;
	}

	public void setZbId(String zbId) {
		this.zbId = zbId;
	}

	public String getZbName() {
		return this.zbName;
	}

	public void setZbName(String zbName) {
		this.zbName = zbName;
	}

	public String getFxColor() {
		return this.fxColor;
	}

	public void setFxColor(String fxColor) {
		this.fxColor = fxColor;
	}

	public String[] getResultTid() {
		return this.resultTid;
	}

	public void setResultTid(String[] resultTid) {
		this.resultTid = resultTid;
	}
}