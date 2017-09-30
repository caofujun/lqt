package com.nis.questionnaire.entity;

import com.nis.comm.entity.BaseEntity;
import com.nis.questionnaire.entity.QsSurveyRecord;
import java.io.Serializable;
import java.util.List;

public class QsSurveyRecordList extends BaseEntity implements Serializable {
	private List<QsSurveyRecord> surveyRecordList;

	public List<QsSurveyRecord> getSurveyRecordList() {
		return this.surveyRecordList;
	}

	public void setSurveyRecordList(List<QsSurveyRecord> surveyRecordList) {
		this.surveyRecordList = surveyRecordList;
	}
}