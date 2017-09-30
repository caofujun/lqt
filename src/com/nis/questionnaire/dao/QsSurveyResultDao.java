package com.nis.questionnaire.dao;

import com.nis.questionnaire.entity.QsReportTopic;
import com.nis.questionnaire.entity.QsSurveyResult;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface QsSurveyResultDao {
	void save(QsSurveyResult arg0);

	void update(QsSurveyResult arg0);

	QsSurveyResult get(@Param("id") String arg0);

	List<QsSurveyResult> findQsSurveyResult(QsSurveyResult arg0);

	int findQsSurveyResultCount(QsSurveyResult arg0);

	List<QsSurveyResult> getAll();

	void deleteRid(@Param("rid") String arg0);

	List<QsSurveyResult> findByRid(@Param("rid") String arg0);

	List<QsReportTopic> findQsReportDefault(QsReportTopic arg0);

	List<QsReportTopic> findQsReportClassify(QsReportTopic arg0);

	QsReportTopic findQsReportClassifyCount(QsReportTopic arg0);

	QsSurveyResult getbyRidAndTid(@Param("rid") String arg0, @Param("tid") String arg1);
}