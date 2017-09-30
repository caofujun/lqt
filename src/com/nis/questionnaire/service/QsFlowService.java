package com.nis.questionnaire.service;

import com.nis.comm.entity.MyPage;
import com.nis.questionnaire.entity.QsFlow;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public interface QsFlowService {
	void save(QsFlow arg0);

	void delete(String arg0);

	void update(QsFlow arg0);

	QsFlow get(String arg0);

	MyPage<QsFlow> a(QsFlow arg0);

	List<QsFlow> getAll();

	List<QsFlow> b(QsFlow arg0);

	List<QsFlow> findQsFlowAll(QsFlow arg0);
}