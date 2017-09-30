package com.nis.questionnaire.service;

import com.nis.access.entity.AcAccount;
import com.nis.comm.entity.MyPage;
import com.nis.questionnaire.entity.QsTopic;
import com.nis.questionnaire.entity.QsTopicOption;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public interface QsTopicService {
	void a(QsTopic arg0, String[] arg1);

	void save(QsTopic arg0);

	void delete(String arg0);

	void a(QsTopic arg0, List<QsTopicOption> arg1);

	QsTopic get(String arg0);

	MyPage<QsTopic> a(QsTopic arg0);

	List<QsTopic> getAll();

	List<QsTopic> N(String arg0, String arg1);

	List<QsTopic> findByQid(String arg0);

	void z(List<QsTopic> arg0);

	Long cA(String arg0);

	List<QsTopic> findTopicInfo(QsTopic arg0);

	void updateScore(QsTopic arg0);

	void a(QsTopic arg0, List<QsTopicOption> arg1, AcAccount arg2);
}