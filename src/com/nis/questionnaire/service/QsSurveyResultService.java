package com.nis.questionnaire.service;

import com.nis.comm.entity.MyPage;
import com.nis.questionnaire.entity.QsReportTopic;
import com.nis.questionnaire.entity.QsSurveyResult;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public interface QsSurveyResultService {
	void a(String arg0, List<QsSurveyResult> arg1);

	void update(QsSurveyResult arg0);

	QsSurveyResult get(String arg0);

	MyPage<QsSurveyResult> a(QsSurveyResult arg0);

	List<QsSurveyResult> getAll();

	List<QsSurveyResult> findByRid(String arg0);

	List<QsReportTopic> findQsReportDefault(QsReportTopic arg0);

	List<QsReportTopic> findQsReportClassify(QsReportTopic arg0);

	QsReportTopic findQsReportClassifyCount(QsReportTopic arg0);
}