package com.nis.questionnaire.dao;

import com.nis.questionnaire.entity.QsTopic;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface QsTopicDao {
	void save(QsTopic arg0);

	void delete(@Param("tid") String arg0);

	void deleteCache();

	void update(QsTopic arg0);

	QsTopic get(@Param("tid") String arg0);

	List<QsTopic> findQsTopic(QsTopic arg0);

	int findQsTopicCount(QsTopic arg0);

	List<QsTopic> getAll();

	List<QsTopic> findByQid(@Param("qid") String arg0);

	void updateSortNo(@Param("tid") String arg0, @Param("sortNo") Long arg1);

	List<QsTopic> findTopicInfo(QsTopic arg0);

	void updateScore(QsTopic arg0);
}