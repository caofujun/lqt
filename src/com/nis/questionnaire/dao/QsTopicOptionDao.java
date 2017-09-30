package com.nis.questionnaire.dao;

import com.nis.questionnaire.entity.QsTopicOption;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface QsTopicOptionDao {
	void save(QsTopicOption arg0);

	void delete(@Param("optId") String arg0);

	void deleteCache();

	void update(QsTopicOption arg0);

	QsTopicOption get(@Param("optId") String arg0);

	List<QsTopicOption> findQsTopicOption(QsTopicOption arg0);

	int findQsTopicOptionCount(QsTopicOption arg0);

	List<QsTopicOption> getAll();

	List<QsTopicOption> findByTid(@Param("tid") String arg0);

	void deleteTid(@Param("tid") String arg0);
}