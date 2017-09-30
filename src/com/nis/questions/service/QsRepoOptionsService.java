package com.nis.questions.service;

import com.nis.comm.entity.MyPage;
import com.nis.questions.entity.QsRepoOptions;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public interface QsRepoOptionsService {
	void a(String arg0, List<QsRepoOptions> arg1);

	void update(QsRepoOptions arg0);

	QsRepoOptions get(String arg0);

	MyPage<QsRepoOptions> a(QsRepoOptions arg0);

	List<QsRepoOptions> getAll();

	List<QsRepoOptions> findByRtid(String arg0);
}