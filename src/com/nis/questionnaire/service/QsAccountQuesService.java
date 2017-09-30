package com.nis.questionnaire.service;

import com.nis.questionnaire.entity.QsAccountQues;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public interface QsAccountQuesService {
	void save(QsAccountQues arg0);

	void delete(String arg0);

	void update(QsAccountQues arg0);

	QsAccountQues get(String arg0);

	List<QsAccountQues> getAll();

	QsAccountQues getByQidAndUserId(String arg0, String arg1);
}