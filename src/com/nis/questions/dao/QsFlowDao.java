package com.nis.questions.dao;

import com.nis.questionnaire.entity.QsFlow;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface QsFlowDao {
	void save(QsFlow arg0);

	void delete(@Param("fid") String arg0);

	void update(QsFlow arg0);

	QsFlow get(@Param("fid") String arg0);

	List<QsFlow> findQsFlow(QsFlow arg0);

	int findQsFlowCount(QsFlow arg0);

	List<QsFlow> getAll();

	List<QsFlow> findQsFlowAll(QsFlow arg0);
}