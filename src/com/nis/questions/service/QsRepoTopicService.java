package com.nis.questions.service;

import com.nis.comm.entity.MyPage;
import com.nis.questions.entity.QsRepoOptions;
import com.nis.questions.entity.QsRepoTopic;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public interface QsRepoTopicService {
	void a(QsRepoTopic arg0, List<QsRepoOptions> arg1);

	void delete(String arg0);

	QsRepoTopic get(String arg0);

	MyPage<QsRepoTopic> a(QsRepoTopic arg0);

	List<QsRepoTopic> getAll();

	MyPage<QsRepoTopic> b(QsRepoTopic arg0);
}