package com.nis.questions.dao;

import com.nis.questions.entity.QsRepoTopic;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface QsRepoTopicDao {
	void save(QsRepoTopic arg0);

	void delete(@Param("tid") String arg0);

	void update(QsRepoTopic arg0);

	QsRepoTopic get(@Param("tid") String arg0);

	List<QsRepoTopic> findQsRepoTopic(QsRepoTopic arg0);

	int findQsRepoTopicCount(QsRepoTopic arg0);

	List<QsRepoTopic> getAll();
}