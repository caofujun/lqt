package com.nis.questionnaire.dao;

import com.nis.questionnaire.entity.QsQuestionnaire;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface QsQuestionnaireDao {
	void save(QsQuestionnaire arg0);

	void delete(@Param("qid") String arg0);

	void deleteCache();

	void update(QsQuestionnaire arg0);

	QsQuestionnaire get(@Param("qid") String arg0);

	List<QsQuestionnaire> findQsQuestionnaire(QsQuestionnaire arg0);

	List<QsQuestionnaire> findQsQuestionnaireAll(QsQuestionnaire arg0);

	int findQsQuestionnaireCount(QsQuestionnaire arg0);

	List<QsQuestionnaire> getAll();

	List<QsQuestionnaire> getFeedback();

	List<QsQuestionnaire> findByIsMod(QsQuestionnaire arg0);

	void updateTmCount(@Param("qid") String arg0);

	void updateStatus(@Param("qid") String arg0, @Param("status") Integer arg1, @Param("startTime") String arg2);

	void updatePublish(@Param("qid") String arg0, @Param("publish") Integer arg1);

	List<QsQuestionnaire> findCanEnter(QsQuestionnaire arg0);

	List<QsQuestionnaire> pagefindCanEnter(QsQuestionnaire arg0);

	int pagefindCanEnterCount(QsQuestionnaire arg0);

	List<QsQuestionnaire> findCanEnterToFollow(QsQuestionnaire arg0);

	void updateAnswerCount(@Param("qid") String arg0, @Param("type") String arg1);

	void updateAnswerCounts(@Param("qid") String arg0, @Param("answerCount") Long arg1);

	QsQuestionnaire getbyCatId(@Param("catId") String arg0);
}