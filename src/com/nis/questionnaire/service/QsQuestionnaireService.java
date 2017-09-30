package com.nis.questionnaire.service;

import com.nis.access.entity.AcAccount;
import com.nis.comm.entity.MyPage;
import com.nis.questionnaire.entity.QsQuestionnaire;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Component;

@Component
public interface QsQuestionnaireService {
	void delete(String arg0);

	void deleteCache();

	void updateTmCount(String arg0);

	QsQuestionnaire get(String arg0);

	MyPage<QsQuestionnaire> a(QsQuestionnaire arg0);

	List<QsQuestionnaire> findQsQuestionnaireAll(QsQuestionnaire arg0);

	List<QsQuestionnaire> getAll();

	List<QsQuestionnaire> getFeedback();

	List<QsQuestionnaire> findByIsMod(QsQuestionnaire arg0);

	void b(QsQuestionnaire arg0);

	QsQuestionnaire a(String arg0, AcAccount arg1, String arg2);

	void updateStatus(String arg0, Integer arg1);

	void updatePublish(String arg0, Integer arg1);

	List<QsQuestionnaire> findCanEnter(QsQuestionnaire arg0);

	MyPage<QsQuestionnaire> c(QsQuestionnaire arg0);

	void c(String arg0, boolean arg1);

	void updateAnswerCounts(String arg0, Long arg1);

	Map<String, Object> cz(String arg0);

	List<QsQuestionnaire> findCanEnterToFollow(QsQuestionnaire arg0);

	QsQuestionnaire getbyCatId(String arg0);
}