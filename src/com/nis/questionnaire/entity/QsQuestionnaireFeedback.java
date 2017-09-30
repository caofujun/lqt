package com.nis.questionnaire.entity;

import com.nis.comm.entity.BaseEntity;
import com.nis.comm.serializer.d;
import java.io.Serializable;
import java.util.Date;
import org.apache.ibatis.type.Alias;
import org.codehaus.jackson.map.annotate.JsonSerialize;

@Alias("qsQuestionnaireFeedback")
public class QsQuestionnaireFeedback extends BaseEntity implements Serializable {
	private String id;
	private String rid;
	private String qid;
	private String qtitle;
	private String tid;
	private String ttitle;
	private Date surveyTime;
	private String patientId;
	private String patientName;
	private String optName;
	private String inputValue;
	private String surveyType;
	private String patientPhone;
	private String unitId;
	private String catId;
	private String catName;
	private String depNo;
	private String depType;
	private String userName;
	private Float feedbackValue;
	private String optValue;
	private String fbStatus;
	private String ttype;

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

	public void setId(String id) {
		this.id = id;
	}

	public void setRid(String rid) {
		this.rid = rid;
	}

	public void setTid(String tid) {
		this.tid = tid;
	}

	public String getQtitle() {
		return this.qtitle;
	}

	public void setQtitle(String qtitle) {
		this.qtitle = qtitle;
	}

	public String getId() {
		return this.id;
	}

	public String getRid() {
		return this.rid;
	}

	public String getTid() {
		return this.tid;
	}

	public String getTtitle() {
		return this.ttitle;
	}

	public void setTtitle(String ttitle) {
		this.ttitle = ttitle;
	}

	public String getPatientName() {
		return this.patientName;
	}

	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}

	public String getInputValue() {
		return this.inputValue;
	}

	public void setInputValue(String inputValue) {
		this.inputValue = inputValue;
	}

	public String getSurveyType() {
		return this.surveyType;
	}

	public void setSurveyType(String surveyType) {
		this.surveyType = surveyType;
	}

	public String getPatientPhone() {
		return this.patientPhone;
	}

	public void setPatientPhone(String patientPhone) {
		this.patientPhone = patientPhone;
	}

	@JsonSerialize(using = d.class)
	public Date getSurveyTime() {
		return this.surveyTime;
	}

	public void setSurveyTime(Date surveyTime) {
		this.surveyTime = surveyTime;
	}

	public String getUnitId() {
		return this.unitId;
	}

	public void setUnitId(String unitId) {
		this.unitId = unitId;
	}

	public String getCatId() {
		return this.catId;
	}

	public void setCatId(String catId) {
		this.catId = catId;
	}

	public String getDepNo() {
		return this.depNo;
	}

	public void setDepNo(String depNo) {
		this.depNo = depNo;
	}

	public String getDepType() {
		return this.depType;
	}

	public void setDepType(String depType) {
		this.depType = depType;
	}

	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getCatName() {
		return this.catName;
	}

	public void setCatName(String catName) {
		this.catName = catName;
	}

	public String getOptName() {
		return this.optName;
	}

	public void setOptName(String optName) {
		this.optName = optName;
	}

	public Float getFeedbackValue() {
		return this.feedbackValue;
	}

	public void setFeedbackValue(Float feedbackValue) {
		this.feedbackValue = feedbackValue;
	}

	public String getOptValue() {
		return this.optValue;
	}

	public void setOptValue(String optValue) {
		this.optValue = optValue;
	}

	public String getFbStatus() {
		return this.fbStatus;
	}

	public void setFbStatus(String fbStatus) {
		this.fbStatus = fbStatus;
	}

	public String getTtype() {
		return this.ttype;
	}

	public void setTtype(String ttype) {
		this.ttype = ttype;
	}
}