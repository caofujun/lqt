package com.nis.questionnaire.dao;

import com.nis.questionnaire.entity.QsQuestionnaireFeedback;
import com.nis.questionnaire.entity.QsReportTopic;
import com.nis.questionnaire.entity.QsSurveyRecord;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;

public interface QsSurveyRecordDao {
	void save(QsSurveyRecord arg0);

	void delete(@Param("rid") String arg0);

	void update(QsSurveyRecord arg0);

	QsSurveyRecord get(@Param("rid") String arg0);

	QsSurveyRecord getBySSLID(@Param("sslid") String arg0);

	List<QsSurveyRecord> findQsSurveyRecord(QsSurveyRecord arg0);

	int findQsSurveyRecordCount(QsSurveyRecord arg0);

	List<QsSurveyRecord> getAll();

	QsSurveyRecord getByPidQid(@Param("patientId") String arg0, @Param("qid") String arg1);

	List<Map<String, Object>> sumScoreAnalyzeGroupDep(QsSurveyRecord arg0);

	List<Map<String, Object>> sumScore(QsSurveyRecord arg0);

	List<Map<String, Object>> sumScoreTrendAnalyze(QsSurveyRecord arg0);

	List<QsReportTopic> zhiBiaoCount(QsReportTopic arg0);

	List<QsQuestionnaireFeedback> findQsQuestionnaireFeedback(QsSurveyRecord arg0);

	int findQsQuestionnaireFeedbackCount(QsSurveyRecord arg0);

	List<QsQuestionnaireFeedback> findQsQuestionnaireFeedbackList(QsSurveyRecord arg0);

	List<QsSurveyRecord> findByVisitId(String arg0);

	List<QsSurveyRecord> findByzyId(@Param("zyid") String arg0, @Param("startDate") String arg1);

	List<QsSurveyRecord> findByzyidAndQid(@Param("zyid") String arg0, @Param("qid") String arg1);

	QsSurveyRecord findByzyidAndDate(@Param("zyid") String arg0, @Param("qid") String arg1,
			@Param("surveyTime") Date arg2);

	int findByQid(@Param("qid") String arg0);
}