package com.nis.questionnaire.service;

import com.nis.comm.entity.MyPage;
import com.nis.questionnaire.entity.QsQuestionnaireFeedback;
import com.nis.questionnaire.entity.QsReportTopic;
import com.nis.questionnaire.entity.QsSurveyRecord;
import com.nis.questionnaire.entity.QsSurveyResult;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.stereotype.Component;

@Component
public interface QsSurveyRecordService {
	void a(QsSurveyRecord arg0, List<QsSurveyResult> arg1);

	void save(QsSurveyRecord arg0);

	void update(QsSurveyRecord arg0);

	QsSurveyRecord getBySSLID(String arg0);

	void delete(String arg0);

	QsSurveyRecord get(String arg0);

	MyPage<QsSurveyRecord> a(QsSurveyRecord arg0);

	List<QsSurveyRecord> getAll();

	List<Map<String, Object>> b(QsSurveyRecord arg0);

	String c(QsSurveyRecord arg0);

	List<Map<String, Object>> d(QsSurveyRecord arg0);

	List<QsReportTopic> zhiBiaoCount(QsReportTopic arg0);

	MyPage<QsQuestionnaireFeedback> e(QsSurveyRecord arg0);

	List<QsQuestionnaireFeedback> f(QsSurveyRecord arg0);

	HSSFWorkbook D(List<QsQuestionnaireFeedback> arg0);

	QsSurveyRecord getByPidQid(String arg0, String arg1);

	List<QsSurveyRecord> findByVisitId(String arg0);

	List<QsSurveyRecord> findByzyId(String arg0, String arg1);

	List<QsSurveyRecord> findByzyidAndQid(String arg0, String arg1);

	int findByQid(String arg0);

	QsSurveyRecord findByzyidAndDate(String arg0, String arg1, Date arg2);
}