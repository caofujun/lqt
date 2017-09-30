package com.nis.questionnaire.service;

import com.nis.comm.entity.MyPage;
import com.nis.questionnaire.entity.QsTopicOption;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public interface QsTopicOptionService {
	void a(String arg0, List<QsTopicOption> arg1);

	void save(QsTopicOption arg0);

	void delete(String arg0);

	void update(QsTopicOption arg0);

	QsTopicOption get(String arg0);

	MyPage<QsTopicOption> a(QsTopicOption arg0);

	List<QsTopicOption> getAll();

	List<QsTopicOption> findByTid(String arg0);
}