package com.nis.questionnaire.dao;

import com.nis.questionnaire.entity.QsAccountQues;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface QsAccountQuesDao {
	void save(QsAccountQues arg0);

	void delete(@Param("id") String arg0);

	void update(QsAccountQues arg0);

	QsAccountQues get(@Param("id") String arg0);

	List<QsAccountQues> getAll();

	QsAccountQues getByQidAndUserId(@Param("qid") String arg0, @Param("userId") String arg1);
}